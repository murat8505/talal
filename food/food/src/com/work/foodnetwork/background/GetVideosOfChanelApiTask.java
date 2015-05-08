package com.work.foodnetwork.background;

import android.content.Context;

import com.google.gson.Gson;
import com.work.foodnetwork.eventhandler.OnApiRequestListener;
import com.work.foodnetwork.gui.custom.MyProgressDialog;
import com.work.foodnetwork.model.YoutobeResponseModel;
import com.work.foodnetwork.utils.MyLog;
import com.work.foodnetwork.youtobe.ApiRequest;

public class GetVideosOfChanelApiTask extends BaseAsyncTask {

	public GetVideosOfChanelApiTask(Context context,MyProgressDialog myProgressDialog, OnApiRequestListener onApiRequestListener) {
		super(context, myProgressDialog, true, onApiRequestListener);
	}

	@Override
	protected String makeRequest(String... params) {
		String response = ApiRequest.getVideoFromChanel(mContext,params[0]);
		return response;
	}

	@Override
	protected void parseResponse(String response) {

		try {
			Gson gson = new Gson();
			mYoutobeResponseModel = (YoutobeResponseModel) gson.fromJson(response, YoutobeResponseModel.class);

			mMessage = "OK";
			MyLog.iGeneral("GetVideosOfChanelApiTask-done: " + mYoutobeResponseModel);
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("GetVideosOfChanelApiTask-parseJsonResponse: " + e);
			mApiHandleCode = PARSE_JSON_ERROR;
			MyLog.logToFile(e, response, "");
		}
	}
}
