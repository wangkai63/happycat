package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.User;
import com.happycat.Bean.goodsclassify;

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

public class LoginActivity extends Activity implements OnClickListener {
	private EditText et_phone, et_password;

	String username;
	String password;
	int uid;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	List<User> userlist = new ArrayList<User>();

	// Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_login);
		initView();
	}

	private void initView() {
		// button=(Button) findViewById(R.id.btn_back);
		// button.setOnClickListener(this);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_password = (EditText) findViewById(R.id.et_password);

		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.action_bar_register).setOnClickListener(this);
		findViewById(R.id.tv_login_forget).setOnClickListener(this);
		findViewById(R.id.tv_login_register).setOnClickListener(this);
		findViewById(R.id.login_btn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 点击了返回
			/*
			 * StringUtils.showToast(this, "返回"); ActivitiyUtils.finish(this);
			 */
			finish();
			break;

		case R.id.action_bar_register:// 点击了注册
			// StringUtils.showToast(this, "注册");
			startActivity(new Intent(this, RegisterActivity.class));
			finish();
			break;

		case R.id.tv_login_forget:// 点击了注册
			// StringUtils.showToast(this, "注册");
			startActivity(new Intent(this, ForgetPwdActivity.class));
			finish();
			break;
		case R.id.login_btn:// 点击了登录
			if (StringUtils.loginCheck(this, et_phone, et_password)) {
				username = et_phone.getText().toString();
				password = et_password.getText().toString();
				login(username, password);// 验证通过开始登录

			}
			break;

		case R.id.tv_login_register:// 点击了注册
			// StringUtils.showToast(this, "注册");
			startActivity(new Intent(this, RegisterActivity.class));
			break;
		}
	}

	// 登录方法。获取输入框中的用户名与数据库中密码数据对比，正确返回true
	private void login(String username, String password) {
		username = et_phone.getText().toString();
		password = et_password.getText().toString();
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("username", username);
		params.addQueryStringParameter("password", password);
		params.addQueryStringParameter("key", "10");

		String url = GlobalContacts.SERVER_URL + "GetUpload";

		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(LoginActivity.this, "该用户不存在！",
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {

				String result = responseInfo.result;
				Log.e("result---------", result);
				Gson gson = new Gson();
				Type typeOfT = (Type) new TypeToken<ArrayList<User>>() {
				}.getType();
				ArrayList<User> list = gson.fromJson(result,
						(java.lang.reflect.Type) typeOfT);
				Log.e("user", list.toString());
				if (list.size() == 1) {
					MyApplication.saveLoginStatus("login",
							list.get(0).getUid(), list.get(0).getUphone(), list
									.get(0).getUpassword(),
									""+list.get(0).getUprovince()+list.get(0).getUcity()+
								    list.get(0).getUcountry()+list.get(0).getUdetail());


					Intent intent = new Intent(LoginActivity.this,
							MainActivity.class);
					/*
					 * intent.putExtra("flag", "1"); intent.putExtra("uid",
					 * list.get(0).getUid()+"");
					 */
					MyApplication.myflag = "1";
					uid = list.get(0).getUid();
					MyApplication.SP_user_id=uid;
					startActivity(intent);
					LoginActivity.this.finish();

				} else {
					Toast.makeText(LoginActivity.this, "fail",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}
}