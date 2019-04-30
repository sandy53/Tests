package com.sandy.tests.record.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *   记录数据字段模型
 * 
 * @author sandy
 * @version $Id: RecordField.java, v 0.1 2019年4月13日 下午1:50:27 sandy Exp $
 */
public class RecordField implements Serializable {

    /**  */
    private static final long serialVersionUID = -847395260539473938L;
    /** 记录编码   关联至记录信息表*/
    @JsonIgnore
    private String            recordCode;
    /** 字段编码 */
    private String            fieldCode;
    /** 字段名称 */
    private String            fieldName;
    /** 字段列名 */
    @JsonIgnore
    private String            fieldColumn;
    /** 是否是数据库主键*/
    private Integer           primaryKey;

    /** 字段数据类型 */
    @JsonIgnore
    private Integer           fieldDatatype;
    /** 字段数据长度 */
    @JsonIgnore
    private Integer           fieldDatalen;
    /** 字段描述*/
    @JsonIgnore
    private String            fieldDesc;
    /** 排序 */
    @JsonIgnore
    private Integer           fieldSort;
    /** 是否默认搜索 */
    private Byte              defSearch;

    public RecordField() {
        super();
    }

    public RecordField(String fieldCode, String fieldColumn) {
        super();
        this.fieldCode = fieldCode;
        this.fieldColumn = fieldColumn;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldColumn() {
        return fieldColumn;
    }

    public void setFieldColumn(String fieldColumn) {
        this.fieldColumn = fieldColumn;
    }

    public Integer getFieldDatatype() {
        return fieldDatatype;
    }

    public void setFieldDatatype(Integer fieldDatatype) {
        this.fieldDatatype = fieldDatatype;
    }

    public Integer getFieldDatalen() {
        return fieldDatalen;
    }

    public void setFieldDatalen(Integer fieldDatalen) {
        this.fieldDatalen = fieldDatalen;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public Integer getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(Integer fieldSort) {
        this.fieldSort = fieldSort;
    }

    public Byte getDefSearch() {
        return defSearch;
    }

    public void setDefSearch(Byte defSearch) {
        this.defSearch = defSearch;
    }

    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }


}
