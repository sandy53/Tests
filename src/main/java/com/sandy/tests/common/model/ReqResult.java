package com.sandy.tests.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 
 * 操作返回对象
 * 
 * @author sandy
 * @version $Id: ReqResult.java, v 0.1 2016年12月20日 下午2:34:42 sandy Exp $
 */
public class ReqResult<T> implements Serializable {
    /**  */
    private static final long serialVersionUID = 2315892187376182610L;

    /**
     * 返回码模型，不序列化到前端
     */
    @JsonIgnore
    private RespCode          respCode         = ResultCode.SUCCESS;
    /**
     * 返回码
     */
    //private int               resultCode       = respCode.getCode();

    /**
     * 返回错误描述   正式环境下可能不返回
     */
    private String            error;
    /** 错误日志唯一标识 */
    private String            errorCode;
    /**
     *   语言环境
     */
    @SuppressWarnings("unused")
    private boolean           languageEn       = false;

    private int               httpStatus;

    /**
     * 返回数据
     */
    public T                  data;

    /**
     * 扩展数据
     */
    public T                  exData;

    public void setExData(T exData) {
        this.exData = exData;
    }

    public T getExData() {
        return exData;
    }

    /**
     * 操作时间
     */
    private long              nowTime          = System.currentTimeMillis();

    public ReqResult() {
    }

    public ReqResult(T data) {
        this.data = data;
    }

    public ReqResult(boolean languageEn) {
        this.languageEn = languageEn;
    }

    public ReqResult(RespCode respCode, boolean languageEn) {
        this.respCode = respCode;
        this.languageEn = languageEn;
        this.setResultCode(respCode, null);
    }

    public ReqResult(RespCode respCode) {
        this.setResultCode(respCode);
        this.setResultCode(respCode, null);
    }

    public void setResultCode(RespCode respCode) {
        this.respCode = respCode;
        this.setResultCode(respCode, null);
    }

    public ReqResult(RespCode respCode, String msg) {
        this.respCode = respCode;
        this.setResultCode(respCode, msg);
    }

    public void setResultCode(RespCode respCode, Object msg) {
        this.respCode = respCode;
        if (this.getError() == null) {
            this.setError(new StringBuilder().append(respCode).append(":")
                .append(respCode.getDesc()).append(" ").append(String.valueOf(msg)).toString());
        }
    }

    public int getResultCode() {
        return respCode.getCode();
    }

    /**
     *  设置语言环境 ， 是否是英文
     * 
     * @param isEn
     */
    public void setLanguage(boolean isEn) {
        this.languageEn = isEn;
    }

    /**
     *  是否成功
     * 
     * @return
     */
    public boolean isSuccess() {
        return this.respCode == ResultCode.SUCCESS || this.respCode == ResultCode.REQUEST_SUCCESS;
    }

    public void setResultCode(int respCode) {
        RespCode type = ResultCode.getType(respCode);
        this.respCode = type == null ? ResultCode.APP_FAIL : type;
    }

    /**
     *   返回文案
     * 
     * @return
     */
    public String getReason() {
        return respCode.getDesc();
    }



    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "{\"ReqResult\" : {\"resultCode\" : \"" + respCode + "\", \"error\" : \"" + error
               + "\", \"data\" : \"" + data + "\", \"nowTime\" : \"" + nowTime + "\"}}";
    }
}
