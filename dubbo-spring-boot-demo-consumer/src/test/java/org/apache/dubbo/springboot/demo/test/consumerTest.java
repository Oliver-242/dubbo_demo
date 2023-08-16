package org.apache.dubbo.springboot.demo.test;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.dubbo.springboot.demo.*;

@Component
public class consumerTest implements CommandLineRunner {
    @DubboReference
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        TReturn ret;
        TParam t1 = new TParam("1231231231231");
        ret = demoService.inquire(t1);
        System.out.println(ret.returnString);
        System.out.println();

        t1 = new TParam("1231231231231", 1000);
        ret = demoService.deposit(t1);
        System.out.println(ret.returnString);
        t1 = new TParam("1231231231231");
        ret = demoService.inquire(t1);
        System.out.println(ret.returnString);
        System.out.println();

        t1 = new TParam("1231231231232", 30);
        ret = demoService.withdraw(t1);
        System.out.println(ret.returnString);
        t1 = new TParam("1231231231232");
        ret = demoService.inquire(t1);
        System.out.println(ret.returnString);
        System.out.println();

        t1 = new TParam("1231231231231", "1231231231234", 1000);
        ret = demoService.transfer(t1);
        System.out.println(ret.returnString);
        t1 = new TParam("1231231231231");
        ret = demoService.inquire(t1);
        System.out.println(ret.returnString);
        t1 = new TParam("1231231231233");
        ret = demoService.inquire(t1);
        System.out.println(ret.returnString);
    }
}