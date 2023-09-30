package org.apache.dubbo.springboot.demo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserAuthInterceptor implements HandlerInterceptor {
    @DubboReference(group = "group1", version = "1.0.0")
    private DemoService demoService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        log.info("用户身份识别...");
        HttpSession httpSession = request.getSession(false);
        try{
            if(httpSession != null) {
                long userId = (long) httpSession.getAttribute("userId");
                Enumeration<String> params = request.getParameterNames();
                List<String> cardIdList = new ArrayList<>();
                while(params.hasMoreElements()) {
                    String paramName = params.nextElement();
                    if(paramName.startsWith("cardid")) {
                        cardIdList.add(request.getParameter(paramName));
                    }
                }
                if(cardIdList.isEmpty()) {
                    throw new Exception("卡参数为空！");
                }
                String url = request.getRequestURI().substring(1);
                if(!demoService.verify(cardIdList, userId, url)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("非法操作！");
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("非法操作！");
            return false;
        }
        log.info("身份验证fail");
        response.getWriter().write("请先登录！");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
