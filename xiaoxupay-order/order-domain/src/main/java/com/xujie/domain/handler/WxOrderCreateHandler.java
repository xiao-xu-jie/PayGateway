package com.xujie.domain.handler;

import cn.hutool.core.util.IdUtil;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.entity.Order;
import com.xujie.feign.WxPayFeignClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WxOrderCreateHandler extends OrderHandler {
    private WxPayFeignClient wxPayFeignClient;
    @Override
    public void doHandle(Order order) {
        // 调用创建订单
        String openNo = IdUtil.getSnowflakeNextIdStr();
        WxOrderRequest orderRequest = WxOrderRequest.builder()
                .totalFee(order.getTotalFee())
                .title(order.getTitle())
                .openNo(openNo)
                .remark(order.getRemark())
                .desc(order.getOrderDesc())
                .build();
        ResponseEntity<WxOrderDTO> response = wxPayFeignClient.createOrder(orderRequest);
        WxOrderDTO wxOrderDTO = response.getData();
        if(response.getCode() != 200) {
            log.error("[WxOrderCreateHandler]创建订单异常：{}",response);
            throw new CustomException(response.getErrMessage());
        }
        order.setChannel(wxOrderDTO.getChannel());
        order.setUrlQrcode(wxOrderDTO.getUrlQrcode());
        order.setUrl(wxOrderDTO.getUrl());
        order.setOpenNo(wxOrderDTO.getOpenNo());
    }
    public WxOrderCreateHandler(WxPayFeignClient wxPayFeignClient) {
        this.wxPayFeignClient = wxPayFeignClient;
    }
}
