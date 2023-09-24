package org.apache.dubbo.springboot.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caijizhou
 * @date 2023/09/24 17:00
 */
@Slf4j
public class PageAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler)
            throws Exception {
        log.info("preHandle鉴权开始");
        if (!isAdminUser(request)) {
            response.sendRedirect("/access-denied");
            return false;
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
        String userRole = (String) request.getSession().getAttribute("userRole");
        return "admin".equals(userRole);
    }
}
