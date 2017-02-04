package com.bluelead.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends Activity implements MoviePosterAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private TextView mErrorMessageTextView;
    private MoviePosterAdapter mMoviePosterAdapter;
    private Toast mToast;
    private Context context = MainActivity.this;
    private static final int NUM_LIST_ITEMS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessageTextView = (TextView) findViewById(R.id.tv_error_message_display);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movieGrid);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMoviePosterAdapter = new MoviePosterAdapter(NUM_LIST_ITEMS, this);
        mRecyclerView.setAdapter(mMoviePosterAdapter);

        if(MovieNetworkUtils.isOnline(context)) {
            Toast.makeText(context, "CONNECTED!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "NOT CONNECTED", Toast.LENGTH_SHORT).show();
        }

        //TEST AREA START

        //TEST AREA END

    }

    private void makeMovieQuery(){
        URL url = MovieNetworkUtils.buildURL(MovieNetworkUtils.POPULAR_QUERY);
        new MovieQueryTask().execute(url);
    }

    public class MovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            String result = null;

            MovieNetworkUtils.getMovies(context);

            return result;
        }

        @Override
        protected void onPostExecute(String searchResults) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            if(searchResults != null && !searchResults.equals("")) {
                showJsonDataView();
                //show data
            }
            else {
                showErrorMessage();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = MainActivity.this;
        Intent activityIntent;
        Class destinationActivity;

        switch(item.getItemId()) {
            case (R.id.settingsOption):
                destinationActivity = SettingsActivity.class;
                activityIntent = new Intent(context, destinationActivity);
                startActivity(activityIntent);
                return true;
            case (R.id.refreshOption):
                makeMovieQuery();
                mMoviePosterAdapter = new MoviePosterAdapter(NUM_LIST_ITEMS, this);
                mRecyclerView.setAdapter(mMoviePosterAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        // First, hide the currently visible data
        mRecyclerView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
}
