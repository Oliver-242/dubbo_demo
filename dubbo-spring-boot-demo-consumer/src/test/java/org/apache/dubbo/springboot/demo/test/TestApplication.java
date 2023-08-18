package org.apache.dubbo.springboot.demo.test;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author oliver
 * @date 2023/08/18 15:50
 */
@SpringBootApplication
@EnableDubbo
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}