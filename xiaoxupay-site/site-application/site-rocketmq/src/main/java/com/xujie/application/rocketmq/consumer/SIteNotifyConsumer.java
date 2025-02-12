package com.xujie.application.rocketmq.consumer;

import com.xujie.domain.task.notify.NotifyTaskThreadPool;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "notify", consumerGroup = "default")
public class SIteNotifyConsumer implements RocketMQListener<String> {

    @Resource
    private NotifyTaskThreadPool notifyTaskThreadPool;

    @Override
    public void onMessage(String info) {
        // 执行通知站点
        log.info("接收到站点通知信息：{}", info);
        String[] split = info.split("-");
        notifyTaskThreadPool.notifySite(split[0], split[1]);
    }
}
