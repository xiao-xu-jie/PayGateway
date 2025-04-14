package com.xujie.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xujie
 * @since 2024/9/15 10:14
 * &#064;Description:  异常基类
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;
}
