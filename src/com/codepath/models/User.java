package com.codepath.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Users")
public class User extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 899970261137379160L;
	/**
	 * 
	 */
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "ProfileImage")
	private String profileImageUrl;	
	
	@Column(name = "ScreenName")
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
		userId =  userJSON.getString("id");
		screenName =  userJSON.getString("screen_name");
		profileImageUrl =  userJSON.getString("profile_image_url");
		followersCount =  userJSON.getString("followers_count");
		followsCount =  userJSON.getString("friends_count");
		tweetsCount =  userJSON.getString("statuses_count");
		description =  userJSON.getString("description");
//		profileBackgroundImageUrl =  userJSON.getString("profile_background_image_url");
//		location = userJSON.getString("location");
	}

	public String getName() {
		return name;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	
	public String getHandle() {
		return screenName;
	}

	public String getByline() {
		// TODO Auto-generated method stub
		return description;
	}

	public String getFollowers() {
		// TODO Auto-generated method stub
		return followersCount;
	}

	public String getFollows() {
		// TODO Auto-generated method stub
		return followsCount;
	}

	public String getUserId() {
		return userId;
	}

	public String getTweetsCount() {
		return tweetsCount;
	}
	
}