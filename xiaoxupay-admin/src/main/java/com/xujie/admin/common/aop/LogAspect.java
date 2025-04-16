package com.xujie.admin.common.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import com.xujie.admin.common.annotations.WebLog;
import com.xujie.admin.domain.BO.OperLogBO;
import com.xujie.admin.domain.service.OperLogDomainService;
import com.xujie.common.exception.CustomException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author Xujie
 * @since 2024/9/23 23:42
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.xujie.admin.common.annotations.WebLog)")
    public void webLog() {
    }

    @Resource(name = "logExecutor")
    private ThreadPoolTaskExecutor logExecutor;

    @Resource
    private OperLogDomainService operLogDomainService;

    @Around("webLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        String errorMsg = null;
        Signature signature = proceedingJoinPoint.getSignature();
        WebLog aspectLogDescription = null;
        try {
            aspectLogDescription = getAspectLogDescription(proceedingJoinPoint);
        } catch (Exception e) {
            throw new RuntimeException("获取切面注解描述失败");
        }
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            errorMsg = e.getMessage();
        }
        // 打印入参
        Object[] args = proceedingJoinPoint.getArgs();
        String request = new Gson().toJson(args);
        String response = new Gson().toJson(result);
        long endTime = System.currentTimeMillis();
        // 获取请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = requestAttributes.getRequest();
        String ip = req.getRemoteAddr();
        String url = req.getRequestURI();
        OperLogBO operLogBO = OperLogBO.builder()
                .errorLog(errorMsg)
                .logDesc(aspectLogDescription.desc())
                .methodName(String.join(".", signature.getDeclaringType().getPackageName(), signature.getName()))
                .requestParams(request)
                .requestType(aspectLogDescription.method())
                .responseBody(response)
                .costTime(endTime - startTime)
                .operateIp(ip)
                .requestPath(url)
                .operateUser(StpUtil.getLoginIdAsLong())
                .build();
        logExecutor.execute(new LogTask(operLogBO));
        if (errorMsg != null) {
            throw new CustomException(errorMsg);
        }
        return result;

    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public WebLog getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    return method.getAnnotation(WebLog.class);
                }
            }
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class LogTask implements Runnable {
        private OperLogBO operLogBO;

        @Override
        public void run() {
            try {
                log.info("日志信息:{}", operLogBO);
                operLogDomainService.add(operLogBO);
            } catch (Exception e) {
                log.error("日志记录失败:{}", e.getMessage());
            }
        }
    }
}
