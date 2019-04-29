package com.sandy.tests.common.util.exception;

import com.sandy.tests.common.model.RespCode;
import com.sandy.tests.common.model.ResultCode;

/**
 *  运行时异常
 * 
 * @author sandy
 * @version $Id: RuningException.java, v 0.1 2018年8月8日 下午1:59:14 sandy Exp $
 */
public class RuningException extends RuntimeException implements BaseExcepion {

    /**  */
    private static final long serialVersionUID = -4555924371655514668L;
    /**
     *  结果码
     */
    protected RespCode        respCode         = ResultCode.FAIL;

    public RuningException(String msg) {
        super(msg);
    }

    public RuningException(Throwable e) {
        super(e);
    }

    /**  
     *    业务异常
     *    
     * @param resultCode   异常码对象
     */
    public RuningException(RespCode respCode) {
        super(respCode.toString());
        this.respCode = respCode;
    }

    /**  
     *    业务异常
     *    
     * @param resultCode   异常码对象
     * @param msg   异常信息
     */
    public RuningException(RespCode respCode, String msg) {
        super(new StringBuffer().append(respCode).append(" ").append(getMsg(msg)).toString());
        this.respCode = respCode;
    }

    /**  
     *    业务异常
     *    
     * @param resultCode   异常码对象
     * @param msg   异常信息
     */
    public RuningException(RespCode respCode, Object msg) {
        super(new StringBuffer().append(respCode).append(" ").append(getMsg(msg)).toString());
        this.respCode = respCode;
    }

    /**  
     *    业务异常
     *    
     * @param resultCode   异常码对象
     * @param recordType  关联记录类型
     * @param msg   异常信息
     * @param e    异常对象
     */
    public RuningException(RespCode respCode, Object msg, Throwable e) {
        super(new StringBuffer().append(respCode).append(" ").append(getMsg(msg)).toString(), e);
        this.respCode = respCode;
    }

    public RuningException(RespCode respCode, Throwable e) {
        super(respCode.getDesc(), e);
        this.respCode = respCode;
    }

    public RuningException(String msg, Throwable e) {
        super(msg, e);
    }

    /**
     * 获取  返回的结果码
     * 
     * @return
     */
    @Override
    public RespCode getCode() {
        return this.respCode;
    }

    /**
     *  字符串处理
     *      null字符串返回空
     * @param msg
     * @return
     */
    private static String getMsg(String msg) {
        return msg == null ? "" : msg;
    }

    /**
     *    对象消息处理
     * 
     * @param msg
     * @return
     */
    private static String getMsg(Object msg) {
        return msg == null ? "" : String.valueOf(msg);
    }
}
