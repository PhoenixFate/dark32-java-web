package com.phoenix.utils;

import java.util.List;

public class PageBean<T> {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 总条数
     */
    private Integer totalCount;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 分页数据
     */
    private List<T> list;

    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;


        if(this.currentPage==null){
            //如果没有指定那一页，则默认第一页
            this.currentPage=1;
        }
        if(this.pageSize==null){
            //如果没有指定每页显示多少条，默认3条
            this.pageSize=3;
        }

        //计算总页数
        this.totalPage=(this.totalCount+this.pageSize-1)/this.pageSize;

        //当前页不能小于1
        if(this.currentPage<1){
            this.currentPage=1;
        }else if(this.currentPage>this.totalPage){ //当前页不能大于总页数
            this.currentPage=this.totalPage;
        }
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
