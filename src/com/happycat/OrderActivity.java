package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.MyOrderBean;
import com.happycat.adapter.MyOrderadapter;
import com.happycat.global.GlobalContacts;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.happycat.util.StringUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class OrderActivity extends Activity implements OnClickListener {

	ViewPager orderViewPager;
	PagerAdapter pagerAdapter;
	MyOrderadapter adapter, wfkadapter, wxfadapter, dpjadapter;
	List<View> listviews = new ArrayList<View>();
	TextView allTabView;
	TextView noFukuanTabView;
	TextView noXiaofeiTabView;
	TextView daiPingjiaTabView;
	ListView alllisListView, wfklListView, wxfListView, dpjListView;
	HttpUtils httpUtils;
	String uid=MyApplication.SP_user_id+"";
	String url = "http://" + MyApplication.getIp() + ":8080/happycat/myServlet";
	List<MyOrderBean> list, list1, wxfList, dpjList;
	private View allView;
	private View noFukuanView;
	private View noXiaofeiView;
	private View daipingjiaView;
	RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_order);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_order);

		initView();
		initEvent();
	}

	private void initView() {
		findViewById(R.id.btn_back).setOnClickListener(this);

		orderViewPager = (ViewPager) findViewById(R.id.order_viewpager);
		allTabView = (TextView) findViewById(R.id.order_all);
		noFukuanTabView = (TextView) findViewById(R.id.order_nofukuan);
		noXiaofeiTabView = (TextView) findViewById(R.id.order_noxiaofei);
		daiPingjiaTabView = (TextView) findViewById(R.id.order_daipingjia);

		LayoutInflater mInflater = LayoutInflater.from(this);

		allView = mInflater.inflate(R.layout.order_all_pager, null);
		noFukuanView = mInflater.inflate(R.layout.order_nofukuan_pager, null);
		noXiaofeiView = mInflater.inflate(R.layout.order_noxiaofei_pager, null);
		daipingjiaView = mInflater.inflate(R.layout.order_daipingjia_pager,
				null);

		listviews.add(allView);
		listviews.add(noFukuanView);
		listviews.add(noXiaofeiView);
		listviews.add(daipingjiaView);

		pagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(listviews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = listviews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return listviews.size();
			}
		};
		orderViewPager.setAdapter(pagerAdapter);
	}

	private void initEvent() {
			allTabView.setOnClickListener(this);
		noFukuanTabView.setOnClickListener(this);
		noXiaofeiTabView.setOnClickListener(this);
		daiPingjiaTabView.setOnClickListener(this);

		// 所有订单
		list = new ArrayList<MyOrderBean>();
		alllisListView = (ListView) allView
				.findViewById(R.id.oder_all_page_listview);
		adapter = new MyOrderadapter(list, OrderActivity.this);
		alllisListView.setAdapter(adapter);
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("key", "1");
		params.addBodyParameter("uid", uid);

		// 客户端向服务器端发送请求
		// MyApplication.getIp()

		httpUtils.send(HttpMethod.POST, // 请求方式
				url, params, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);

						String result = responseInfo.result;

						// 使用GSon框架进行json解析
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<MyOrderBean>>() {
						}.getType();
						List<MyOrderBean> resultlist = gson.fromJson(result,
								typeOfT);
						list.addAll(resultlist);
						adapter.setList(list);
						adapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("所有订单", "失败");
					}

				});// 服务器回调函数

		//待支付 订单
		list1 = new ArrayList<MyOrderBean>();
		wfklListView = (ListView) noFukuanView
				.findViewById(R.id.oder_nofukuan_page_listview);
		wfkadapter = new MyOrderadapter(list1, OrderActivity.this);
		wfklListView.setAdapter(wfkadapter);
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams param = new RequestParams();
		param.addBodyParameter("key", "2");
		param.addBodyParameter("uid", uid);
		// 客户端向服务器端发送请求

		httpUtils.send(HttpMethod.POST, // 请求方式
				url, param, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);
						String result = responseInfo.result;

						// 使用GSon框架进行json解析
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<MyOrderBean>>() {
						}.getType();
						List<MyOrderBean> resultlist = gson.fromJson(result,
								typeOfT);
						list1.addAll(resultlist);
						wfkadapter.setList(list1);

						wfkadapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("未付款订单", "失败");
					}

				});// 服务器回调函数
		// 已评价 订单
		wxfList = new ArrayList<MyOrderBean>();
		wxfListView = (ListView) noXiaofeiView
				.findViewById(R.id.oder_noxiaofei_page_listview);
		wxfadapter = new MyOrderadapter(wxfList, OrderActivity.this);
		wxfListView.setAdapter(wxfadapter);
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams param3 = new RequestParams();
		param3.addBodyParameter("key", "3");
		param3.addBodyParameter("uid", uid);
		// 客户端向服务器端发送请求

		httpUtils.send(HttpMethod.POST, // 请求方式
				url, param3, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);
						String result = responseInfo.result;

						// 使用GSon框架进行json解析
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<MyOrderBean>>() {
						}.getType();
						List<MyOrderBean> resultlist = gson.fromJson(result,
								typeOfT);
						wxfList.addAll(resultlist);
						wxfadapter.setList(wxfList);
						wxfadapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("未消费  订单", "失败");
					}

				});// 服务器回调函数
		// 待评价 订单
		dpjList = new ArrayList<MyOrderBean>();
		dpjListView = (ListView) daipingjiaView
				.findViewById(R.id.oder_daipingjia_page_listview);
		dpjadapter = new MyOrderadapter(dpjList, OrderActivity.this);
		dpjListView.setAdapter(dpjadapter);
		dpjListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				int mid = dpjList.get(arg2).getMid();
				Intent intent = new Intent(OrderActivity.this,
						DaiPingJiaOrderActivity.class);
				intent.putExtra("mid", mid);
				startActivity(intent);
				finish();

			}
		});
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams param4 = new RequestParams();
		param4.addBodyParameter("key", "4");
		param4.addBodyParameter("uid", uid);
		// 客户端向服务器端发送请求
		httpUtils.send(HttpMethod.POST, // 请求方式
				url, param4, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);
						String result = responseInfo.result;

						// 使用GSon框架进行json解析
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<MyOrderBean>>() {
						}.getType();
						List<MyOrderBean> resultlist = gson.fromJson(result,
								typeOfT);
						dpjList.addAll(resultlist);
						dpjadapter.setList(dpjList);
						dpjadapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("待评价  订单", "失败");
					}

				});// 服务器回调函数
		radioGroup = (RadioGroup) findViewById(R.id.dd_top);

		// 监听page状态改变事件
		orderViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// int currentItem = orderViewPager.getCurrentItem();
				switch (arg0) {
				case 0:
					// 滑动到全部
					// 当前被选中的radiobutton的id
					radioGroup.check(R.id.order_all);

					break;
				case 1:

					radioGroup.check(R.id.order_nofukuan);
					break;
				case 2:

					radioGroup.check(R.id.order_noxiaofei);
					break;
				case 3:

					radioGroup.check(R.id.order_daipingjia);
					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			Intent intent1 = new Intent(OrderActivity.this, MainActivity.class);

			// MyApplication.myflag = "1";
			// Log.e("资料===flag", MyApplication.myflag);
			startActivity(intent1);
			ActivitiyUtils.finish(this);
			
			
		
			break;
		case R.id.order_all:
			orderViewPager.setCurrentItem(0);
			break;
		case R.id.order_nofukuan:
			orderViewPager.setCurrentItem(1);

			break;
		case R.id.order_noxiaofei:
			orderViewPager.setCurrentItem(2);

			break;
		case R.id.order_daipingjia:
			orderViewPager.setCurrentItem(3);

			break;

		default:
			break;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MyApplication.myflag = "1";
	}
}
