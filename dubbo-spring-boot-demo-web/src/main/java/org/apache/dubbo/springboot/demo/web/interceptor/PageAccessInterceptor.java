package org.apache.dubbo.springboot.demo.web.interceptor;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caijizhou
 * @date 2023/09/24 17:00
 */
public class PageAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler)
            throws Exception {
        if (!isAdminUser(request)) {
            // 如果用户不是管理员，可以重定向到无权限页面
            response.sendRedirect("/access-denied");
            return false; // 中止请求
        }

        return true; // 允许继续请求处理
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
