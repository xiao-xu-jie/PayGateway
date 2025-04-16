package com.xujie.admin.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.admin.common.utils.ConditionCheck;
import com.xujie.admin.domain.BO.OperLogBO;
import com.xujie.admin.domain.convert.OperLogConvert;
import com.xujie.admin.domain.service.OperLogDomainService;
import com.xujie.admin.infra.DO.SysOperLog;
import com.xujie.admin.infra.service.OperLogService;
import com.xujie.common.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (OperLog)表服务实现类
 *
 * @author xujie
 * @since 2024-09-25 16:00:23
 */
@Slf4j
@Service
public class OperLogDomainServiceImpl implements OperLogDomainService {
    @Resource
    private OperLogService baseService;
    @Resource
    private OperLogConvert baseConvert;

    @Override
    public void add(OperLogBO operLogBO) {

        Long l = baseService.addOne(baseConvert.convertBO2DO(operLogBO));
        ConditionCheck.nullAndThrow(l, new CustomException("添加操作日志失败"));
    }

    @Override
    public Page<OperLogBO> getPageList(OperLogBO operLogBO, Integer pageNum, Integer pageSize) {
        SysOperLog sysOperLog = baseConvert.convertBO2DO(operLogBO);
        return baseConvert.convertPageDO2BO(baseService.getPageList(sysOperLog, pageNum, pageSize));
    }

    @Override
    public void delete(Long[] ids) {
        boolean b = baseService.deleteBatch(ids);
        ConditionCheck.falseAndThrow(b, new CustomException("删除操作日志失败"));
    }

    @Override
    public void update(OperLogBO operLogBO) {
        boolean b = baseService.updateOne(operLogBO.getId(), baseConvert.convertBO2DO(operLogBO));
        ConditionCheck.falseAndThrow(b, new CustomException("更新操作日志失败"));
    }
}

