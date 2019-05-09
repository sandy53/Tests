package com.sandy.tests.record.model;

import java.util.List;

/**
 *  记录变更用模型
 * 
 * @author sandy
 * @version $Id: RecordSave.java, v 0.1 2019年4月13日 下午5:19:59 sandy Exp $
 */
public class RecordUpdate extends RecordQS {

    /**  */
    private static final long serialVersionUID = 7670499768194587783L;


    private Long               recordId;

    private Long              updateTime;

    /**
     * 需要修改的字段集
     */
    private List<FieldUpdate> updateFields;

    public RecordUpdate() {
        super();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public List<FieldUpdate> getUpdateFields() {
        return updateFields;
    }

    public void setUpdateFields(List<FieldUpdate> updateFields) {
        this.updateFields = updateFields;
    }

}
