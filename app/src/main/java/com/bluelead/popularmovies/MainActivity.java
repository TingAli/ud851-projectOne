package com.bluelead.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;

public class MainActivity extends Activity
        implements MoviePosterAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private TextView mErrorMessageTextView;
    private MoviePosterAdapter mMoviePosterAdapter;
    private Toast mToast;
    private final Context CONTEXT = MainActivity.this;
    private ArrayList<Movie> mMoviesList;
    public static final int NUM_LIST_ITEMS = 6;
    private Bundle mBundle;
    private SharedPreferences mSharedPreferences;
    private final String[] QUERIES = new String[] {
            new String(MovieNetworkUtils.POPULAR_QUERY),
            new String(MovieNetworkUtils.TOP_RATED_QUERY)
    };

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

        // needs to check sharepreferences for query and sets to popular by default otherwise

        // Start
        makeMovieQuery();
    }

    private void makeMovieQuery(){
        if(MovieNetworkUtils.isOnline(CONTEXT)) {
            new MovieQueryTask().execute();
        }
        else {
            Toast.makeText(CONTEXT, "ERROR: No Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public class MovieQueryTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            MovieNetworkUtils.getMovies(CONTEXT, MovieNetworkUtils.POPULAR_QUERY, 0,
                    new Callback<ArrayList<Movie>>() {
                @Override
                public void next(ArrayList<Movie> result) {
                    mMoviesList = result;
                    if(mMoviesList != null) {
                        showJsonDataView();
                    }
                    else {
                        showErrorMessage();
                    }
                }
            });

            return mMoviesList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> moviesList) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
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
        Context CONTEXT = MainActivity.this;
        Intent activityIntent;
        Class destinationActivity;

        switch(item.getItemId()) {
            case (R.id.settingsOption):
                destinationActivity = SettingsActivity.class;
                activityIntent = new Intent(CONTEXT, destinationActivity);
                startActivity(activityIntent);
                return true;
            case (R.id.refreshOption):
                makeMovieQuery();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showJsonDataView() {
        mMoviePosterAdapter = new MoviePosterAdapter(CONTEXT, NUM_LIST_ITEMS,
                MainActivity.this, mMoviesList);
        mRecyclerView.setAdapter(mMoviePosterAdapter);
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

        // Intent for starting new DetailsActivity here...
        Class detailsActivity = DetailsActivity.class;
        Intent detailsIntent = new Intent(CONTEXT, detailsActivity);

        mBundle = new Bundle();
        Movie movieAtIndex = mMoviesList.get(clickedItemIndex);
        mBundle.putParcelable("PAR_KEY", movieAtIndex);
        detailsIntent.putExtras(mBundle);

        startActivity(detailsIntent);
    }
}
