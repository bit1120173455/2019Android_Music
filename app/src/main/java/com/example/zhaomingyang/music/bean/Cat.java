package com.example.zhaomingyang.music.bean;

import com.google.gson.annotations.SerializedName;

public class Cat {
    @SerializedName("id") private String id;
//        @SerializedName("categories") private List<category> categories;
    @SerializedName("url") private String url;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override public String toString() {
        return "{" +
                "breeds"+"[]"+
                ", categories=" +
//                    ", categories=" + categories +
                ", id=" + id +
                ", url='" + url + '\'' +
                '}';

    }
}
