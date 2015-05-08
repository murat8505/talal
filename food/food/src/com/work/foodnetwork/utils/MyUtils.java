package com.work.foodnetwork.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class MyUtils {

	/**
	 * Checks if the device has Internet connection.
	 * 
	 * @return <code>true</code> if the phone is connected to the Internet.
	 */
	public static boolean hasConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			return true;
		}

		NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			return true;
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}

		return false;
	}
	/**
	 * check new version of application
	 * 
	 * @param context
	 * @param newVersionName
	 *            last version get from server
	 * @return true if has new version, false otherwise
	 */
	public static boolean hasNewVersion(Context context, String newVersionName) {

		PackageInfo pInfo;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			String currentVersionName = pInfo.versionName;

			int maxLen = currentVersionName.length() - currentVersionName.replace(".", "").length();
			int newVersionLen = newVersionName.length() - newVersionName.replace(".", "").length();
			if (newVersionLen > maxLen)
				maxLen = newVersionLen;

			if (parseVersion(currentVersionName, maxLen + 1) < parseVersion(newVersionName, maxLen + 1)) {
				MyLog.iGeneral("Has new version");
				return true;
			} else {
				MyLog.iGeneral("No new version");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("checkVersion: " + e);
			return false;
		}
	}

	public static int compareVersion(Context context, String newVersionName) {

		PackageInfo pInfo;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			String currentVersionName = pInfo.versionName;

			int maxLen = currentVersionName.length() - currentVersionName.replace(".", "").length();
			int newVersionLen = newVersionName.length() - newVersionName.replace(".", "").length();
			if (newVersionLen > maxLen)
				maxLen = newVersionLen;

			int newVersion = parseVersion(newVersionName, maxLen + 1);
			int currentVersion = parseVersion(currentVersionName, maxLen + 1);
			return currentVersion - newVersion;
			// if ( currentVersion < newVersion) {
			// MyLog.iGeneral("Has new version");
			// return 1;
			// } else {
			// MyLog.iGeneral("No new version");
			// return 0;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("checkVersion: " + e);
			return 0;
		}
	}

	/**
	 * get current version of app
	 * 
	 * @param context
	 * @return current version app name
	 */
	public static String getVersionName(Context context) {
		try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pInfo.versionName;
		} catch (Exception e) {
			MyLog.eGeneral("MyUtils-getVersionName: " + e);
			return "";
		}
	}

	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	public static int getAppVersionCode(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	/**
	 * parse version app name (string) to version app number (int)
	 * 
	 * @param versionString
	 * @param length
	 * @return number version of app
	 */
	private static int parseVersion(String versionString, int length) {
		try {
			int version = 0;
			String[] arr = versionString.split("[.]");
			for (int i = 0; i < arr.length; i++) {
				try {
					version += (Integer.parseInt(arr[i]) * Math.pow(10d, (double) (length - i)));
				} catch (Exception e) {
				}
			}
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("parseVersion: " + e);
			return 0;
		}
	}

	/**
	 * show keyboard if isShow is true and hide keyboard otherwise
	 * 
	 * @param context
	 * @param view
	 * @param isShow
	 */
	public static void requestKeyBoard(Context context, View view, Boolean isShow) {
		try {

			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			// hide
			if (!isShow) {
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			} else {
				// show
				view.requestFocus();
				imm.showSoftInput(view, 0);
			}

		} catch (Exception e) {
			MyLog.eGeneral("MyUtils-requestKeyBoard: " + e);
		}
	}

	/**
	 * 
	 * @return to day String on device langue
	 */
	public static String getToDayString() {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd MMM", Locale.getDefault());
			return simpleDateFormat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("MyUtils-getToDayString: " + e);
			return "";
		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	/**
	 * get window screen size in pixel
	 * @param context
	 * @return
	 */
	public static Point getWindowSizeInPixel(Context context) {

		Point size = new Point();
		WindowManager w = ((Activity) context).getWindowManager();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			w.getDefaultDisplay().getSize(size);
		} else {
			Display d = w.getDefaultDisplay();
			size.x = d.getWidth();
			size.y = d.getHeight();
		}
		return size;
	}

	/**
	 * get string from resource folder
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static String getString(Context context, int resId) {
		return context.getResources().getString(resId);
	}

	/**
	 * Get device id
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceUid(Context context) {
		try {
			return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		} catch (Exception e) {
			MyLog.eGeneral("getDeviceUid-" + e);
			return "deviceid-" + System.currentTimeMillis();
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 
	 * @param context
	 * @param miliseconds
	 */
	public static void vibrate(Context context, long miliseconds) {
		try {
			Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(miliseconds);

		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("Vibrate error: " + e);
		}
	}

	/**
	 * 
	 * @param context
	 */
	public static void playNorificationSound(Context context) {
		try {
			RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
					.play();
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("Play notification sound error: " + e);
		}
	}
}
