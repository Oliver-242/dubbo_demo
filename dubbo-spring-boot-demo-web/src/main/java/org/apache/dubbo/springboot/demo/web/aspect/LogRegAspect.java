package org.apache.dubbo.springboot.demo.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author caijizhou
 * @date 2023/09/26 13:50
 */
@Aspect
@Component
@Slf4j
public class LogRegAspect {
    @After("execution(public * org.apache.dubbo.springboot.demo.web.controller.LoginController.login(..))" +
            "&& args(httpServletRequest, ..)")
    public void loginSession(JoinPoint joinPoint, HttpServletRequest httpServletRequest) {
        log.info("In aopPointCutLogin: " + httpServletRequest.getSession().getAttribute("userId") +
                ", " + httpServletRequest.getSession().getAttribute("userType"));
    }

}
