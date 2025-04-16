package com.xujie.admin.infra.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.infra.DO.SysRoleRouter;
import com.xujie.admin.infra.DO.SysRouters;
import com.xujie.admin.infra.mapper.SysRoutersMapper;
import com.xujie.admin.infra.service.RoleRouterService;
import com.xujie.admin.infra.service.RouterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 路由服务实现类
 *
 * @author Xujie
 * @since 2024/9/17 22:27
 **/

@Slf4j
@Service
public class RouterServiceImpl implements RouterService {
    @Resource
    private SysRoutersMapper baseMapper;
    @Resource
    private RoleRouterService roleRouterService;


    /**
     * 获取路由列表
     *
     * @return 路由列表
     */
    @Override
    public List<SysRouters> getRouters() {
        List<SysRouters> sysRouters = baseMapper.selectList(null);
        List<String> roleList = StpUtil.getRoleList();
        List<SysRoleRouter> roleRouterByRoleId = roleRouterService.getRoleRouterByRoleCode(roleList);
        List<Long> ids = roleRouterByRoleId.stream().map(SysRoleRouter::getRouterId).toList();
        return sysRouters.stream().filter(item -> ids.contains(item.getId())).toList();
    }

    /**
     * 获取所有路由列表
     *
     * @return 路由列表
     */
    public List<SysRouters> getAllRouters() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<SysRouters> getAllTopRouters() {
        LambdaQueryWrapper<SysRouters> routersLambdaQueryWrapper = Wrappers.lambdaQuery();
        routersLambdaQueryWrapper.eq(SysRouters::getParentId, 0);
        List<SysRouters> sysRouters = baseMapper.selectList(routersLambdaQueryWrapper);
        return sysRouters;
    }

    @Override
    public Long addOne(SysRouters router) {
        try {
            baseMapper.insert(router);
        } catch (Exception e) {
            log.error("添加路由失败：{}", e.getMessage());
            return null;
        }
        return router.getId();
    }

    @Override
    public SysRouters getOneByEntity(SysRouters baseDO) {
        return getListByEntity(baseDO).stream().findAny().orElse(null);
    }

    @Override
    public List<SysRouters> getListByEntity(SysRouters baseDO) {
        LambdaQueryWrapper<SysRouters> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(baseDO.getId()!=null,SysRouters::getId, baseDO.getId());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getName()),SysRouters::getName, baseDO.getName());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getTitle()),SysRouters::getTitle, baseDO.getTitle());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getPath()),SysRouters::getPath, baseDO.getPath());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getComponent()),SysRouters::getComponent, baseDO.getComponent());
        queryWrapper.eq(baseDO.getParentId()!=null,SysRouters::getParentId, baseDO.getParentId());

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean deleteOne(Long id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("删除路由失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean updateOne(Long id, SysRouters router) {
        try {
            router.setId(id);
            baseMapper.updateById(router);
        } catch (Exception e) {
            log.error("更新路由失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Page<SysRouters> getPageList(SysRouters baseDO, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysRouters> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(baseDO.getId()!=null,SysRouters::getId, baseDO.getId());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getName()),SysRouters::getName, baseDO.getName());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getTitle()),SysRouters::getTitle, baseDO.getTitle());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getPath()),SysRouters::getPath, baseDO.getPath());
        queryWrapper.eq(StringUtils.isNotBlank(baseDO.getComponent()),SysRouters::getComponent, baseDO.getComponent());
        queryWrapper.eq(SysRouters::getParentId,0);
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        return baseMapper.deleteByIds(List.of(ids)) > 0;
    }
}
