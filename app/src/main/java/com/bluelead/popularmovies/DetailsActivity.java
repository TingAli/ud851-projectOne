package com.bluelead.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends Activity {
    private Toast mToast;
    private final Context CONTEXT = DetailsActivity.this;
    private TextView mOriginalTitleTextView, mOverviewTextView, mVoteAverageTextView,
            mReleaseDateTextView;
    private ImageView mMoviePosterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mOriginalTitleTextView = (TextView) findViewById(R.id.original_title_tv);
        mOverviewTextView = (TextView) findViewById(R.id.movie_overview_tv);
        mVoteAverageTextView = (TextView) findViewById(R.id.movie_vote_average_tv);
        mReleaseDateTextView = (TextView) findViewById(R.id.movie_release_date_tv);
        mMoviePosterImageView = (ImageView) findViewById(R.id.movie_poster_iv);

        mOriginalTitleTextView.setText("ORIGINAL TITLE..");

    }
}
