package com.work.foodnetwork.eventhandler;

import com.work.foodnetwork.model.YoutobeResponseModel;

public interface OnApiRequestListener {

	public void onDone(int handleCode, YoutobeResponseModel result, String message);

}
