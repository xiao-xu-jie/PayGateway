package com.xujie.payGateway.common.entity;

import com.xujie.payGateway.common.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private Integer code;
    private String message;
    private T Data;
    private String errMessage;

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> responseEntity = buildByEnum(ResponseCodeEnum.SUCCESS);
        responseEntity.setData(data);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> success(T data, String message) {
        ResponseEntity<T> responseEntity = buildByEnum(ResponseCodeEnum.SUCCESS);
        responseEntity.setData(data);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> error(String msg) {
        ResponseEntity<T> responseEntity = buildByEnum(ResponseCodeEnum.ERROR);
        responseEntity.setErrMessage(msg);
        return responseEntity;
    }

    public boolean isSuccess() {
        return Objects.equals(ResponseCodeEnum.SUCCESS.getCode(), this.code);
    }

    private static <T> ResponseEntity<T> buildByEnum(ResponseCodeEnum responseCodeEnum) {
        return ResponseEntity.<T>builder()
                .code(responseCodeEnum.getCode())
                .message(responseCodeEnum.getMessage())
                .build();
    }

}
