package com.xujie.startegy;

import cn.hutool.json.JSONObject;
import com.xujie.startegy.wx.hupijiao.entity.OrderRequest;
import com.xujie.startegy.wx.hupijiao.entity.RefundRequest;


public interface PayService {
    JSONObject createOrder(OrderRequest orderRequest);

    JSONObject refundOrder(RefundRequest refundRequest);

}
