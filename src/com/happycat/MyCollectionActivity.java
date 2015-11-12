package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.CollectBean;
import com.happycat.Bean.CollectGoodsBean;
import com.happycat.adapter.GoodsCollectionAdapter;
import com.happycat.adapter.ShopsCollectionAdapter;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.happycat.util.StringUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.integer;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MyCollectionActivity extends Activity implements OnClickListener {

	ViewPager viewPager;
	PagerAdapter pagerAdapter;
	List<View> listViews = new ArrayList<View>();
	RadioGroup radioGroup;
	List<CollectGoodsBean> goodslist;
	List<CollectBean> shopslist;
	ListView goodsListView, shopsListView;
	GoodsCollectionAdapter goodsaAdapter;
	ShopsCollectionAdapter shopsaAdapter;
	HttpUtils httpUtils;

	String uid = MyApplication.SP_user_id + "";
	private View goodsView;
	private View shopsView;
	String url = "http://" + MyApplication.getIp() + ":8080/happycat/GetUpload";

	RadioButton goodsButton, shopsButton;

	// TextView goodsTabView;
	// TextView shopsTabView;
	// private String[] tStrings=new String[]{""};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_collection);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_collection_main);

		initView();
		initEvent();
	}

	private void initView() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		viewPager = (ViewPager) findViewById(R.id.collection_viewpager);
		goodsButton = (RadioButton) findViewById(R.id.collection_goods);
		shopsButton = (RadioButton) findViewById(R.id.collection_shops);

		goodsButton.setOnClickListener(this);
		shopsButton.setOnClickListener(this);

		LayoutInflater mInflater = LayoutInflater.from(this);
		goodsView = mInflater.inflate(R.layout.collection_goods_pager, null);
		shopsView = mInflater.inflate(R.layout.collection_shops_pager, null);

		/*
		 * viewPager.addView(goodsView); viewPager.addView(shopsView);
		 */

		listViews.add(goodsView);
		listViews.add(shopsView);
		pagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(listViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				View view = listViews.get(position);
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
				return listViews.size();
			}
		};
		viewPager.setAdapter(pagerAdapter);

	}

	private void initEvent() {
	

	
		goodsButton.setOnClickListener(this);
		shopsButton.setOnClickListener(this);

		// 商品收藏
		goodslist = new ArrayList<CollectGoodsBean>();
		goodsListView = (ListView) goodsView
				.findViewById(R.id.oder_all_page_listview);

		goodsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int mid = goodslist.get(arg2).getMid();
				String mname = goodslist.get(arg2).getMname();
				String mimg = goodslist.get(arg2).getMimg();
				String mpsf = goodslist.get(arg2).getTip();
				String mpjsu = goodslist.get(arg2).getLongtime();

				String mqsj = goodslist.get(arg2).getMprice();
				String mtime = goodslist.get(arg2).getMtime();
				Intent intent = new Intent(MyCollectionActivity.this,
						MerchatDataActivity.class);
				intent.putExtra("mid", mid);
				intent.putExtra("name", mname);
				intent.putExtra("psf", mpsf);
				intent.putExtra("pjsu", mpjsu);
				intent.putExtra("qsj", mqsj);
				intent.putExtra("img", mimg);
				intent.putExtra("mtime", mtime);
				startActivity(intent);

			}
		});
		goodsaAdapter = new GoodsCollectionAdapter(goodslist,
				MyCollectionActivity.this);

		goodsListView.setAdapter(goodsaAdapter);

		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("key", "15");
		params.addBodyParameter("uid", uid);

		httpUtils.send(HttpMethod.POST, // 请求方式
				url, params, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);

						String result = responseInfo.result;
						Log.e("美食读出数据", result);
						// 使用GSon框架进行json解析
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<CollectGoodsBean>>() {
						}.getType();
						List<CollectGoodsBean> resultlist = gson.fromJson(
								result, typeOfT);
						goodslist.addAll(resultlist);
						goodsaAdapter.setList(goodslist);
						goodsaAdapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e(" 商品收藏", "修改吧");
					}

				});// 服务器回调函数

		// 店铺收藏
		shopslist = new ArrayList<CollectBean>();
		shopsListView = (ListView) shopsView
				.findViewById(R.id.oder_all_page1_listview);
		shopsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int mid = shopslist.get(arg2).getMid();
				String mname = shopslist.get(arg2).getMname();
				String mimg = shopslist.get(arg2).getMimg();
				String mpsf = shopslist.get(arg2).getTip();
				String mpjsu = shopslist.get(arg2).getLongtime();
				String mphone = shopslist.get(arg2).getMphone();
				String mqsj = shopslist.get(arg2).getMprice();
				String mtime = shopslist.get(arg2).getMtime();
				Intent intent = new Intent(MyCollectionActivity.this,
						MerchatDataActivity.class);
				intent.putExtra("mid", mid);
				intent.putExtra("name", mname);
				intent.putExtra("psf", mpsf);
				intent.putExtra("pjsu", mpjsu);
				intent.putExtra("phone", mphone);
				intent.putExtra("qsj", mqsj);
				intent.putExtra("img", mimg);
				intent.putExtra("mtime", mtime);
				startActivity(intent);

			}
		});
		{

			shopsaAdapter = new ShopsCollectionAdapter(shopslist,
					MyCollectionActivity.this);

			shopsListView.setAdapter(shopsaAdapter);

			// 开始使用XUtils框架从服务器上下载大类表数据
			httpUtils = new HttpUtils();
			RequestParams params1 = new RequestParams();
			params1.addBodyParameter("key", "14");
			params1.addBodyParameter("uid", uid);

			httpUtils.send(HttpMethod.POST, // 请求方式
					url, params1, new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							// adapter = new AdverAdapter(MainActivity.this,
							// list);

							String result = responseInfo.result;
							Log.e("店铺读出数据", result);
							// 使用GSon框架进行json解析
							Gson gson = new Gson();
							Type typeOfT = new TypeToken<List<CollectBean>>() {
							}.getType();
							List<CollectBean> resultlist = gson.fromJson(
									result, typeOfT);
							shopslist.addAll(resultlist);
							shopsaAdapter.setList(shopslist);

							shopsaAdapter.notifyDataSetChanged();

						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// 访问网络失败调用方法
							// TODO Auto-generated method stub
							Log.e("店铺收藏", "修改吧");
						}

					});// 服务器回调函数

			// 找到viewpager
			radioGroup = (RadioGroup) findViewById(R.id.ddd_top);
			viewPager.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {
					int currentItem = viewPager.getCurrentItem();
					switch (currentItem) {
					case 0:
						radioGroup.check(R.id.collection_goods);
						break;
					case 1:
						radioGroup.check(R.id.collection_shops);
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

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.collection_goods:
			viewPager.setCurrentItem(0);

			break;

		case R.id.collection_shops:
			viewPager.setCurrentItem(1);

			break;

		case R.id.btn_back:
			
			Intent intent1 = new Intent(MyCollectionActivity.this, MainActivity.class);

			// MyApplication.myflag = "1";
			// Log.e("资料===flag", MyApplication.myflag);
			startActivity(intent1);
			ActivitiyUtils.finish(this);
			

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
