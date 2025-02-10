package com.xujie.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.infra.mapper.SiteInfoMapper;
import com.xujie.infra.entity.SiteInfo;
import com.xujie.infra.service.SiteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SiteServiceImpl implements SiteService {
    @Resource
    private SiteInfoMapper siteInfoMapper;
    @Override
    public void insertOneSite(SiteInfo siteInfo) {
        siteInfoMapper.insertOrUpdate(siteInfo);
    }

    @Override
    public void updateOneSiteBySiteId(Long siteAppId, SiteInfo siteInfo) {
        LambdaQueryWrapper<SiteInfo> eq = Wrappers.<SiteInfo>lambdaQuery()
                .eq(ObjectUtils.isNotEmpty(siteAppId), SiteInfo::getSiteAppid, siteAppId);
        siteInfoMapper.update(siteInfo,eq);
    }

    @Override
    public void deleteOneSiteBySiteId(String siteAppId) {
        LambdaQueryWrapper<SiteInfo> eq = Wrappers.<SiteInfo>lambdaQuery()
                .eq(ObjectUtils.isNotEmpty(siteAppId), SiteInfo::getSiteAppid, siteAppId);
        siteInfoMapper.delete(eq);
    }

    @Override
    public SiteInfo selectOneByAppid(String appid) {
        SiteInfo build = SiteInfo.builder().siteAppid(appid).build();
        return selectListByEntity(build).stream().findAny().orElse(null);
    }

    @Override
    public List<SiteInfo> selectListByEntity(SiteInfo siteInfo) {
        return siteInfoMapper.selectByAll(siteInfo);
    }
}
