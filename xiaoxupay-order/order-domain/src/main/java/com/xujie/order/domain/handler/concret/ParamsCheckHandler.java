package com.xujie.order.domain.handler.concret;

import com.xujie.common.enums.BizResponseEnum;
import com.xujie.common.exception.CustomException;
import com.xujie.order.common.enums.ChannelTypeEnum;
import com.xujie.order.common.enums.ClientTypeEnum;
import com.xujie.order.domain.entity.Order;
import com.xujie.order.domain.handler.AbstractOrderHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 检验参数合理性
 * 检验时间戳
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ParamsCheckHandler extends AbstractOrderHandler {
    private final Environment environment;


    @Override
    protected void doHandle(Order order) {

        String env = environment.getProperty("spring.profiles.active");
        String timestamp = order.getTimestamp();
        Long time = Long.parseLong(timestamp);
        Date date = null;
        try {
            date = new Date(time);
        } catch (Exception e) {
            log.error("时间戳校验失败：{}", timestamp, e);
        }
        DateTime now = DateTime.now();
        boolean after = now.plusMinutes(2).isBefore(time);
        if (!after) {
            log.error("时间戳过期：{}", timestamp);
            if ("prod".equals(env)) {
                throw new CustomException(BizResponseEnum.TIMESTAMP_EXPIRED);
            }
        }

        // 检查channel类型
        String channel = order.getChannel();
        ChannelTypeEnum channelTypeEnum = ChannelTypeEnum.getValue(channel);
        if (ObjectUtils.isEmpty(channelTypeEnum)) {
            throw new CustomException(BizResponseEnum.CHANNEL_TYPE_NOT_EXIST);
        }

        // 检查client类型
        String client = order.getClient();
        ClientTypeEnum clientTypeEnum = ClientTypeEnum.getValue(client);
        if (ObjectUtils.isEmpty(clientTypeEnum)) {
            throw new CustomException(BizResponseEnum.CLIENT_TYPE_NOT_EXIST);
        }


    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
