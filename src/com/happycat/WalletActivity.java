package com.happycat;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.MerchatXqBean;
import com.happycat.Bean.MyBurseBean;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WalletActivity extends Activity implements OnClickListener {

	LinearLayout enterInfoLayout;
	LinearLayout chongzhiLayout;
	LinearLayout tixianLayout;
	ImageView imageView;
	TextView textView;
	private String url;
	HttpUtils httpUtils;
	List<MyBurseBean> mlist;
	private String uid=MyApplication.SP_user_id+"";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallet);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_wallet_main);
		initView();
		
		if (uid!=null) {
			initData();
		}
		
	}

	private void initData() {
		//uid=getIntent().getStringExtra("uid");
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		//String a = getIntent().getStringExtra("mid");
		params.addBodyParameter("key", "5");
		params.addBodyParameter("uid", uid);
		// 客户端向服务器端发送请求
		// MyApplication.getIp()
		url = "http://" + MyApplication.getIp() + ":8080/happycat/myServlet";

		httpUtils.send(HttpMethod.POST, // 请求方式
				url, params, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// adapter = new AdverAdapter(MainActivity.this, list);
						// 开始使用XUtils框架从服务器上下载大类表数据
						String result = responseInfo.result;
						System.out.println(result);

						Type listType = new TypeToken<LinkedList<MyBurseBean>>() {
						}.getType();
						Gson gson = new Gson();
						mlist = gson.fromJson(result, listType);
						for (int i = 0; i < mlist.size(); i++) {

						}
						String path = "http://" + MyApplication.getIp()
								+ ":8080/happycat/img/";
						MyApplication.bitmapUtils.display(imageView, path
								+ mlist.get(0).getUimg());
						textView.setText(mlist.get(0).getSum()+"元");
						
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("失败了", "修改吧");
					}

				});// 服务器回调函数

	}

	private void initView() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		enterInfoLayout = (LinearLayout) findViewById(R.id.wallet_balance_info);
		chongzhiLayout = (LinearLayout) findViewById(R.id.wallet_recharge);
		tixianLayout = (LinearLayout) findViewById(R.id.wallet_taken);
		imageView = (ImageView) findViewById(R.id.my_img);
		textView = (TextView) findViewById(R.id.my_ye);

		enterInfoLayout.setOnClickListener(this);
		chongzhiLayout.setOnClickListener(this);
		tixianLayout.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			Intent intent1 = new Intent(WalletActivity.this, MainActivity.class);
			startActivity(intent1);
			ActivitiyUtils.finish(this);
			break;
		case R.id.wallet_balance_info:
			StringUtils.showToast(this, "单击了进入账单详情");
			break;
		case R.id.wallet_recharge:
			StringUtils.showToast(this, "单击了充值");
			break;
		case R.id.wallet_taken:
			StringUtils.showToast(this, "单击了提现");
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
