package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.*;

import com.bamboo.dao.DepositCardsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author oliver
 * @date 2023/08/17 16:00
 */
@DubboService(group = "group1", version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
@Service
public class DemoServiceImpl implements DemoService{
    @Resource
    DepositCardsDao depositCardsDao;

    @Override
    public TReturn deposit(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        int rem = depositCardsDao.depositMoneyByCardId(tParam.firstAccount, tParam.money);
        if (rem == 0) {
            res.status = 1;
            res.returnString = "查询结果为空！";
        } else {
            res.returnString = "成功向" + tParam.firstAccount + "存入" + tParam.money + "元！";
        }
        return res;
    }

    @Override
    public TReturn withdraw(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        Long rem_1 = depositCardsDao.selectMoneyByCardId(tParam.firstAccount);
        if (rem_1 == null) {
            res.status = 1;
            res.returnString = "查询结果为空！";
        } else {
            int rem_2 = depositCardsDao.withdrawMoneyByCardId(tParam.firstAccount, tParam.money);
            if (rem_2 == 0){
                res.status = 2;
                res.returnString = "余额不足！";
            } else {
                res.returnString = "成功从" + tParam.firstAccount + "提现" + tParam.money + "元";
            }
        }
        return res;
    }

    @Override
    public TReturn transfer(TParam tParam) throws Exception{
        TReturn res = new TReturn();

        int rem_1 = depositCardsDao.withdrawMoneyByCardId(tParam.firstAccount, tParam.money);
        if (rem_1 == 0) {
            res.status = 1;
            res.returnString = "转账失败！";
        } else {
            int rem_2 = depositCardsDao.depositMoneyByCardId(tParam.secondAccount, tParam.money);
            if (rem_2 == 0){
                res.status = 1;
                res.returnString = "转账失败！";
                throw new Exception("转账失败");
            } else {
                res.returnString = "由" + tParam.firstAccount + "向" + tParam.secondAccount + "转账"
                    + tParam.money + "元！";
            }
        }
        return res;
    }

    @Override
    public TReturn inquire(TParam tParam) throws Exception {;
        TReturn res = new TReturn();
        Long money = depositCardsDao.selectMoneyByCardId(tParam.firstAccount);
        if (money == null) {
            res.status = 1;
            res.returnString = "查询结果为空！";
        } else {
            res.data = money;
            res.returnString = "账户" + tParam.firstAccount + "的余额为：" + money;
        }
        return res;
    }

}
