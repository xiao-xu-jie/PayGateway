package com.xujie.domain.service.impl;

import com.xujie.common.enums.OrderStatus;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.convert.DomainConvert;
import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.OrderHandlerContext;
import com.xujie.domain.service.OrderDomainService;
import com.xujie.infra.entity.SiteOrder;
import com.xujie.infra.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderDomainServiceImpl implements OrderDomainService {
    @Resource
    private OrderHandlerContext orderHandlerContext;
    @Resource
    private OrderService orderService;
    @Resource
    private DomainConvert convert;

    @Override
    public Order processOrder(Order order) {
        orderHandlerContext.process(order);
        try {
            orderService.insertOrder(convert.bo2do(order));
        } catch (DuplicateKeyException e) {
            throw new CustomException("订单号已经存在");
        }
        log.info("订单保存到数据库：{}", order);
        return order;
    }

    @Override
    public void handleOrderExpired(String openNo) {
        log.info("处理订单过期：{}", openNo);
        SiteOrder orderByEntity = orderService.getOrderByEntity(SiteOrder.builder()
                .openNo(openNo)
                .build());

        // 订单处于待支付
        if (ObjectUtils.isNotEmpty(orderByEntity)
                && ObjectUtils.compare(orderByEntity.getOrderStatus(), OrderStatus.WAIT_PAY) == 0) {
            orderByEntity.setOrderStatus(OrderStatus.EXPIRED);
            // 更新订单状态
            // TODO 解决订单过期时支付冲突问题
            orderService.updateOrder(openNo, orderByEntity);
        } else {
            log.error("订单过期处理逻辑异常：{}", orderByEntity);
        }

    }

    @Override
    public void handleOrderPaid(String openNo) {
        log.info("处理订单已支付：{}", openNo);
        SiteOrder orderByEntity = orderService.getOrderByEntity(SiteOrder.builder()
                .openNo(openNo)
                .build());
        if (!OrderStatus.WAIT_PAY.equals(orderByEntity.getOrderStatus())) {
            log.error("订单已支付处理错误：{}，{}", openNo, orderByEntity);
        } else {
            orderByEntity.setOrderStatus(OrderStatus.SUCCESS);
            orderByEntity.setPayTime(new Date());
            orderService.updateOrder(openNo, orderByEntity);
        }
    }

    @Override
    public void handleOrderPaid(List<String> list) {
        orderService.updateOrderPaidBatch(list);
    }
}
