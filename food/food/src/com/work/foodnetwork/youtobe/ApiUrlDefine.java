package com.work.foodnetwork.youtobe;

import java.util.Locale;

public class ApiUrlDefine {

	public static final String DEVELOPER_KEY = DeveloperKey.DEVELOPER_KEY;

	public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

	public static final String CHANELS_ID = "https://www.googleapis.com/youtube/v3/channels?part=id&forUsername=%s&key=%s";
	public static final String SEARCH_VIDEO = "https://www.googleapis.com/youtube/v3/search?channelId=%s&key=%s&part=snippet,id&type=video&order=date&maxResults=50";
	public static final String VIDEOS = "videos/";
	public static final String CHANELS = "channels/";

	public static String getChanelsIdUrl(String userName) {
		return String.format(Locale.getDefault(), CHANELS_ID, userName, DEVELOPER_KEY);
	}

	public static String searchVideosIdUrl(String chanelId) {
		return String.format(Locale.getDefault(), SEARCH_VIDEO, chanelId, DEVELOPER_KEY);
	}
}
