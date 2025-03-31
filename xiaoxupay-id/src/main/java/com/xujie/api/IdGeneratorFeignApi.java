package com.xujie.api;

import com.xujie.common.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${spring.application.name}")
public interface IdGeneratorFeignApi {

    @GetMapping("/id/getId")
    ResponseEntity<Long> getSeqId(@RequestParam(name = "name") String name);

}
