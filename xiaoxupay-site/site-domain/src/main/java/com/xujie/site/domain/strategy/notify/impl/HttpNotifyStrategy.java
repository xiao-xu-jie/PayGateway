package com.xujie.site.domain.strategy.notify.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xujie.common.exception.CustomException;
import com.xujie.site.domain.entity.NotifyRequest;
import com.xujie.site.domain.strategy.notify.NotifyStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Slf4j
@Component(value = "http-notify")
public class HttpNotifyStrategy extends NotifyStrategy {

    @Override
    protected String notify(NotifyRequest request) {
        Map<String, Object> map = BeanUtil.beanToMap(request, "openNo");
        map.put("nonceStr", RandomUtil.randomString(16));
        map.put("timestamp", System.currentTimeMillis());
        List<Map.Entry<String, Object>> list = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Object> entry : list) {
            str.append(entry.getKey()).append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        str.deleteCharAt(str.length() - 1);
        str.append(request.getSiteSecret());
        String hash = SecureUtil.md5(str.toString());
        map.put("hash", hash);
        try {
            new URL(request.getNotifyUrl());
        } catch (MalformedURLException e) {
            throw new CustomException("回调通知URL格式错误");
        }
        try (HttpResponse response = HttpUtil.createPost(request.getNotifyUrl())
                .body(JSONUtil.toJsonStr(map))
                .timeout(1000 * 30)
                .contentType(ContentType.JSON.getValue())
                .keepAlive(true)
                .execute()) {
            if (StringUtils.compare(response.body(), "success") != 0) {
                throw new CustomException("返回值异常");
            }
            return response.body();
        } catch (Exception e) {
            log.error("通知失败", e);
        }

        return "error";
    }
}
