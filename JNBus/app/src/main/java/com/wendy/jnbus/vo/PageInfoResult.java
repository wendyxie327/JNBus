package com.wendy.jnbus.vo;

import java.util.List;

/**
 * 对于Response类中，result有分页功能的展示
 * Created by Wendy on 2016/12/16.
 */
public class PageInfoResult<T> {

    private PageInfo pageParam;// 分页基本信息
    private List<T> result;

    public class PageInfo {

        private int offset; // 开始页码
        private int len ;   //长度
        private int totalNum; //总共几个

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }
    }


    public PageInfo getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageInfo pageParam) {
        this.pageParam = pageParam;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
