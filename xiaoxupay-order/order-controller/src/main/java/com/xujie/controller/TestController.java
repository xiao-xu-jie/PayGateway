package com.xujie.controller;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<?> hello(@RequestParam(value = "id",required = true) String id){
        ResponseEntity<?> success = ResponseEntity.success(new Date());
        log.info("id：{}",id);
        if(id.equals("1")) {
            throw new CustomException("error");
        }
        return success;
    }
}
