package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.provider.SnowService;
import org.apache.dubbo.springboot.demo.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author caijizhou
 * @date 2023/08/31 9:50
 */
@DubboService(group = "group1", version = "1.0.0")
@Service
@Slf4j
public class SnowServiceImpl implements SnowService {
    @Autowired
    private Environment environment;

    private SnowFlake snowFlake;

    /**
     * 初始化snowflake
     */
    @Override
    public void initiator() {
        if (snowFlake == null) {
            snowFlake = new SnowFlake(Long.parseLong(Objects.requireNonNull(environment.getProperty("datacenterId"))),
                    Long.parseLong(Objects.requireNonNull(environment.getProperty("machineId"))));
        }
        System.out.println(environment.getProperty("machineId"));
    }

    @Override
    public String getGeneratedId() {
        return String.valueOf(snowFlake.nextId());
    }
}
