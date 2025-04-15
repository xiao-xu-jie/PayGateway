package com.xujie.order.domain.strategy.wx;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.exception.BaseException;
import com.xujie.common.exception.CustomException;
import com.xujie.id.api.IdGeneratorFeignApi;
import com.xujie.id.api.constants.IdConstant;
import com.xujie.order.common.enums.OrderNotifyStatus;
import com.xujie.order.common.enums.OrderStatus;
import com.xujie.order.domain.entity.Order;
import com.xujie.order.domain.strategy.ChannelStrategy;
import com.xujie.pay.api.dto.WxOrderDTO;
import com.xujie.pay.api.dto.WxOrderRequest;
import com.xujie.pay.api.feign.PayFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "wx-Channel")
public class WxChannelStrategy extends ChannelStrategy {
    @Resource
    private PayFeignApi payFeignApi;

    @Resource
    private IdGeneratorFeignApi idGeneratorFeignApi;


    @Override
    public void handle(Order order) {
        // 调用创建订单
        ResponseEntity<Long> responseEntity = idGeneratorFeignApi.getId(IdConstant.ORDER_OPEN_NO_SNOWFLAKE);
        int count = 5;
        while (!responseEntity.isSuccess() && count-- > 0) {
            responseEntity = idGeneratorFeignApi.getId(IdConstant.ORDER_OPEN_NO_SNOWFLAKE);
        }
        if (!responseEntity.isSuccess()) {
            throw new BaseException(1001, "Id 服务调用失败");
        }
        Long openNo = responseEntity.getData();
        WxOrderRequest orderRequest = WxOrderRequest.builder()
                .totalFee(order.getTotalFee())
                .title(order.getTitle())
                .openNo(String.valueOf(openNo))
                .siteAppid(order.getSiteAppid())
                .remark(order.getRemark())
                .desc(order.getOrderDesc())
                .client(order.getClient())
                .build();
        ResponseEntity<WxOrderDTO> response = payFeignApi.createOrder(orderRequest);
        WxOrderDTO wxOrderDTO = response.getData();
        if (response.getCode() != 200) {
            log.error("[WxChannelStrategy]创建订单异常：{}", response);
            throw new CustomException(response.getErrMessage());
        }
        order.setUrlQrcode(wxOrderDTO.getUrlQrcode());
        order.setUrl(wxOrderDTO.getUrl());
        order.setOpenNo(wxOrderDTO.getOpenNo());
        // 设置订单状态
        order.setOrderStatus(OrderStatus.WAIT_PAY.getCode());
        // 设置订单通知状态
        order.setNotifyStatus(OrderNotifyStatus.WAIT_NOTIFY.getCode());
        order.setTransactionId(wxOrderDTO.getTransactionId());
        order.setExpireTime(DateUtil.date().offset(DateField.MINUTE, 15));
        order.setCreateTime(DateUtil.date());
    }
}
