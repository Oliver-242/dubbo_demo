package org.apache.dubbo.springboot.demo.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author caijizhou
 * @date 2023/09/24 17:00
 */
@Slf4j
@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(pathCheck(httpServletRequest)) {
            log.info("过滤中...");

            log.info("过滤结束");
        } else {
            log.info("跳过过滤");
            chain.doFilter(request, response);
        }

        HttpSession session = httpServletRequest.getSession(false);
        // 示例：假设用户登录信息存储在Session中
        if (session.getAttribute("user") == null) {
            // 未登录，重定向到登录页
            httpServletResponse.sendRedirect("/login");
        } else {
            // 用户已登录，继续请求处理
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean pathCheck(HttpServletRequest httpServletRequest) {
        log.info("doFilter路径检查开始...");
        String requestUrl = httpServletRequest.getRequestURI();
        return !("/".equals(requestUrl) || "/register".equals(requestUrl));
    }
}