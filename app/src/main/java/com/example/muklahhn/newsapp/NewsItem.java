package com.example.muklahhn.newsapp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Muklah H N on 14/07/2018.
 */

public class NewsItem implements Parcelable {

    private String section_name;
    private String web_publication_date;
    private String web_title;
    private String web_url;
    private String tags_web_title;

    public NewsItem(String section_name, String web_publication_date, String web_title, String web_url, String tags_web_title) {
        this.section_name = section_name;
        this.web_publication_date = web_publication_date;
        this.web_title = web_title;
        this.web_url = web_url;
        this.tags_web_title = tags_web_title;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(section_name);
        out.writeString(web_publication_date);
        out.writeString(web_title);
        out.writeString(web_url);
        out.writeString(tags_web_title);
    }

    private NewsItem(Parcel in) {
        this.section_name              = in.readString();
        this.web_publication_date               = in.readString();
        this.web_title         = in.readString();
        this.web_url               = in.readString();
        this.tags_web_title               = in.readString();
    }

    public NewsItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<NewsItem> CREATOR = new Parcelable.Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }
        @Override
        public NewsItem[] newArray(int i) {
            return new NewsItem[i];
        }
    };

    public String getSectionName() {
        return section_name;
    }

    public String getWebPublicationDate() {
        return web_publication_date;
    }

    public String getWebTitle() {
        return web_title;
    }

    public String getWebUrl() {
        return web_url;
    }

    public String getTagsWebTitle() {
        return tags_web_title;
    }

}
