package com.sandy.tests.record.model;

import java.io.Serializable;

import com.sandy.tests.record.util.Genernate;

/**
 * 记录基础模型
 * 
 * @author sandy
 * @version $Id: RecordModel.java, v 0.1 2019年4月29日 下午7:46:34 sandy Exp $
 */
public class RecordModel implements Serializable {

    /**  */
    private static final long serialVersionUID = 9113033764443216307L;
    @Genernate(value = "record_id", isId = true)
    protected Long            recordId;
    @Genernate(desc = "状态")
    protected Byte            state;
    @Genernate(value = "create_time", desc = "创建时间")
    protected Long            createTime;
    @Genernate(value = "update_time", desc = "修改时间 ")
    protected Long            updateTime;
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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
