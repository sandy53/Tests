package com.sandy.tests.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.tests.common.model.Paging;
import com.sandy.tests.common.model.RecordQuery;
import com.sandy.tests.common.model.RecordQuery.Condition;
import com.sandy.tests.common.model.ReqResult;
import com.sandy.tests.common.util.HttpUtil;
import com.sandy.tests.service.RecordService;


@RestController
@RequestMapping("/psi/tests/")
public class RecordController {
    @Autowired
    private RecordService recordService;


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
