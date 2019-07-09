package com.hairuo.tbk.pojo;

/**
 * @Description 枚举状态码
 * @Author czhe
 */
public enum ResultCode {

    SYSTEMERROR(0, "系统级别错误，后端代码发生异常"),
    SUCCESS(1, "请求成功"),
    LOGICERROR(2, "逻辑级别错误"),
    PARAMERROR(3, "请求参数不完整");

    private int code;//状态码
    private String msg;//描述

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
