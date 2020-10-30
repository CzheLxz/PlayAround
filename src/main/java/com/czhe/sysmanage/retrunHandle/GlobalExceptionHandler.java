package com.czhe.sysmanage.retrunHandle;

import com.czhe.sysmanage.paramCheck.ParamIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/10/30 17:02
 * @description 全局异常处理
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义业务异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest request, BizException e) {
        log.error("发生业务异常! 原因是: {}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest request, NullPointerException e) {
        log.error("发生空指针异常!原因为: {}", e.getMessage());
        return ResultBody.error(ErrorMsg.BODY_NOT_MATCH);
    }

    /**
     * 处理请求方法不支持的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("发生请求方法不支持异常！原因是:", e);
        return ResultBody.error(ErrorMsg.REQUEST_METHOD_SUPPORT_ERROR);
    }

    /**
     * 处理请求参数为空异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ParamIsNullException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultBody exceptionHandler3(HttpServletRequest request, Exception e) {
        log.error("参数为空！原因是:", e);
        return ResultBody.error(ErrorMsg.SIGNATURE_NOT_MATCH.getResultCode(), e.getMessage());
    }


    /**
     * 处理资源未找到
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = ChangeSetPersister.NotFoundException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest request, ChangeSetPersister.NotFoundException e) {
        log.error("发生请求方法不支持异常！原因是:", e);
        return ResultBody.error(ErrorMsg.NOT_FOUND);
    }

    /**
     * 处理类型转换异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest request, NumberFormatException e) {
        log.error("类型转换异常！原因是:", e);
        return ResultBody.error(ErrorMsg.NUMBER_PARSE_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return ResultBody.error(ErrorMsg.INTERNAL_SERVER_ERROR);
    }


}