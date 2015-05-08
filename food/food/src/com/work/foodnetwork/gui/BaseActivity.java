package com.work.foodnetwork.gui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.work.foodnetwork.gui.custom.MyDialog;
import com.work.foodnetwork.utils.MyToast;

public class BaseActivity extends Activity {

	private boolean isBackPressed = false;
	protected MyToast myToast;
	protected Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		myToast = new MyToast(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	public void onBackPressed() {

		if (!isBackPressed && (this instanceof CategoiesVideoActivity)) {
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

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		MyDialog.resizeDialog(mContext);
		super.onConfigurationChanged(newConfig);
	}
}
