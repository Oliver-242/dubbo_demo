package org.apache.dubbo.springboot.demo.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caijizhou
 * @date 2023/09/24 17:50
 */
@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> loggingFilter() {
        log.info("Filter初始化中...");
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/*");
        log.info("Filter初始化中...");
        return registrationBean;
    }
}