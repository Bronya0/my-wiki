package com.example.mywiki.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 分页请求参数封装
 */
public class PageReq {

    //页码
    @NotNull(message = "页码不能为空")
    private int page;

    //条数，限制非空、最大数
    @NotNull(message = "条数不能为空")
    @Max(value = 1000,message = "最大查询数：1000")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageReq{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}