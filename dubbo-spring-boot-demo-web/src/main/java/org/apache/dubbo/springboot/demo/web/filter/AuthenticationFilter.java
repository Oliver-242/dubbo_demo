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
        log.info("In doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(pathCheck(httpServletRequest)) {
            HttpSession httpSession = httpServletRequest.getSession(false);
            if(httpSession == null) {
                chain.doFilter(request,response);
            } else {                                               //已登录用户按照用户类型定位到不同操作界面
                String userType = (String) httpSession.getAttribute("userType");
                if(Objects.equals(userType, UserTypeEnum.USER.getUserType())) {
                    httpServletResponse.sendRedirect("/dogettransfer");
                } else {
                    httpServletResponse.sendRedirect("/admin/home");
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean pathCheck(HttpServletRequest httpServletRequest) {
        log.info("doFilter路径检查开始...");
        String requestUrl = httpServletRequest.getRequestURI();
        return ("/".equals(requestUrl) || "/register".equals(requestUrl) || "/dogetlogin".equals(requestUrl));
    }
}