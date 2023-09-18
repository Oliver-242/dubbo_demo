package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import org.apache.dubbo.springboot.demo.mapper.DepositCardsDao;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author caijizhou
 * @date 2023/08/17 16:00
 */
@DubboService(group = "group1", version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final DepositCardsDao depositCardsDao;

    @Autowired
    private JedisPool jedisPool; // 注入 Jedis 实例

    Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    private final String prefix = "balance_";

    @Autowired
    public DemoServiceImpl(DepositCardsDao depositCardsDao) {
        this.depositCardsDao = depositCardsDao;
    }

    @Override
    public TReturn deposit(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        int rem = depositCardsDao.depositMoneyByCardId(tParam.getFirstAccount(), tParam.getMoney());
        if (rem == 0) {
            res.setStatus(1);
            res.setReturnString("查询结果为空！");
        } else {
            syncToRedis(tParam.getFirstAccount());
            res.setData(tParam.getMoney());
            res.setStatus(0);
            res.setReturnString("成功向" + tParam.getFirstAccount() + "存入" + tParam.getMoney() + "元！");
        }
        return res;
    }

    @Override
    public TReturn withdraw(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        Long rem_1 = depositCardsDao.selectMoneyByCardId(tParam.getFirstAccount());
        if (rem_1 == null) {
            res.setStatus(1);
            res.setReturnString("查询结果为空！");
        } else {
            int rem_2 = depositCardsDao.withdrawMoneyByCardId(tParam.getFirstAccount(), tParam.getMoney());
            if (rem_2 == 0){
                res.setStatus(2);
                res.setReturnString("余额不足！");
            } else {
                syncToRedis(tParam.getFirstAccount());
                res.setData(tParam.getMoney());
                res.setStatus(0);
                res.setReturnString("成功从" + tParam.getFirstAccount() + "提现" + tParam.getMoney() + "元");
            }
        }
        return res;
    }

    @Override
    public TReturn transfer(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        int var1 = depositCardsDao.withdrawMoneyByCardId(tParam.getFirstAccount(), tParam.getMoney());
        if (var1 == 0) {
            res.setStatus(1);
            res.setReturnString("转账失败！");
        } else {
            int var2 = depositCardsDao.depositMoneyByCardId(tParam.getSecondAccount(), tParam.getMoney());
            if (var2 == 0){
                res.setStatus(1);
                res.setReturnString("转账失败！");
                log.error("转账失败！");
                throw new Exception("转账失败");
            } else {
                syncToRedis(tParam.getFirstAccount());
                syncToRedis(tParam.getSecondAccount());
                res.setStatus(0);
                res.setData(tParam.getMoney());
                res.setReturnString("由" + tParam.getFirstAccount() + "向" + tParam.getSecondAccount() + "转账"
                    + tParam.getMoney() + "元！");
            }
        }
        return res;
    }

    @Override
    public TReturn inquire(TParam tParam) throws Exception {
        String cacheKey = this.prefix + tParam.getFirstAccount();
        TReturn res = new TReturn();

        try (Jedis jedis = jedisPool.getResource()) {
            // 先尝试从缓存中获取数据
            String cachedResult = jedis.get(cacheKey);
            if (cachedResult != null) {
                res.setData(Long.parseLong(cachedResult));
                res.setReturnString("账户" + tParam.getFirstAccount() + "的余额为：" + cachedResult);
                return res; // 从缓存返回结果
            }

            // 如果缓存中没有数据，从数据库查询
            Long money = depositCardsDao.selectMoneyByCardId(tParam.getFirstAccount());
            if (money == null) {
                res.setStatus(1);
                res.setReturnString("查询结果为空！");
            } else {
                res.setData(money);
                res.setReturnString("账户" + tParam.getFirstAccount() + "的余额为：" + money);

                // 将查询结果缓存到 Redis 中，设置过期时间
                jedis.setex(cacheKey, 300, String.valueOf(res.getData()));
            }
        } catch (Exception e) {
            logger.error("缓存出错：" + e.getMessage());
            throw new Exception("缓存出错！");
        }

        return res;
    }


    @Override
    @Async
    public CompletableFuture<TReturn> inquireAsync(TParam tParam) throws Exception {
        String cacheKey = this.prefix + tParam.getFirstAccount();
        TReturn res = new TReturn();

        try (Jedis jedis = jedisPool.getResource()) {
            // 先尝试从缓存中获取数据
            String cachedResult = jedis.get(cacheKey);
            if (cachedResult != null) {
                res.setData(Long.parseLong(cachedResult));
                res.setReturnString("账户" + tParam.getFirstAccount() + "的余额为：" + cachedResult);
                return CompletableFuture.completedFuture(res); // 从缓存返回结果
            }

            // 如果缓存中没有数据，从数据库查询
            Long money = depositCardsDao.selectMoneyByCardId(tParam.getFirstAccount());
            if (money == null) {
                res.setStatus(1);
                res.setReturnString("查询结果为空！");
            } else {
                res.setData(money);
                res.setReturnString("账户" + tParam.getFirstAccount() + "的余额为：" + money);

                // 将查询结果缓存到 Redis 中，设置过期时间
                jedis.setex(cacheKey, 300, String.valueOf(res.getData()));
            }
        } catch (Exception e) {
            logger.info("缓存出错：" + e.getMessage());
            throw new Exception("缓存出错！");
        }

        return CompletableFuture.completedFuture(res);
    }

    private void syncToRedis(String cardId) throws Exception {
        String cacheKey = this.prefix + cardId;
        long money = depositCardsDao.selectMoneyByCardId(cardId);
        try (Jedis jedis = jedisPool.getResource()) {
            String cachedResult = jedis.get(cacheKey);
            if(cachedResult != null) {
                jedis.setex(cacheKey, 300, String.valueOf(money));
            }
        } catch (Exception ex) {
            logger.info("缓存出错：" + ex.getMessage());
            throw new Exception("缓存出错！");
        }
    }

}
