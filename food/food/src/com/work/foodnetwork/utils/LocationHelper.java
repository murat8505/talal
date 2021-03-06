package com.work.foodnetwork.utils;

import java.util.Locale;

import android.content.Context;
import android.telephony.TelephonyManager;

public class LocationHelper {

	public static String getCountryCodeBySim(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String countryCode = tm.getSimCountryIso();
		return countryCode;
	}

	public static String getCountryCode(Context context) {
		try {
			final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			final String simCountry = tm.getSimCountryIso();
			if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
				return simCountry.toLowerCase(Locale.US);
			} else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be
																				// unreliable)
				String networkCountry = tm.getNetworkCountryIso();
				if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
					return networkCountry.toLowerCase(Locale.US);
				}
			}
		} catch (Exception e) {
		}
		return "";
	}

}
