package com.xujie.controller;

import com.xujie.common.dto.WxOrderRequest;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.startegy.content.WxPayContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class PayController {

    private final WxPayContext payContext;
    public PayController(WxPayContext payContext){
        this.payContext = payContext;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody @Validated WxOrderRequest request){
        return ResponseEntity.success(payContext.processOrder(request));
    }

}
