package com.xujie.order.domain.handler.concret;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.enums.BizResponseEnum;
import com.xujie.common.exception.CustomException;
import com.xujie.order.domain.entity.Order;
import com.xujie.order.domain.handler.AbstractOrderHandler;
import com.xujie.site.api.dto.SiteDTO;
import com.xujie.site.api.feign.SiteFeignApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRespEncryptHandler extends AbstractOrderHandler {
    private final SiteFeignApi siteFeignApi;

    @Override
    protected void doHandle(Order order) {
        if (ObjectUtils.anyNull(order, order.getSiteAppid())) {
            log.error("订单信息缺失！！");
            throw new CustomException(BizResponseEnum.UN_EXCEPT_ERROR);
        }
        ResponseEntity<SiteDTO> siteResponse = siteFeignApi.searchByAppid(order.getSiteAppid());
        if (!siteResponse.isSuccess()) {
            log.error("调用站点api异常");
            throw new CustomException(BizResponseEnum.UN_EXCEPT_ERROR);
        }
        SiteDTO siteDTO = siteResponse.getData();
        long currentTimeMillis = System.currentTimeMillis();
        order.setTimestamp(String.valueOf(currentTimeMillis));
        order.setNonceStr(RandomUtil.randomString(16));
        order.setHash(generateOrderHash(order, siteDTO));
    }

    private String generateOrderHash(Order order, SiteDTO siteDTO) {
        Map<String, Object> map = BeanUtil.beanToMap(order);
        List<Map.Entry<String, Object>> list = map.entrySet().stream()
                .filter(o -> ObjectUtils.isNotEmpty(o.getValue()))
                .filter(o -> StringUtils.compare(o.getKey(), "hash") != 0)
                .sorted(Map.Entry.comparingByKey())
                .toList();
        log.info("[OrderRespEncryptHandler]排序后的参数：{}", list);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : list) {
            sb.append(entry.getKey()).append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(siteDTO.getSiteSecret());
        log.info("[OrderRespEncryptHandler]拼接后的字符串：{}", sb);
        String hash = SecureUtil.md5(sb.toString());
        log.info("订单创建返回后的hash：{}", hash);
        return hash;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
