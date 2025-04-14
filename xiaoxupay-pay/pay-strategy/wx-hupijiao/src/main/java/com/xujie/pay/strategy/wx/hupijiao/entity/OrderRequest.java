package com.xujie.pay.strategy.wx.hupijiao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static com.xujie.pay.strategy.wx.hupijiao.constants.HuPiJiaoPayConstant.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    /**
     * 订单ID
     **/
    private String orderId;
    /**
     * 回调地址
     **/
    private String notifyUrl;
    /**
     * 支付成功返回地址
     **/
    private String returnUrl;
    /**
     * 订单标题
     **/
    private String title;
    /**
     * 订单价格 精确分
     **/
    private Double totalFee;
    /**
     * 订单备注
     **/
    private String remark;

    public Map<String, Object> getReqBody() {
        Map<String, Object> map = new HashMap<>();
        map.put(ORDER_ID, orderId);
        map.put(NOTIFY_URL, notifyUrl);
        map.put(RETURN_URL, returnUrl);
        map.put(TITLE, title);
        map.put(TOTAL_FEE, totalFee);
        map.put(PLUGINS, remark);
        return map;
    }
}
