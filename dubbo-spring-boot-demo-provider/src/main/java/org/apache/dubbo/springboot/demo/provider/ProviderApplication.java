package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author oliver
 * @date 2023/08/18 15:50
 */
@SpringBootApplication(scanBasePackages = {"com.bamboo.dao", "org.apache.dubbo.springboot.demo"})
@EnableDubbo
@EnableTransactionManagement
@MapperScan("com.bamboo.dao")
@Deprecated
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}