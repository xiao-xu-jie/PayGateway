package com.xujie.api.feign;

import com.xujie.api.dto.WxOrderDTO;
import com.xujie.api.dto.WxOrderRequest;
import com.xujie.payGateway.common.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "xiaoxupay-pay")
public interface PayFeignApi {

    @PostMapping("/wx/createOrder")
    ResponseEntity<WxOrderDTO> createOrder(@RequestBody @Validated WxOrderRequest request);
}
