package com.bluelead.popularmovies;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
