package com.xujie.pay.api.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.pay.api.dto.WxOrderDTO;
import com.xujie.pay.api.dto.WxOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "xiaoxupay-pay")
public interface PayFeignApi {

    @PostMapping("/wx/createOrder")
    ResponseEntity<WxOrderDTO> createOrder(@RequestBody @Validated WxOrderRequest request);
}
