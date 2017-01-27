package com.bluelead.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements MoviePosterAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private MoviePosterAdapter mMoviePosterAdapter;
    private Toast mToast;
    private static final int NUM_LIST_ITEMS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movieGrid);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMoviePosterAdapter = new MoviePosterAdapter(NUM_LIST_ITEMS, this);
        mRecyclerView.setAdapter(mMoviePosterAdapter);
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

        if(item.getItemId() == R.id.settingsOption) {
            destinationActivity = SettingsActivity.class;
            activityIntent = new Intent(context, destinationActivity);
            startActivity(activityIntent);
        }
        return super.onOptionsItemSelected(item);
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
