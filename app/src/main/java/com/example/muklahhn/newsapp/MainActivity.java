package com.example.muklahhn.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        NewsAdapter.RecyclerViewAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<ArrayList<NewsItem>> {

    NewsAdapter mAdapter;
    RecyclerView mNewsList;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    private static final int NEWS_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsList = findViewById(R.id.rv_news);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mNewsList.setLayoutManager(LayoutManager);
        mNewsList.setHasFixedSize(true);
        mAdapter = new NewsAdapter(this);
        mNewsList.setAdapter(mAdapter);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        int loaderId = NEWS_LOADER_ID;

        LoaderManager.LoaderCallbacks<ArrayList<NewsItem>> callback = MainActivity.this;
        getSupportLoaderManager().initLoader(loaderId, null, callback);
    }

    @Override
    public Loader<ArrayList<NewsItem>> onCreateLoader(int id, final Bundle loaderArgs) {

        return new AsyncTaskLoader<ArrayList<NewsItem>>(this) {

            ArrayList<NewsItem> mNewsData = null;

            @Override
            protected void onStartLoading() {
                if (mNewsData != null) {
                    deliverResult(mNewsData);
                } else {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public ArrayList<NewsItem> loadInBackground() {

                URL newsRequestUrl = Networking.buildUrl();

                try {
                    String jsonNewsResponse = Networking.makeHttpRequest(newsRequestUrl);

                    ArrayList<NewsItem> simpleJsonNewsData = Json.getSimpleNewsStringsFromJson(MainActivity.this, jsonNewsResponse);

                    return simpleJsonNewsData;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(ArrayList<NewsItem> data) {
                mNewsData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsItem>> loader, ArrayList<NewsItem> data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mAdapter.setNewsData(data);
        if (null == data) {
            showErrorMessage();
        } else {
            showNewsDataView();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsItem>> loader) {
    }

    @Override
    public void onClick(NewsItem news) {
        Uri newsUri = Uri.parse(news.getWebUrl());
        Intent intentToStartDetailActivity = new Intent(Intent.ACTION_VIEW, newsUri);
        if (intentToStartDetailActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(intentToStartDetailActivity);
        }
    }

    private void showNewsDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mNewsList.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mNewsList.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

}
