package com.xujie;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.order.OrderApplication;
import com.xujie.pay.api.dto.WxOrderRequest;
import com.xujie.pay.api.feign.PayFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = OrderApplication.class)
public class TestFeign {
    @Resource
    private PayFeignApi payFeignApi;

    @Test
    public void testWxFeign() {
        WxOrderRequest orderRequest = WxOrderRequest.builder()
                .openNo("31234234")
                .title("村上春树")
                .totalFee(0.1)
                .remark("测试")
                .build();
        ResponseEntity<?> order = payFeignApi.createOrder(orderRequest);
        log.info("{}", order);
    }
}
