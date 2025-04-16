package com.xujie.admin.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xujie.admin.DTO.res.Meta;
import com.xujie.admin.common.utils.ConditionCheck;
import com.xujie.admin.common.utils.RouterUtil;
import com.xujie.admin.domain.BO.RoleBO;
import com.xujie.admin.domain.BO.RoutersBO;
import com.xujie.admin.domain.convert.RoleConvert;
import com.xujie.admin.domain.convert.RoutersConvert;
import com.xujie.admin.domain.service.RoleDomainService;
import com.xujie.admin.infra.DO.SysRole;
import com.xujie.admin.infra.DO.SysRoleRouter;
import com.xujie.admin.infra.DO.SysRouters;
import com.xujie.admin.infra.service.RoleRouterService;
import com.xujie.admin.infra.service.RoleService;
import com.xujie.admin.infra.service.RouterService;
import com.xujie.common.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/20 21:48
 **/

@Slf4j
@Service
public class RoleDomainServiceImpl implements RoleDomainService {
    @Resource
    private RoleService baseService;
    @Resource
    private RoleRouterService roleRouterService;
    @Resource
    private RoleConvert roleConvert;
    @Resource
    private RouterService routerService;
    @Resource
    private RoutersConvert routersConvert;


    @Override
    public List<RoleBO> getAllRoleList() {
        List<SysRole> allRoleByEntity = baseService.getListByEntity(SysRole.builder().build());
        List<RoleBO> list = roleConvert.convertListDO2BO(allRoleByEntity);
        return list;
    }

    @Override
    public List<Long> getRoutersByRoleId(Long roleId) {
        return roleRouterService.getRoleRouterByRoleId(Lists.newArrayList(roleId)).stream()
                .map(SysRoleRouter::getRouterId)
                .toList();
    }

    @Override
    public List<RoutersBO> getAllRouters() {
        List<SysRouters> allRouters = routerService.getAllRouters();
        List<RoutersBO> routerBOS = routersConvert.convertListDO2BO(allRouters);
        routerBOS.forEach(item -> {
            item.setMeta(new Meta(item.getTitle(), item.getIcon(), item.getRank(), item.getShowlink()));
        });
        // 递归设置子节点
        routerBOS = RouterUtil.setChildren(routerBOS);
        return routerBOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleBO roleBO) {
        Long id = baseService.addOne(roleConvert.convertBO2DO(roleBO));
        ConditionCheck.nullAndThrow(id, new CustomException("添加角色失败"));
        if (roleBO.getRouters() == null || roleBO.getRouters().isEmpty()) {
            return;
        }
        roleRouterService.addRoleRouter(roleBO.getRouters(), id);
    }

    @Override
    public Page<RoleBO> getPageList(RoleBO roleBO, Integer pageNum, Integer pageSize) {
        Page<SysRole> rolePageList = baseService.getPageList(roleConvert.convertBO2DO(roleBO), pageNum, pageSize);
        return roleConvert.convertPageDO2BO(rolePageList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        ConditionCheck.falseAndThrow(roleRouterService.getRoleRouterByRoleId(Lists.newArrayList(ids)).isEmpty()
                , new CustomException("删除角色失败，角色下存在路由"));
        boolean flag = baseService.deleteBatch(ids);
        ConditionCheck.falseAndThrow(flag, new CustomException("删除角色失败"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleBO roleBO) {
        baseService.updateOne(roleBO.getId(), roleConvert.convertBO2DO(roleBO));
        roleRouterService.deleteRoleRouterByRoleId(roleBO.getId());
        if (roleBO.getRouters() == null || roleBO.getRouters().isEmpty()) {
            return;
        }
        roleRouterService.addRoleRouter(roleBO.getRouters(), roleBO.getId());
    }
}
