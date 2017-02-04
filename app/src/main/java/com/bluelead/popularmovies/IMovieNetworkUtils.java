package com.bluelead.popularmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Imdad Ali on 02/02/2017.
 */

public interface IMovieNetworkUtils {
    @GET("movie/popular") //http://api.themoviedb.org/3/movie/popular?api_key=08f5e8f842b74e8eee405e07bc06c86c
    Call<List<Movie>> popularGet(@Query("api_key") String apikey);

    @GET("movie/top_rated") //http://api.themoviedb.org/3/movie/popular?api_key=08f5e8f842b74e8eee405e07bc06c86c
    Call<List<Movie>> topRatedGet(@Query("api_key") String apikey);
}
