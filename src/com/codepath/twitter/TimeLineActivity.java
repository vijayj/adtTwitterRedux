package com.codepath.twitter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.codepath.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimeLineActivity extends Activity {

	private ListView lvTimeline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		setupViews();
		loadTweets();
	}

	private void loadTweets() {		
		RestClientApp.getRestClient().getHomeTimeline(0, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray arrayOfTweets) {
//				Log.d("DEBUG Success",arrayOfTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJSONArray(arrayOfTweets);
				TweetAdapter adapter = new TweetAdapter(getApplicationContext(), tweets);
				lvTimeline.setAdapter(adapter);
			}

			@Override
			public void onFailure(Throwable t, JSONArray array) {
				Log.d("DEBUG Timeline failure", t.getMessage() + " : " +  array.toString());
				super.onFailure(t, array);
			}
			
			@Override
			public void onFailure(Throwable t, JSONObject object) {
				Log.d("DEBUG Timeline failure", t.getMessage() + " : " +  object.toString());
				super.onFailure(t, object);
			}			
		});
	}

	private void setupViews() {
		lvTimeline = (ListView) findViewById(R.id.lvTimeline);
//		adapter = new TweetAdapter(this, resource, objects)
//		lvTimeline.setAdapter(adapter)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

}
