package com.xujie.strategy.wx.hupijiao.constants;

public class HuPiJiaoPayConstant {
    /**
     * 订单ID
     **/
    public static final String ORDER_ID = "trade_order_id";
    /**
     * 订单价格 元 精确分 如0.01
     **/
    public static final String TOTAL_FEE = "total_fee";
    /**
     * 订单标题
     **/
    public static final String TITLE = "title";
    /**
     * 订单备注
     **/
    public static final String PLUGINS = "plugins";
    /**
     * 通知回调地址 url 什么含义 我们后台需要知道 用户支付了
     **/
    public static final String NOTIFY_URL = "notify_url";
    /**
     * 使用 响应字段中 url 就直接跳到百度了，如果访问，url_qrcode ，
     * 不会直接跳转，只有当支付完成后，再次刷新 url_qrcode中的连接，才会跳转。
     **/
    public static final String RETURN_URL = "return_url";

    /**
     * 退款原因
     */
    public static final String REFUND_REASON = "reason";

}
