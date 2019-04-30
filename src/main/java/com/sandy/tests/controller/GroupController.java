package com.sandy.tests.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.tests.common.enums.RecordEnum;
import com.sandy.tests.common.enums.VersionType;
import com.sandy.tests.common.model.ReqResult;
import com.sandy.tests.common.util.Assert;
import com.sandy.tests.model.ApiVersion;
import com.sandy.tests.model.ApiVersions;
import com.sandy.tests.record.model.RecordQuery;
import com.sandy.tests.record.model.RecordQuery.CompareType;
import com.sandy.tests.record.model.RecordQuery.Condition;
import com.sandy.tests.record.service.RecordService;

/**
 *  api 组合控制器
 * 
 * @author sandy
 * @version $Id: GroupController.java, v 0.1 2019年4月25日 下午1:47:21 sandy Exp $
 */
@RestController
@RequestMapping("/api/tests/")
public class GroupController {

    //  private Logger logger = LoggerFactory.getLogger(GroupController.class);

    private static final String KEY_APICODE = "apiCode";

    private static final String KEY_CODE    = "code";

    @Autowired
    private RecordService recordService;

    @RequestMapping("/group/apis")
    public Object doQuryByCode(@RequestParam String versionCode) {
        //1查询  组下的 apiCode列表
        RecordQuery query = new RecordQuery(RecordEnum.ApiVersions.name(), null);
        query.setConditions(Arrays.asList(new Condition("versionCode", versionCode)));
        query.setDoPage(false);
        recordService.doQuery(query);
        List<Map<String, Object>> result = null;
        if ((result = query.getResults()) == null || result.isEmpty()) {
            return new ReqResult<>();
        }
        //2 查询api列表信息
        Condition condtion = new Condition();
        condtion.setField(KEY_CODE);
        condtion.setCompareType(CompareType.IN.name());
        List<Object> values = new ArrayList<Object>();
        for (Map<String, Object> map : result) {
            values.add(map.get(KEY_APICODE));
        }
        condtion.setValues(values);
        query = new RecordQuery(RecordEnum.ApiInfo.name(), null);
        query.setConditions(Arrays.asList(condtion));
        query.setDoPage(false);
        recordService.doQuery(query);
        return new ReqResult<>(query);
    }

    /**
     *组合数据添加
     * 
     * @param groupCode
     * @param groupTitle
     * @param apiCode
     * @param apiPath
     * @return
     * @throws Exception
     */
    @RequestMapping("/group/save")
    public Object doTest(String groupCode, String groupTitle,
                         String apiCode, String apiPath) {
        long millis = System.currentTimeMillis();
        ApiVersion version = null;
        if (StringUtils.isEmpty(groupCode)) {
            Assert.notEmpty(groupTitle, "group Title is empty!");
            groupCode = UUID.randomUUID().toString();
            version = new ApiVersion();
            version.setTitle(groupTitle);
            version.setCode(groupCode);
            version.setType(VersionType.VERSION.name());
            version.setCreateTime(millis);
            recordService.doSave(RecordEnum.ApiVersion, Arrays.asList(version));
        }
        ApiVersions versions = null;
        if (!StringUtils.isEmpty(groupCode)) {
            versions = new ApiVersions();
            versions.setVersionCode(groupCode);
            versions.setApiCode(apiCode);
            versions.setApiPath(apiPath);
            versions.setCreateTime(millis);
            recordService.doSave(RecordEnum.ApiVersions, Arrays.asList(versions));
        }
        return new ReqResult<>(versions);
    }



}
