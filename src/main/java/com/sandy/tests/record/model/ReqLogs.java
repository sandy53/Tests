package com.sandy.tests.record.model;

import java.io.Serializable;

import com.sandy.tests.util.Genernate;


/**
 *   api请求日志模型
 * 
 * @author sandy
 * @version $Id: ApiReqLogs.java, v 0.1 2019年4月28日 下午5:57:11 sandy Exp $
 */
@Genernate(value = "ts_req_logs", desc = "api请求日志表")
public class ReqLogs implements Serializable {

    /**  */
    private static final long serialVersionUID = 6254307716619590738L;


    @Genernate(desc = "记录编码")
    private String            code;
    @Genernate(value = "api_code", desc = "api编码")
    private String            apiCode;
    @Genernate(value = "path", desc = "api全路径")
    private String            path;
    @Genernate(desc = "状态")
    private Byte              status;
    @Genernate(value = "create_time", desc = "创建时间")
    private Long              createTime;
    @Genernate(value = "update_time", desc = "修改时间 ")
    private Long              updateTime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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


}
