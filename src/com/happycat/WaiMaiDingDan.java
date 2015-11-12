package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.happucat.R;
import com.example.happucat.R.id;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.Goods;
import com.happycat.Bean.MyBurseBean;
import com.happycat.adapter.DingDan_indentAdapter;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class WaiMaiDingDan extends Activity {
	TextView jine, result_address;
	EditText phone;
	//返回按钮
	ImageButton left, rite;
    //支付方式按钮
	RadioButton qb, hdfk;
	Button queren;
	boolean flag,f;
	String name, dcphone, address,goodsGson,yuer,s;
    ListView IndentlistView;
    List<MyBurseBean> mlist;
    DingDan_indentAdapter adapter;
    //购买的商品集合
    List<Goods> list;
    //购买商品数量初始为0
    int inumber=0,mid=0,uid=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.waimai_dingdan);
		initview();
        initdata();
	}

	private void initdata() {
		
	}

	private void initview() {
		IndentlistView=(ListView) findViewById(R.id.dingdan_deta);
	// TODO Auto-generated method stub
		result_address = (TextView) findViewById(R.id.result_address);
		//获取用户地址信息和联系方式并默认为收货地址。
		Log.e("address==========", MyApplication.SP_user_address);
		result_address.setText(MyApplication.SP_user_address+"\n"+"联系电话"+MyApplication.SP_user_phone);
		jine = (TextView) findViewById(R.id.jine);
		Intent intent = getIntent();
		// 获取商家名称。
		name = intent.getStringExtra("mname");
		 goodsGson = intent.getStringExtra("goodsList");
		Type typeOfT = new TypeToken<List<Goods>>() {
		}.getType();
		Gson gson = new Gson();
		list = gson.fromJson(goodsGson, typeOfT);
		adapter=new DingDan_indentAdapter(list, this);
		IndentlistView.setAdapter(adapter);
		
	    //获取购买商品的数量
		
		for (int i = 0; i < list.size(); i++) {
			inumber+=list.get(i).getGnum();
		
		}
		mid=list.get(0).getMid();
		uid=MyApplication.SP_user_id;
		Log.e("uid", ""+uid);
		Log.e("mid", ""+mid);
		
	    
		 String s1 = intent.getStringExtra("jine");
		 s=s1.substring(0, s1.length()-1);
		jine.setText("应付" + s + "元");
		// 返回
		left = (ImageButton) findViewById(R.id.photo);
		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		rite = (ImageButton) findViewById(R.id.address);
		rite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WaiMaiDingDan.this,
						AddressActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		// 订餐电话
		phone = (EditText) findViewById(R.id.dcphone);
		//设置edittext为不可编辑状态
		phone.setFocusable(false);
		phone.setFocusableInTouchMode(false);
		phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//设置edittext为可编辑状态
			  phone.setFocusable(true);
			  phone.setFocusableInTouchMode(true);
			  phone.requestFocus();
			}
		});


		// 确认
		queren = (Button) findViewById(R.id.queren);
		queren.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String adds = result_address.getText().toString();
				if (!f) {
					Toast.makeText(WaiMaiDingDan.this, "请选择支付方式",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent2 = new Intent(WaiMaiDingDan.this,
							WaiMai_zhifuActivity.class);
					dcphone = phone.getText().toString();
					intent2.putExtra("phone", dcphone);
					intent2.putExtra("mname", name);
					intent2.putExtra("flag", flag);
					intent2.putExtra("address", adds);
					intent2.putExtra("mid", ""+mid);
					intent2.putExtra("inumber", ""+inumber);
					intent2.putExtra("price", s);
					if(flag){
					intent2.putExtra("yuer", yuer);}
					//将购买的商品gson传到支付页面
					intent2.putExtra("goods", goodsGson);
					
					startActivity(intent2);
				}
			}
		});
		// 支付方式
		qb = (RadioButton) findViewById(R.id.qb);
		hdfk = (RadioButton) findViewById(R.id.hdfk);
		qb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				hdfk.setChecked(false);
				flag = true;
				f=true;
				initData();
			}
		});

		hdfk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qb.setChecked(false);
				flag = false;
				f=true;

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			address = bundle.getString("address");
			result_address.setText(address);

		}
	}

private void initData() {
		
		// 开始使用XUtils框架从服务器上下载大类表数据
	HttpUtils	httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		//String a = getIntent().getStringExtra("mid");
		params.addBodyParameter("key", "5");
		params.addBodyParameter("uid", ""+uid);
		// 客户端向服务器端发送请求
		// MyApplication.getIp()
	String	url1 = "http://" + MyApplication.getIp() + ":8080/happycat/myServlet";

		httpUtils.send(HttpMethod.POST, // 请求方式
				url1, params, new RequestCallBack<String>() {

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
						
	
						   yuer=""+mlist.get(0).getSum();
						   Log.e("余额", yuer);
						
						
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 访问网络失败调用方法
						// TODO Auto-generated method stub
						Log.e("失败了", "修改吧");
					}

				});// 服务器回调函数

	}
}
