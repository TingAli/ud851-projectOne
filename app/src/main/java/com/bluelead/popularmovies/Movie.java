package com.bluelead.popularmovies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Imdad Ali on 28/01/2017.
 */

public class Movie implements Serializable {
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";

    @SerializedName("id")
    private int mMovieId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mMoviePosterPath;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("vote_average")
    private Double mUserRating;
    @SerializedName("release_date")
    private Date mReleaseDate;

    public int getmMovieId() {
        return mMovieId;
    }

    public String getMovieTitle() {
        return mTitle;
    }

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }

    public String getMovieOverview() {
        return mOverview;
    }

    public Double getMovieUserRating() {
        return mUserRating;
    }

    public Date getMovieReleaseDate() {
        return mReleaseDate;
    }

}
