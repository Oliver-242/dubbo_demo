package org.apache.dubbo.springboot.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.springboot.demo.enums.UserTypeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author caijizhou
 * @date 2023/10/01 16:00
 */
@Component
@Slf4j
public class AdminAuthInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        log.info("管理员身份识别...");
        HttpSession httpSession = request.getSession(false);
        try{
            if(httpSession == null) {
                log.info("未登录！");
                response.getWriter().write("请先登录！");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            } else{
                if(httpSession.getAttribute("userType") == UserTypeEnum.ADMIN.getUserType()) {
                    return true;
                } else {
                    log.info("身份验证fail");
                    response.getWriter().write("权限异常！");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }
        } catch(Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}
