package com.android.lib.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeResponse {

    private String kind;
    private String etag;
    private String nextPageToken;
    private String regionCode;
    private PageInfo pageInfo;
    private List<Items> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class PageInfo {
        /**
         * totalResults : 178
         * resultsPerPage : 20
         */

        private int totalResults;
        private int resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(int resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }
    }

    public static class Items {
        /**
         * kind : youtube#searchResult
         * etag : "XI7nbFXulYBIpL0ayR_gDh3eu1k/gmTVUYF52IrjZWACK-V3f8ecFzI"
         * id : {"kind":"youtube#video","videoId":"arSdtiF5byY"}
         * snippet : {"publishedAt":"2018-07-30T06:36:47.000Z","channelId":"UCCbtto6O9fmY_iSn0m3h7ng","title":"Thử nghiệm hệ thống ESC - Hướng dẫn QUAY ĐẦU XE 180 độ","description":"Hyundai Elantra là một trong những mẫu xe an toàn nhất của Hyundai. Và Video dưới đây giúp bạn hiểu hơn về tính năng an toàn: Hệ thống cân bằng điện tử...","thumbnails":{"default":{"url":"https://i.ytimg.com/vi/arSdtiF5byY/default.jpg","width":120,"height":90},"medium":{"url":"https://i.ytimg.com/vi/arSdtiF5byY/mqdefault.jpg","width":320,"height":180},"high":{"url":"https://i.ytimg.com/vi/arSdtiF5byY/hqdefault.jpg","width":480,"height":360}},"channelTitle":"Hyundai Thành Công Việt Nam","liveBroadcastContent":"none"}
         */

        private String kind;
        private String etag;
        private Id id;
        private Snippet snippet;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public static class Id {
            /**
             * kind : youtube#video
             * videoId : arSdtiF5byY
             */

            private String kind;
            private String videoId;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }

        public static class Snippet {
            /**
             * publishedAt : 2018-07-30T06:36:47.000Z
             * channelId : UCCbtto6O9fmY_iSn0m3h7ng
             * title : Thử nghiệm hệ thống ESC - Hướng dẫn QUAY ĐẦU XE 180 độ
             * description : Hyundai Elantra là một trong những mẫu xe an toàn nhất của Hyundai. Và Video dưới đây giúp bạn hiểu hơn về tính năng an toàn: Hệ thống cân bằng điện tử...
             * thumbnails : {"default":{"url":"https://i.ytimg.com/vi/arSdtiF5byY/default.jpg","width":120,"height":90},"medium":{"url":"https://i.ytimg.com/vi/arSdtiF5byY/mqdefault.jpg","width":320,"height":180},"high":{"url":"https://i.ytimg.com/vi/arSdtiF5byY/hqdefault.jpg","width":480,"height":360}}
             * channelTitle : Hyundai Thành Công Việt Nam
             * liveBroadcastContent : none
             */

            private String publishedAt;
            private String channelId;
            private String title;
            private String description;
            private Thumbnails thumbnails;
            private String channelTitle;
            private String liveBroadcastContent;

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public void setChannelTitle(String channelTitle) {
                this.channelTitle = channelTitle;
            }

            public String getLiveBroadcastContent() {
                return liveBroadcastContent;
            }

            public void setLiveBroadcastContent(String liveBroadcastContent) {
                this.liveBroadcastContent = liveBroadcastContent;
            }

            public static class Thumbnails {
                /**
                 * default : {"url":"https://i.ytimg.com/vi/arSdtiF5byY/default.jpg","width":120,"height":90}
                 * medium : {"url":"https://i.ytimg.com/vi/arSdtiF5byY/mqdefault.jpg","width":320,"height":180}
                 * high : {"url":"https://i.ytimg.com/vi/arSdtiF5byY/hqdefault.jpg","width":480,"height":360}
                 */

                @SerializedName("default")
                private Default defaultX;
                private Medium medium;
                private High high;

                public Default getDefaultX() {
                    return defaultX;
                }

                public void setDefaultX(Default defaultX) {
                    this.defaultX = defaultX;
                }

                public Medium getMedium() {
                    return medium;
                }

                public void setMedium(Medium medium) {
                    this.medium = medium;
                }

                public High getHigh() {
                    return high;
                }

                public void setHigh(High high) {
                    this.high = high;
                }

                public static class Default {
                    /**
                     * url : https://i.ytimg.com/vi/arSdtiF5byY/default.jpg
                     * width : 120
                     * height : 90
                     */

                    private String url;
                    private int width;
                    private int height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }
                }

                public static class Medium {
                    /**
                     * url : https://i.ytimg.com/vi/arSdtiF5byY/mqdefault.jpg
                     * width : 320
                     * height : 180
                     */

                    private String url;
                    private int width;
                    private int height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }
                }

                public static class High {
                    /**
                     * url : https://i.ytimg.com/vi/arSdtiF5byY/hqdefault.jpg
                     * width : 480
                     * height : 360
                     */

                    private String url;
                    private int width;
                    private int height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }
                }
            }
        }
    }
}
