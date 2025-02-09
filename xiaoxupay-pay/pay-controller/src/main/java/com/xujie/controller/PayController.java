package com.xujie.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.startegy.PayContext;
import com.xujie.startegy.wx.hupijiao.entity.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class PayController {

    private final PayContext payContext;
    public PayController(PayContext payContext){
        this.payContext = payContext;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.success(payContext.processOrder(request));
    }

}
