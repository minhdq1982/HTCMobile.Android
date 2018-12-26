package com.android.lib.model.response;

import java.util.List;

public class NewsFeedResponse {

    private Paging paging;
    private List<Data> data;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Paging {

        private Cursors cursors;
        private String next;

        public Cursors getCursors() {
            return cursors;
        }

        public void setCursors(Cursors cursors) {
            this.cursors = cursors;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public static class Cursors {

            private String before;
            private String after;

            public String getBefore() {
                return before;
            }

            public void setBefore(String before) {
                this.before = before;
            }

            public String getAfter() {
                return after;
            }

            public void setAfter(String after) {
                this.after = after;
            }
        }
    }

    public static class Data {

        private String full_picture;
        private String message;
        private String created_time;
        private String link;
        private Privacy privacy;
        private String id;
        private int type;
        private String description;


        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFull_picture() {
            return full_picture;
        }

        public void setFull_picture(String full_picture) {
            this.full_picture = full_picture;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Privacy getPrivacy() {
            return privacy;
        }

        public void setPrivacy(Privacy privacy) {
            this.privacy = privacy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class Privacy {
            /**
             * allow :
             * deny :
             * description : Public
             * friends :
             * value : EVERYONE
             */

            private String allow;
            private String deny;
            private String description;
            private String friends;
            private String value;

            public String getAllow() {
                return allow;
            }

            public void setAllow(String allow) {
                this.allow = allow;
            }

            public String getDeny() {
                return deny;
            }

            public void setDeny(String deny) {
                this.deny = deny;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFriends() {
                return friends;
            }

            public void setFriends(String friends) {
                this.friends = friends;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
