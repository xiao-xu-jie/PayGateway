package com.xujie.admin.infra.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.admin.infra.DO.SiteOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SiteOrder)表数据库访问层
 *
 * @author xujie
 * @since 2025-04-17 12:04:57
 */
public interface SiteOrderMapper extends BaseMapper

        <SiteOrder> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SiteOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SiteOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SiteOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SiteOrder> entities);

}

