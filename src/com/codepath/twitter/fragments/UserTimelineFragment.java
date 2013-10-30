package com.codepath.twitter.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.codepath.models.Tweet;
import com.codepath.twitter.RestClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {

	private String screenName;

	@Override
	protected void loadTweets(final long maxId) {
		RestClientApp.getRestClient().getUserTimeline(screenName, maxId,
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
						// Log.d("DEBUG Success",arrayOfTweets.toString());
						ArrayList<Tweet> tweets = Tweet
								.fromJSONArray(arrayOfTweets);
						newlyLoadedTweets(tweets);						
					}
				});
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		screenName =  getArguments().getString("screen_name");
		loadTweets(-1);
	}

}
