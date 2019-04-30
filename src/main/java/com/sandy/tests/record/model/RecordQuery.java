package com.sandy.tests.record.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  查询封装模型类
 * 
 * @author sandy
 * @version $Id: RecordQuery.java, v 0.1 2019年1月8日 下午11:43:07 sandy Exp $
 */
public class RecordQuery extends RecordQS {

    /**  */
    private static final long         serialVersionUID = -9186566569048118881L;

    private boolean                   doPage = true;

    private Paging                    paging;
    /** 查询条件*/
    @JsonIgnore
    private List<Condition>           conditions;
    /** 排序列*/
    @JsonIgnore
    private List<String>              sortColumns;
    /** 排序类型*/
    private boolean                   isASC;

    private List<Map<String, Object>> results;

    public RecordQuery(String recordCode, Paging paging) {
        super();
        this.recordCode = recordCode;
        this.paging = paging;
    }

    public void setTotal(int total) {
        if (this.paging == null) {
            this.paging = new Paging();
        }
        this.paging.setTotal(total);
    }

    /**
     * 基础查询条件封装
     * 
     * @author sandy
     * @version $Id: RecordQuery.java, v 0.1 2019年1月9日 下午11:00:52 sandy Exp $
     */
    public static class SearchBase {

        /** 条件列*/
        private List<RecordField> fields;
        /** 条件查询值  */
        private List<String>      values;

        public SearchBase() {
            super();
        }



        public List<RecordField> getFields() {
            return fields;
        }

        public void setFields(List<RecordField> fields) {
            this.fields = fields;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

    }

    /**
     * 扩展查询条件类
     * 
     * @author sandy
     * @version $Id: RecordQuery.java, v 0.1 2019年1月8日 下午11:48:25 sandy Exp $
     */
    public static class Condition {
        /** 条件名 */
        private String field;
        /** 条件列名 */
        private String column;
        /** 条件查询值  */
        private Object value;
        
        private List<Object> values;
        /** 比较类型  */
        private String compareType;
        /** 范围比较左值  */
        private Object leftValue;
        /** 范围比较右值  */
        private Object rightValue;

        public Condition() {
            super();
        }

        public Condition(String field, Object value) {
            super();
            this.field = field;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getCompareType() {
            return compareType;
        }

        public void setCompareType(String compareType) {
            this.compareType = compareType;
        }

        public Object getLeftValue() {
            return leftValue;
        }

        public void setLeftValue(Object leftValue) {
            this.leftValue = leftValue;
        }

        public Object getRightValue() {
            return rightValue;
        }

        public void setRightValue(Object rightValue) {
            this.rightValue = rightValue;
        }

        public List<Object> getValues() {
            return values;
        }

        public void setValues(List<Object> values) {
            this.values = values;
        }

    }

    /**
     * 比较类型
     * 
     * @author sandy
     * @version $Id: RecordQuery.java, v 0.1 2019年1月8日 下午11:52:16 sandy Exp $
     */
    public enum CompareType {
                             /** 相等*/
                             EQUAL,
                             /** 范围*/
                             RANG,

                             IN
    }



    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<String> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<String> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public boolean isASC() {
        return isASC;
    }

    public void setASC(boolean isASC) {
        this.isASC = isASC;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public boolean isDoPage() {
        return doPage;
    }

    public void setDoPage(boolean doPage) {
        this.doPage = doPage;
    }

}
