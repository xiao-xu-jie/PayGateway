package com.xujie.api.feign;

import com.xujie.api.dto.WxOrderDTO;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.api.dto.WxOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(name = "xiaoxupay-pay")
public interface PayFeignApi {

    @PostMapping("/wx/createOrder")
    ResponseEntity<WxOrderDTO> createOrder(@RequestBody @Validated WxOrderRequest request);
}
