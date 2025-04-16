package com.xujie.admin.DTO.res;

import com.google.common.collect.Lists;
import com.xujie.admin.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/17 22:47
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouterResDTO extends BaseDTO {
    /**
     * 子节点
     */
    List<RouterResDTO> children;
    List<String> roles = Lists.newArrayList("admin");
    /**
     * 路由ID
     */

    private Long id;
    /**
     * 名称
     */

    private String name;
    private Meta meta;
    /**
     * 路径
     */

    private String path;
    /**
     * 重定向
     */

    private String redirect;
    /**
     * 组件
     */

    private String component;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 更新时间
     */

    private Date updateTime;
}

