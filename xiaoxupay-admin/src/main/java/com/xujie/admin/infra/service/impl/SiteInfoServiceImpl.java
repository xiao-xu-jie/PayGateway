package com.xujie.admin.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.common.utils.QueryWrapperUtil;
import com.xujie.admin.infra.DO.SiteInfo;
import com.xujie.admin.infra.mapper.SiteInfoMapper;
import com.xujie.admin.infra.service.SiteInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteInfoServiceImpl implements SiteInfoService {
    @Resource
    private SiteInfoMapper siteInfoMapper;

    @Override
    public Long addOne(SiteInfo baseDO) {
        return 0L;
    }

    @Override
    public SiteInfo getOneByEntity(SiteInfo baseDO) {
        return null;
    }

    @Override
    public List<SiteInfo> getListByEntity(SiteInfo baseDO) {
        QueryWrapper<SiteInfo> siteInfoQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
        return siteInfoMapper.selectList(siteInfoQueryWrapper);
    }

    @Override
    public boolean deleteOne(Long id) {
        return false;
    }

    @Override
    public boolean updateOne(Long id, SiteInfo baseDO) {
        return siteInfoMapper.update(baseDO, Wrappers.<SiteInfo>lambdaUpdate().eq(SiteInfo::getId, id)) > 0;
    }

    @Override
    public Page<SiteInfo> getPageList(SiteInfo baseDO, Integer pageNum, Integer pageSize) {
        QueryWrapper<SiteInfo> siteInfoQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
        return siteInfoMapper.selectPage(new Page<>(pageNum, pageSize), siteInfoQueryWrapper);
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        return false;
    }
}
