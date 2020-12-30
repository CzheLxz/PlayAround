package com.czhe.sysmanage.retrunHandle;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 16:27
 * @description
 **/
public enum ErrorMsg implements BaseErrorInfo {

    //定义操作
    SUCCESS("200", "成功"),
    UNSUPPORTED_ENCODING("400", "编码错误"),
    ILLEGAL_ACCESS("401", "权限错误"),
    UNSUPPORTED_OPERATION("403", "禁止操作"),
    NOT_FOUND("404", "未找到该资源"),
    METHOD_SUPPORT_ERROR("405", "当前请求方法不支持"),
    ILLEGAL_ARGUMENT("406", "参数错误"),
    NOT_LOGGED_IN("407", "未登录"),
    TIME_OUT("408", "超时"),
    CONFLICT("409", "重复! 已存在"),
    CONDITION_ERROR("412", "条件错误"),
    UNSUPPORTED_TYPE("415", "类型错误"),
    OUT_OF_RANGE("416", "超出范围"),
    NULL_POINTER("417", "对象为空"),
    SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503", "服务器正忙,请稍后再试!");

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
