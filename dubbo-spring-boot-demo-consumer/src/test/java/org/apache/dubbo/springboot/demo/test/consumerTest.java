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
        demoService.sayHello();
        System.out.println("hell world~");
        demoService.deposit("adsad", 120);
    }
}