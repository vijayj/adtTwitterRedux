package com.codepath.twitter.fragments;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.models.Tweet;
import com.codepath.twitter.R;
import com.codepath.twitter.TweetAdapter;

import eu.erikw.PullToRefreshListView;

public class TweetsListFragment extends Fragment {

	private TweetAdapter adapter;
	private PullToRefreshListView lvTimeline;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		adapter = new TweetAdapter(getActivity(), tweets);
		lvTimeline = (PullToRefreshListView) getActivity().findViewById(R.id.lvTimeline);
		lvTimeline.setAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweets_list, container,false);		
	}

	public TweetAdapter getAdapter() {
		return adapter; 		
	}

}

