package com.work.foodnetwork.gui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.work.foodnetwork.MyApplication;
import com.work.foodnetwork.R;
import com.work.foodnetwork.background.GetVideosOfChanelApiTask;
import com.work.foodnetwork.eventhandler.OnApiRequestListener;
import com.work.foodnetwork.gui.custom.MyDialog;
import com.work.foodnetwork.gui.custom.MyProgressDialog;
import com.work.foodnetwork.model.MyVideoEntry;
import com.work.foodnetwork.model.VideoItemModel;
import com.work.foodnetwork.model.YoutobeResponseModel;
import com.work.foodnetwork.utils.MyConstants;
import com.work.foodnetwork.utils.MyPreferenceUtils;
import com.work.foodnetwork.youtobe.ChanelsDefine;
import com.work.foodnetwork.youtobe.ChanelsDefine.Chanel;

public class CategoiesVideoActivity extends BaseActivity implements OnClickListener {

	private LinearLayout btn1, btn2, btn3, btn4, btn5;
	private TextView tv1, tv2, tv3, tv4, tv5;
	private Switch switchGetByLocale;
	private MyProgressDialog myProgressDialog;
	private ArrayList<Chanel> listChanels;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoies_video);

		initView();
	}

	private void initView() {
		listChanels = new ArrayList<ChanelsDefine.Chanel>();
		listChanels.add(ChanelsDefine.LauraVitalesKitchen);
		listChanels.add(ChanelsDefine.JamieOliver);
		listChanels.add(ChanelsDefine.Cocinaalnatural);
		listChanels.add(ChanelsDefine.cookingwithdog);
		listChanels.add(ChanelsDefine.robjnixon);

		myProgressDialog = new MyProgressDialog(mContext);

		btn1 = (LinearLayout) findViewById(R.id.layout_1);
		btn2 = (LinearLayout) findViewById(R.id.layout_2);
		btn3 = (LinearLayout) findViewById(R.id.layout_3);
		btn4 = (LinearLayout) findViewById(R.id.layout_4);
		btn5 = (LinearLayout) findViewById(R.id.layout_5);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);

		tv1 = (TextView) findViewById(R.id.btn_1);
		tv2 = (TextView) findViewById(R.id.btn_2);
		tv3 = (TextView) findViewById(R.id.btn_3);
		tv4 = (TextView) findViewById(R.id.btn_4);
		tv5 = (TextView) findViewById(R.id.btn_5);

		tv1.setText(listChanels.get(0).name);
		tv2.setText(listChanels.get(1).name);
		tv3.setText(listChanels.get(2).name);
		tv4.setText(listChanels.get(3).name);
		tv5.setText(listChanels.get(4).name);

		switchGetByLocale = (Switch) findViewById(R.id.switchGetByLocale);
		switchGetByLocale.setChecked(MyPreferenceUtils.getByLocale(mContext));
		switchGetByLocale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				MyPreferenceUtils.setByLocale(mContext, switchGetByLocale.isChecked());
			}
		});
	}

	@Override
	public void onClick(final View v) {

		v.setEnabled(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				v.setEnabled(true);
			}
		}, 300);

		switch (v.getId()) {
		case R.id.layout_1:
			loadVideoList(listChanels.get(0));
			break;

		case R.id.layout_2:
			loadVideoList(listChanels.get(1));
			break;

		case R.id.layout_3:
			loadVideoList(listChanels.get(2));
			break;

		case R.id.layout_4:
			loadVideoList(listChanels.get(3));
			break;

		case R.id.layout_5:
			loadVideoList(listChanels.get(4));
			break;
		default:
			break;
		}
	}

	private void loadVideoList(final Chanel chanel) {

		GetVideosOfChanelApiTask getVideosOfChanelApiTask = new GetVideosOfChanelApiTask(mContext, myProgressDialog,
				new OnApiRequestListener() {

					@Override
					public void onDone(int handleCode, YoutobeResponseModel result, String message) {

						if (result != null) {
							MyApplication.videoEntries = new ArrayList<MyVideoEntry>();
							List<VideoItemModel> items = result.items;
							if (items != null && items.size() > 0) {
								for (VideoItemModel item : items) {
									MyVideoEntry entry = new MyVideoEntry(item.snippet.title, item.id.videoId);
									MyApplication.videoEntries.add(entry);
								}
								Intent intent = new Intent(mContext, ListVideosActivity.class);
								intent.putExtra("CHANEL_NAME", chanel.name);
								startActivity(intent);
							} else {
								String title = "Error";
								message = "No videos was found.";
								MyDialog.showDialog(mContext, title, message, false, null);
							}
						} else {

							String title = "Error";
							if (handleCode == MyConstants.ApiHandleCode.NO_NETWORK) {
								message = mContext.getString(R.string.no_network);
							} else if (handleCode == MyConstants.ApiHandleCode.NULL_OR_EMPTY_RESPONSE_ERROR) {

								message = "Empty response data!";
							} else if (handleCode == MyConstants.ApiHandleCode.PARSE_JSON_ERROR) {

								message = "Wrong response data format!";
							} else {

								message = "Unknow error!";
							}
							MyDialog.showDialog(mContext, title, message, false, null);
						}
					}
				});
		getVideosOfChanelApiTask.execute(chanel.id);
	}
}
