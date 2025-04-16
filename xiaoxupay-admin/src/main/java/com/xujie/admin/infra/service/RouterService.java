package com.xujie.admin.infra.service;

import com.xujie.admin.common.base.service.BaseService;
import com.xujie.admin.infra.DO.SysRouters;

import java.util.List;

/**
 * 路由服务接口
 */
public interface RouterService extends BaseService<SysRouters> {

    List<SysRouters> getRouters();
    List<SysRouters> getAllTopRouters();
    List<SysRouters> getAllRouters();
}
