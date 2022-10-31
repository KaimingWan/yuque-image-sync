package com.kaimingwan.core.http.model;

/**
 * @author wanshao create time is 2021/4/17
 **/
public enum ResultEnum {

    SUCCESS("1", "Request success"),
    ERROR("0", "Request failure");

    private String code;
    private String msg;

    private ResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() { return this.code; }

    public String getMsg() { return this.msg; }
}
