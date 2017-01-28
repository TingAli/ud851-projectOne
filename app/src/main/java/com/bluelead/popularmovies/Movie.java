package com.bluelead.popularmovies;

import java.util.Date;

/**
 * Created by Imdad Ali on 28/01/2017.
 */

public class Movie {
    private int mMovieId;
    private String mTitle;
    private String mMoviePosterPath;
    private String mOverview;
    private Double mUserRating;
    private Date mReleaseDate;

    public Movie(int movieId, String title, String moviePosterPath, String overview, Double userRating,
                 Date releaseDate){
        mMovieId = movieId;
        mTitle = title;
        mMoviePosterPath = moviePosterPath;
        mOverview = overview;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
    }
}
