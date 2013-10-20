package com.codepath.twitter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.models.Tweet;


public class TweetAdapter extends ArrayAdapter<Tweet> {

	public TweetAdapter(Context context, List<Tweet> tweets) {
		super(context,0, tweets);		
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
		
		TextView userName = (TextView) itemView.findViewById(R.id.tvUserName);
		userName.setText(tweet.getUserName());
		TextView handle = (TextView) itemView.findViewById(R.id.tvHandle);
		handle.setText(tweet.getHandle());
		TextView text = (TextView) itemView.findViewById(R.id.tvText);
		text.setText(tweet.getText());
				
		return itemView;		
	}	

}
