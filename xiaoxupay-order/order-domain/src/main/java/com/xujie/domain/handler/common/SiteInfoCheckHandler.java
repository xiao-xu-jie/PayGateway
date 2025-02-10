package com.xujie.domain.handler.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.OrderHandler;
import com.xujie.dto.SiteDTO;
import com.xujie.feign.SiteFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class SiteInfoCheckHandler extends OrderHandler {


    private final SiteFeignClient siteFeignClient;
    @Override
    protected void doHandle(Order order) {
        ResponseEntity<SiteDTO> siteDTOResponseEntity = siteFeignClient.searchByAppid(order.getSiteAppid());
        if(siteDTOResponseEntity.getCode() != 200) {
            throw new CustomException("站点信息有误！");
        }
        // 站点信息
        log.info("[SiteInfoCheckHandler]站点信息：{}",siteDTOResponseEntity);
        // 获取appSecret
        SiteDTO siteDTO = siteDTOResponseEntity.getData();
        String siteSecret = siteDTO.getSiteSecret();
        String reqHash = order.getHash();
        Map<String, Object> map = BeanUtil.beanToMap(order);
        List<Map.Entry<String, Object>> list = map.entrySet().stream()
                .filter(o -> ObjectUtils.isNotEmpty(o.getValue()))
                .filter(o -> StringUtils.compare(o.getKey(),"hash") != 0)
                .sorted(Map.Entry.comparingByKey())
                .toList();
        log.info("[SiteInfoCheckHandler]排序后的参数：{}",list);
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> entry:list) {
            sb.append(entry.getKey()).append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(siteDTO.getSiteSecret());
        log.info("[SiteInfoCheckHandler]拼接后的字符串：{}",sb);
        String hash = SecureUtil.md5(sb.toString());
        log.info("[SiteInfoCheckHandler]hash对比：请求hash{}，计算{}",reqHash,hash);
        if(StringUtils.compare(hash,reqHash) != 0) {
            throw new CustomException("auth 失败");
        }

    }
    public SiteInfoCheckHandler(SiteFeignClient siteFeignClient) {
        this.siteFeignClient = siteFeignClient;
    }
}
