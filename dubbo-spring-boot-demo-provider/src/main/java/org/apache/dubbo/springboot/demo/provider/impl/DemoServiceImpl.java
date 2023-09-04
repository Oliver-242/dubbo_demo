package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import org.apache.dubbo.springboot.demo.mapper.DepositCardsDao;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author oliver
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
                res.setReturnString("成功从" + tParam.getFirstAccount() + "提现" + tParam.getMoney() + "元");
            }
        }
        return res;
    }

    @Override
    public TReturn transfer(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        int rem_1 = depositCardsDao.withdrawMoneyByCardId(tParam.getFirstAccount(), tParam.getMoney());
        if (rem_1 == 0) {
            res.setStatus(1);
            res.setReturnString("转账失败！");
        } else {
            int rem_2 = depositCardsDao.depositMoneyByCardId(tParam.getSecondAccount(), tParam.getMoney());
            if (rem_2 == 0){
                res.setStatus(1);
                res.setReturnString("转账失败！");
                throw new Exception("转账失败");
            } else {
                res.setReturnString("由" + tParam.getFirstAccount() + "向" + tParam.getSecondAccount() + "转账"
                    + tParam.getMoney() + "元！");
            }
        }
        return res;
    }

    @Override
    public TReturn inquire(TParam tParam) throws Exception {
        String cacheKey = "balance_" + tParam.getFirstAccount();
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
                jedis.setex(cacheKey, 3600, String.valueOf(res.getData())); // 设置缓存时间为1小时
            }
        } catch (Exception e) {
            // 处理异常
        }

        return res;
    }

//    @Override
//    public CompletableFuture<TReturn> inquire(TParam tParam) throws Exception {;
//        TReturn res = new TReturn();
//        Long money = depositCardsDao.selectMoneyByCardId(tParam.getFirstAccount());
//        if (money == null) {
//            res.setStatus(1);
//            res.setReturnString("查询结果为空！");
//        } else {
//            res.setData(money);
//            res.setReturnString("账户" + tParam.getFirstAccount() + "的余额为：" + money);
//        }
//        return CompletableFuture.completedFuture(res);
//    }

    @Override
    @Async
    public CompletableFuture<TReturn> inquireAsync(TParam tParam) throws Exception {
        String cacheKey = "balance_" + tParam.getFirstAccount();
        TReturn res = new TReturn();

        try (Jedis jedis = jedisPool.getResource()) {
            // 先尝试从缓存中获取数据
            String cachedResult = jedis.get(cacheKey);
            if (cachedResult != null) {
                res.setReturnString(cachedResult);
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
                jedis.setex(cacheKey, 3600, res.getReturnString()); // 设置缓存时间为1小时
            }
        } catch (Exception e) {
            // 处理异常
        }

        return CompletableFuture.completedFuture(res);
    }

}
