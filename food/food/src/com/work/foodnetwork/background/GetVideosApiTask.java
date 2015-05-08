package com.work.foodnetwork.background;

import android.content.Context;

import com.google.gson.Gson;
import com.work.foodnetwork.eventhandler.OnApiRequestListener;
import com.work.foodnetwork.model.YoutobeResponseModel;
import com.work.foodnetwork.utils.MyLog;
import com.work.foodnetwork.youtobe.ApiRequest;

public class GetVideosApiTask extends BaseAsyncTask {

	public GetVideosApiTask(Context context, OnApiRequestListener onApiRequestListener) {
		super(context, null, true, onApiRequestListener);
	}

	@Override
	protected String makeRequest(String... params) {
		String response = ApiRequest.testGetVideo();
		mMessage = response;
		return response;
	}

	@Override
	protected void parseResponse(String response) {

		try {
			Gson gson = new Gson();
			YoutobeResponseModel youtobeResponseModel = (YoutobeResponseModel) gson.fromJson(response,
					YoutobeResponseModel.class);

			MyLog.iGeneral("CheckVersionApiTask-parseJsonResponse: " + youtobeResponseModel);
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("CheckVersionApiTask-parseJsonResponse: " + e);
			mApiHandleCode = PARSE_JSON_ERROR;
			MyLog.logToFile(e, response, "");
		}
	}
}
