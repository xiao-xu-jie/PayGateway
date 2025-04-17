package com.xujie.site.api.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.groups.CreateGroup;
import com.xujie.site.api.dto.SiteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xiaoxupay-site")
public interface SiteFeignApi {

    /**
     * 提供appid查询站点信息
     *
     * @param appid
     * @return
     */
    @GetMapping("/api/site/searchByAppid")
    public ResponseEntity<SiteDTO> searchByAppid(@RequestParam("appid") String appid);

    /**
     * 站点添加
     *
     * @param createDTO
     * @return
     */
    @PostMapping("/api/site/addSite")
    public ResponseEntity<?> addSite(@RequestBody @Validated(CreateGroup.class) SiteDTO createDTO);
}
