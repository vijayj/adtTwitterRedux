package com.codepath.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
	String text;
	boolean retweeted;
	String hashtags;
	long id;
	String createdAt;
//	User user;
	private User user;

	public Tweet(JSONObject jsonObject) throws JSONException {
		id = jsonObject.getLong("id");
		retweeted = jsonObject.getBoolean("retweeted");
		text = jsonObject.getString("text");
		createdAt = jsonObject.getString("created_at"); //can be converted to date and formatted
		user = new User(jsonObject.getJSONObject("user"));
		JSONArray hashtagsArray = jsonObject.getJSONObject("entities").getJSONArray("hashtags");
		hashtags = "";
		for (int i = 0; i < hashtagsArray.length(); i++) {
			hashtags += hashtagsArray.getString(i) + " ";
		}
				
	}

	public static ArrayList<Tweet> fromJSONArray(JSONArray results) {
		ArrayList<Tweet> images = new ArrayList<Tweet>(results.length());
		for (int i = 0; i < results.length(); i++) {
			try {
				images.add(new Tweet(results.getJSONObject(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return images;
	}
	
	private Date parseTwitterDate(String createdAt)
	{
	    final String TWITTER = "EEE MMM dd HH:mm:ss Z yyyy";
	    SimpleDateFormat sf = new SimpleDateFormat(TWITTER, Locale.US);
	    sf.setLenient(true);
	    try {
			return sf.parse(createdAt);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}

	@Override
	public String toString() {
		return "Tweet [text=" + text + ", retweeted=" + retweeted + ", id="
				+ id + ", createdAt=" + createdAt + "]";
	}

	public String getUserName() {
		return user.getName();
	}

	public String getHandle() {
		return "@" + user.getHandle();
	}

	public String getUserImage() {
		return user.getProfileImageUrl();
	}

	
	public String getText() {
		return text;
	}

	public String getHashtags() {
		return hashtags;
	}
	
	public Date getTimestamp() {
		return parseTwitterDate(createdAt);
	}

	public String getCreatedAt() {
		return createdAt;
	}
	
	public  User getUser(){
		return user;
	}
	
}

class TwitterMeta {
	long sinceId;
	long maxId;
}