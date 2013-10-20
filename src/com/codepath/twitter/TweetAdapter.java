package com.codepath.twitter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.models.Tweet;
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
		
		setView(itemView, R.id.tvUserName, tweet.getUserName());
		setView(itemView, R.id.tvHandle, tweet.getHandle());		
		setView(itemView, R.id.tvText, tweet.getText());		
		setView(itemView, R.id.tvTimestamp, prettyTime.format(tweet.getTimestamp()) + tweet.getCreatedAt());		
						
		return itemView;		
	}

	private void setView(View itemView, int id, String value) {
		TextView view = (TextView) itemView.findViewById(id);
		view.setText(value);
		view.setTextColor(getContext().getResources().getColor(android.R.color.black));
	}	

}
