package com.work.foodnetwork.gui.custom;

import android.app.Dialog;
import android.content.Context;

import com.work.foodnetwork.R;

public class MyProgressDialog extends Dialog {

	public MyProgressDialog(Context context) {
		super(context, R.style.my_progress_dialog);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.my_progress_dialog);
		setCancelable(false);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
