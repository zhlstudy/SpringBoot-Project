package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


//@Aspect(将当前类标记为切面类)
//@Component(将当前类的对象创建使用维护交由Spring容器维护)
@Aspect
@Component
public class TimerAspect {
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //开始时间
        long start = System.currentTimeMillis();
        //调用目标方法,比如login方法,getByUid方法
        Object result = pjp.proceed();
        //结束时间
        long end = System.currentTimeMillis();
        System.err.println("耗时:"+(end-start));
        return result;
    }

}
