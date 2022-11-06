package com.kaimingwan.core.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wanshao create time is 2021/4/17
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ResponseData<T> implements Serializable {

  private String code;
  private String msg;
  private T data;

  private String fileName;


  public ResponseData(String code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public ResponseData(String code, String msg, T data, String fileName) {
    this.code = code;
    this.msg = msg;
    this.data = data;
    this.fileName = fileName;
  }

  public ResponseData(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ResponseData(ResultEnum resultEnum) {
    this.code = resultEnum.getCode();
    this.msg = resultEnum.getMsg();
  }

  public ResponseData(ResultEnum resultEnum, T data) {
    this.code = resultEnum.getCode();
    this.msg = resultEnum.getMsg();
    this.data = data;
  }

  public ResponseData() {
  }

  public boolean isSuccess() {
    return ResultEnum.SUCCESS.getCode().equals(this.code);
  }

  public boolean isFail() {
    return !ResultEnum.SUCCESS.getCode().equals(this.code);
  }


}
