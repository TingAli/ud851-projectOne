package com.bluelead.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.*;


/**
 * Created by Imdad Ali on 28/01/2017.
 */

public class MovieNetworkUtils {
    private final static String POSTER_BASE_URL = " http://image.tmdb.org/t/p/";
    private final static String POSTER_SIZE = "w185";
    private final static String KEY_PARAM = "api_key";

    private final static String API_BASE_URL = "http://api.themoviedb.org/3";
    private final static String API_KEY = "08f5e8f842b74e8eee405e07bc06c86c";
    private final static String MOVIE_QUERY = "movie";
    public final static String TOP_RATED_QUERY = "top_rated";
    public final static String POPULAR_QUERY = "popular";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static URL buildURL(String popularOrRatedQuery) {
        Uri buildUri = Uri.parse(API_BASE_URL).buildUpon()
                .appendPath(MOVIE_QUERY)
                .appendPath(popularOrRatedQuery)
                .appendQueryParameter(KEY_PARAM, API_KEY)
                .build();

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static void getResponseFromHttpUrl(URL url) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Movie service = retrofit.create(Movie.class);
    }
}
