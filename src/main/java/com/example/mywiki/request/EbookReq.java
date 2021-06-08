package com.example.mywiki.request;

/**
 * 请求参数封装
 */
public class EbookReq {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

      public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EbookReq{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}