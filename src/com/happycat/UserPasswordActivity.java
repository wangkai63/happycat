package com.happycat;

import com.example.happucat.R;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.http.client.entity.UploadEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserPasswordActivity extends Activity {
	TextView t1, t2, t3, t4;
	EditText et1, et2, et3;
	Button xgPassword;
	ImageButton fanhui;
	String xinpsd1, xinpsd2, jiupsd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_password);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_my_password);
		initView();
		
	}

	private void initView() {
		t1 = (TextView) findViewById(R.id.tv_psd_jiu);
		t2 = (TextView) findViewById(R.id.tv_psd_xin);
		t3 = (TextView) findViewById(R.id.tv_psd_zai);
		t4 = (TextView) findViewById(R.id.tv_psd_tixing);
		et1 = (EditText) findViewById(R.id.et_psd_jiu);
		et2 = (EditText) findViewById(R.id.et_psd_xin);
		et3 = (EditText) findViewById(R.id.et_psd_zai);
		xgPassword = (Button) findViewById(R.id.btn_psd_xin);
		fanhui = (ImageButton) findViewById(R.id.btn_xiugaimima);
        fanhui.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent=new 
			Intent(UserPasswordActivity.this, UserActivity.class);
				setResult(Activity.DEFAULT_KEYS_DISABLE);
				finish();
			}
		});
		// password();
		xgPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Tool();
				if(jiupsd.equals("")){
					 Toast.makeText(UserPasswordActivity.this, "请输入密码",
								Toast.LENGTH_LONG).show();
					
					
					
				}
				else{
				   if (jiupsd.equals(MyApplication.SP_user_password)) {
					  if (xinpsd1.equals(xinpsd2)) {
						password();
						 finish();
					}   if(!xinpsd1.equals(xinpsd2)) {
						Toast.makeText(UserPasswordActivity.this, "前后密码不一致",
								Toast.LENGTH_LONG).show();
					}
					 
				} 
				
			      if(!jiupsd.equals(MyApplication.SP_user_password)){
					 Toast.makeText(UserPasswordActivity.this, "当前密码错误",
							Toast.LENGTH_LONG).show();
				
				}
				
			}
			}	
			
		});

	}

	private void Tool() {
		xinpsd1 = et2.getText().toString().trim();
		xinpsd2 = et3.getText().toString().trim();
		jiupsd = et1.getText().toString().trim();

	}

	private void password() {

		RequestParams params = new RequestParams();
		params.addBodyParameter("key", "" + 4);
		params.addBodyParameter("uphone", MyApplication.SP_user_phone);
		// params.addBodyParameter("upassword", jiupsd);
		params.addBodyParameter("newpassword", xinpsd1);	
		params.addBodyParameter("et1", xinpsd2);
	
		HttpUtils httpUtils = new HttpUtils();
		String url = "http://" + MyApplication.getIp() + ":8080/happycat/User";

		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(UserPasswordActivity.this, "请检查网络",
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						if (result.equals("success")) {
							Toast.makeText(UserPasswordActivity.this, "密码修改成功",
									0).show();
							
						} else {
							Toast.makeText(UserPasswordActivity.this,
									"密码修改失败，请检查网络", 0).show();
						}

					}
				});
                   
	}
}
