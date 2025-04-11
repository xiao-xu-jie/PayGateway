package com.xujie.common.exception;

import com.xujie.payGateway.common.enums.ResponseCodeEnum;
import com.xujie.payGateway.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** &#064;Author: Xujie @Date: 2024/7/15 21:58 @Description: 业务异常 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends BaseException {

  private Integer code;
  private String message;

  public CustomException(ResponseCodeEnum baseResultEnum) {
    this.code = baseResultEnum.getCode();
    this.message = baseResultEnum.getMessage();
  }

  public CustomException(String s) {
    this.message = s;
    this.code = ResponseCodeEnum.ERROR.getCode();
  }
}
