package com.bluelead.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Imdad Ali on 28/01/2017.
 */

public class MovieNetworkUtils {
    private final static String POSTER_BASE_URL = " http://image.tmdb.org/t/p/";
    private final static String POSTER_SIZE = "w185";
    private final static String KEY_PARAM = "api_key";

    public final static String API_BASE_URL = "http://api.themoviedb.org/3/";
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

    public static void getResponseFromHttpUrl(final Context context, String query) throws IOException {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Builder builder =
                new Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create(gson)
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        IMovieNetworkUtils client =  retrofit.create(IMovieNetworkUtils.class);

        Call<List<Movie>> call =
                client.movieQuery(query);

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                // The network call was a success and we got a response
                // use the repository list and display it
                System.out.println(response);
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_LONG).show();

                List<Movie> moviesList = response.body();
                for(Movie movie : moviesList) {
                    System.out.println(movie);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                // the network call was a failure
                // handle error
                t.printStackTrace();
                Toast.makeText(context, "FAIL", Toast.LENGTH_LONG).show();
            }
        });
    }
}
