package org.apache.dubbo.springboot.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PageAccessInterceptor pageAccessInterceptor;
    @Autowired
    private UserAuthInterceptor userAuthInterceptor;

    private final static String[] EXCLUDE = {"/", "/register"};

    private final static String[] ADD_AUTH = {"/deposit", "/query", "/transfer", "/withdraw"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Intercepter初始化中...");

        registry.addInterceptor(pageAccessInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE);
        registry.addInterceptor(userAuthInterceptor)
                .addPathPatterns(ADD_AUTH);

        log.info("Intercepter初始化完毕");
    }
}