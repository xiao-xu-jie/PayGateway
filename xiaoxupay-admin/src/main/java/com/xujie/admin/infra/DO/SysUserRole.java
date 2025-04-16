package com.xujie.admin.infra.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_role")
public class SysUserRole {
    public static final String COL_USER_ID = "user_id";
    public static final String COL_ROLE_ID = "role_id";
    private Long userId;
    private Long roleId;
}