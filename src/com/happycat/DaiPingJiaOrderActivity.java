package com.happycat;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import com.example.happucat.R;
import com.example.happucat.R.layout;
import com.example.happucat.R.menu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.MerchatXqBean;
import com.happycat.Bean.MyOrderBean;
import com.happycat.Bean.SouSuoBean;
import com.happycat.global.GlobalContacts;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DaiPingJiaOrderActivity extends Activity implements
		OnClickListener {
	ImageView gimg;
	HttpUtils httpUtils;
	Intent intent;
	String url = "http://" + MyApplication.getIp() + ":8080/happycat/myServlet";
	String url1 = "http://" + MyApplication.getIp() + ":8080/happycat/MG";
	String uid = MyApplication.SP_user_id + "";;
	TextView mname, gname, istatus, sumprice, number, iphone, identifier;
	List<MyOrderBean> mlist;
	EditText editText;
	Button button;
	ImageButton imageButton;
	String cimg;

	RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.order_pingjia);
		initViews();
		initData();

	}

	private void initData() {

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
						Type listType = new TypeToken<LinkedList<MyOrderBean>>() {
						}.getType();

						mlist = gson.fromJson(result, listType);
						for (int i = 0; i < mlist.size(); i++) {

						}
						String path = "http://" + MyApplication.getIp()
								+ ":8080/happycat/img/";
						MyApplication.bitmapUtils.display(gimg, path
								+ mlist.get(0).getGimg());
						mname.setText("订单名称：" + mlist.get(0).getMname());
						gname.setText("购买商品： " + mlist.get(0).getGname());
						istatus.setText("订单号： " + mlist.get(0).getIstatus());
						sumprice.setText("￥" + mlist.get(0).getPrice());

						number.setText("x " + mlist.get(0).getNum());

						iphone.setText("下单电话： " + mlist.get(0).getiPhone());
						identifier.setText(mlist.get(0).getIdentifier());

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("待评价  订单", "失败");
					}

				});// 服务器回调函数

	}

	private void initViews() {
		gimg = (ImageView) findViewById(R.id.dd_gimg);
		mname = (TextView) findViewById(R.id.dd_mname);
		gname = (TextView) findViewById(R.id.dd_gname);
		identifier = (TextView) findViewById(R.id.dd_identifier);
		iphone = (TextView) findViewById(R.id.dd_phone);
		number = (TextView) findViewById(R.id.dd_number);
		sumprice = (TextView) findViewById(R.id.dd_sumprice);
		istatus = (TextView) findViewById(R.id.dd_istatus);
		editText = (EditText) findViewById(R.id.pingyu);
		editText.setOnClickListener(this);
		button = (Button) findViewById(R.id.tijiao);
		button.setOnClickListener(this);
		imageButton = (ImageButton) findViewById(R.id.sjreturn);
		imageButton.setOnClickListener(this);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		float a = ratingBar.getRating();
		if (a == 1) {
			cimg = "xing1.png";
		}
		if (a == 2) {
			cimg = "xing2.png";
		}
		if (a == 3) {
			cimg = "xing3.png";
		}
		if (a == 4) {
			cimg = "xing4.png";
		}
		if (a == 5) {
			cimg = "xing5.png";
		}
		if (a == 0) {
			cimg = "xing5.png";
		}
	}

	@Override
	public void onClick(View arg0) {
		// 传入评语
		switch (arg0.getId()) {
		case R.id.pingyu:

			break;
		case R.id.tijiao:
			if (2 < editText.getText().toString().trim().length()) {
				String ttext = editText.getText().toString().trim();
				try {
					String result = URLEncoder.encode(ttext, "utf-8");
					httpUtils = new HttpUtils();
					RequestParams params = new RequestParams();
					params.addBodyParameter("key", 7 + "");
					params.addBodyParameter("uid", uid);
					params.addBodyParameter("mid", mlist.get(0).getMid() + "");
					params.addBodyParameter("ttext", result);
					params.addBodyParameter("cimg", cimg);

					httpUtils.send(HttpMethod.POST, url1, params,
							new RequestCallBack<String>() {

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {

								}

								@Override
								public void onFailure(HttpException error,
										String msg) {

								}
							});
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 改变订单状态
				// 开始使用XUtils框架从服务器上下载大类表数据
				httpUtils = new HttpUtils();
				RequestParams params = new RequestParams();
				params.addBodyParameter("key", "6");
				params.addBodyParameter("uid", uid);
				params.addBodyParameter("mid", mlist.get(0).getMid() + "");

				// 客户端向服务器端发送请求

				httpUtils.send(HttpMethod.POST, // 请求方式
						url1, params, new RequestCallBack<String>() {

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {

							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								// 访问网络失败调用方法
								// TODO Auto-generated method stub
								Log.e("所有订单", "失败");
							}

						});// 服务器回调函数

				finish();
			} else {
				Toast.makeText(DaiPingJiaOrderActivity.this, "请输入评语哦亲", 0)
						.show();
			}
			break;
		case R.id.sjreturn:
			if (editText.getText() == null) {
				intent = new Intent(DaiPingJiaOrderActivity.this,
						OrderActivity.class);
				startActivity(intent);
				finish();
			} else {
				finish();
			}

			break;
		default:
			break;
		}

	}

}
