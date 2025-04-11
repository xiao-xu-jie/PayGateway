package com.xujie.pay.common.utils;

import cn.hutool.crypto.SecureUtil;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class HashUtil {
    public static String hash(Set<Map.Entry<String, Object>> entrySet, String appSecret) {
        StringBuilder sb = new StringBuilder();
        entrySet.stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(item -> {
                    if (!"hash".equals(item.getKey())) {
                        sb.append(item.getKey())
                                .append("=")
                                .append(item.getValue().toString())
                                .append("&");
                    }
                });

        // 删除最后一个 "&"
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        // 拼接 appSecret 并计算 MD5
        sb.append(appSecret);
        return SecureUtil.md5(sb.toString());
    }
}
