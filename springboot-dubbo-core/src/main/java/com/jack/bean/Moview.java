package com.jack.bean;

import java.io.Serializable;

public class Moview implements Serializable {
    //id
    public String id;
    //名称
    public String name;
    //地址
    public String url;
    //类型
    public int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
