package com.sandy.tests.record.service;

import java.util.Collection;
import java.util.List;

import com.sandy.tests.common.enums.RecordEnum;
import com.sandy.tests.record.model.RecordInfo;
import com.sandy.tests.record.model.RecordQuery;
import com.sandy.tests.record.model.RecordSave;


public interface RecordService {

    void doQuery(RecordQuery query);

    Collection<RecordInfo> loadRecordInfos();

    /**
     *   查询记录信息
     * 
     * @param recordCode
     * @return
     */
    RecordInfo queryRecordInfo(String recordCode);

    void doSave(RecordSave save);


    void doSave(RecordEnum recordEnum, List<?> record);
}
