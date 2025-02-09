package com.xujie.startegy;

import cn.hutool.json.JSONObject;
import com.xujie.startegy.wx.hupijiao.entity.OrderRequest;
import com.xujie.startegy.wx.hupijiao.entity.RefundRequest;

import java.util.Map;


public interface PayService {
    JSONObject createOrder(OrderRequest orderRequest);

    JSONObject refundOrder(RefundRequest refundRequest);
    Object checkNotify(Map<String, Object> map);

}
