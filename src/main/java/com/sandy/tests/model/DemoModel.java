package com.sandy.tests.model;

import com.sandy.tests.record.model.RecordModel;
import com.sandy.tests.record.util.Genernate;

/**
 *  demo 模型
 * 
 * @author sandy
 * @version $Id: DemoModel.java, v 0.1 2019年4月29日 下午7:49:37 sandy Exp $
 */
@Genernate(value = "ts_demo", desc = "demo表")
public class DemoModel extends RecordModel {

    /**  */
    private static final long serialVersionUID = 5118256829619710675L;

    @Genernate(value = "demo_title", desc = "demo标题")
    private String            demoTitle;

    public String getDemoTitle() {
        return demoTitle;
    }

    public void setDemoTitle(String demoTitle) {
        this.demoTitle = demoTitle;
    }

}
