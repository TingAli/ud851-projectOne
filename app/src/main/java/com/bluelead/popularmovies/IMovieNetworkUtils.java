package com.bluelead.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Imdad Ali on 02/02/2017.
 */

public interface IMovieNetworkUtils {
    @GET("3/movie/popular")
    Call<ApiResponse> getPopular(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Call<ApiResponse> getTopRated(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<ApiResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
