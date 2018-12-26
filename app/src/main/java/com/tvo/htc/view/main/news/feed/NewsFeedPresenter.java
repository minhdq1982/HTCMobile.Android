package com.tvo.htc.view.main.news.feed;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.NewsFeedResponse;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.model.response.YoutubeResponse;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tvo.htc.util.AppConstants.FACEBOOK_FIELDS;
import static com.tvo.htc.util.AppConstants.FACEBOOK_URL;
import static com.tvo.htc.util.AppConstants.START_OFFSET;
import static com.tvo.htc.util.AppConstants.YOUTUBE_APIKEY;
import static com.tvo.htc.util.AppConstants.YOUTUBE_BASE_URL;
import static com.tvo.htc.util.AppConstants.YOUTUBE_ORDER;
import static com.tvo.htc.util.AppConstants.YOUTUBE_PART;
import static com.tvo.htc.util.AppConstants.YOUTUBE_URL;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NewsFeedPresenter extends BasePresenter<NewsFeedContract.View> implements NewsFeedContract.Presenter {

    private SettingResponse.Setting mSetting;

    private List<NewsFeedResponse.Data> mList;
    private List<NewsFeedResponse.Data> mListDisplayed;
    private String nextUrl = getFacebookURL();
    private String pageToken = "";
    private boolean isAllowLoadMoreFB = true;
    private boolean isAllowLoadMoreYoutube = true;
    private int count = 0;

    NewsFeedPresenter(Context context) {
        super(context);
        mListDisplayed = new ArrayList<>();
    }

    private String getFacebookURL() {
        mSetting = SessionDataManager.getInstance().getSetting();
        if (mSetting != null) {
            return FACEBOOK_URL + mSetting.getFacebookPageId() + "/feed?"
                    + "fields=" + FACEBOOK_FIELDS
                    + "&limit=" + AppConstants.PAGE_LIMIT
                    + "&access_token=" + mSetting.getFacebookAccessToken();
        } else {
            return null;
        }
    }

    private String getYoutubeURL(String pageToken) {
        mSetting = SessionDataManager.getInstance().getSetting();
        if (mSetting != null) {
            return YOUTUBE_URL
                    + "?key=" + YOUTUBE_APIKEY
                    + "&channelId=" + mSetting.getYouTubeChannelId()
                    + "&part=" + YOUTUBE_PART
                    + "&order=" + YOUTUBE_ORDER
                    + "&maxResults=" + AppConstants.PAGE_LIMIT
                    + "&pageToken=" + pageToken;
        } else {
            return null;
        }
    }

    @Override
    public void loadData(int skipCount, boolean refreshing) {
        if (mSetting != null) {
            count = 0;
            mList = new ArrayList<>();
            if (refreshing) {
                mListDisplayed = new ArrayList<>();
            }
            loadDataFacebook(skipCount, refreshing);
            loadDataYoutube(skipCount, refreshing);
        } else {
            getView().addList(new ArrayList<>(), isAllowLoadMoreFB || isAllowLoadMoreYoutube);
            getView().getLoadFail();
        }
    }


    private void loadDataFacebook(int skipCount, boolean refreshing) {
        if (refreshing) {
            nextUrl = getFacebookURL();
        }
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if(skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(false, true);
        }
        RESTManager.getInstance().getFacebookApi(nextUrl, new IRequestListener<NewsFeedResponse>(httpRequestOption) {
            @Override
            public void onCompleted(NewsFeedResponse data) {
                super.onCompleted(data);
                int pageSize = data.getData() == null ? 0 : data.getData().size();
                if (pageSize == 0 || pageSize % AppConstants.PAGE_LIMIT != 0) {
                    isAllowLoadMoreFB = false;
                }
                if (data.getData() != null) {
                    addToList(getItemFB(data.getData()), isAllowLoadMoreFB || isAllowLoadMoreYoutube, skipCount);
                    nextUrl = data.getPaging().getNext();
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                if (skipCount == START_OFFSET) {
                    getView().addList(new ArrayList<>(), isAllowLoadMoreFB || isAllowLoadMoreYoutube);
                    if (mList.size() == 0) {
                        getView().getLoadFail();
                    }
                }
            }
        });
    }

    private void loadDataYoutube(int skipCount, boolean refreshing) {
        Logger.d(skipCount + "_-_");
        if (refreshing) {
            pageToken = "";
        }
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        RESTManager.getInstance().getYoutubeApi(getYoutubeURL(pageToken), new IRequestListener<YoutubeResponse>(httpRequestOption) {
            @Override
            public void onCompleted(YoutubeResponse data) {
                super.onCompleted(data);
                int pageSize = data.getItems() == null ? 0 : data.getItems().size();
                if (pageSize == 0 || pageSize % AppConstants.PAGE_LIMIT != 0) {
                    isAllowLoadMoreYoutube = false;
                }
                if (data.getItems() != null) {
                    addToList(getItemYTB(new ArrayList<>(), data), isAllowLoadMoreFB || isAllowLoadMoreYoutube, skipCount);
                }
                pageToken = data.getNextPageToken();
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                if (skipCount == START_OFFSET) {
                    getView().addList(new ArrayList<>(), isAllowLoadMoreFB || isAllowLoadMoreYoutube);
                    if (mList.size() == 0) {
                        getView().getLoadFail();
                    }
                }
            }
        });
    }

    private void addToList(List<NewsFeedResponse.Data> item, boolean isAllowLoadMore, int skipCount) {
        count++;
        mList.addAll(item);
        if (count == 2) {
            mListDisplayed.addAll(mList);
            if (skipCount == START_OFFSET) {
                getView().addList(softing(mList), isAllowLoadMore);
            } else {
                getView().updateList(softing(mListDisplayed), isAllowLoadMore);
            }
        }
    }

    private List<NewsFeedResponse.Data> getItemFB(List<NewsFeedResponse.Data> data) {
        List<NewsFeedResponse.Data> list = new ArrayList<>();
        for (NewsFeedResponse.Data x : data) {
            x.setType(4);
            x.setCreated_time(x.getCreated_time());
            x.setDescription(x.getMessage());
            list.add(x);
        }
        return list;
    }

    private List<NewsFeedResponse.Data> getItemYTB(List<NewsFeedResponse.Data> list, YoutubeResponse data) {
        if (data.getItems() == null || data.getItems().isEmpty()) {
            return list;
        }

        for (int i = 0; i < data.getItems().size(); i++) {
            YoutubeResponse.Items.Snippet snippet = data.getItems().get(i).getSnippet();
            NewsFeedResponse.Data item = new NewsFeedResponse.Data();
            item.setType(5);
            item.setCreated_time(snippet.getPublishedAt());
            item.setDescription(snippet.getDescription());
            item.setFull_picture(snippet.getThumbnails().getHigh().getUrl());
            item.setLink(YOUTUBE_BASE_URL + data.getItems().get(i).getId().getVideoId());
            item.setMessage(snippet.getTitle());
            list.add(item);
        }
        return list;
    }

    private List<NewsFeedResponse.Data> softing(List<NewsFeedResponse.Data> list) {
        Collections.sort(list, (o1, o2) -> {
            String data1 = LibUtils.convertStringToDate(o1.getCreated_time()).getTime() + "";
            String data2 = LibUtils.convertStringToDate(o2.getCreated_time()).getTime() + "";
            return data2.compareTo(data1);
        });
        return list;
    }

}
