package com.codepath.twitter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.codepath.twitter.fragments.UserInfoFragment;
import com.codepath.twitter.fragments.UserTimelineFragment;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		String screenName = getIntent().getStringExtra("screen_name");
		
		UserInfoFragment userInfo = new UserInfoFragment();
		userInfo.setArguments(getIntent().getExtras());

		Bundle bundle = new Bundle();
		bundle.putString("screen_name", screenName);

		UserTimelineFragment userTimeline = new UserTimelineFragment();
		userTimeline.setArguments(bundle);
		
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		
		fts.replace(R.id.profile_frame, userInfo);
		fts.replace(R.id.user_timeline_frame, userTimeline);

		fts.commit();				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
