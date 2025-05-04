package com.zzy.aop;

import com.zzy.anno.LogOperation;
import com.zzy.mapper.OperateLogMapper;
import com.zzy.pojo.OperateLog;
import com.zzy.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知
    @Around("@annotation(com.zzy.anno.LogOperation)")
    public Object around(ProceedingJoinPoint joinPoint/*, LogOperation log*/) throws Throwable {
//        1. get className
        String className = joinPoint.getTarget().getClass().getName();
//        2. get methodName
        String methodName = joinPoint.getSignature().getName();
//        3. get method params
        Object[] args = joinPoint.getArgs();

//        4. get operate time
        LocalDateTime operateTime = LocalDateTime.now();
//        5.1 get cost time(start)
        long startTime = System.currentTimeMillis();
//        6. get return value
        Object result = joinPoint.proceed();
//        5.2 get cost time(end)
        long endTime = System.currentTimeMillis();
//        5.3 get cost time
        long executeTime = endTime - startTime;
//        6. get empId
        Integer empId = CurrentHolder.getCurrentId();
//        7. create operate log
        OperateLog operateLog = new OperateLog(null, empId, operateTime, className, methodName, Arrays.toString(args), result.toString(), executeTime);
//        8. insert operate log
        operateLogMapper.insert(operateLog);
//        9. return
        return result;
    }


    public Integer getEmpId(){
        return 1;
    }
}