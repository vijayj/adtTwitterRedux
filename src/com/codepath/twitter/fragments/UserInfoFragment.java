package com.codepath.twitter.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.models.User;
import com.codepath.twitter.R;
import com.codepath.twitter.RestClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UserInfoFragment extends Fragment {
	private String screenName;

	private void loadProfileInfo(String screenName) {
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

			@Override
			public void onFailure(Throwable t, JSONArray array) {
				Log.d("DEBUG Timeline failure",
						t.getMessage() + " : " + array.toString());
				super.onFailure(t, array);
			}

			@Override
			public void onFailure(Throwable t, JSONObject object) {
				Log.d("DEBUG Timeline failure",
						t.getMessage() + " : " + object.toString());
				super.onFailure(t, object);
			}

			@Override
			public void onSuccess(JSONObject profileInfo) {
				User user;
				try {
					user = new User(profileInfo);
					refreshViews(user);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		if (screenName.isEmpty()) {
			RestClientApp.getRestClient().getMyInfo(handler);
		} else {
			RestClientApp.getRestClient().getUserProfile(screenName, handler);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadProfileInfo(screenName);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		screenName = getArguments().getString("screen_name");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_info, container, false);
	}

	private void refreshViews(User user) {
		setTextView(R.id.tvScreenName, user.getName());
		setTextView(R.id.tvByline, user.getByline());
		setTextView(R.id.tvFollowers, user.getFollowers(), "Followers");
		setTextView(R.id.tvFollows, user.getFollows(), "Follows");
		setTextView(R.id.tvTweetCount, user.getTweetsCount(), "Tweets");
		setImage(R.id.ivProfilePic, user.getProfileImageUrl());

		getActivity().getActionBar().setTitle("@" + user.getHandle());
	}

	private void setImage(int handle, String url) {
		ImageLoader imageLoader = ImageLoader.getInstance();
		ImageView imgView = (ImageView) getActivity().findViewById(handle);
		imgView.setImageResource(android.R.color.transparent);

		imageLoader.displayImage(url, imgView);
	}

	private void setTextView(int viewHandle, String data) {
		TextView screenName = (TextView) getActivity().findViewById(viewHandle);
		screenName.setText(data);
	}

	private void setTextView(int viewHandle, String data, String suffix) {
		TextView screenName = (TextView) getActivity().findViewById(viewHandle);
		screenName.setText(data + " " + suffix);
	}

}
