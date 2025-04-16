package com.xujie.admin.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.admin.infra.DO.SysOperLog;
import org.apache.ibatis.annotations.Param;

public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    int updateById(@Param("updated")SysOperLog updated,@Param("id")Long id);


}
