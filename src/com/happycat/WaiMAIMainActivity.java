package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.Goods;
import com.happycat.Bean.MerchatBean;
import com.happycat.adapter.Merchatadapter;
import com.happycat.adapter.Myadapter;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.event.OnScrollStateChanged;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class WaiMAIMainActivity extends Activity {

	ListView listView;
	// 数据来源
	List<MerchatBean> list = new ArrayList<MerchatBean>();
	Merchatadapter adapter;
	HttpUtils httpUtils;
	private String url;
	TextView textView;
	ImageButton iButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waimai_merchat_main);

		initview();
		Intent intent = getIntent();
		url = intent.getStringExtra("weburl");
		initData();
		if (adapter == null) {
			textView = (TextView) findViewById(R.id.emptyview);
			textView.setText("访问网址不存在!!!");
			listView.setEmptyView(textView);
		}

	}

	public void initData() {

		// 向服务器发送请求，查询数据
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				// 使用GSon框架进行json解析
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<MerchatBean>>() {
				}.getType();
				List<MerchatBean> resultlist = gson.fromJson(result, typeOfT);
				list.addAll(resultlist);

				adapter.setList(list);
				adapter.notifyDataSetChanged();

			}
		});

	}

	public void initview() {
		// TODO Auto-generated method stub
		Log.e("www", "进入listview");
		listView = (ListView) findViewById(R.id.marchet_listview);
		adapter = new Merchatadapter(list, this);
		listView.setAdapter(adapter);
		// 当用户快速滑动界面的时候，暂停加载图片，当滑动结束，继续加载界面可见部分图片
		// 此时，不可见界面依然不再加载
		listView.setOnScrollListener(new PauseOnScrollListener(
				MyApplication.bitmapUtils, // bitmaputil工具类
				false,// 正常滑动，true表示暂停加载，false继续加载
				true));// 飞速滑动，true表示暂停加载，false继续加载

		// 监听每个item的事件。
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int mid = list.get(position).getMid();
				String mname = list.get(position).getMname();
				String mpsf = "" + list.get(position).getTip();
				String mpjsu = list.get(position).getLongtime();
				double mqsj = list.get(position).getMprice();
				String mtime = list.get(position).getMtime();
				String mphone = list.get(position).getMphone();
				String mimg = list.get(position).getMimg();
				
				Intent intent = new Intent(WaiMAIMainActivity.this,
						MerchatDataActivity.class);
				// intent.putExtra("mid", mid);
				intent.putExtra("mid", mid);
				intent.putExtra("name", mname);
				intent.putExtra("psf", mpsf);
				intent.putExtra("pjsu", mpjsu);
				intent.putExtra("qsj", mqsj);
				intent.putExtra("phone", mphone);
				intent.putExtra("img", mimg);
				intent.putExtra("mtime", mtime);
				// intent.putExtra("bean", list.get(position));
				startActivity(intent);

			}
		});
		// 监听顶部导航返回按钮。
		iButton = (ImageButton) findViewById(R.id.photo);
		iButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

}
