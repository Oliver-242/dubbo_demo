package org.apache.dubbo.springboot.demo.web.interceptor;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author caijizhou
 * @date 2023/09/27 17:00
 */
@Component
public class UserAuthInterceptor implements HandlerInterceptor {
    @DubboReference(version = "1.0.0", group = "group1")
    private DemoService demoService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null) {
            long userId = (long) httpSession.getAttribute("userId");
            Enumeration<String> params = request.getParameterNames();
            List<String> cardIdList = new ArrayList<>();
            while(params.hasMoreElements()) {
                String paramName = params.nextElement();
                if(paramName.startsWith("cardid")) {
                    cardIdList.add(paramName);
                }
            }
            String url = request.getRequestURI().substring(1);
            return demoService.verify(cardIdList, userId, url);
        }
        return false;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                           @NotNull Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) throws Exception {
    }
}
