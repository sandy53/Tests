package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;


/**
 *   api请求日志模型
 * 
 * @author sandy
 * @version $Id: ApiReqLogs.java, v 0.1 2019年4月28日 下午5:57:11 sandy Exp $
 */
@Genernate(value = "ts_req_logs", desc = "api请求日志表")
public class ReqLogs extends RecordModel {

    /**  */
    private static final long serialVersionUID = 6254307716619590738L;


    @Genernate(desc = "记录编码")
    private String            code;
    @Genernate(value = "api_code", desc = "api编码")
    private String            apiCode;
    @Genernate(value = "path", desc = "api全路径")
    private String            path;


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
    @Override
    public Byte getStatus() {
        return status;
    }
    @Override
    public void setStatus(Byte status) {
        this.status = status;
    }
    @Override
    public Long getCreateTime() {
        return createTime;
    }
    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    @Override
    public Long getUpdateTime() {
        return updateTime;
    }
    @Override
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


}
