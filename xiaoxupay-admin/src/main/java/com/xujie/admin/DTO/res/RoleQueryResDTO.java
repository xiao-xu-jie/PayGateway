package com.xujie.admin.DTO.res;

import com.xujie.admin.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色查询DTO
 *
 * @author Xujie
 * @since 2024/9/20 21:55
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryResDTO extends BaseDTO {

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色code
     */
    private String code;


    private String name;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
