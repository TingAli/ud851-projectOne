package com.bluelead.popularmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Imdad Ali on 02/02/2017.
 */

public interface IMovieNetworkUtils {
    @GET("/movie/popular")
    Call<List<Movie>> reposForUser(
            @Path("user") String user
    );
}
