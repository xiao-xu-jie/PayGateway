package com.xujie.infra.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.common.enums.OrderStatus;
import com.xujie.infra.entity.SiteOrder;
import com.xujie.infra.mapper.SiteOrderMapper;
import com.xujie.infra.service.OrderService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private SiteOrderMapper orderMapper;

    @Override
    public SiteOrder getOrderByEntity(SiteOrder order) {
        List<SiteOrder> siteOrders = getOrderListByEntity(order);
        return siteOrders.stream().findFirst().orElse(null);
    }

    @Override
    public List<SiteOrder> getOrderListByEntity(SiteOrder order) {
        return orderMapper.selectByAll(order);
    }

    @Override
    public void insertOrder(SiteOrder order) {

        orderMapper.insert(order);
    }

    @Override
    public void updateOrder(String openNo, SiteOrder order) {
        LambdaQueryWrapper<SiteOrder> eq = Wrappers.<SiteOrder>lambdaQuery().
                eq(StringUtils.isNotBlank(openNo), SiteOrder::getOpenNo, openNo);
        orderMapper.update(order, eq);
    }

    @Override
    public void updateOrderPaidBatch(List<String> list) {
        LambdaUpdateWrapper<SiteOrder> set = Wrappers.lambdaUpdate(SiteOrder.class)
                .eq(SiteOrder::getOrderStatus, OrderStatus.WAIT_PAY)
                .in(SiteOrder::getOrderStatus, list)
                .set(SiteOrder::getOrderStatus, OrderStatus.SUCCESS)
                .set(SiteOrder::getPayTime, DateUtil.date());
        orderMapper.update(set);
    }


}
