package com.nhnacademy.edu.springframework.project;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Component
@EnableAspectJAutoProxy
@Aspect
public class TimeTraceApp {

    @Around("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        System.out.println("stopWatch = " + stopWatch);
        try {

            stopWatch.start();

            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
            String name = joinPoint.getSignature().getName();
            System.out.println(String.format("[%s].[%s] [%d]ms", className, name, stopWatch.getTotalTimeMillis()));
        }

    }


}
