package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.DemoService;
import com.bamboo.MybatisDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@DubboService
public class DemoServiceImpl implements DemoService{
    @Override
    public void sayHello () {
        System.out.println("Hello world!");
    }
    @Override
    public Boolean deposit (String cardId, int money) throws Exception{
        MybatisDemo.startMybatis();
        return true;
    }

    @Override
    public Boolean withdraw(String cardId, int money) {
        System.out.println("In demoService.withdraw");
        return true;
    }

    @Override
    public Boolean transfer(String sender, String receiver, int money) {
        return true;
    }

    @Override
    public Boolean inquire(String cardId) {
        return true;
    }

}
