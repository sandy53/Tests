package com.sandy.tests.common.model;

import java.io.Serializable;

/**
 *  分页模型类
 * 
 * @author zhangyg
 * @version $Id: Pages.java, v 0.1 2016年12月30日 下午1:44:04 zhangyg Exp $
 */
public class Paging implements Serializable {

    /**  */
    private static final long serialVersionUID = -7577086978726246198L;
    /**
     * 当前页面
     */
    private int               currentPage      = 1;
    /**
     * 总记录条数
     */
    private int               total;
    /**
     * 总页数
     */
    private int               pages;

    /**
     *  分页起始位置 
     */
    private int               limitStart       = 0;
    /**
     * 单页显示记录数量
     */
    private int               limitNum         = 10;

    /**
     *  请求第一页的时间
     */
    private long              nowTime;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPages() {
        this.pages = (this.total + (this.limitNum - 1)) / this.limitNum;
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getLimitStart() {
        this.limitStart = (this.currentPage - 1) * this.limitNum;
        return this.limitStart;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart = limitStart;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public long getNowTime() {
        if (nowTime == 0) {
            return System.currentTimeMillis();
        } else {
            return nowTime;
        }

    }

    public void setNowTime(long nowTime) {
        this.nowTime = System.currentTimeMillis();
    }

}
