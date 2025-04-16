package com.xujie.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.DTO.req.RoutersAddReqDTO;
import com.xujie.admin.DTO.req.RoutersQueryReqDTO;
import com.xujie.admin.DTO.res.RoutersQueryResDTO;
import com.xujie.admin.common.base.controller.BaseController;
import com.xujie.admin.common.entity.Result;
import com.xujie.admin.domain.BO.RoutersBO;
import com.xujie.admin.domain.convert.RoutersConvert;
import com.xujie.admin.domain.service.RoutersDomainService;
import com.xujie.admin.infra.DO.SysRouters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (SysRouters)表控制层
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@RestController
@RequestMapping("/routers")
public class RoutersController extends BaseController<RoutersQueryReqDTO, RoutersQueryResDTO, RoutersAddReqDTO, RoutersBO, SysRouters, RoutersConvert, RoutersDomainService> {

    public RoutersController(RoutersConvert baseConvert, RoutersDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }

    /**
     * 分页查询
     *
     * @param routersQueryReqDTO 查询条件
     * @param pageNum            页码
     * @param pageSize           每页数量
     * @return 分页结果
     */
    @Override
    public Result<Page<RoutersQueryResDTO>> selectPage(RoutersQueryReqDTO routersQueryReqDTO, Integer pageNum, Integer pageSize) {
        Result<Page<RoutersQueryResDTO>> pageResult = super.selectPage(routersQueryReqDTO, pageNum, pageSize);
        pageResult.getData().getRecords().forEach(item ->{
            if(item.getParentId() == 0) {
                item.setHasChildren(true);
            }
        });
        return pageResult;
    }

    /**
     * 获取路由树
     * @param parentId 父节点id
     * @return 路由树
     */
    @GetMapping("/getRoutersChildren")
    public Result<List<RoutersQueryResDTO>> getChildren(@RequestParam("id") Long parentId) {
        List<RoutersBO> list = baseDomainService.getChildren(parentId);
        return Result.ok(baseConvert.convertListBO2DTO(list));
    }

    @GetMapping("/getTopRouters")
    public Result<List<RoutersQueryResDTO>> getTopRouters() {
        List<RoutersBO> list = baseDomainService.getTopRouters();
        return Result.ok(baseConvert.convertListBO2DTO(list));
    }
}

