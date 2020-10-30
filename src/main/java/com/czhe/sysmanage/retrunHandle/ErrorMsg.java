package com.czhe.sysmanage.retrunHandle;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 16:27
 * @description
 **/
public enum ErrorMsg implements BaseErrorInfo{

    //定义操作
    SUCCESS("200","成功! "),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404","未找到该资源!"),
    NUMBER_PARSE_ERROR("406","类型转换异常!"),
    INTERNAL_SERVER_ERROR("500","服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙,请稍后再试!"),
    REQUEST_METHOD_SUPPORT_ERROR("40001","当前请求方法不支持");

    private String resultCode;
    private String resultMsg;

    ErrorMsg(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
