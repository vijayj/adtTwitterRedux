package com.codepath.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
	String text;
	boolean retweeted;
	String[] hashtags;
	long id;
	String createdAt;
//	User user;

	public Tweet(JSONObject jsonObject) throws JSONException {
		id = jsonObject.getLong("id");
		retweeted = jsonObject.getBoolean("retweeted");
		text = jsonObject.getString("text");
		createdAt = jsonObject.getString("created_at"); //can be converted to date and formatted
//		user = new User(jsonObject.get("user"));
//		hashtags = jsonObject.getJSONObject("entities").getJSONArray("hashtags").map { |e| e }
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

	@Override
	public String toString() {
		return "Tweet [text=" + text + ", retweeted=" + retweeted + ", id="
				+ id + ", createdAt=" + createdAt + "]";
	}

	public String getUserName() {
		return "Vijay Jambu";
	}

	public String getHandle() {
		return "@hannn";
	}
	
	public String getText() {
		return text;
	}
	
}

class User {
	String name;
	String profileImageUrl;
	String location;
	String profileBackgroundImageUrl;
	String screenName;
}

class TwitterMeta {
	long sinceId;
	long maxId;
}