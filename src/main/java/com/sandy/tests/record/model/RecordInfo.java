package com.sandy.tests.record.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 记录信息 模型
 * 
 * @author sandy
 * @version $Id: RecordInfo.java, v 0.1 2019年4月13日 下午1:50:17 sandy Exp $
 */
public class RecordInfo implements Serializable {

    /**  */
    private static final long                  serialVersionUID = -8533128864823050788L;

    /**
     * 所有字段信息
     */
    private List<RecordField>                  fields;

    /**
     *  设置的默认查询的字段
     */
    @JsonIgnore
    private List<RecordField>                  defFields;

    
    private Map<String, RecordField> fieldMap;


    /** 记录编号 */
    private String                             recordCode;
    /** 记录名称*/
    private String                             recordName;
    /** 记录数据所在表*/
    @JsonIgnore
    private String                             recordTable;
    /** 记录描述*/
    private String                             recordDesc;
    /** 记录子类型*/
    private String                             recordType;
    /** 记录主类型*/
    // private String                             mainType;
    /** 是否默认搜索*/
    private Byte                               searchDef;



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

    @JsonIgnore
    public String getRecordTable() {
        return recordTable;
    }

    public void setRecordTable(String recordTable) {
        this.recordTable = recordTable;
    }

    public String getRecordDesc() {
        return recordDesc;
    }

    public void setRecordDesc(String recordDesc) {
        this.recordDesc = recordDesc;
    }

    public List<RecordField> getFields() {
        return fields;
    }

    public void setFields(List<RecordField> fields) {
        this.fields = fields;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }



    public Byte getSearchDef() {
        return searchDef;
    }

    public void setSearchDef(Byte searchDef) {
        this.searchDef = searchDef;
    }

    /*    public String getMainType() {
        return mainType;
    }
    
    public void setMainType(String mainType) {
        this.mainType = mainType;
    }*/


    public List<RecordField> getDefFields() {
        return defFields;
    }

    public void setDefFields(List<RecordField> defFields) {
        this.defFields = defFields;
    }

    public Map<String, RecordField> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, RecordField> fieldMap) {
        this.fieldMap = fieldMap;
    }

}
