package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import org.apache.dubbo.springboot.demo.mapper.DepositCardsDao;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public TReturn inquire(TParam tParam) throws Exception {;
        TReturn res = new TReturn();
        Long money = depositCardsDao.selectMoneyByCardId(tParam.getFirstAccount());
        if (money == null) {
            res.setStatus(1);
            res.setReturnString("查询结果为空！");
        } else {
            res.setData(money);
            res.setReturnString("账户" + tParam.getFirstAccount() + "的余额为：" + money);
        }
        return res;
    }

}
