package com.xujie.startegy;

import cn.hutool.json.JSONObject;
import com.xujie.startegy.wx.hupijiao.entity.OrderRequest;

public abstract class AbstractPayContext {
    abstract public JSONObject processOrder(OrderRequest request);
}
