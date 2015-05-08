package com.work.foodnetwork.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

public class MyToast {

	private final int TIME = 1500;
	private Toast mToast;
	private Context mContext;

	public MyToast(Context context) {
		mContext = context;
		mToast = new Toast(mContext);
	}

	/**
	 * Show msg as Toast, cancel previous toast if having
	 * 
	 * @param msg
	 *            message
	 */
	public void showToast(String msg) {
		mToast.cancel();
		mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		mToast.show();
		cancel();
	}

	/**
	 * Show msg as Toast, cancel previous toast if having
	 * 
	 * @param msg
	 *            message
	 */
	public void showToastCenter(String msg) {
		mToast.cancel();
		mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
		cancel();
	}

	private void cancel() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mToast.cancel();
			}
		}, TIME);
	}

}
