package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i("LOG_TAG", "onStartLoading Executing..." );
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        Log.i("LOG_TAG", "loadInBackground Executing..." );

        if (mUrl == null) {
            return null;
        }

        List<News> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}