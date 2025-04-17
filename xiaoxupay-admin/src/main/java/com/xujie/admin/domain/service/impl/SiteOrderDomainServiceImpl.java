package com.xujie.admin.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.domain.BO.SiteOrderBO;
import com.xujie.admin.domain.service.SiteOrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (SiteOrder)表服务实现类
 *
 * @author xujie
 * @since 2025-04-17 12:04:58
 */
@Slf4j
@Service
public class SiteOrderDomainServiceImpl implements SiteOrderDomainService {

    @Override
    public void add(SiteOrderBO siteOrderBO) {

    }

    @Override
    public Page<SiteOrderBO> getPageList(SiteOrderBO siteOrderBO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public void update(SiteOrderBO siteOrderBO) {

    }
}

