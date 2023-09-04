package org.apache.dubbo.springboot.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author caijizhou
 * @date 2023/08/29 16:00
 */
@SpringBootApplication(scanBasePackages = {"org.apache.dubbo.springboot.demo.web.controller"
                        , "org.apache.dubbo.springboot.demo.provider", "org.apache.dubbo.springboot.demo.provider.impl"})
@MapperScan("org.apache.dubbo.springboot.demo.mapper")
@EnableTransactionManagement
@EnableAsync
@EnableDubbo
@Slf4j
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class MyControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyControllerApplication.class, args);
    }
}
