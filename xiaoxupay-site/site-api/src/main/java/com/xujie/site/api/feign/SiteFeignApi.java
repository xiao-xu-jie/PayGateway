package com.xujie.site.api.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.site.api.dto.SiteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xiaoxupay-site")
public interface SiteFeignApi {

    /**
     * 提供appid查询站点学习
     *
     * @param appid
     * @return
     */
    @GetMapping("/api/site/searchByAppid")
    public ResponseEntity<SiteDTO> searchByAppid(@RequestParam("appid") String appid);
}
