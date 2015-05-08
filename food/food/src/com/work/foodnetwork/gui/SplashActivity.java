package com.work.foodnetwork.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.work.foodnetwork.R;

public class SplashActivity extends BaseActivity {

	private final int SPLASH_TIME_OUT = 1000;
	private long startTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		startTime = System.currentTimeMillis();

//		GetVideosApiTask getVideosApiTask = new GetVideosApiTask(this, new OnApiRequestListener() {
//			@Override
//			public void onDone(int handleCode, int apiResponseCode, String message) {
//				MyLog.iGeneral("Response: " + message);
//				startNextActivity(false);
//			}
//		});
//		getVideosApiTask.execute();

		startNextActivity(false);
	}

	private void startNextActivity(boolean isForce) {

		long delayTime = SPLASH_TIME_OUT - (System.currentTimeMillis() - startTime);
		if (isForce || delayTime < 0)
			delayTime = 0;

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, CategoiesVideoActivity.class);
				startActivity(intent);
				finish();
			}
		}, delayTime);
	}
}
