package com.sandy.tests.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sandy.tests.common.enums.RecordEnum;
import com.sandy.tests.common.model.RecordQuery;
import com.sandy.tests.common.model.RecordQuery.Condition;
import com.sandy.tests.common.model.RecordSave;
import com.sandy.tests.common.model.ResultCode;
import com.sandy.tests.common.util.Assert;
import com.sandy.tests.common.util.exception.RuningException;
import com.sandy.tests.dao.RecordMapper;
import com.sandy.tests.model.RecordField;
import com.sandy.tests.model.RecordInfo;
import com.sandy.tests.service.RecordService;

import net.sf.json.JSONObject;

/**
 *  记录查询业务处理类
 * 
 * @author sandy
 * @version $Id: RecordServiceImpl.java, v 0.1 2019年1月8日 下午11:56:55 sandy Exp $
 */
@Service
public class RecordServiceImpl implements RecordService {

    private Logger                logger          = LoggerFactory
        .getLogger(RecordServiceImpl.class);

    /**
     * 临时容器
     */
    private static Map<String, RecordInfo> recordMap;



    /** mybatis 查询标识*/
    private final static String   QUERY_STATEMENT = "com.sandy.tests.dao.RecordMapper.selectByQuery";

    @Resource
    private SqlSessionTemplate    sqlSessionTemplate;
    @Resource
    private RecordMapper          recordMapper;



    @Override
    public void doQuery(RecordQuery query) {
        //TODO 判断空
        RecordInfo recordInfo = queryRecordInfo(query.getRecordCode());
        query.setRecordName(recordInfo.getRecordName());
        buildQueryCondition(query, recordInfo);
        int total = recordMapper.countByQuery(query);
        if (total <= 0) {
            return;
        }
        query.setTotal(total);
        RecordResultHandler handler = new RecordResultHandler();
        sqlSessionTemplate.select(QUERY_STATEMENT, query, handler);
        query.setResults(handler.getResultList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doSave(RecordSave save) {
        Assert.notNull(save, "record doSave  save is null");
        Assert.notEmpty(save.getRecordCode(), "record doSave  recordCode is empty");
        //获取记录元数据信息
        RecordInfo recordInfo = queryRecordInfo(save.getRecordCode());
        save.setFields(recordInfo.getFields());
        save.setTable(recordInfo.getRecordTable());
        List<?> records = save.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            //无记录保存
            return;
        }
        //转换数据，以便保存， 
        List<List<String>> values = new ArrayList<List<String>>();
        List<String> value = null;
        JSONObject json = null;
        List<RecordField> fields = recordInfo.getFields();
        for (Object recordBase : records) {
            json = JSONObject.fromObject(recordBase);
            value = new ArrayList<String>();
            for (RecordField recordField : fields) {
                //String fieldCode = recordField.getFieldCode();
                // String string = json.getString(fieldCode);
                value.add(json.getString(recordField.getFieldCode()));//把需要保存的值转换为对应column位置的值
            }
            values.add(value);
        }
        save.setValues(values);
        int insert = recordMapper.insert(save);
        if (insert < values.size()) {
            logger.error("COMMON-DO-SAVE FAIL: {}, {}", insert, values, recordInfo.getRecordCode());
            throw new RuningException(ResultCode.RECORD_SAVE_FAIL);
        }
    }

    /**
     * 封装查询语句条件
     * 
     * @param recordInfo
     * @return
     */
    private void buildQueryCondition(RecordQuery query, RecordInfo recordInfo) {
        //封装查询 条件
        query.setTable(recordInfo.getRecordTable());
        if (query.getFields() == null) {
            query.setFields(recordInfo.getFields());
        }
        if (CollectionUtils.isEmpty(query.getFields())) { //没有默认查询字段
            throw new RuningException(
                /*ResultCode.NOT_SET_SEARCH_FIELD,*/ recordInfo.getRecordCode());
        }
        if (!CollectionUtils.isEmpty(query.getConditions())) {
            //封装 列名
            RecordField feild = null;
            List<Condition> removes = null;
            for (Condition condition : query.getConditions()) {
                feild = recordInfo.getFieldMap().get(condition.getField());
                if (feild == null) {
                    //TODO  日志
                    if(removes == null) {
                        removes = new ArrayList<RecordQuery.Condition>();
                    }
                    removes.add(condition);
                    continue;
                }
                condition.setColumn(feild.getFieldColumn());
            }
            if (!CollectionUtils.isEmpty(removes)) {
                query.getConditions().removeAll(removes);
            }
        }


    }

    /**
     * 加载记录基本信息
     * 
     * @param recordCode
     * @return
     */
    @Override
    public RecordInfo queryRecordInfo(String recordCode) {
        Assert.notEmpty(recordCode);
        if (recordMap != null) {
            RecordInfo recordInfo = recordMap.get(recordCode);
            if (recordInfo == null) {
                throw new RuningException(ResultCode.RECORD_NOT_EXIST, recordCode);
            }
            return recordInfo;
        }

        recordMap = loadRecordInfo();
        if (recordMap == null || recordMap.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST, recordCode);
        }
        RecordInfo recordInfo = recordMap.get(recordCode);
        if (recordInfo == null) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST, recordCode);
        }
        return recordInfo;
    }

    @Override
    public Collection<RecordInfo> loadRecordInfos() {
        Map<String, RecordInfo> infoMap = loadRecordInfo();
        return infoMap == null ? null : infoMap.values();
    }

    //@SuppressWarnings("unchecked")
    private Map<String, RecordInfo> loadRecordInfo() {
        Map<String, RecordInfo> map = null;
        /*  Object object = redisUtil.hash(CountKey.DICT_RECORD_INFO_KEY);
        if (object != null && object instanceof Map<?, ?>) {
            map = (Map<String, RecordInfo>) object;
            if (!map.isEmpty()) {
                // return map;
            }
        }*/
        List<RecordInfo> list = recordMapper.selectAllRecordInfo();
        List<String> codes = new ArrayList<String>();
        map = new HashMap<>();
        for (RecordInfo recordInfo : list) {
            map.put(recordInfo.getRecordCode(), recordInfo);
            codes.add(recordInfo.getRecordCode());
        }
        List<RecordField> fieldList = recordMapper.selectAllFieldByCodes(codes);
        RecordInfo record = null;
        for (RecordField field : fieldList) {
            if ((record = map.get(field.getRecordCode())) == null) {
                //不支持的字段 
                continue;
            }
            if (record.getFields() == null) {
                record.setFields(new ArrayList<>());
            }
            if (record.getFieldMap() == null) {
                record.setFieldMap( new HashMap<String, RecordField>());
            }
            record.getFieldMap().put(field.getFieldCode(), field);
            record.getFields().add(field);
        }
        //redisUtil.hashAddAll(CountKey.DICT_RECORD_INFO_KEY, map);
        return map;

    }



    @Override
    public void doSave(RecordEnum recordEnum, List<?> records) {
        Assert.notNull(recordEnum);
        Assert.notEmpty(records);
        this.doSave(new RecordSave(recordEnum.name(), records));
    }

}
