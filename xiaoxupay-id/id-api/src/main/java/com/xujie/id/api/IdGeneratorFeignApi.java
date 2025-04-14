package com.xujie.id.api;

import com.xujie.common.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xiaoxupay-id-generator")
public interface IdGeneratorFeignApi {

    @GetMapping("/id/getId")
    ResponseEntity<Long> getId(@RequestParam(name = "name") String name);

}
