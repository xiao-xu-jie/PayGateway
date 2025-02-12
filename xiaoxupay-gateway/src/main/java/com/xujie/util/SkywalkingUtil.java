package com.xujie.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.server.ServerWebExchange;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class SkywalkingUtil {

    /**
     * tid放入MDC
     *
     * @param exchange
     */
    public static void putTidIntoMdc(ServerWebExchange exchange) {
        try {
            Object entrySpanInstance = exchange.getAttributes().get("SKYWALKING_SPAN");
            if (ObjectUtil.isEmpty(entrySpanInstance)) {
                return;
            }
            Class<?> entrySpanClazz = entrySpanInstance.getClass().getSuperclass().getSuperclass();
            Field field = entrySpanClazz.getDeclaredField("owner");
            field.setAccessible(true);
            Object ownerInstance = field.get(entrySpanInstance);
            Class<?> ownerClazz = ownerInstance.getClass();
            Method getTraceId = ownerClazz.getMethod("getReadablePrimaryTraceId");
            String traceId = (String) getTraceId.invoke(ownerInstance);
            MDC.put("tid", traceId);
        } catch (Exception e) {
            log.error("gateway追踪码获取失败", e);
        }
    }

}
