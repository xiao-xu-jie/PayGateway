package com.xujie.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.startegy.PayContext;
import com.xujie.startegy.PayService;
import com.xujie.startegy.wx.hupijiao.entity.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class PayController {

    private final PayService wxPayService;
    public PayController(PayContext payContext){
        wxPayService = payContext.getWxPayService();
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.success(wxPayService.createOrder(request));
    }

}
