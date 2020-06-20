package com.example.wanandroid.bean;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private static int SUCCESS_CODE = 0;//成功的code
    private static final long serialVersionUID = 5213230387175987834L;

    public int getCode() {
        return errorCode;
    }

    public void setCode(int code) {
        this.errorCode = code;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String msg) {
        this.errorMsg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int errorCode;
    public String errorMsg;
    public T data;


    public boolean isSuccess() {
        return getCode() == SUCCESS_CODE;
    }
}
