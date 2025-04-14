package com.xujie.order.domain.handler.concret;

import com.xujie.common.enums.BizResponseEnum;
import com.xujie.common.exception.CustomException;
import com.xujie.order.domain.entity.Order;
import com.xujie.order.domain.handler.AbstractOrderHandler;
import com.xujie.order.infra.entity.SiteOrder;
import com.xujie.order.infra.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderInfoCheckHandler extends AbstractOrderHandler {
    private final OrderService orderService;

    @Override
    protected void doHandle(Order order) {
        SiteOrder orderByTradeNo = orderService.getOrderByTradeNo(order.getSiteAppid(), order.getTradeNo());
        if (ObjectUtils.isNotEmpty(orderByTradeNo)) {
            throw new CustomException(BizResponseEnum.TRADE_NO_EXISTED);
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
