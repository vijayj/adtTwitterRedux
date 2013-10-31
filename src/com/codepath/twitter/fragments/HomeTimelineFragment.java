package com.codepath.twitter.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.models.Tweet;
import com.codepath.twitter.RestClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class HomeTimelineFragment extends TweetsListFragment {

	@Override
	protected void loadTweets(final long maxId) {
		RestClientApp.getRestClient().getHomeTimeline(maxId,
				new JsonHttpResponseHandler() {

					@Override
					public void onFailure(Throwable t, JSONArray array) {
						Log.d("DEBUG Timeline failure", t.getMessage() + " : "
								+ array.toString());
						super.onFailure(t, array);
					}

					@Override
					public void onFailure(Throwable t, JSONObject object) {
						Log.d("DEBUG Timeline failure", t.getMessage() + " : "
								+ object.toString());
						super.onFailure(t, object);
					}

					@Override
					public void onSuccess(JSONArray arrayOfTweets) {
						ArrayList<Tweet> tweets = Tweet
								.fromJSONArray(arrayOfTweets);
						newlyLoadedTweets(tweets);
					}
				});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadTweets(-1);
	}
}
