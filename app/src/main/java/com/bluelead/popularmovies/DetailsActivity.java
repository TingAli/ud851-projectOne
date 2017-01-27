package com.bluelead.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = DetailsActivity.this;
        Intent activityIntent;
        Class destinationActivity;

        if(item.getItemId() == R.id.settingsOption) {
            destinationActivity = SettingsActivity.class;
            activityIntent = new Intent(context, destinationActivity);
            startActivity(activityIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
