package com.xujie.domain.handler.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.xujie.application.redis.utils.RedisUtils;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.AbstractOrderHandler;
import com.xujie.dto.SiteDTO;
import com.xujie.feign.SiteFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 站点创建订单请求参数校验
 */
@Slf4j
public class SiteInfoCheckHandler extends AbstractOrderHandler {

    private final SiteFeignClient siteFeignClient;

    /**
     * 去掉空值
     * 参数名称字典升序
     * 末尾加上secret
     * md5校验hash
     */
    @Override
    protected void doHandle(Order order) {

        // 获取站点信息
        SiteDTO siteDTO = getSiteInfoByAppid(order.getSiteAppid());
        String siteSecret = siteDTO.getSiteSecret();
        String reqHash = order.getHash();
        Map<String, Object> map = BeanUtil.beanToMap(order);
        List<Map.Entry<String, Object>> list = map.entrySet().stream()
                .filter(o -> ObjectUtils.isNotEmpty(o.getValue()))
                .filter(o -> StringUtils.compare(o.getKey(), "hash") != 0)
                .sorted(Map.Entry.comparingByKey())
                .toList();
        log.info("[SiteInfoCheckHandler]排序后的参数：{}", list);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : list) {
            sb.append(entry.getKey()).append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(siteSecret);
        log.info("[SiteInfoCheckHandler]拼接后的字符串：{}", sb);
        String hash = SecureUtil.md5(sb.toString());
        log.info("[SiteInfoCheckHandler]hash对比：请求hash{}，计算{}", reqHash, hash);
        if (StringUtils.compare(hash, reqHash) != 0) {
            throw new CustomException("auth 失败");
        }

    }

    /**
     * 通过siteAppid获取site信息
     * 如果redis中有缓存，从缓存拿
     * 没有就掉接口，查询后放置缓存
     *
     * @param siteAppid
     * @return
     */
    private SiteDTO getSiteInfoByAppid(String siteAppid) {
        Optional<SiteDTO> cacheObject = RedisUtils.getCacheObject("order:" + siteAppid, SiteDTO.class);
        return cacheObject.orElseGet(() -> {
            SiteDTO data;
            // TODO 可以换成分布式锁
            synchronized (this) {
                Optional<SiteDTO> siteDTO = RedisUtils.getCacheObject("order:" + siteAppid, SiteDTO.class);
                if (siteDTO.isPresent()) {
                    return siteDTO.get();
                }
                ResponseEntity<SiteDTO> siteDTOResponseEntity = siteFeignClient.searchByAppid(siteAppid);
                // 站点信息
                log.info("[SiteInfoCheckHandler]站点信息：{}", siteDTOResponseEntity);
                if (siteDTOResponseEntity.getCode() != 200) {
                    throw new CustomException("站点信息有误！");
                }
                data = siteDTOResponseEntity.getData();
                RedisUtils.setCacheObject("order:" + siteAppid, JSONUtil.toJsonStr(data));
            }
            return data;
        });
    }

    public SiteInfoCheckHandler(SiteFeignClient siteFeignClient) {
        this.siteFeignClient = siteFeignClient;
    }
}
