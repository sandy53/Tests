package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;

/**
 * Api 版本， 相当于一个组合
 * 
 * @author sandy
 * @version $Id: ApiVersion.java, v 0.1 2019年4月26日 下午1:58:34 sandy Exp $
 */
@Genernate(value = "ts_api_version", desc = "api组合信息表")
public class ApiVersion extends RecordModel {

    /**  */
    private static final long serialVersionUID = 7798686470407579342L;

    @Genernate(value = "code", desc = "编码")
    private String            code;
    @Genernate(value = "type", desc = "组合类型")
    private String            type;
    @Genernate(value = "title", desc = "描述")
    private String            title;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
