package com.codepath.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class User extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 899970261137379160L;
	/**
	 * 
	 */

	private String name;

	@Column(name = "ProfileImage")
	private String profileImageUrl;

	private String screenName;

	private String followersCount;
	private String followsCount;
	private String description;
	private String userId;
	private String tweetsCount;

	public User() {
		super();
	}

	public User(JSONObject userJSON) throws JSONException {
		name = userJSON.getString("name");
		userId = userJSON.getString("id");
		screenName = userJSON.getString("screen_name");
		profileImageUrl = userJSON.getString("profile_image_url");
		followersCount = userJSON.getString("followers_count");
		followsCount = userJSON.getString("friends_count");
		tweetsCount = userJSON.getString("statuses_count");
		description = userJSON.getString("description");
	}

	public String getByline() {
		return description;
	}

	public String getFollowers() {
		return followersCount;
	}

	public String getFollows() {
		return followsCount;
	}

	public String getHandle() {
		return screenName;
	}

	public String getName() {
		return name;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public String getTweetsCount() {
		return tweetsCount;
	}

	public String getUserId() {
		return userId;
	}

}