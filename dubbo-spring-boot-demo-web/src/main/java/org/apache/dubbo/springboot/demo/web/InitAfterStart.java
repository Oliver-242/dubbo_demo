package org.apache.dubbo.springboot.demo.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.provider.SnowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author caijizhou
 * @date 2023/08/31 16:00
 */
@Component
public class InitAfterStart implements CommandLineRunner {
    @DubboReference(group = "group1", version = "1.0.0")
    private SnowService snowService;

    @Override
    public void run(String... rags) throws Exception {
        snowService.initiator();
        System.out.println("fff");
    }
}
