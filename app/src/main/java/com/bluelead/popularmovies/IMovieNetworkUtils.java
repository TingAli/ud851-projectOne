package com.bluelead.popularmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Imdad Ali on 02/02/2017.
 */

public interface IMovieNetworkUtils {
    @GET("movie/{query}?api_key=08f5e8f842b74e8eee405e07bc06c86c") //http://api.themoviedb.org/3/movie/popular?api_key=08f5e8f842b74e8eee405e07bc06c86c
    Call<List<Movie>> movieQuery(
            @Path("query") String query
    );
}
