package com.iswn.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLogAspect {
    final static Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * 环绕通知, 切面表达式:
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表 aop 监控的类所在的包
     * 第三处 .. 代表该包以及子包下的所有类方法
     * 第四处 * 代表类名, *代表所有类
     * 第五处 *(..) *代表类中的方法名, (..) 表示方法中的任何参数
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* com.iswn.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("========= 开始执行 {}.{} =====", proceedingJoinPoint.getTarget().getClass(),
                proceedingJoinPoint.getSignature().getName());
        // 记录开始时间
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();

        long diff = end - begin;
        if (diff > 3000) {
            logger.error("=========== 执行结束 ========, 耗时: {} 毫秒========", diff);
        } else if(diff > 2000) {
            logger.warn("=========== 执行结束 ========, 耗时: {} 毫秒========", diff);
        } else {
            logger.info("=========== 执行结束 ========, 耗时: {} 毫秒========", diff);
        }

        return result;
    }
}
