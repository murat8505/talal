package com.work.foodnetwork.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.work.foodnetwork.eventhandler.OnApiRequestListener;
import com.work.foodnetwork.gui.custom.MyProgressDialog;
import com.work.foodnetwork.model.YoutobeResponseModel;
import com.work.foodnetwork.utils.MyConstants.ApiHandleCode;
import com.work.foodnetwork.utils.MyConstants.ApiResponseCode;
import com.work.foodnetwork.utils.MyUtils;

public abstract class BaseAsyncTask extends AsyncTask<String, Integer, Integer> implements ApiHandleCode,
		ApiResponseCode {

	protected Context mContext;
	protected OnApiRequestListener mOnApiRequestListener;
	protected MyProgressDialog mProgressDialog;
	protected boolean isCancelProgressAble;

	//
	protected YoutobeResponseModel mYoutobeResponseModel;
	protected int mApiHandleCode;
	protected String mMessage;

	public BaseAsyncTask(Context context, MyProgressDialog progressDialog, boolean cancelProgressAble,
			OnApiRequestListener onApiRequestListener) {
		mContext = context;
		mProgressDialog = progressDialog;
		mOnApiRequestListener = onApiRequestListener;
		this.isCancelProgressAble = cancelProgressAble;
	}

	@Override
	protected void onPreExecute() {
		if (mProgressDialog != null && !mProgressDialog.isShowing())
			mProgressDialog.show();
		super.onPreExecute();
	}

	abstract protected String makeRequest(String... params);

	abstract protected void parseResponse(String response);

	@Override
	protected Integer doInBackground(String... params) {

		mApiHandleCode = OK;
		// check network
		if (MyUtils.hasConnection(mContext)) {

			// make request to api server
			String response = makeRequest(params);
			// parse response
			if (response != null && !response.isEmpty()) {
				parseResponse(response);
			} else {
				mApiHandleCode = NULL_OR_EMPTY_RESPONSE_ERROR;
			}
		} else {
			mApiHandleCode = NO_NETWORK;
		}
		return mApiHandleCode;
	}

	@Override
	protected void onPostExecute(Integer result) {

		if (mProgressDialog != null && (isCancelProgressAble || result != OK))
			mProgressDialog.dismiss();

		if (mOnApiRequestListener != null) {
			// send back to GUI through handle
			mOnApiRequestListener.onDone(mApiHandleCode, mYoutobeResponseModel, mMessage);
		}

		super.onPostExecute(result);
	}

	public void setCancelProgressAble(boolean cancelProgressAble) {
		this.isCancelProgressAble = cancelProgressAble;
	}

	public void setOnApiRequestListener(OnApiRequestListener mOnListenApiRequest) {
		this.mOnApiRequestListener = mOnListenApiRequest;
	}

}
