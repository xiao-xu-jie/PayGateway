package com.xujie.admin.infra.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Xujie
 * @Date: 2024/7/14 19:50
 * @Description:
 **/

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        log.info("开始插入填充...");
        this.strictInsertFill(metaObject, "createTime", Date.class, DateTime.now().toDate());
        this.strictUpdateFill(metaObject, "updateTime", Date.class, DateTime.now().toDate());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        log.info("开始更新填充...");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, DateTime.now().toDate());
    }
}
