package com.xujie.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.convert.OrderDTOConvert;
import com.xujie.domain.entity.Order;
import com.xujie.domain.service.OrderDomainService;
import com.xujie.pojo.dto.SiteCreateOrderDTO;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点调用接口创建订单
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderDomainService orderDomainService;
    @Resource
    private OrderDTOConvert convert;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated SiteCreateOrderDTO createOrderDTO) {
        Order order = orderDomainService.processOrder(convert.dto2bo(createOrderDTO));
        return ResponseEntity.success(order);
    }
}
