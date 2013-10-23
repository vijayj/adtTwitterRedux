package com.codepath.twitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.codepath.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimeLineActivity extends Activity {

	public static final int COMPOSE_REQUEST_CODE = 2001;
	private ListView lvTimeline;
	private List<Tweet> tweets = new ArrayList<Tweet>();
	private TweetAdapter adapter;
	private boolean savedTweets = false;

	private void loadTweets(final long maxId) {
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
						// Log.d("DEBUG Success",arrayOfTweets.toString());
						ArrayList<Tweet> tweets = Tweet
								.fromJSONArray(arrayOfTweets);
						if (!savedTweets) {
							saveTweets(tweets);
							savedTweets = true;
						}
						adapter.addAll(tweets);
					}
				});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		setupViews();
		adapter = new TweetAdapter(getApplicationContext(), tweets);
		lvTimeline.setAdapter(adapter);
		EndlessScrollListener endlessScrollListener = new EndlessScrollListener() {

			private Tweet findOldestTweet() {
				return adapter.getItem(adapter.getCount() - 1);
			}

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// TODO Auto-generated method stub
				Tweet tweet = findOldestTweet();
				long maxId = tweet == null ? -1 : tweet.getTwitterId();
				loadTweets(maxId);

			}
		};
		lvTimeline.setOnScrollListener(endlessScrollListener);

		loadOldTweets();
		loadTweets(-1);
		// TODO(VJ) - just store the tweets from this request
		// also optionally from pull to refresh
		// we can store raw tweets as json or as complete objects
		// simplest thing is to store all tweets ? - should we then make network
		// calls when we are at the end ?
	}

	protected void saveTweets(ArrayList<Tweet> tweets) {
		// deleteAllTweets()
		ActiveAndroid.beginTransaction();
		try {
			for (Tweet tweet : tweets) {
//				tweet.save();
			}
			ActiveAndroid.setTransactionSuccessful();
		} finally {
			ActiveAndroid.endTransaction();
		}
	}

	private void loadOldTweets() {
		// tweets = new Select().from(Tweet.class).execute();
		// adapter.addAll(tweets);
	}

	private void deleteAll() {
		new Delete().from(Tweet.class).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	public void onComposeTweet(MenuItem mi) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivityForResult(i, COMPOSE_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == COMPOSE_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {

				Tweet tweet = (Tweet) data.getSerializableExtra("tweet");
				adapter.insert(tweet, 0);
				// TextView tvResult =
				// (TextView)findViewById(R.id.txtDisplayResult);
				// tvResult.setText(data.getStringExtra("result"));
				// Toast.makeText(this, + "",
				// Toast.LENGTH_SHORT).show();
			}
		}

	}

	private void setupViews() {
		lvTimeline = (ListView) findViewById(R.id.lvTimeline);
		// adapter = new TweetAdapter(this, resource, objects)
		// lvTimeline.setAdapter(adapter)
	}

}
