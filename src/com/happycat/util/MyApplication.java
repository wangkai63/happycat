package com.happycat.util;

import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;
import com.lidroid.xutils.BitmapUtils;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyApplication extends Application {
	private static String ip = "192.168.191.1";
	//private static String ip = "10.203.1.15";
	public static SharedPreferences sharedPreferences;
	public static String SP_login_status;// 用户登录状态
	public static int SP_user_id=0;// 登录用户的id
	public static String myflag="0";//标志
	public static String SP_user_phone;// 登录用户的手机号
	public static String SP_user_password = "SP_user_password";// 登录用户的密码
	public static String SP_user_address;//用户登录时的地址
	private static Editor editor;
	
	private static List<Activity> mainActivity = new ArrayList<Activity>();

	
	
	public static String getIp() {
		return ip;
	}
	public static BitmapUtils bitmapUtils;
	@Override
	public void onCreate() {
		//application的onCreate只会调用一次，加之bitmapUtils是
		//static修饰的，表示所有对象共享
		bitmapUtils = new BitmapUtils(getApplicationContext());
		//图片加载失败时的图片
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
		//图片加载时显示的图片
		bitmapUtils.configDefaultLoadingImage(R.drawable.b1);
		super.onCreate();
		getSharedPreferences();
		
	}
	private void getSharedPreferences() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("sharePreferences",
				MODE_PRIVATE);
		editor = sharedPreferences.edit();
		SP_login_status = sharedPreferences.getString(
				"login_status", "logout");
		SP_user_id = sharedPreferences.getInt("user_id",
				0);
		SP_user_phone = sharedPreferences.getString("user_phone",
				null);
		SP_user_password = sharedPreferences.getString(
				"user_password", null);
		SP_user_address=sharedPreferences.getString("user_address", null);

	}

	public static void saveLoginStatus(String status, int id, String phone,
			String password,String address) {
		SP_login_status = status;
		SP_user_id = id;
		SP_user_phone = phone;
		SP_user_password = password;
		editor.putString("login_status", status);
		editor.putInt("user_id", id);
		editor.putString("user_phone", phone);
		editor.putString("user_password", password);
		editor.putString("user_address", address);
		editor.commit();
	}

	public static void saveLogoutStatus() {
		SP_login_status = "logout";
		SP_user_id = 0;
		SP_user_password = null;
		editor.clear();
		editor.putString("user_phone", SP_user_phone);
	

		editor.commit();
	}
	public List<Activity> MainActivity() {
		return mainActivity;
	}

	public void addActivity(Activity act) {
		mainActivity.add(act);
	}

	public static void finishAll() {
		for (Activity act : mainActivity) {
			if (!act.isFinishing()) {
				act.finish();
			}
		}
		mainActivity = null;
	}

}
