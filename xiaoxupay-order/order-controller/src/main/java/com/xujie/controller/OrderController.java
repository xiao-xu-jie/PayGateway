package com.xujie.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.convert.OrderDTOConvert;
import com.xujie.domain.entity.Order;
import com.xujie.domain.service.OrderDomainService;
import com.xujie.pojo.dto.OrderDto;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点调用接口创建订单
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderDomainService orderDomainService;
    @Resource
    private OrderDTOConvert convert;

    /**
     * @api {POST} /order/create create()
     * @apiVersion 1.0.0
     * @apiGroup OrderController
     * @apiName create()
     * @apiParam (请求体) {Number} tradeNo 站点内NO
     * @apiParam (请求体) {String} siteAppid 站点ID
     * @apiParam (请求体) {String} title 订单标题
     * @apiParam (请求体) {String} orderDesc 订单描述
     * @apiParam (请求体) {Number} realFee 实际金额
     * @apiParam (请求体) {Number} discount
     * @apiParam (请求体) {Number} totalFee 总金额
     * @apiParam (请求体) {String} channel 支付通道
     * @apiParam (请求体) {String} client 客户端
     * @apiParam (请求体) {String} remark 备注
     * @apiParam (请求体) {String} nonceStr 随机字符串
     * @apiParam (请求体) {String} timestamp 时间戳
     * @apiParam (请求体) {String} hash hash校验
     * @apiParamExample 请求体示例
     * {"realFee":8813.25,"tradeNo":814,"channel":"ZFB","discount":1034.58,"remark":"gTRqr","title":"9hDs1prF","nonceStr":"XSYv","totalFee":5133.02,"client":"H5","siteAppid":"YZl3l","orderDesc":"JE","hash":"SX","timestamp":"PC"}
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} message
     * @apiSuccess (响应结果) {Object} Data
     * @apiSuccess (响应结果) {String} Data.transactionId 第三方ID
     * @apiSuccess (响应结果) {String} Data.openNo 平台统一NO
     * @apiSuccess (响应结果) {Number} Data.tradeNo 站点内NO
     * @apiSuccess (响应结果) {String} Data.siteAppid 站点APPID
     * @apiSuccess (响应结果) {String} Data.title 订单标题
     * @apiSuccess (响应结果) {String} Data.orderDesc 订单描述
     * @apiSuccess (响应结果) {Number} Data.realFee 实际金额
     * @apiSuccess (响应结果) {Number} Data.discount
     * @apiSuccess (响应结果) {Number} Data.totalFee 总金额
     * @apiSuccess (响应结果) {String} Data.channel 支付通道
     * @apiSuccess (响应结果) {String} Data.client 支付通道
     * @apiSuccess (响应结果) {String} Data.urlQrcode
     * @apiSuccess (响应结果) {String} Data.url
     * @apiSuccess (响应结果) {String} Data.remark 备注
     * @apiSuccess (响应结果) {String} Data.jsonData
     * @apiSuccess (响应结果) {Number} Data.createTime
     * @apiSuccess (响应结果) {Number} Data.expireTime 过期时间
     * @apiSuccess (响应结果) {Number} Data.payTime 支付时间
     * @apiSuccess (响应结果) {String} Data.orderStatus 订单状态
     * @apiSuccess (响应结果) {String} Data.notifyStatus 订单通知状态
     * @apiSuccess (响应结果) {String} Data.nonceStr 随机字符串
     * @apiSuccess (响应结果) {String} Data.timestamp 时间戳
     * @apiSuccess (响应结果) {String} Data.hash hash校验
     * @apiSuccess (响应结果) {String} errMessage
     * @apiSuccessExample 响应结果示例
     * {"code":2557,"Data":{"realFee":5141.34,"tradeNo":1039,"payTime":3712333992262,"urlQrcode":"x9tdhZStw","openNo":"SMWs4","channel":"WX","notifyStatus":"RETRY_NOTIFY","discount":3417.42,"orderStatus":"WAIT_PAY","remark":"XzkicYE","title":"9JZ8cfOA0","transactionId":"QS0","url":"lARWe","nonceStr":"NB7Xfw6FvG","jsonData":"MY","expireTime":2133515431621,"totalFee":6821.25,"createTime":2844574487255,"client":"APP","siteAppid":"3XMkkO","orderDesc":"PI3","hash":"ERqwAUKYvF","timestamp":"1hzCY"},"message":"1LfF","errMessage":"U14c"}
     */
    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody @Validated OrderDto.SiteCreateOrderRequest createOrderRequest) {
        Order order = orderDomainService.processOrder(convert.dto2bo(createOrderRequest));
        return ResponseEntity.success(order);
    }

    /**
     * @api {POST} /order/query queryOrder()
     * @apiVersion 1.0.0
     * @apiGroup OrderController
     * @apiName queryOrder()
     * @apiParam (请求体) {String} siteAppid 站点的appId
     * @apiParam (请求体) {Number} tradeNo 站点内NO
     * @apiParamExample 请求体示例
     * {"tradeNo":6112,"siteAppid":"kDmtUe"}
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} message
     * @apiSuccess (响应结果) {Object} Data
     * @apiSuccess (响应结果) {String} Data.transactionId 第三方ID
     * @apiSuccess (响应结果) {String} Data.openNo 平台统一NO
     * @apiSuccess (响应结果) {Number} Data.tradeNo 站点内NO
     * @apiSuccess (响应结果) {String} Data.siteAppid 站点APPID
     * @apiSuccess (响应结果) {String} Data.title 订单标题
     * @apiSuccess (响应结果) {String} Data.orderDesc 订单描述
     * @apiSuccess (响应结果) {Number} Data.realFee 实际金额
     * @apiSuccess (响应结果) {Number} Data.discount
     * @apiSuccess (响应结果) {Number} Data.totalFee 总金额
     * @apiSuccess (响应结果) {String} Data.channel 支付通道
     * @apiSuccess (响应结果) {String} Data.client 支付通道
     * @apiSuccess (响应结果) {String} Data.urlQrcode
     * @apiSuccess (响应结果) {String} Data.url
     * @apiSuccess (响应结果) {String} Data.remark 备注
     * @apiSuccess (响应结果) {String} Data.jsonData
     * @apiSuccess (响应结果) {Number} Data.createTime
     * @apiSuccess (响应结果) {Number} Data.expireTime 过期时间
     * @apiSuccess (响应结果) {Number} Data.payTime 支付时间
     * @apiSuccess (响应结果) {String} Data.orderStatus 订单状态
     * @apiSuccess (响应结果) {String} Data.notifyStatus 订单通知状态
     * @apiSuccess (响应结果) {String} Data.nonceStr 随机字符串
     * @apiSuccess (响应结果) {String} Data.timestamp 时间戳
     * @apiSuccess (响应结果) {String} Data.hash hash校验
     * @apiSuccess (响应结果) {String} errMessage
     * @apiSuccessExample 响应结果示例
     * {"code":6476,"Data":{"realFee":7400.47,"tradeNo":4712,"payTime":3335059826141,"urlQrcode":"dZ9a7c","openNo":"BnvBvQJ","channel":"ZFB","notifyStatus":"NOTIFY_SUCCESS","discount":1397.85,"orderStatus":"EXPIRED","remark":"82","title":"GC9f","transactionId":"oZXRtC","url":"MMxKIdC","nonceStr":"q","jsonData":"s","expireTime":3786378130613,"totalFee":2607.06,"createTime":2776955772943,"client":"H5","siteAppid":"K","orderDesc":"kq","hash":"tlpM","timestamp":"Gh258dk"},"message":"FDPSd8KJF","errMessage":"a4Zu2"}
     */
    @PostMapping("/query")
    public ResponseEntity<Order> queryOrder(@RequestBody @Validated OrderDto.SiteQueryOrderRequest siteQueryOrderRequest) {
        Order order = orderDomainService.queryOrder(siteQueryOrderRequest.getSiteAppid(), siteQueryOrderRequest.getSiteSecret(), siteQueryOrderRequest.getTradeNo());
        return ResponseEntity.success(order);
    }
}
