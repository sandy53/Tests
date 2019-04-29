package com.sandy.tests.record.model;

import java.io.Serializable;

import com.sandy.tests.util.Genernate;


/**
 *  api版本组合关联关系表
 * 
 * @author sandy
 * @version $Id: ApiVersions.java, v 0.1 2019年4月26日 下午1:59:28 sandy Exp $
 */
@Genernate(value = "ts_api_versions", desc = "api组合关联表")
public class ApiVersions implements Serializable {

    /**  */
    private static final long serialVersionUID = 9138726669017414174L;

    @Genernate(value = "version_code", desc = "组合code")
    private String            versionCode;
    @Genernate(value = "api_code", desc = "api编码")
    private String            apiCode;
    @Genernate(value = "api_path", desc = "api请求地址")
    private String            apiPath;
    @Genernate(desc = "状态")
    private Byte              status;
    @Genernate(value = "create_time", desc = "创建时间")
    private Long              createTime;
    @Genernate(value = "update_time", desc = "修改时间 ")
    private Long              updateTime;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
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

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

}
