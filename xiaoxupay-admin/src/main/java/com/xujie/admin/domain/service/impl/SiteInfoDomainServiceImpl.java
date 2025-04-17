package com.xujie.admin.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.domain.BO.SiteInfoBO;
import com.xujie.admin.domain.convert.SiteInfoConvert;
import com.xujie.admin.domain.service.SiteInfoDomainService;
import com.xujie.admin.infra.DO.SiteInfo;
import com.xujie.admin.infra.service.SiteInfoService;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.exception.CustomException;
import com.xujie.site.api.dto.SiteDTO;
import com.xujie.site.api.feign.SiteFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * (SiteInfo)表服务实现类
 *
 * @author xujie
 * @since 2025-04-17 12:04:54
 */
@Slf4j
@Service
public class SiteInfoDomainServiceImpl implements SiteInfoDomainService {

    @Resource
    private SiteInfoService siteInfoService;

    @Resource
    private SiteInfoConvert siteInfoConvert;

    @Resource
    private SiteFeignApi siteFeignApi;

    @Override
    public void add(SiteInfoBO siteInfoBO) {
        SiteDTO siteDTO = new SiteDTO();
        BeanUtils.copyProperties(siteInfoBO, siteDTO);
        ResponseEntity<?> responseEntity = siteFeignApi.addSite(siteDTO);
        if (!responseEntity.isSuccess()) {
            throw new CustomException(responseEntity.getMessage());
        }
    }

    @Override
    public Page<SiteInfoBO> getPageList(SiteInfoBO siteInfoBO, Integer pageNum, Integer pageSize) {
        Page<SiteInfo> pageList = siteInfoService.getPageList(siteInfoConvert.convertBO2DO(siteInfoBO), pageNum, pageSize);
        return siteInfoConvert.convertPageDO2BO(pageList);
    }

    @Override
    public void delete(Long[] ids) {
        siteInfoService.deleteBatch(ids);
    }

    @Override
    public void update(SiteInfoBO siteInfoBO) {
        SiteInfo siteInfo = siteInfoConvert.convertBO2DO(siteInfoBO);
        siteInfoService.updateOne(siteInfo.getId(), siteInfo);
    }
}

