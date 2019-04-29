package com.sandy.tests.record.model;

import java.io.Serializable;

import com.sandy.tests.util.Genernate;

/**
 *  API请求参数表
 * 
 * @author sandy
 * @version $Id: ApiParams.java, v 0.1 2019年4月23日 上午11:53:51 sandy Exp $
 */
@Genernate(value = "ts_api_params", desc = "api请求参数表")
public class ApiParams implements Serializable {

    /**  */
    private static final long serialVersionUID = 7293121031193925629L;
    @Genernate(value = "api_code", desc = "api编码")
    private String            apiCode;
    @Genernate(value = "param_type", desc = "参数名称")
    private String            paramType;
    @Genernate(desc = "参数名")
    private String            param;
    private String            value;
    @Genernate(desc = "备注")
    private String            remarks;
    @Genernate(value = "data_type", desc = "数据类型")
    private String            dataType;
    @Genernate(value = "date_len", desc = "数据长度 ")
    private String            dateLen;
    @Genernate(desc = "状态")
    private String            required;
    @Genernate(desc = "状态")
    private Byte              status;
    @Genernate(value = "create_time", desc = "创建时间")
    private Long              createTime;
    @Genernate(value = "update_time", desc = "修改时间 ")
    private Long              updateTime;

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDateLen() {
        return dateLen;
    }

    public void setDateLen(String dateLen) {
        this.dateLen = dateLen;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
