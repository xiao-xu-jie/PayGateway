package com.xujie.domain.strategy.wx;

import cn.hutool.core.util.IdUtil;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.enums.OrderStatus;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.entity.Order;
import com.xujie.domain.strategy.ChannelStrategy;
import com.xujie.feign.WxPayFeignClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "wx-Channel")
public class WxChannelStrategy extends ChannelStrategy {
    @Resource
    private WxPayFeignClient wxPayFeignClient;

    @Override
    public void handle(Order order) {
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
        if (response.getCode() != 200) {
            log.error("[WxChannelStrategy]创建订单异常：{}", response);
            throw new CustomException(response.getErrMessage());
        }
        order.setUrlQrcode(wxOrderDTO.getUrlQrcode());
        order.setUrl(wxOrderDTO.getUrl());
        order.setOpenNo(wxOrderDTO.getOpenNo());
        order.setOrderStatus(OrderStatus.WAIT_PAY);
        order.setTransactionId(wxOrderDTO.getTransactionId());
    }
}
