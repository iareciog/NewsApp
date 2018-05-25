package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    /** Loader id for the AsynkTaskLoader */
    private static final int NEWS_LOADER_ID = 1;

    /** Adapter for the list of news */
    private NewsAdapter mAdapter;

    /**TextView for the Empty State */
    private TextView mEmptyStateTextView;

    /** URL of the guardian site for obtain the news */
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?&show-tags=contributor&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);
        // Find the {@link TextView} empty view in the layout
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Obtain the connectivity details for processing
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get the info for active networks
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        // Obtain in boolean if is connected or not.
        final boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current news that was clicked on
                News currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        /*
         * If isConnected is true the LoaderManager will execute the Async Task.
         * If not. Find the loading spinner and change the view to gone and set the text for empty
         * state.
         */

        if (isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).


            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        }else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connections);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> earthquakes) {
        mEmptyStateTextView.setText(R.string.no_news);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);
        mAdapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i("LOG_TAG", "onLoaderReset Executing..." );
        mAdapter.clear();
    }
}
