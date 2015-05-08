package com.work.foodnetwork.model;

import com.google.gson.annotations.SerializedName;

public class ThumbnailsModel {

	@SerializedName("default")
	public ThumbnailModel defaultt;
	public ThumbnailModel medium;
	public ThumbnailModel high;

}
