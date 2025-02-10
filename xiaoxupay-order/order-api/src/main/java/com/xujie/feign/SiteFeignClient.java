package com.xujie.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.dto.SiteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xiaoxupay-site",path = "site")
public interface SiteFeignClient {
    @GetMapping("/searchByAppid")
    public ResponseEntity<SiteDTO> searchByAppid(@RequestParam("appid") String appid);
}
