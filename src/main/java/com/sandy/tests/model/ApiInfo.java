package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;

/**
 * api信息
 * 
 * @author sandy
 * @version $Id: ApiInfo.java, v 0.1 2019年4月23日 上午9:12:27 sandy Exp $
 */
@Genernate(value = "ts_api_info", desc = "api基础信息表")
public class ApiInfo extends RecordModel {

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




}
