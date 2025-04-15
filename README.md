<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">小徐支付网关v1.0</h1>
<h4 align="center">基于 Vue/Element UI 和 Spring Cloud & Alibaba 前后端分离的分布式微服务架构</h4>

## 项目介绍

小徐支付网关是一款Java开发的支付网关，适用于Developer开发个人应用时快速接入支付功能，支付支持微信、支付宝、QQ等，支持对接官方以及第三方平台（虎皮椒支付、彩虹易支付）；
开箱即用，只需在配置中心配置即可使用，配置一次即可完成多个网站接入支付功能。

## 流程说明
### 订单创建
![image.png](https://raw.gitcode.com/user-images/assets/4665047/ae02c92d-11f7-47c9-a00a-56c3c4e99f15/image.png 'image.png')

### 订单回调
![image.png](https://raw.gitcode.com/user-images/assets/4665047/223fa4a5-b4b8-4e25-ad6a-96b8247f0c9b/image.png 'image.png')

## 示例代码
1、在引入client依赖的业务端添加如下配置
```yml
pay:
  client:
    enable: true
    site:
      site-app-id: ZX3KbRpny05LndAk
      site-app-secret: xujie520
      server-url: http://localhost:8013
```
2、监听支付成功事件

```java
	@EventListener
    public void orderPaidHandle(XOrderPaidEvent xOrderPaidEvent) {
        String openNo = xOrderPaidEvent.getOpenNo();
        // 业务逻辑
    }
```
两步即可将项目引入支付功能
