package org.apache.dubbo.springboot.demo.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.springboot.demo.annotation.EntranceLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author caijizhou
 * @date 2023/09/27 10:50
 */
@Slf4j
@Aspect
@Component
public class EntranceLogAspect {
    @Pointcut("@annotation(org.apache.dubbo.springboot.demo.annotation.EntranceLog)")
    public void logPointCut() {
    }

    @Around(value = "logPointCut() && @annotation(entranceLog)")
    public Object doAround(final ProceedingJoinPoint proceedingJoinPoint, EntranceLog entranceLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object result = proceedingJoinPoint.proceed();
        long cost = System.currentTimeMillis() - startTime;
        log.info("[EntranceLogAspect] {} end. costTime = {}ms, result={}", methodName, cost, result);
        return result;
    }

}
