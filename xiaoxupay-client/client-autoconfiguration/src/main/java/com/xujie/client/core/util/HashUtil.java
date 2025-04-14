package com.xujie.client.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.xujie.client.dto.XOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class HashUtil {

    public static String hash(XOrderDto.XOrderCreateResponse order, String siteAppSecret) {
        Map<String, Object> map = BeanUtil.beanToMap(order);
        List<Map.Entry<String, Object>> list = map.entrySet().stream()
                .filter(o -> ObjectUtils.isNotEmpty(o.getValue()))
                .filter(o -> StringUtils.compare(o.getKey(), "hash") != 0)
                .sorted(Map.Entry.comparingByKey())
                .toList();
        log.info("[HashUtil]排序后的参数：{}", list);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : list) {
            sb.append(entry.getKey()).append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(siteAppSecret);
        log.info("[HashUtil]拼接后的字符串：{}", sb);
        String hash = SecureUtil.md5(sb.toString());
        log.info("[HashUtil]hash={}", hash);
        return hash;
    }
}
