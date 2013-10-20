package com.codepath.twitter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ocpsoft.pretty.time.PrettyTime;


public class TweetAdapter extends ArrayAdapter<Tweet> {

	private PrettyTime prettyTime;

	public TweetAdapter(Context context, List<Tweet> tweets) {
		super(context,0, tweets);	
		prettyTime = new PrettyTime();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tweet tweet = this.getItem(position);
		View itemView;
		if(convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			itemView =  inflator.inflate(R.layout.tweet,null,false);
		} else {
			itemView = convertView;
		}
		
		setImage(itemView, tweet);
		setView(itemView, R.id.tvUserName, tweet.getUserName(), Color.parseColor("#555555"));
		setView(itemView, R.id.tvHandle, tweet.getHandle(), Color.BLACK);								
		setView(itemView, R.id.tvTimestamp, prettyTime.format(tweet.getTimestamp()), Color.GRAY);
		setTweetContent(tweet, itemView);
						
		return itemView;		
	}

	private void setImage(View itemView, Tweet tweet) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		ImageView imgView = (ImageView) itemView.findViewById(R.id.ivUserImage);
		imgView.setImageResource(android.R.color.transparent);
		
//		Log.d("DEBUG image url", tweet.getUserImage());
        imageLoader.displayImage(tweet.getUserImage(), imgView);
	}

	private void setTweetContent(Tweet tweet, View itemView) {
		//TODO(VJ) - find a way to make the links clickable
		TextView view = (TextView) itemView.findViewById(R.id.tvText);
		view.setText(Html.fromHtml(tweet.getText()));	
		view.setTextColor(Color.BLACK);
	}

	private void setView(View itemView, int id, String value, int color) {
		TextView view = (TextView) itemView.findViewById(id);
		view.setText(value);
		view.setTextColor(color);
	}	

}
