package com.xujie.admin.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.domain.BO.NotifyLogBO;
import com.xujie.admin.domain.service.NotifyLogDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (NotifyLog)表服务实现类
 *
 * @author xujie
 * @since 2025-04-17 12:04:55
 */
@Slf4j
@Service
public class NotifyLogDomainServiceImpl implements NotifyLogDomainService {

    @Override
    public void add(NotifyLogBO notifyLogBO) {

    }

    @Override
    public Page<NotifyLogBO> getPageList(NotifyLogBO notifyLogBO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public void update(NotifyLogBO notifyLogBO) {

    }
}

