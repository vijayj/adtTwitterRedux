package com.codepath.twitter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class ComposeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compose, menu);
        return true;
    }
    
    public void cancel(View v){
    	setResult(RESULT_CANCELED);
    	this.finish();    	
    }
    
    public void postTweet(View v){
    	MultiAutoCompleteTextView view = (MultiAutoCompleteTextView) findViewById(R.id.mTVTweet);
    	String tweetText = view.getText().toString();
		RestClientApp.getRestClient().postTweet(tweetText, new JsonHttpResponseHandler(){

			@Override
			public void onFailure(Throwable t, JSONArray array) {
				Toast.makeText(getApplicationContext(), "failed to post", Toast.LENGTH_LONG).show();
				Log.d("FAILED to post Array", array.toString());
				super.onFailure(t, array);
			}

			@Override
			public void onFailure(Throwable t, JSONObject object) {
				Toast.makeText(getApplicationContext(), "failed to post", Toast.LENGTH_LONG).show();
				Log.d("FAILED to post object", object.toString());
				super.onFailure(t, object);
			}

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
//				Log.d("RESPONSE DEBUG", object.toString());
				try {
					Tweet tweet = new Tweet(object);					
					onSuccessfulPost(tweet);
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "failed to parse posted tweet", Toast.LENGTH_LONG).show();
					Log.d("Parse failure", e.getMessage());
					setResult(RESULT_CANCELED);			    	
				}				
			}    		
    	});
    }
    
	private void onSuccessfulPost(Tweet tweet) {
		Intent i = new Intent(this, TimeLineActivity.class);
		i.putExtra("tweet", tweet);
		setResult(RESULT_OK);
    	this.finish();
		
	}
    
}
