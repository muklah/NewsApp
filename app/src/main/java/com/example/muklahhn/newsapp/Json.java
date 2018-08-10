package com.example.muklahhn.newsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Muklah H N on 14/07/2018.
 */

public class Json {

    public static ArrayList<NewsItem> getSimpleNewsStringsFromJson(Context context, String newsJsonString)
            throws JSONException {

        final String RESULTS = "results";
        final String RESPONSE = "response";
        final String SECTION_NAME = "sectionName";
        final String WEB_PUBLICATION_DATE = "webPublicationDate";
        final String WEB_TITLE = "webTitle";
        final String WEB_URL = "webUrl";

        ArrayList<NewsItem> parsedNewsData = new ArrayList<NewsItem>();

        JSONObject newsObjects = new JSONObject(newsJsonString);
        JSONObject responseObject = newsObjects.getJSONObject(RESPONSE);
        JSONArray newsArray = responseObject.getJSONArray(RESULTS);

        for (int i = 0; i < newsArray.length(); i++) {
            String section_name;
            String web_publication_date;
            String web_title;
            String web_url;

            JSONObject newsObject = newsArray.getJSONObject(i);

            section_name = newsObject.getString(SECTION_NAME);
            web_publication_date = newsObject.getString(WEB_PUBLICATION_DATE);
            web_title = newsObject.getString(WEB_TITLE);
            web_url = newsObject.getString(WEB_URL);

            parsedNewsData.add(new NewsItem(section_name, web_publication_date, web_title, web_url));

        }

        return parsedNewsData;
    }
}