package com.xujie.client.service.impl;

import com.xujie.client.config.XPayConfig;
import com.xujie.client.core.domain.XOrder;
import com.xujie.client.core.exceptio.XOrderException;
import com.xujie.client.core.util.HashUtil;
import com.xujie.client.core.util.RequestUtil;
import com.xujie.client.dto.XOrderDto;
import com.xujie.client.dto.converts.XOrderConvert;
import com.xujie.client.service.api.XOrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

@Slf4j
public class XOrderServiceImpl implements XOrderService {
    @Resource
    private RequestUtil requestUtil;

    @Resource
    private XPayConfig xPayConfig;

    /**
     * @throws XOrderException
     */
    @Override
    public XOrder createOrder(XOrderDto.XOrderCreateRequest request) {
        String siteAppId = xPayConfig.getSiteAppId();
        String siteAppSecret = xPayConfig.getSiteAppSecret();

        // 初始化req的参数以及进行hash
        Map<String, Object> reqBody = HashUtil.hash(request, siteAppId, siteAppSecret);


        // 向服务端发送请求
        XOrderDto.XOrderCreateResponse orderCreateResponse = requestUtil.createOrderRequest(reqBody);

        // 校验订单信息
        // 校验hash
        String hash = HashUtil.hash(orderCreateResponse, siteAppSecret);
        if (ObjectUtils.compare(orderCreateResponse.getHash(), hash) == 0) {
            log.info("创建请求相应校验成功");
        } else {
            log.info("创建请求相应校验失败，响应hash：{} \n 计算hash：{}", orderCreateResponse.getHash(), hash);
            throw new XOrderException("订单校验失败");
        }
        return XOrderConvert.toXOrder(orderCreateResponse);
    }
}
