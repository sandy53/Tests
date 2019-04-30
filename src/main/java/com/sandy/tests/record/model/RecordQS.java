package com.sandy.tests.record.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *   记录查询|保存 基础模型类
 * 
 * @author sandy
 * @version $Id: RecordQS.java, v 0.1 2019年4月13日 下午5:14:52 sandy Exp $
 */
public class RecordQS implements Serializable {

    /**  */
    private static final long   serialVersionUID = 4333528055808501764L;

    protected String            recordCode;
    protected String                  recordName;
    /** 列*/
    protected List<RecordField>       fields;
    /** 表*/
    @JsonIgnore
    protected String                  table;

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public List<RecordField> getFields() {
        return fields;
    }

    public void setFields(List<RecordField> fields) {
        this.fields = fields;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
