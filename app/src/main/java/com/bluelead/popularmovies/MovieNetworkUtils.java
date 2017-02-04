package com.bluelead.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Imdad Ali on 28/01/2017.
 */

public class MovieNetworkUtils {
    public final static String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String BASE_URL_IMAGE_BACKDROP = "http://image.tmdb.org/t/p/w780";
    public final static String KEY_PARAM = "api_key";

    public final static String API_BASE_URL = "http://api.themoviedb.org/";
    public final static String API_KEY = "08f5e8f842b74e8eee405e07bc06c86c";
    private final static String MOVIE_QUERY = "movie";
    public final static String TOP_RATED_QUERY = "top_rated";
    public final static String POPULAR_QUERY = "popular";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static URL buildURL(String popularOrRatedQuery) { //here it builds the url
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

    public static void getMovies(final Context context){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMovieNetworkUtils apiService =
                retrofit.create(IMovieNetworkUtils.class);

        Call<ApiResponse> call = apiService.getPopular(API_KEY);
        call.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                System.out.println(response);
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_LONG).show();
                String rawJson = response.body().toString();
                System.out.println(rawJson);

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // the network call was a failure
                // handle error
                t.printStackTrace();
                Toast.makeText(context, "FAIL", Toast.LENGTH_LONG).show();

            }
        });
    }
}
