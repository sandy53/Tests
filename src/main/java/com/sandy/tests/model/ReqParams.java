package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;


/**
 *   api请求日志模型
 * 
 * @author sandy
 * @version $Id: ApiReqLogs.java, v 0.1 2019年4月28日 下午5:57:11 sandy Exp $
 */
@Genernate(value = "ts_req_params", desc = "api请求日志参数表")
public class ReqParams extends RecordModel {

    /**  */
    private static final long serialVersionUID = 6254307716619590738L;


    @Genernate(desc = "记录编码")
    private String            code;
    @Genernate(value = "param_type", desc = "参数名称")
    private String            paramType;
    @Genernate(desc = "参数名")
    private String            param;
    @Genernate(desc = "参数值")
    private String            value;
    @Genernate(desc = "备注")
    private String            remarks;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }



    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



}
