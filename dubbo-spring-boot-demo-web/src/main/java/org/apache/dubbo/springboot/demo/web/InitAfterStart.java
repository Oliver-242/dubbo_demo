package org.apache.dubbo.springboot.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.provider.SnowService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author caijizhou
 * @date 2023/08/31 16:00
 */
@Component
@Slf4j
public class InitAfterStart implements CommandLineRunner {
    @DubboReference(group = "group1", version = "1.0.0")
    private SnowService snowService;

    private String applicationName;

    @Value("${spring.application.name}")
    private void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("您正在使用{}", applicationName);
        snowService.initiator();
    }
}
