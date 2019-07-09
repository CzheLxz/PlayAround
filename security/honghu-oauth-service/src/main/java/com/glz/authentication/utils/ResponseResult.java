package com.glz.authentication.utils;


/**
 * @author czhe
 * @Description 响应结果
 */


public class ResponseResult {

    private String code;
    private String message;
    private Object data;


    public ResponseResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ResponseResult success(ResponseCode responseCode, Object data) {
        return new ResponseResult(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static ResponseResult failure(ResponseCode responseCode) {
        return new ResponseResult(responseCode.getCode(), responseCode.getMessage());
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
