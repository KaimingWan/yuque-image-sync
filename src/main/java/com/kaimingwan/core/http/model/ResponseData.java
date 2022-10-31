package com.kaimingwan.core.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * @author wanshao create time is 2021/4/17
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData<T> implements Serializable {

    private String code;
    private String msg;
    private T      data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String workerSeqNumber;

    public ResponseData(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public ResponseData(ResultEnum resultEnum, T data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    public ResponseData(){
    }

    public boolean isSuccess() { return ResultEnum.SUCCESS.getCode().equals(this.code); }

    public boolean isFail() { return !ResultEnum.SUCCESS.getCode().equals(this.code); }

    public String getCode() { return this.code; }

    public void setCode(String code) { this.code = code; }

    public String getMsg() { return this.msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public T getData() { return this.data; }

    public void setData(T data) { this.data = data; }

    public void setRequestId(final String requestId) { this.requestId = requestId; }

    public String getRequestId() { return this.requestId; }

    public String getWorkerSeqNumber() { return this.workerSeqNumber; }

    public void setWorkerSeqNumber(final String workerSeqNumber) { this.workerSeqNumber = workerSeqNumber; }
}
