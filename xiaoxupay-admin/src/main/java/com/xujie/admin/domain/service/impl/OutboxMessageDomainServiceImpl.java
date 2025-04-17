package com.xujie.admin.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.domain.BO.OutboxMessageBO;
import com.xujie.admin.domain.service.OutboxMessageDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 事务性消息发送箱表(OutboxMessage)表服务实现类
 *
 * @author xujie
 * @since 2025-04-17 12:04:56
 */
@Slf4j
@Service
public class OutboxMessageDomainServiceImpl implements OutboxMessageDomainService {

    @Override
    public void add(OutboxMessageBO outboxMessageBO) {

    }

    @Override
    public Page<OutboxMessageBO> getPageList(OutboxMessageBO outboxMessageBO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public void update(OutboxMessageBO outboxMessageBO) {

    }
}

