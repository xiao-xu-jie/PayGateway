package com.xujie.client.api.controller;

import com.xujie.client.config.XPayConfig;
import com.xujie.client.core.util.HashUtil;
import com.xujie.client.dto.XOrderDto;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientNotifyController {

    @Resource
    private XPayConfig config;

    @PostMapping("/xorder/pay/notify")
    public String payNotify(@RequestBody XOrderDto.XOrderNotifyRequest xOrderNotifyRequest) {
        // 校验请求的hash
        String hash = xOrderNotifyRequest.getHash();
        boolean check = HashUtil.checkNotifyHash(xOrderNotifyRequest, config.getSiteAppSecret());
        if (!check) {
            log.info("[ClientNotifyController] xorder 订单支付回调信息，校验失败：{}", xOrderNotifyRequest);
            return "fail";
        } else {
            log.info("[ClientNotifyController] xorder 订单支付回调信息，校验成功：{}", xOrderNotifyRequest);
        }
        return "success";
    }
}
