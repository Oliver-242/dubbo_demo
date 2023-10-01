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
    @Autowired
    private AdminAuthInterceptor adminAuthInterceptor;

    private final static String[] EXCLUDE = {"/", "/register"};

    private final static String[] ADD_AUTH = {"/deposit", "/query", "/transfer", "/withdraw"};

    private final static String[] ADD_ADMIN_AUTH = {"/freeze-user", "/delete-user", "/restore-user"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Interceptor初始化中...");

        registry.addInterceptor(pageAccessInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE);
        registry.addInterceptor(userAuthInterceptor)
                .addPathPatterns(ADD_AUTH);
        registry.addInterceptor(adminAuthInterceptor)
                .addPathPatterns(ADD_ADMIN_AUTH);

        log.info("Interceptor初始化完毕");
    }
}