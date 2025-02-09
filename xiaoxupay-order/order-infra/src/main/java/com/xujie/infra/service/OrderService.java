package com.xujie.infra.service;

import com.xujie.infra.entity.SiteOrder;

import java.util.List;

public interface OrderService {
    SiteOrder getOrderByEntity(SiteOrder order);
    List<SiteOrder> getOrderListByEntity(SiteOrder order);
    void insertOrder(SiteOrder order);
    void updateOrder(String openNo,SiteOrder order);
}
