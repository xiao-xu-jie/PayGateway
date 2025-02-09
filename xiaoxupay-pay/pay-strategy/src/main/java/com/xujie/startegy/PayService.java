package com.xujie.startegy;

import cn.hutool.json.JSONObject;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.startegy.wx.hupijiao.entity.RefundRequest;

import java.util.Map;


public interface PayService {
    WxOrderDTO createOrder(WxOrderRequest orderRequest);


    JSONObject refundOrder(RefundRequest refundRequest);
    Object checkNotify(Map<String, Object> map);

}
