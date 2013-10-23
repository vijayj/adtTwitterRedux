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
	
		
	public User() {
		super();
	}

	public User(JSONObject userJSON) throws JSONException {
		name = userJSON.getString("name");
		screenName =  userJSON.getString("screen_name");
		profileImageUrl =  userJSON.getString("profile_image_url");
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
	
}