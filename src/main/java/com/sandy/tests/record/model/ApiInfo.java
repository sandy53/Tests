package com.sandy.tests.record.model;

import java.io.Serializable;

import com.sandy.tests.util.Genernate;

/**
 * api信息
 * 
 * @author sandy
 * @version $Id: ApiInfo.java, v 0.1 2019年4月23日 上午9:12:27 sandy Exp $
 */
@Genernate(value = "ts_api_info", desc = "api基础信息表")
public class ApiInfo implements Serializable {

    /**  */
    private static final long serialVersionUID = 6254307716619590738L;


    @Genernate(desc = "记录编码")
    private String            code;
    @Genernate(desc = "标题")
    private String            title;
    @Genernate(desc = "请求路径")
    private String            path;
    @Genernate(desc = "请求方式")
    private String            method;
    @Genernate(desc = "备注")
    private String            remarks;
    @Genernate(desc = "状态")
    private Byte              status;
    @Genernate(value = "create_time", desc = "创建时间")
    private Long              createTime;
    @Genernate(value = "update_time", desc = "修改时间 ")
    private Long              updateTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

}
