package org.apache.dubbo.springboot.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.apache.dubbo.springboot.demo.web.controller",
                        "org.apache.dubbo.springboot.demo.provider"})
@EnableDubbo
public class MyControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyControllerApplication.class, args);
    }
}
