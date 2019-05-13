package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;



/**
 *  api请求日志结果
 * 
 * @author sandy
 * @version $Id: ReqResponse.java, v 0.1 2019年5月13日 下午8:59:57 sandy Exp $
 */
@Genernate(value = "ts_req_response", desc = "api请求结果")
public class ReqResponse extends RecordModel {


    /**  */
    private static final long serialVersionUID = 9015026656211256009L;

    @Genernate(desc = "记录编码")
    private String            code;
    @Genernate(value = "result", desc = "结果内容")
    private String            result;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



}
