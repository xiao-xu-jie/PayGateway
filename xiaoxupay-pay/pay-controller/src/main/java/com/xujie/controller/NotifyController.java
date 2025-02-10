package com.xujie.controller;

import com.xujie.service.MessageService;
import com.xujie.startegy.wx.hupijiao.impl.HuPiJiaoWxPayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/notify")
public class NotifyController {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private MessageService messageService;

    @RequestMapping("/hpj")
    public String notify(@RequestParam() Map<String, Object> map){
        HuPiJiaoWxPayService huPiJiaoWxPayService = applicationContext.getBean(HuPiJiaoWxPayService.class);
        String orderNo = null;
        try {
            orderNo = huPiJiaoWxPayService.checkNotify(map).toString();
        } catch (Exception e) {
            log.error("[HuPiJiao]回调异常：{}",e.getMessage());
            return "error";
        }
        log.info("[虎皮椒] 开始--支付成功回调--校验成功：{}", map);
        messageService.sendOrderPaidMessage(orderNo);
        return "success";
    }
}
