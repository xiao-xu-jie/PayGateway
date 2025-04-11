package com.xujie.strategy.wx.wxpay.config;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.xujie.pay.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.io.InputStream;

/**
 * @author Xujie
 * @since 2025/2/24 21:47
 **/
@Configuration
@ConditionalOnProperty(prefix = "wx.pay", name = {"appId", "mchId", "merchantSerialNumber", "apiV3Key", "notifyUrl"})
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxPayConfig {
    public NotificationParser parser;
    @Value("${wx.pay.appId}")
    private String appId;
    @Value("${wx.pay.mchId}")
    private String merchantId;
    @Value("${wx.pay.keyPath}")
    private String privateKeyPath;
    @Value("${wx.pay.merchantSerialNumber}")
    private String merchantSerialNumber;
    @Value("${wx.pay.apiV3Key}")
    private String apiV3Key;
    @Value("${wx.pay.notifyUrl}")
    private String notifyUrl;

    @Bean
    public RSAAutoCertificateConfig config() {
        String privateKey = getPrivateKey();

        return new RSAAutoCertificateConfig.Builder()
                .merchantId(merchantId)
                .privateKey(privateKey)
                .merchantSerialNumber(merchantSerialNumber)
                .apiV3Key(apiV3Key)
                .build();
    }

    private String getPrivateKey() {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("wechat/apiclient_key.pem")) {
            if (resourceAsStream != null) {
                byte[] bytes = resourceAsStream.readAllBytes();
                return new String(bytes);
            } else {
                throw new CustomException("未配置微信支付钥密文件");
            }
        } catch (Exception e) {
            log.error("钥密读取异常：{}", e.getMessage(), e);
        }
        return null;
    }

    @Bean
    public JsapiServiceExtension jsapiServiceExtension(Config config) {
        return new JsapiServiceExtension.Builder()
                .config(config)
                .build();
    }

    @Bean
    public NativePayService nativePayService(Config config) {
        return new NativePayService.Builder()
                .config(config)
                .build();
    }

    //解密报文
    @Bean("notificationParser")
    public NotificationParser getNotificationConfig(RSAAutoCertificateConfig config) {
        if (ObjectUtils.isEmpty(config)) {
            String key = getPrivateKey();
            config = new RSAAutoCertificateConfig.Builder()
                    .merchantId(merchantId)
                    .privateKey(key)
                    .merchantSerialNumber(merchantSerialNumber)
                    .apiV3Key(apiV3Key)
                    .build();
        }
        if (ObjectUtils.isEmpty(parser)) {
            parser = new NotificationParser(config);
        }
        return parser;
    }
}
