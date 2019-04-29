package com.sandy.tests.dao;

import java.util.List;

import com.sandy.tests.common.model.RecordQuery;
import com.sandy.tests.common.model.RecordSave;
import com.sandy.tests.model.RecordField;
import com.sandy.tests.model.RecordInfo;



public interface RecordMapper {

    int countByQuery(RecordQuery query);

    //  Object selectByQuery(RecordQuery query);

    /**
     * 根据数据编码查询数据信息
     * 
     * @return
     */
    RecordInfo selectInfoByCode(String recordCode);

    /**
     * 查询多个记录表的字段
     * 
     * @param codes
     * @return
     */
    List<RecordField> selectAllFieldByCodes(List<String> codes);

    List<RecordInfo> selectAllRecordInfo();


    int insert(RecordSave save);

}