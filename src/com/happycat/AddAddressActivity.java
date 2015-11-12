package com.happycat;

import com.example.happucat.R;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AddAddressActivity extends Activity {
	EditText province, city, country, detail, phone;
	String aprovince, acity, acountry, adetail, aphone;
	Button okButton;
	RelativeLayout layout;
	// ImageButton imageButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_address_add);
		initview();
		initData();
	}

	private void initview() {
		// TODO Auto-generated method stub
		province = (EditText) findViewById(R.id.add_address_province);
		city = (EditText) findViewById(R.id.add_address_city);
		country = (EditText) findViewById(R.id.add_address_country);
		detail = (EditText) findViewById(R.id.add_address_detail);
		phone = (EditText) findViewById(R.id.add_address_phone);
		okButton = (Button) findViewById(R.id.address_ok);
		
		/*
		 * imageButton = (ImageButton) findViewById(R.id.photo1);
		 * imageButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { finish();
		 * 
		 * } });
		 */
	}

	private void initData() {
		
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				aprovince = province.getText().toString();
				acity = city.getText().toString();
				acountry = country.getText().toString();
				adetail = detail.getText().toString();
				aphone = phone.getText().toString();
				if (aprovince.isEmpty() || acity.isEmpty()
						|| acountry.isEmpty() || adetail.isEmpty()
						|| aphone.isEmpty()) {
					Toast.makeText(AddAddressActivity.this, "不能为空哦", 0).show();
				} else {

					String url = "http://" + MyApplication.getIp()
							+ ":8080/happycat/Addaddress?uid="
							+ MyApplication.SP_user_id + "&province="
							+ aprovince + "&city=" + acity + "&country="
							+ acountry + "&detail=" + adetail + "&phone="
							+ aphone;

					HttpUtils httpUtils = new HttpUtils();
					httpUtils.send(HttpMethod.GET, url,
							new RequestCallBack<Object>() {

								@Override
								public void onFailure(HttpException arg0,
										String arg1) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(ResponseInfo<Object> arg0) {
									// TODO Auto-generated method stub
									Toast.makeText(AddAddressActivity.this,
											"添加成功", 0).show();
									setResult(0, new Intent(
											AddAddressActivity.this,
											AddressActivity.class));

									finish();
								}
							});
				}
			}
		});

	}

}
