package com.codepath.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 568082762384648159L;

	public Tweet() {
		super();
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
	
	private String text;
	private boolean retweeted;
	private String hashtags;
	
	private long id;
	private String createdAt;

	//	User user;
	private User user;

	public Tweet(JSONObject jsonObject) throws JSONException {
		id = jsonObject.getLong("id");
		retweeted = jsonObject.getBoolean("retweeted");
		text = jsonObject.getString("text");
		createdAt = jsonObject.getString("created_at"); //can be converted to date and formatted
//		user = new User(jsonObject.getJSONObject("user"));
		JSONArray hashtagsArray = jsonObject.getJSONObject("entities").getJSONArray("hashtags");
		hashtags = "";
		for (int i = 0; i < hashtagsArray.length(); i++) {
			hashtags += hashtagsArray.getString(i) + " ";
		}
				
	}
	
	public String getCreatedAt() {
		return createdAt;
	}

	public String getHandle() {
		return "@" + user.getHandle();
	}

	public String getHashtags() {
		return hashtags;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	
	public Date getTimestamp() {
		return parseTwitterDate(createdAt);
	}

//	public  User getUser(){
//		return user;
//	}
//	
	public String getUserImage() {
		return "aa.jpg" ;//user.getProfileImageUrl();
	}

	public String getUserName() {
		return "aa" ; //user.getName();
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

}

class TwitterMeta {
	long sinceId;
	long maxId;
}