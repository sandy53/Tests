package com.sandy.tests.record.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.tests.common.model.ReqResult;
import com.sandy.tests.common.util.Assert;
import com.sandy.tests.common.util.HttpUtil;
import com.sandy.tests.record.model.Paging;
import com.sandy.tests.record.model.RecordQuery;
import com.sandy.tests.record.model.RecordQuery.Condition;
import com.sandy.tests.record.model.RecordUpdate;
import com.sandy.tests.record.service.RecordService;

/**
 * 
 *   记录控制器
 * 
 * @author sandy
 * @version $Id: RecordController.java, v 0.1 2019年4月30日 下午2:01:17 sandy Exp $
 */
@RestController
@RequestMapping("/api/tests/")
public class RecordController {
    @Autowired
    private RecordService recordService;

    /**
     *  通用记录删除
     *  
     * 
     * @param recordCode
     * @param primary
     * @param key
     * @return
     */
    @RequestMapping("/common/remove")
    public ReqResult<RecordQuery> doRemove(@RequestParam String recordCode,
                                           @RequestParam String primary, @RequestParam Long key) {

        Assert.notEmpty(recordCode);
        Assert.notEmpty(primary);
        Assert.isPositiveInteger(key);
        RecordUpdate update = new RecordUpdate();
        update.setRecordCode(recordCode);
        update.setPrimaryKey(primary);
        update.setRecordId(key);
        update.setUpdateTime(System.currentTimeMillis());
        recordService.doRemove(update);
        return new ReqResult<>();
    }

    /**
     * 通用查询
     * 
     * @param request
     * @param recordCode
     * @param paging
     * @return
     */
    @RequestMapping("/common/{recordCode}/search")
    public ReqResult<RecordQuery> doQuery(HttpServletRequest request,
                                          @PathVariable("recordCode") String recordCode,
                                          Paging paging) {
        RecordQuery query = new RecordQuery(recordCode, paging);
        condition(request, query);
        recordService.doQuery(query);
        return new ReqResult<RecordQuery>(query);
    }

    public void condition(HttpServletRequest request, RecordQuery query) {
        Map<String, String> params = HttpUtil.fetchParams(request.getParameterMap());
        if (CollectionUtils.isEmpty(params)) {
            return;
        }
        List<Condition> conditions = new ArrayList<RecordQuery.Condition>();
        for (Entry<String, String> entry : params.entrySet()) {
            conditions.add(new Condition(entry.getKey(), entry.getValue()));
        }
        query.setConditions(conditions);
    }

}
