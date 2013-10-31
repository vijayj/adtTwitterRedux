package com.codepath.twitter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.models.Tweet;
import com.codepath.twitter.fragments.HomeTimelineFragment;
import com.codepath.twitter.fragments.MentionsFragment;

public class TimeLineActivity extends FragmentActivity implements TabListener {

	public static final int COMPOSE_REQUEST_CODE = 2001;
	private HomeTimelineFragment homeTimelineFragment;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == COMPOSE_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Tweet tweet = (Tweet) data.getSerializableExtra("tweet");
				homeTimelineFragment.getAdapter().insert(tweet, 0);
			}
		}
	}

	public void onComposeTweet(MenuItem mi) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivityForResult(i, COMPOSE_REQUEST_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// 

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager
				.beginTransaction();

		if (tab.getTag() == "HomeTimelineFragment") {
			// TODO(VJ) - this is so messy - need to find a better way to
			// organize fragments and handles
			// also event handling code is super messy -
			homeTimelineFragment = new HomeTimelineFragment();
			fts.replace(R.id.frame_layout, homeTimelineFragment);
		} else if (tab.getTag() == "MentionsFragment") {
			fts.replace(R.id.frame_layout, new MentionsFragment());
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// 
	}

	private void setupViews() {
		// setup action bar for tabs
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		// actionBar.setStackedBackgroundDrawable(android.R.drawable.alert_dark_frame);

		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTimelineFragment").setIcon(R.drawable.ic_home)
				.setTabListener(this);

		Tab tabMentions = actionBar.newTab().setText("Mentions")
				.setTag("MentionsFragment").setIcon(R.drawable.ic_mentions)
				.setTabListener(this);

		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	public void showMyProfile(MenuItem mi) {
		Intent i = new Intent(this, ProfileActivity.class);

		i.putExtra("screen_name", "");

		startActivity(i);
		// startActivityForResult(i, COMPOSE_REQUEST_CODE);
	}
}
