package com.work.foodnetwork;

import java.util.ArrayList;

import android.app.Application;

import com.work.foodnetwork.model.MyVideoEntry;
import com.work.foodnetwork.model.YoutobeResponseModel;

public class MyApplication extends Application {
	
	public static YoutobeResponseModel youtobeResponseModel;
	
	public static  ArrayList<MyVideoEntry> videoEntries;
}
