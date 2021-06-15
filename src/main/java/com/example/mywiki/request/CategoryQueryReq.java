package com.example.mywiki.request;

/**
 * Created by tangssst@qq.com on 2021/06/15
 */
public class CategoryQueryReq extends PageReq{
    private Long id;

    private Long parent;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryQueryReq{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
