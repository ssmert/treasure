package com.jakers.mustneed.core.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Configuration
public class LogAspect {

    @Around("execution(* com.jakers.*.*.api.*Controller.*(..))"
        + " || execution(* com.jakers.mustneed..service..*Service.*(..))"
        + " || execution(* com.jakers.mustneed..repository.*Impl.*(..))")
    public Object loggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        String type = joinPoint.getSignature().getDeclaringTypeName();
        String name = "";

        stopWatch.start();
        Object retVal = joinPoint.proceed();
        stopWatch.stop();

        if (type.contains("Controller")) {
            name = "Controller  \t:  ";
        } else if (type.contains("Service")) {
            name = "Service     \t:  ";
        } else if (type.contains("Repository")) {
            name = "Repository  \t:  ";
        }

        log.info(String.format("%s[%s.%s] Execute Time : %.3f(sec)", name, type,
            joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis() / 1000.0));

        return retVal;
    }
}
