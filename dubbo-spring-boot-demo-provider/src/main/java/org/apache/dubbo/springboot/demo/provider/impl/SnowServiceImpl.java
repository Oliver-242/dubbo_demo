package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.provider.SnowService;
import org.apache.dubbo.springboot.demo.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * @author caijizhou
 * @date 2023/08/31 9:50
 */
@DubboService(group = "group1", version = "1.0.0")
@Slf4j
public class SnowServiceImpl implements SnowService {
    @Autowired
    private Environment environment;

//    @Autowired
    private SnowFlake snowFlake;

    /**
     * 初始化snowflake
     */
    @Override
    public void initiator() {
        try {
            assert snowFlake == null;
            snowFlake = new SnowFlake(Long.parseLong(Objects.requireNonNull(environment.getProperty("datacenterId"))),
                    Long.parseLong(Objects.requireNonNull(environment.getProperty("machineId"))));
            log.debug("当前数据中心ID为{},服务器ID为{}",
                    environment.getProperty("datacenterId"), environment.getProperty("machineId"));
        } catch (AssertionError ex) {
            log.error("snowFlake初始化之前非空: {}", ex.getMessage());
        }

    }

    @Override
    public String getGeneratedId() {
        return String.valueOf(snowFlake.nextId());
    }
}
