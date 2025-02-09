package com.xujie.domain.handler;

import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.common.SiteInfoCheckHandler;
import com.xujie.feign.WxPayFeignClient;
import org.springframework.stereotype.Component;

@Component
public class OrderHandlerContext {

    private final OrderHandler handler;

    public OrderHandlerContext(WxPayFeignClient wxPayFeignClient) {
        handler = new SiteInfoCheckHandler();
        handler.setNext(new WxOrderCreateHandler(wxPayFeignClient));
    }
    public void process(Order order) {
        handler.handle(order);
    }

}
