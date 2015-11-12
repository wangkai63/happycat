package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.MerchatXqBean;
import com.happycat.global.GlobalContacts;
import com.happycat.util.MyApplication;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.integer;
import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DianPuActivity extends Activity implements OnClickListener {
	FragmentManager manager;
	FragmentTransaction transaction;
	Fragment fragment;
	ImageView imageView;
	TextView textView1, textView2, textView3, textView4, textView5, textView6,
			textView7, textView8;
	HttpUtils httpUtils;
	List<MerchatXqBean> mlist;
	private String url, url1;
	Intent intent;
	String uid=MyApplication.SP_user_id+"";
	ImageButton imageButton, imageButton2, imageButton3;
	String mid;
	private int count, collection;// 区分是收藏还是取消

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.dianpu);
		initViews();
		initData();
	}

	private void initData() {
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		mid = getIntent().getStringExtra("mid");
		params.addBodyParameter("key", "8");
		params.addBodyParameter("mid", mid);
		// 客户端向服务器端发送请求
		// MyApplication.getIp()
		url = "http://" + MyApplication.getIp() + ":8080/happycat/GetUpload";

		httpUtils.send(HttpMethod.POST, // 请求方式
				url, params, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);
						// 开始使用XUtils框架从服务器上下载大类表数据
						String result = responseInfo.result;
						System.out.println(result);

						Type listType = new TypeToken<LinkedList<MerchatXqBean>>() {
						}.getType();
						Gson gson = new Gson();
						mlist = gson.fromJson(result, listType);
						for (int i = 0; i < mlist.size(); i++) {

						}
						String path = "http://" + MyApplication.getIp()
								+ ":8080/happycat/img/";
						MyApplication.bitmapUtils.display(imageView, path
								+ mlist.get(0).getMimg());
						textView1.setText(mlist.get(0).getMname());
						textView2.setText(mlist.get(0).getLongtime());
						textView3.setText(mlist.get(0).getMprice() + "￥");
						textView4.setText(mlist.get(0).getTip() + "￥");

						textView5
								.setText("营业时间 ：   " + mlist.get(0).getMtime());

						textView6.setText("   ：" + mlist.get(0).getMphone());
						textView7.setText("商家地址 ：   "
								+ mlist.get(0).getMaddress());
						textView8
								.setText("商店描述 ：   " + mlist.get(0).getMtext());

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("失败了", "修改吧");
					}

				});// 服务器回调函数

		// 判断是否收藏
		url1 = "http://" + MyApplication.getIp() + ":8080/happycat/MG";
		HttpUtils utils = new HttpUtils();
		RequestParams param1 = new RequestParams();
		param1.addBodyParameter("key", "1");
		mid = getIntent().getStringExtra("mid");
		param1.addQueryStringParameter("uid", uid);
		param1.addBodyParameter("mid", mid);
		utils.send(HttpMethod.POST, url1, param1,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						collection = Integer.parseInt(result);
						Log.e("collection", collection + "");
						if (collection == 0) {
							count = 0;
							imageButton2.setImageResource(R.drawable.star2);
						} else if (collection == 1) {
							count = 1;
							imageButton2.setImageResource(R.drawable.star1);
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void initViews() {
		imageView = (ImageView) findViewById(R.id.sjimg);
		textView1 = (TextView) findViewById(R.id.sjname);
		textView2 = (TextView) findViewById(R.id.sjtime);
		textView3 = (TextView) findViewById(R.id.sjprice);
		textView4 = (TextView) findViewById(R.id.sjtip);
		textView5 = (TextView) findViewById(R.id.sjyytime);
		textView6 = (TextView) findViewById(R.id.sjphone);
		textView7 = (TextView) findViewById(R.id.sjaddress);
		textView8 = (TextView) findViewById(R.id.sjtext);
		imageButton = (ImageButton) findViewById(R.id.sjreturn);
		imageButton2 = (ImageButton) findViewById(R.id.sjshoucang);
		imageButton3 = (ImageButton) findViewById(R.id.phone1);
		imageButton.setOnClickListener(this);
		imageButton2.setOnClickListener(this);
		imageButton3.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.sjreturn:
			// 返回首页
			finish();
			break;

		case R.id.sjshoucang:

			if (count == 0) {
				count = 1;// 此时表示用户要收藏

				
			} else {
				count = 0;// 标识用户要取消收藏


			}
			getInsertFromServer(mid, count);
			break;
		case R.id.phone1:
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ mlist.get(0).getMphone()));
			startActivity(intent);

			break;
		default:
			break;
		}
	}

	private void getInsertFromServer(String mid2, int count2) {
		url1 = "http://" + MyApplication.getIp() + ":8080/happycat/MG";
		HttpUtils utils = new HttpUtils();
		mid = getIntent().getStringExtra("mid");
		RequestParams params = new RequestParams();
		params.addBodyParameter("key", "2");

		params.addBodyParameter("mid", mid);
		// 用户id尚未获取
		params.addBodyParameter("uid",uid);
		params.addBodyParameter("count", "" + count2);
		utils.send(HttpMethod.POST, url1, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						if (result.equals("true")) {
							imageButton2.setImageResource(R.drawable.star1);

							Toast.makeText(DianPuActivity.this, "已收藏",
									Toast.LENGTH_SHORT).show();

						}
						if (result.equals("false")&&!"0".equals(uid)) {
							imageButton2.setImageResource(R.drawable.star2);
							/*
							 * Toast.makeText(PictureDetailActivity.this, "已取消",
							 * Toast.LENGTH_SHORT).show();
							 */
							Toast.makeText(DianPuActivity.this, "已取消",
									Toast.LENGTH_SHORT).show();
						}
						if ("0".equals(uid)) {
							
							Toast.makeText(DianPuActivity.this, "请登录",
									Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});

	}

}
