package com.czhe.sysmanage.retrunHandle;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 16:37
 * @description
 **/
@Data
public class ResultBody {

    private String code;
    private String message;
    private Object result;

    public ResultBody() {
    }

    public ResultBody(BaseErrorInfo baseErrorInfo) {
        this.code = baseErrorInfo.getResultCode();
        this.message = baseErrorInfo.getResultMsg();
    }

    public static ResultBody success() {
        return success(null);
    }

    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(ErrorMsg.SUCCESS.getResultCode());
        rb.setMessage(ErrorMsg.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    public static ResultBody error(BaseErrorInfo baseErrorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(baseErrorInfo.getResultCode());
        rb.setMessage(baseErrorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    public static ResultBody error(String code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}