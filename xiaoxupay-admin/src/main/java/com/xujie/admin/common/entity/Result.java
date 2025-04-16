package com.xujie.admin.common.entity;


import com.xujie.admin.common.enums.BaseResultEnum;
import com.xujie.common.exception.BaseException;
import lombok.Data;

/**
 * @Author: Xujie
 * @Date: 2024/7/15 21:51
 * @Description:
 **/
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private Boolean success;
    private String detail;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(BaseResultEnum.SUCCESS.getCode());
        result.setMessage(BaseResultEnum.SUCCESS.getMessage());
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> okMessage(String message) {
        Result<T> result = new Result<>();
        result.setCode(BaseResultEnum.SUCCESS.getCode());
        result.setMessage(message);
        result.setSuccess(true);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> fail(String message, String detail) {
        Result<T> result = new Result<>();
        result.setCode(BaseResultEnum.FAIL.getCode());
        result.setMessage(message);
        result.setSuccess(false);
        result.setDetail(detail);
        return result;
    }

    public static <T> Result<T> fail(BaseException baseException, String detail) {
        Result<T> result = new Result<>();
        result.setCode(baseException.getCode());
        result.setMessage(baseException.getMessage());
        result.setSuccess(false);
        result.setDetail(detail);
        return result;
    }

}
