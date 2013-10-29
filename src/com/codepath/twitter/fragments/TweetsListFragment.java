package com.codepath.twitter.fragments;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.codepath.models.Tweet;
import com.codepath.twitter.EndlessScrollListener;
import com.codepath.twitter.ProfileActivity;
import com.codepath.twitter.R;
import com.codepath.twitter.TweetAdapter;

import eu.erikw.PullToRefreshListView;

public abstract class TweetsListFragment extends Fragment{

	private TweetAdapter adapter;
	private PullToRefreshListView lvTimeline;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		adapter = new TweetAdapter(getActivity(), tweets , getOnClickListener());
		lvTimeline = (PullToRefreshListView) getActivity().findViewById(R.id.lvTimeline);
		lvTimeline.setAdapter(adapter);		
		lvTimeline.setOnScrollListener(getEndlessScrollListener());
	}
	
	
	private OnClickListener getOnClickListener(){
		return new OnClickListener(){
			@Override
			public void onClick(View v) {
				//for time being assume view is image view
				ImageView iView = (ImageView) v;				
				String screenName = iView.getTag().toString();

				Intent i = new Intent(getActivity(),ProfileActivity.class);
				i.putExtra("screen_name", screenName);
				startActivity(i);
//				launchUserProfile(userId);				
			}
		};	
	}
	

	private EndlessScrollListener getEndlessScrollListener() {
		return new EndlessScrollListener() {
		
				private Tweet findOldestTweet() {
					if(adapter.isEmpty()){
						return null;
					}
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
		
	}

	protected abstract void loadTweets(final long maxId);
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweets_list, container,false);		
	}

	public TweetAdapter getAdapter() {
		return adapter; 		
	}
	

}

