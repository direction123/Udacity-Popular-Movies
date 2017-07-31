package com.example.popmovies.async;

import android.os.AsyncTask;

import com.example.popmovies.adapter.MovieReviewAdapter;
import com.example.popmovies.utilities.MovieJsonUtils;
import com.example.popmovies.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by fangxiangwang on 7/30/17.
 */

public class FetchMovieReviewTask extends AsyncTask<String, Void, String[]> {
    private MovieReviewAdapter mMovieReviewAdapter;

    public FetchMovieReviewTask (MovieReviewAdapter movieReviewAdapter) {
        mMovieReviewAdapter = movieReviewAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String[] doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        String movieId = params[0];
        URL moviePopRequestUrl = NetworkUtils.buildReviewUrl(movieId);
        try {
            String jsonMovieReviewResponse = NetworkUtils.getResponseFromHttpUrl(moviePopRequestUrl);
            String[] movieReviewListJsonStrings = MovieJsonUtils.getMovieListJsonStrings(jsonMovieReviewResponse);
            return movieReviewListJsonStrings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String[] movieData) {
        if (movieData != null) {
            mMovieReviewAdapter.setMoiveReviewData(movieData);
        }
    }

}
