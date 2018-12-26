package com.android.lib.model.response;

import java.util.List;

public class FacebookGroupResponse extends BaseResponse {

    /**
     * data : {"totalCount":0,"items":[{"name":"string","link":"string","id":0}]}
     */

    private Group data;

    public Group getData() {
        return data;
    }

    public void setData(Group data) {
        this.data = data;
    }

    public static class Group {
        /**
         * totalCount : 0
         * items : [{"name":"string","link":"string","id":0}]
         */

        private int totalCount;
        private List<Items> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public static class Items {
            /**
             * name : string
             * link : string
             * id : 0
             */

            private String name;
            private String link;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
