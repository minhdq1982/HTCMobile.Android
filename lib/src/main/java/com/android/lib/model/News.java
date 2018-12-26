package com.android.lib.model;

public class News {

    /**
     * image : null
     * title : Tin từ HTC thông báo
     * shortContent : Tin từ HTC thông báo
     * fullContent : Thông báo tặng mỗi khách hàng 2 tỷ cho vui
     * source : CMS
     * creationTime : 2018-11-09T00:00:00
     * lastModificationTime : null
     * id : 11
     */

    private String image;
    private String title;
    private String shortContent;
    private String fullContent;
    private String source;
    private String creationTime;
    private String lastModificationTime;
    private int id;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
