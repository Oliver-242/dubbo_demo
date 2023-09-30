package org.apache.dubbo.springboot.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.springboot.demo.enums.UserTypeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author caijizhou
 * @date 2023/09/24 17:00
 */
@Component
@Slf4j
public class PageAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        log.info("preHandle鉴权开始");
        String url = request.getRequestURI();
        if("/login".equals(url)) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("phonenumber", request.getParameter("phonenumber"));
            httpSession.setAttribute("userType", request.getParameter("userType"));
            return true;
        } else if("/admin/home".equals(url)) {
            return isAdminUser(request);
        }
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                           @NotNull Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) throws Exception {
    }

    private boolean isAdminUser(HttpServletRequest request) {
        try {
            String userRole = (String) request.getSession(false).getAttribute("userType");
            return UserTypeEnum.ADMIN.name().equals(userRole);
        } catch(NullPointerException ex) {
            return false;
        }
    }
}
