package com.android.lib.model.response;

import com.android.lib.model.News;

import java.util.List;

public class GroupNews {
    /**
     * id : 0
     * name : string
     * displayOrder : 0
     * items : [{"image":"string","title":"string","shortContent":"string","fullContent":"string","newsCategory":0,"newsSource":"string","creationTime":"2018-11-07T10:54:09.298Z","lastModificationTime":"2018-11-07T10:54:09.298Z","id":0}]
     */

    private int id;
    private String name;
    private int displayOrder;
    private List<News> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<News> getItems() {
        return items;
    }

    public void setItems(List<News> items) {
        this.items = items;
    }
}
