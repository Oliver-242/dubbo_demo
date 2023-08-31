package org.apache.dubbo.springboot.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author caijizhou
 * @date 2023/08/29 16:00
 */
@SpringBootApplication(scanBasePackages = {"org.apache.dubbo.springboot.demo.web.controller",
                        "org.apache.dubbo.springboot.demo.provider"})
@EnableDubbo
@Slf4j
public class MyControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyControllerApplication.class, args);
    }
}
