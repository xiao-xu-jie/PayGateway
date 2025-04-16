package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色路由关联表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_router")
public class SysRoleRouter {
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 路由ID
     */
    @TableField(value = "router_id")
    private Long routerId;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROUTER_ID = "router_id";
}