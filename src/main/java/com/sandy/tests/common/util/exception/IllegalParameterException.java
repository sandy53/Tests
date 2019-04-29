package com.sandy.tests.common.util.exception;

import com.sandy.tests.common.model.ResultCode;

/**
 *   传入非常参数异常
 * 
 * @author zhangyg
 * @version $Id: IllegalParameterException.java, v 0.1 2017年1月7日 上午9:56:52 zhangyg Exp $
 */
public class IllegalParameterException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -2284979586567886286L;

    /**
     *  结果码
     *     默认是参数错误
     */
    protected ResultCode      resultCode       = ResultCode.PARAMETER_ERROR;

    public IllegalParameterException() {
        super();
    }

    public IllegalParameterException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public IllegalParameterException(ResultCode resultCode, String s) {
        super(s);
        this.resultCode = resultCode;
        this.resultCode.setDesc(s);
    }

    public IllegalParameterException(String s) {
        super(s);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }

    /**
     *  获取错误结果码
     * 
     * @return
     */
    public ResultCode getCode() {
        return resultCode;
    }

}
