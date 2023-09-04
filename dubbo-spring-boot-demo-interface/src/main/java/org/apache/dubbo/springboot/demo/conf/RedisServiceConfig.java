package org.apache.dubbo.springboot.demo.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author caijizhou
 * @date 2023/09/03 17:00
 */
@Configuration
@Service
public class RedisServiceConfig extends CachingConfigurerSupport {

    Logger logger = LoggerFactory.getLogger(RedisServiceConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        logger.info("Creating JedisPool with host: {}, port: {}, timeout: {}", host, port, timeout);
        return new JedisPool(jedisPoolConfig, host, port, timeout, null);
    }
}