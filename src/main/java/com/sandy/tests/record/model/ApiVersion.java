package com.sandy.tests.record.model;

import java.io.Serializable;

import com.sandy.tests.util.Genernate;

/**
 * Api 版本， 相当于一个组合
 * 
 * @author sandy
 * @version $Id: ApiVersion.java, v 0.1 2019年4月26日 下午1:58:34 sandy Exp $
 */
@Genernate(value = "ts_api_version", desc = "api组合信息表")
public class ApiVersion implements Serializable {

    /**  */
    private static final long serialVersionUID = 7798686470407579342L;

    @Genernate(value = "code", desc = "编码")
    private String            code;
    @Genernate(value = "type", desc = "组合类型")
    private String            type;
    @Genernate(value = "title", desc = "描述")
    private String            title;
    @Genernate(desc = "状态")
    private Byte              status;
    @Genernate(value = "create_time", desc = "创建时间")
    private Long              createTime;
    @Genernate(value = "update_time", desc = "修改时间 ")
    private Long              updateTime;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
