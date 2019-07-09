package com.yufan.common.bean;

public class ResultMsg {

    private int code;//编码
    private String msg;//说明

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        String str = "code=[" + code + "]" + ",msg=[" + msg + "]";
        return str;
    }
}
