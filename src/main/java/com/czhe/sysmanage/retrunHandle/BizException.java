package com.czhe.sysmanage.retrunHandle;

import lombok.Data;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 16:50
 * @description
 **/
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -846976546795574726L;

    protected String errorCode;
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfo baseErrorInfo) {
        super(baseErrorInfo.getResultCode());
        this.errorCode = baseErrorInfo.getResultCode();
        this.errorMsg = baseErrorInfo.getResultMsg();
    }

    public BizException(BaseErrorInfo baseErrorInfo, Throwable cause) {
        super(baseErrorInfo.getResultCode(), cause);
        this.errorCode = baseErrorInfo.getResultCode();
        this.errorMsg = baseErrorInfo.getResultMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}