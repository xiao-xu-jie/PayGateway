package com.xujie.pay.api.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.pay.api.dto.WxOrderDTO;
import com.xujie.pay.api.dto.WxOrderRequest;
import com.xujie.pay.strategy.context.WxPayContext;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PayFeignProvider implements PayFeignApi {

    private final WxPayContext payContext;

    @Override
    public ResponseEntity<WxOrderDTO> createOrder(@RequestBody @Validated WxOrderRequest request) {
        return ResponseEntity.success(payContext.processOrder(request));
    }
}
