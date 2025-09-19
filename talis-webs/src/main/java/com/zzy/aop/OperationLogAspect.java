package com.zzy.aop;

import com.zzy.mapper.OperateLogMapper;
import com.zzy.pojo.OperateLog;
import com.zzy.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知
    @Around("@annotation(zzy.anno.LogOperation)")
    public Object around(ProceedingJoinPoint joinPoint/*, LogOperation log*/) throws Throwable {
//        1. get className
        String className = joinPoint.getTarget().getClass().getName();
//        2. get methodName
        String methodName = joinPoint.getSignature().getName();
//        3. get method params
        Object[] args = joinPoint.getArgs();

        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();


        OperateLog operateLog = new OperateLog();
        operateLog.setCostTime(end - begin);
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setMethodName(methodName);
        operateLog.setClassName(className);
        operateLog.setReturnValue(result.toString());
        operateLog.setMethodParams(Arrays.toString(args));
//        when no filter check, this may cause error.
        operateLog.setOperateEmpId(CurrentHolder.getCurrentId());


        operateLogMapper.insert(operateLog);


        return result;
    }


    public Integer getEmpId(){
        return 1;
    }
}