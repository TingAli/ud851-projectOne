package com.bluelead.popularmovies;

/**
 * Created by Imdad Ali on 06/02/2017.
 */

public interface Callback<T> {
    void next(T result);
}
