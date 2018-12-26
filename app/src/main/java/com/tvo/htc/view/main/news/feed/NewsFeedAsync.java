package com.tvo.htc.view.main.news.feed;

import android.os.AsyncTask;

import com.android.lib.model.response.NewsFeedResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedAsync extends AsyncTask<String, Void, List<NewsFeedResponse.Data>> {

    private List<NewsFeedResponse.Data> list;

    private GetYTBList setListener;

    public void setSetListener(GetYTBList setListener) {
        this.setListener = setListener;
    }

    @Override
    protected List<NewsFeedResponse.Data> doInBackground(String... strings) {
        list = new ArrayList<>();
        Document document;
        try {
            document = Jsoup.connect(strings[0]).get();
            Elements sub = document.select("feed > entry");
            //Elements group = sub;

            for (Element element : sub) {
                NewsFeedResponse.Data data = new NewsFeedResponse.Data();
                Element title = element.getElementsByTag("title").first();
                Element link = element.getElementsByTag("link").first();
                Element published = element.getElementsByTag("published").first();
                Element group = element.child(8);
                Element thumbnail = group.child(2);
                Element description = group.child(3);

                data.setMessage(title.text());
                data.setLink(link.attr("href"));
                data.setCreated_time(published.text());
                data.setFull_picture(thumbnail.attr("url"));
                data.setDescription(description.text());

                list.add(data);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<NewsFeedResponse.Data> list) {
        super.onPostExecute(list);
        if (list == null)
            setListener.getError();
        else
            setListener.getYtbList(list);
    }

    interface GetYTBList {
        void getYtbList(List<NewsFeedResponse.Data> list);

        void getError();
    }
}
