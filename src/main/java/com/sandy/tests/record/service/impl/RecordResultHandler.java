package com.sandy.tests.record.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

/**
 *  记录查询 mybatis 结果封装类
 * 
 * @author sandy
 * @version $Id: RecordResultHandler.java, v 0.1 2019年1月9日 上午1:27:16 sandy Exp $
 */
@SuppressWarnings("rawtypes")
public class RecordResultHandler implements ResultHandler<Map> {

    private final List<Map<String, Object>> list;

    public RecordResultHandler() {
        list = new ArrayList<Map<String, Object>>();
    }

    @Override
    public void handleResult(ResultContext<? extends Map> resultContext) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = resultContext.getResultObject();
        list.add(map);
    }

    public List<Map<String, Object>> getResultList() {
        return list;
    }
}
