package org.apache.dubbo.springboot.demo.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.springboot.demo.enums.UserTypeEnum;
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
import java.util.Objects;

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
            HttpSession httpSession = httpServletRequest.getSession(false);
            if(httpSession == null) {
//                httpServletResponse.sendRedirect("/");        //未登录用户定向到登录界面
            } else {
                return;
//                chain.doFilter(request, response);
            }
            log.info("过滤结束");
        } else {
            log.info("跳过过滤");
            HttpSession httpSession = httpServletRequest.getSession(false);
            if(httpSession == null) {
//                chain.doFilter(request,response);
            } else {                                               //已登录用户按照用户类型定位到不同操作界面
                String userType = (String) httpSession.getAttribute("userType");
                if(Objects.equals(userType, UserTypeEnum.USER.name())) {
                    httpServletResponse.sendRedirect("/transfer");
                } else {
                    httpServletResponse.sendRedirect("/home");
                }
            }
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