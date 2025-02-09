package com.xujie.startegy.wx.hupijiao.aop;

import cn.hutool.json.JSONObject;
import com.xujie.common.exception.CustomException;
import com.xujie.common.utils.HashUtil;
import com.xujie.startegy.wx.hupijiao.config.HuPiJiaoPayConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * desc：校验支付平台返回数据
 */
@Slf4j
@Aspect
@Component
public class PayResponseCheckAOP {
    @Resource(name = "huPiJiaoPayConfig")
    private HuPiJiaoPayConfig config;

    @Pointcut("execution(* com.xujie.startegy.wx.hupijiao.impl.HuPiJiaoWxPayService.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行目标方法
        Object result = joinPoint.proceed();
        // 检查返回值
        JSONObject response = (JSONObject) result;
        // Hash值计算
        String hash = HashUtil.hash(response.entrySet(), config.getAppSecret());
        // 获取响应中的 hash 值
        String resHash = response.getStr("hash");
        log.info("[PayResponseCheckAOP] Hash值校验：{}，{}", hash, resHash);

        // 比较 hash 值
        if (ObjectUtils.compare(hash, resHash) != 0) {
            throw new CustomException("hash校验异常");  // 抛出异常，中断方法
        }

        return result;
    }
}
