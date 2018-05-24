package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the activity
     * @param url to load data from
     */

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        List<News> result = QueryUtils.fetchNewsData(mUrl);
        return result ;
    }
}