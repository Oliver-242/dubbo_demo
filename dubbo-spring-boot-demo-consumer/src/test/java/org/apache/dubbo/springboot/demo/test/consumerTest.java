package org.apache.dubbo.springboot.demo.test;

import java.sql.*;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.DemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class consumerTest implements CommandLineRunner {
    @DubboReference
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inquire test:");
        String res = demoService.inquire("1231231231231");
        System.out.println(res);

        System.out.println("Deposit test:");
        res = demoService.deposit("123123123123", 10);
        System.out.println(res);

        res = demoService.inquire("1231231231231");
        System.out.println(res);

        System.out.println("Withdraw test:");
        res = demoService.withdraw("1231231231231", 10);
        System.out.println(res);

        res = demoService.inquire("1231231231231");
        System.out.println(res);

        System.out.println("Transfer test:");
        res = demoService.transfer("1231231231232", "1231231231231", 10);
        System.out.println(res);

        res = demoService.inquire("1231231231231");
        System.out.println(res);

        res = demoService.transfer("1231231231232", "1231231231231", 1001);
        System.out.println(res);

        res = demoService.inquire("1231231231231");
        System.out.println(res);
        res = demoService.inquire("1231231231232");
        System.out.println(res);
    }
}