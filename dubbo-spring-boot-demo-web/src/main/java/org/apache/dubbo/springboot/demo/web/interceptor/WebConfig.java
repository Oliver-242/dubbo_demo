package org.apache.dubbo.springboot.demo.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author caijizhou
 * @date 2023/09/24 17:15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final static String[] EXCLUDE = {"/", "/register"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageAccessInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE);
    }
}