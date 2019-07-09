package com.glz.authentication.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czhe
 * @Description 响应code msg
 */
public enum ResponseCode {

    RESPONSE_CODEEO0000("EO0000 ", "成功"),
    RESPONSE_CODEEO0001("EO0001 ", "渠道信息有误"),
    RESPONSE_CODEEO0002("EO0002 ", "APPId,APPKey不一致"),
    RESPONSE_CODEEO0003("EO0003 ", "该用户已被注册,请稍后再试"),
    RESPONSE_CODEEO0004("EO0004 ", "验证码不正确"),
    RESPONSE_CODEEO0005("EO0005 ", "密码格式有误"),
    RESPONSE_CODEEO0006("EO0006 ", "请上送必须上送的项"),
    RESPONSE_CODEEO0007("EO0007 ", "账号不存在"),
    RESPONSE_CODEEO0008("EO0008 ", "账号与密码不匹配"),
    RESPONSE_CODEEO0009("EO0009 ", "令牌信息不存在"),
    RESPONSE_CODEEO0010("EO0010 ", "该手机号已被绑定其他账户,请勿重新绑定"),
    RESPONSE_CODEEO0011("EO0011 ", "系统异常,请联系客服"),
    RESPONSE_CODEEO0012("EO0012 ", "验证码类型有误,请上送正确的验证码类型"),
    RESPONSE_CODEEO0013("EO0013 ", "登录方式有误,请上送正确的登录方式"),
    RESPONSE_CODEEO0014("EO0014 ", "第三方类型有误,请上送正确的第三方类型"),
    RESPONSE_CODEEO0015("EO0015 ", "操作类型有误,请上送正确的操作类型"),
    RESPONSE_CODEEO0016("EO0016 ", "用户验证失效"),
    RESPONSE_CODEEO0017("EO0017 ", "系统异常"),
    RESPONSE_CODEEO0018("EO0018 ", "手机号格式有误"),
    RESPONSE_CODEEO0019("EO0019 ", ",输入信息格式不正确"),
    RESPONSE_CODEEO0020("EO0020 ", "该账号没有绑定过该第三方账号,无法解绑");

    // 状态码
    private String code;
    // 返回消息
    private String message;

    //返回数据
    private Object data;


    private ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static Map getMap(ResponseCode responseCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", responseCode.getCode());
        map.put("message", responseCode.getMessage());
        return map;
    }


}
