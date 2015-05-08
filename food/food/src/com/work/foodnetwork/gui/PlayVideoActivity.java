package com.work.foodnetwork.gui;

import android.os.Bundle;
import android.os.Handler;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.work.foodnetwork.R;
import com.work.foodnetwork.utils.MyToast;
import com.work.foodnetwork.youtobe.DeveloperKey;

public class PlayVideoActivity extends YouTubeFailureRecoveryActivity {
	private boolean isBackPressed = false;
	protected MyToast myToast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		myToast = new MyToast(this);
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {
			player.cueVideo("wKJ9KzGQq0w");
		}
	}

	@Override
	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

	@Override
	public void onBackPressed() {

		if (!isBackPressed) {
			isBackPressed = true;
			myToast.showToast("Press again to exit!");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					isBackPressed = false;
				}
			}, 1500);
		} else {
			super.onBackPressed();
		}
	}

}
