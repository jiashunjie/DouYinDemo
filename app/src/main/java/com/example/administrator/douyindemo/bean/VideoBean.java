package com.example.administrator.douyindemo.bean;
/**
 * @author Jshunjie
 * @date 2018/5/25
 * @description 视频数据实体类
 */
public class VideoBean {

    private String title;
    private String url;
    private String thumb;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public VideoBean(String title, String thumb, String url) {
        this.title = title;
        this.url = url;
        this.thumb = thumb;

    }
}
