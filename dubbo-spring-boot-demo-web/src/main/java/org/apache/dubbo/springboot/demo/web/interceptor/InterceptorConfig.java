package org.apache.dubbo.springboot.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author caijizhou
 * @date 2023/09/24 17:15
 */
@Slf4j
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final static String[] EXCLUDE = {"/", "/register"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Intercepter初始化中...");
        registry.addInterceptor(new PageAccessInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE);
        log.info("Intercepter初始化完毕");
    }
}