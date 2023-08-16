package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.*;

import com.bamboo.mapper.DepositCardsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@DubboService
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl implements DemoService{
    @Override
    public TReturn deposit(TParam tParam) throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TReturn res = new TReturn();

        DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
        int rem = depositCardsMapper.depositMoneyByCardId(tParam.firstAccount, tParam.money);
        if (rem == 0) {
            res.status = 1;
            res.returnString = "查询结果为空！";
        } else {
            res.returnString = "成功向" + tParam.firstAccount + "存入" + tParam.money + "元！";
        }
        sqlSession.close();
        return res;
    }

    @Override
    public TReturn withdraw(TParam tParam) throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TReturn res = new TReturn();

        DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
        Long rem_1 = depositCardsMapper.selectMoneyByCardId(tParam.firstAccount);
        if (rem_1 == null) {
            res.status = 1;
            res.returnString = "查询结果为空！";
        } else {
            int rem_2 = depositCardsMapper.withdrawMoneyByCardId(tParam.firstAccount, tParam.money);
            if (rem_2 == 0){
                res.status = 2;
                res.returnString = "余额不足！";
            } else {
                res.returnString = "成功从" + tParam.firstAccount + "提现" + tParam.money + "元";
            }
        }
        sqlSession.close();
        return res;
    }

    @Override
    public TReturn transfer(TParam tParam) throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TReturn res = new TReturn();

        DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
        int rem_1 = depositCardsMapper.withdrawMoneyByCardId(tParam.firstAccount, tParam.money);
        if (rem_1 == 0) {
            res.status = 1;
            res.returnString = "转账失败！";
        } else {
            int rem_2 = depositCardsMapper.depositMoneyByCardId(tParam.secondAccount, tParam.money);
            if (rem_2 == 0){
                res.status = 1;
                res.returnString = "转账失败！";
                throw new Exception();
            } else {
                res.returnString = "由" + tParam.firstAccount + "向" + tParam.secondAccount + "转账"
                    + tParam.money + "元！";
            }
        }
        sqlSession.close();
        return res;
    }

    @Override
    public TReturn inquire(TParam tParam) throws Exception {

        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);

        TReturn res = new TReturn();
        DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
        Long money = depositCardsMapper.selectMoneyByCardId(tParam.firstAccount);
        if (money == null) {
            res.status = 1;
            res.returnString = "查询结果为空！";
        } else {
            res.data = money;
            res.returnString = "账户" + tParam.firstAccount + "的余额为：" + money;
        }
        sqlSession.close();
        return res;
    }

}
