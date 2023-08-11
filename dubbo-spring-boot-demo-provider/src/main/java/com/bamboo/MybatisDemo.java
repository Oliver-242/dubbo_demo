package com.bamboo;

import com.bamboo.mapper.DepositCardsMapper;
import com.bamboo.pojo.DepositCards;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) {
        try {
            startMybatis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void startMybatis() throws Exception {
        //1
        String resource = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //2
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3
        DepositCardsMapper studentMapper = sqlSession.getMapper(DepositCardsMapper.class);
        //4
        List<DepositCards> students = studentMapper.selectAll();
        //5
        for (DepositCards student : students) {
            System.out.println(student);
        }
        //6
        sqlSession.close();
    }
}