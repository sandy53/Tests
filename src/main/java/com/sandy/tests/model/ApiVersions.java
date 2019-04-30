package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;


/**
 *  api版本组合关联关系表
 * 
 * @author sandy
 * @version $Id: ApiVersions.java, v 0.1 2019年4月26日 下午1:59:28 sandy Exp $
 */
@Genernate(value = "ts_api_versions", desc = "api组合关联表")
public class ApiVersions extends RecordModel {

    /**  */
    private static final long serialVersionUID = 9138726669017414174L;

    @Genernate(value = "version_code", desc = "组合code")
    private String            versionCode;
    @Genernate(value = "api_code", desc = "api编码")
    private String            apiCode;
    @Genernate(value = "api_path", desc = "api请求地址")
    private String            apiPath;


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





    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

}
