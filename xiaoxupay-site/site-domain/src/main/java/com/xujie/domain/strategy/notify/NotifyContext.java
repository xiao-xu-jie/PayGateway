package com.xujie.domain.strategy.notify;

import com.xujie.common.enums.NotifyType;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.entity.NotifyRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class NotifyContext {
    @Resource
    private Map<String, NotifyStrategy> strategyMap;
    private final String suffix = "-notify";

    public String notifyByType(NotifyType type, NotifyRequest request) {
        NotifyStrategy notifyStrategy = strategyMap.get(type.getDesc() + suffix);
        if (ObjectUtils.isEmpty(notifyStrategy)) {
            log.error("策略不存在：{}", type);
            throw new CustomException("策略不存在");
        }

        return notifyStrategy.notify(request);
    }
}
