package org.apache.dubbo.springboot.demo.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author caijizhou
 * @date 2023/09/24 17:00
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 示例：假设用户登录信息存储在Session中
        if (httpServletRequest.getSession().getAttribute("user") == null) {
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
}