package com.codepath.twitter;

import java.util.ArrayList;
import java.util.List;

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
	private List<Tweet> tweets = new ArrayList<Tweet>();
	private TweetAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		setupViews();
		adapter = new TweetAdapter(getApplicationContext(), tweets);
		lvTimeline.setAdapter(adapter);
		EndlessScrollListener endlessScrollListener = new EndlessScrollListener() {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// TODO Auto-generated method stub
				Tweet tweet = findOldestTweet();
				long maxId = tweet == null ? -1 : tweet.getId();
				loadTweets(maxId);
				
			}

			private Tweet findOldestTweet() {	
				return adapter.getItem(adapter.getCount()-1);
			}
		};
		lvTimeline.setOnScrollListener(endlessScrollListener);
		loadTweets(-1);
	}

	private void loadTweets(final long maxId) {		
		RestClientApp.getRestClient().getHomeTimeline(maxId, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray arrayOfTweets) {
//				Log.d("DEBUG Success",arrayOfTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJSONArray(arrayOfTweets);
				//remove the first tweet as that is the last tweet
				if(tweets.get(0).getId() == maxId){
					tweets.remove(0);
				}
				adapter.addAll(tweets);			
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
