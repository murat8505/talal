package com.work.foodnetwork.youtobe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;

import com.work.foodnetwork.utils.MyLog;
import com.work.foodnetwork.utils.MyPreferenceUtils;

public class ApiRequest {

	/**
	 * 
	 */
	private static final String CONTENT_TYPE_HEAPDER_FORM = "application/x-www-form-urlencoded";

	/**
	 * 
	 */
	private static final int TIME_OUT = 30000;

	/**
	 * 
	 */
	private static ArrayList<NameValuePair> mPostParameters;

	private static HttpClient getHttpClient() {
		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIME_OUT);
		HttpClient httpclient = new DefaultHttpClient();
		return httpclient;
	}

	/**
	 * get string content from uri by GET method
	 * 
	 * @param uri
	 *            uri of api server
	 * @return string response
	 */
	private static String get(String uri) {

		long startTime = System.currentTimeMillis();

		BufferedReader in = null;
		String responseString = "";

		try {

			HttpGet httpGet = new HttpGet();
			httpGet.setURI(new URI(uri));
			httpGet.addHeader("Content-Type", CONTENT_TYPE_HEAPDER_FORM);

			HttpResponse response = getHttpClient().execute(httpGet);
			// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = in.readLine();
			while (line != null) {
				responseString += line;
				line = in.readLine();
			}
			// }

			MyLog.iGeneral(String.format(Locale.getDefault(), "Total time: %d ms - Response %s - CODE: %d: %s",
					System.currentTimeMillis() - startTime, uri, response.getStatusLine().getStatusCode(),
					responseString));
		} catch (Exception e) {
			responseString = "";
			MyLog.eGeneral(String.format(Locale.getDefault(), "Total time: %d ms - Error in http connection ",
					System.currentTimeMillis() - startTime, e.toString()));
		}

		return responseString;
	}

	/**
	 * get string content from uri by POST method
	 * 
	 * @param uri
	 *            uri of api server
	 * @return string response
	 */
	private static String post(String uri) {

		long startTime = System.currentTimeMillis();
		BufferedReader in = null;
		String responseString = "";

		try {

			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(uri));
			httpPost.addHeader("Content-Type", CONTENT_TYPE_HEAPDER_FORM);
			httpPost.setEntity(new UrlEncodedFormEntity(mPostParameters));
			HttpResponse response = getHttpClient().execute(httpPost);
			// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = in.readLine();
			while (line != null) {
				responseString += line;
				line = in.readLine();
			}
			// }

			MyLog.iGeneral(String.format(Locale.getDefault(), "Total time: %d ms - Response %s - CODE: %d: %s",
					System.currentTimeMillis() - startTime, uri, response.getStatusLine().getStatusCode(),
					responseString));
		} catch (Exception e) {
			responseString = "";
			MyLog.eGeneral(String.format(Locale.getDefault(), "Total time: %d ms - Error in http connection: %s",
					System.currentTimeMillis() - startTime, e.toString()));
		}

		return responseString;
	}

	public static String testGetVideo() {

		String url = ApiUrlDefine.BASE_URL + "?id=7lCDEYXw3mM&key=AIzaSyDV4pGJlcJtdtHYa2M-mi4uVRr-aQu26ws&part=snippet";
		return get(url);
	}

	public static String getVideoFromChanel(Context context, String chanelId) {

		String url = ApiUrlDefine.searchVideosIdUrl(chanelId);
		if (MyPreferenceUtils.getByLocale(context))
			url += String.format(Locale.getDefault(), "&regionCode=%s",
					context.getResources().getConfiguration().locale.getCountry());
		return get(url);
	}

}