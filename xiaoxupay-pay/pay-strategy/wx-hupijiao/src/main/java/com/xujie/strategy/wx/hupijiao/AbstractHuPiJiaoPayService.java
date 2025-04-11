package com.xujie.strategy.wx.hupijiao;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.xujie.pay.common.exception.CustomException;
import com.xujie.strategy.PayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

@Slf4j
public abstract class AbstractHuPiJiaoPayService implements PayService {

    protected static JSONObject getJsonObject(String post) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(post);
        } catch (Exception e) {
            log.error("返回JSON序列化失败", e);
            throw new CustomException("平台返回JSON序列化失败");
        }
        return jsonObject;
    }


    protected String getHash(Map<String, Object> requestBody, String appSecret) {
        StringBuilder sb = new StringBuilder();
        requestBody.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(s -> sb.append(s).append("&"));
        sb.deleteCharAt(sb.length() - 1);
        sb.append(appSecret);
        return SecureUtil.md5(sb.toString());
    }

    protected String post(String url, Map<String, Object> body, WebClient webClient, Integer timeout) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        body.forEach((k, v) -> {
            data.add(k, v.toString());
        });
        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(data))
                .retrieve()
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(timeout));
    }

    /**
     * 获取精确到秒的时间戳   原理 获取毫秒时间戳，因为 1秒 = 100毫秒 去除后三位 就是秒的时间戳
     *
     * @return
     */
    protected static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.parseInt(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    protected String getNonceStr() {
        return RandomStringUtils.randomAlphabetic(32);
    }
}
