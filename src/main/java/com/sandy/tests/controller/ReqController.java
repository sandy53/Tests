package com.sandy.tests.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sandy.tests.common.enums.ParamType;
import com.sandy.tests.common.enums.RecordEnum;
import com.sandy.tests.common.model.RecordQuery;
import com.sandy.tests.common.model.RecordQuery.Condition;
import com.sandy.tests.common.util.StringUtil;
import com.sandy.tests.common.util.exception.BusinessException;
import com.sandy.tests.common.util.http.HttpRequester;
import com.sandy.tests.record.model.ApiInfo;
import com.sandy.tests.record.model.ApiParams;
import com.sandy.tests.record.model.ReqLogs;
import com.sandy.tests.record.model.ReqParams;
import com.sandy.tests.service.RecordService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  api 请求控制器
 * 
 * @author sandy
 * @version $Id: ReqController.java, v 0.1 2019年4月25日 下午1:47:21 sandy Exp $
 */
@RestController
@RequestMapping("/psi/tests/")
public class ReqController {

    private Logger        logger = LoggerFactory.getLogger(ReqController.class);

    @Autowired
    private RecordService recordService;

    @RequestMapping("/request")
    public Object doTest(String apiCode, @RequestParam String path, String method, String headers,
                         String params) throws Exception {

        if (StringUtil.isBlank(apiCode)) {
            apiCode = initApi(path, method);
        }
        RecordQuery query = new RecordQuery(RecordEnum.ApiParams.name(), null);
        query.setConditions(Arrays.asList(new Condition("apiCode", apiCode)));
        query.setDoPage(false);
        recordService.doQuery(query);
        List<String> original = null;
        List<Map<String, Object>> results;
        if (!CollectionUtils.isEmpty((results = query.getResults()))) {
            original = new ArrayList<String>();
            for (Map<String, Object> result : results) {
                original.add(result.get("param").toString());
            }
        }
        String logCode = UUID.randomUUID().toString(); //批次号，代表一次请求
        ReqLogs reqLog = new ReqLogs();
        reqLog.setApiCode(apiCode);
        reqLog.setCode(logCode);
        reqLog.setCreateTime(System.currentTimeMillis());
        reqLog.setPath(path);
        recordService.doSave(RecordEnum.ReqLogs, Arrays.asList(reqLog));

        Map<String, String> httpParams = doSaveParams(ParamType.PARAM, reqLog, params, original);
        Map<String, String> httpHeaders = doSaveParams(ParamType.HEADER, reqLog, headers, original);

        if (HttpMethod.POST.name().equalsIgnoreCase(method)) {
            return doPost(path, httpParams, httpHeaders);

        } else {
            return doGet(path, httpParams, httpHeaders);
        }
    }

    /**
     * 初始化api
     * 
     * @param path
     * @param method
     * @return
     */
    private String initApi(final String path, String method) {
        String apiPath = pathParse(path);
        RecordQuery query = new RecordQuery(RecordEnum.ApiInfo.name(), null);
        query.setConditions(Arrays.asList(new Condition("path", apiPath)));
        query.setDoPage(false);
        recordService.doQuery(query);
        if (CollectionUtils.isEmpty(query.getResults())) {
            String apiCode = UUID.randomUUID().toString();
            ApiInfo api = new ApiInfo();
            api.setPath(apiPath);
            api.setMethod(method);
            api.setCode(apiCode);
            api.setCreateTime(System.currentTimeMillis());
            recordService.doSave(RecordEnum.ApiInfo, Arrays.asList(api));
            return apiCode;
        }
        return (String) query.getResults().get(0).get("code");
    }

    /**
     * 请求路径解析
     */
    private static String pathParse(final String path) {
        //1.去除请求协议 
        String apiPath = path.replaceAll("http://|https://", "");
        //2.去除host
        int index = -1;
        if ((index = apiPath.indexOf("/")) >= 0) {
            apiPath = apiPath.substring(index);
        }
        //3.去除查询参数
        if ((index = apiPath.indexOf("?")) >= 0) {
            apiPath = apiPath.substring(0, index);
        }
        return apiPath;
    }

    private Map<String, String> doSaveParams(ParamType paramType, ReqLogs reqLog, String params,
                                             List<String> original) {
        if (StringUtil.isBlank(params)) {
            return null;
        }
        JSONArray array = JSONArray.fromObject(params);
        ApiParams param;
        Map<String, String> httpParams = new HashMap<>();
        JSONObject jsonObject = null;
        String paramKey = null;
        String paramVal = null;
        String paramRemarks = null;
        List<ApiParams> apiParams = new ArrayList<ApiParams>();
        List<ReqParams> reqParams = new ArrayList<ReqParams>();
        ReqParams reqParam = null;
        long millis = System.currentTimeMillis();

        for (Object obj : array) {
            jsonObject = JSONObject.fromObject(obj);
            paramKey = jsonObject.getString("param");
            paramVal = jsonObject.getString("value");
            paramRemarks = jsonObject.getString("remarks");
            reqParam = new ReqParams();
            reqParam.setCode(reqLog.getCode());
            reqParam.setParam(paramKey);
            reqParam.setParamType(paramType.name());
            reqParam.setRemarks(paramRemarks);
            reqParam.setValue(paramVal);
            reqParam.setCreateTime(millis);
            reqParams.add(reqParam);
            httpParams.put(paramKey, paramVal);
            if (original != null && original.contains(paramKey)) {
                continue;
            }
            param = new ApiParams();
            param.setApiCode(reqLog.getApiCode());
            param.setParamType(paramType.name());
            param.setParam(paramKey);
            param.setValue(paramVal);
            param.setRemarks(paramRemarks);
            param.setCreateTime(millis);
            apiParams.add(param);
        }
        if (!CollectionUtils.isEmpty(apiParams)) {
            try {
                recordService.doSave(RecordEnum.ApiParams, apiParams);
            } catch (Exception e) {
                logger.error("PARAM-SAVE E: {} -> {}", reqLog.getApiCode(), paramKey);
            }
        }
        if (!CollectionUtils.isEmpty(reqParams)) {
            try {
                recordService.doSave(RecordEnum.ReqParams, reqParams);
            } catch (Exception e) {
                logger.error("LOGS-SAVE E: {} -> {}", reqLog.getCode(), paramKey);
            }
        }
        return httpParams;
    }

    public String doPost(String path, Map<String, String> params,
                         Map<String, String> headers) throws JsonParseException,
                                                      JsonMappingException, IOException {
        try {
            String resp = HttpRequester.post(path, params, headers);
            logger.error("resp", resp);
            return resp;
        } catch (BusinessException e) {
            logger.error("", e);
        }
        return null;

    }

    public String doGet(String path, Map<String, String> params,
                        Map<String, String> headers) throws Exception {

        String resp = HttpRequester.get(path, headers);
        logger.error("resp", resp);
        return resp;

    }

}
