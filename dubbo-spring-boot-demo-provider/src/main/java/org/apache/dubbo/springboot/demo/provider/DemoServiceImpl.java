package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.DemoService;

import com.bamboo.mapper.DepositCardsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import java.io.InputStream;
import java.util.List;

@DubboService
public class DemoServiceImpl implements DemoService{
    @Override
    public String deposit(String cardId, long money) throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);

        JdbcTransaction jdbcTransaction = new JdbcTransaction(sqlSession.getConnection());
        String res = null;

        try {
            DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
            Long rem = depositCardsMapper.selectMoneyByCardId(cardId);
            if (rem == null) {
                res = "查询结果为空！";
            } else {
                depositCardsMapper.depositMoneyByCardId(cardId, money);
                res = "成功存入" + money + "元！";
            }
            jdbcTransaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            jdbcTransaction.rollback();
        }

        return res;
    }

    @Override
    public String withdraw(String cardId, long money) throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);

        JdbcTransaction jdbcTransaction = new JdbcTransaction(sqlSession.getConnection());
        String res = null;

        try {
            DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
            Long rem = depositCardsMapper.selectMoneyByCardId(cardId);
            if (rem == null) {
                res = "查询结果为空！";
            } else if (rem >= money) {
                depositCardsMapper.withdrawMoneyByCardId(cardId, money);
                res = "成功取出" + money + "元！";
            } else {
                res = "余额不足！";
            }
            jdbcTransaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            jdbcTransaction.rollback();
        }

        return res;
    }

    @Override
    public String transfer(String sender, String receiver, long money) throws Exception{
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);

        JdbcTransaction jdbcTransaction = new JdbcTransaction(sqlSession.getConnection());
        String res = null;

        try {
            DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
            Long rem1 = depositCardsMapper.selectMoneyByCardId(sender);
            Long rem2 = depositCardsMapper.selectMoneyByCardId(receiver);
            if (rem1 == null || rem2 == null) {
                res = "查询结果为空！";
            } else if (rem1 >= money) {
                depositCardsMapper.transferMoney(sender, receiver, money);
                res = sender + "给" + receiver + "成功转账" + money + "元！";
            } else {
                res = "余额不足！";
            }
            jdbcTransaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            jdbcTransaction.rollback();
        }

        return res;
    }

    @Override
    public String inquire(String cardId) throws Exception {

        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);

        JdbcTransaction jdbcTransaction = new JdbcTransaction(sqlSession.getConnection());

        String res = null;
        try {
            DepositCardsMapper depositCardsMapper = sqlSession.getMapper(DepositCardsMapper.class);
            Long money = depositCardsMapper.selectMoneyByCardId(cardId);
            jdbcTransaction.commit();
            if (money == null) {
                res = "查询结果为空！";
            } else {
                res = "账户" + cardId + "的余额为：" + money;
            }
//            System.out.println(money);
        } catch (Exception ex) {
            jdbcTransaction.rollback();
            throw ex;
        }

        sqlSession.close();
        return res;
    }

}
