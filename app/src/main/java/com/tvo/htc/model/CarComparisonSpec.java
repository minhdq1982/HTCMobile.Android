package com.tvo.htc.model;

import android.util.Pair;

import java.util.List;

public class CarComparisonSpec {

    private String name;
    private List<Item> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private String name;
        private Pair<String, String> value;
        private boolean isGroup;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Pair<String, String> getValue() {
            return value;
        }

        public void setValue(Pair<String, String> value) {
            this.value = value;
        }

        public boolean isGroup() {
            return isGroup;
        }

        public void setGroup(boolean group) {
            isGroup = group;
        }
    }
}
