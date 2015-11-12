package com.happycat;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.MyShouYeBean;
import com.happycat.global.GlobalContacts;
import com.happycat.util.DanjiUtils;
import com.happycat.util.MyApplication;
import com.happycay.fragments.HomeFragment;
import com.happycay.fragments.WaimaiFragment;
import com.happycay.fragments.WodeFragment;
import com.happycay.fragments.XiaoxiFragment;
import com.lidroid.xutils.HttpUtils;
import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	Fragment fragment;
	FragmentManager manager;
	FragmentTransaction transaction;
	RadioGroup group;
	RadioButton waimai, wode,home;
	GestureDetector mDetector;
	HttpUtils httpUtils;
	String flag;
	Intent intent;
	List<MyShouYeBean> mlist;
	TextView ncTextView, sexTextView, zhTextView;
	ImageButton imageButton;
	String uid=MyApplication.SP_user_id+"";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 去掉标题栏
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		mDetector = new GestureDetector(this, new DanjiUtils(this));

		initView();
		initDatas();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mDetector.onTouchEvent(event);

	}

	private void firstShow() {
		
		flag = MyApplication.myflag;
		Log.e("首页===flag", MyApplication.myflag);
		if ("1".equals(flag)) {
			Bundle b = new Bundle();
			b.putString("uid", uid);
			b.putString("flag", flag);
			 MyApplication.myflag="0";
			fragment = new WodeFragment();
			fragment.setArguments(b);
			replace(fragment);
			wode.setChecked(true);
			
			Log.e("首页----uid", uid);
			
		} else {
			fragment = new HomeFragment();
			replace(fragment);
			home.setChecked(true);			
		}
	}

	private void initView() {
		imageButton = (ImageButton) findViewById(R.id.tixing);
		sexTextView = (TextView) findViewById(R.id.dlsex);
		zhTextView = (TextView) findViewById(R.id.dlzh);
		ncTextView = (TextView) findViewById(R.id.dlzc);
		// TODO Auto-generated method stub
		group = (RadioGroup) findViewById(R.id.bottomtab);
		waimai = (RadioButton) findViewById(R.id.waimai);
		home=(RadioButton) findViewById(R.id.shouye);
		wode = (RadioButton) findViewById(R.id.wode);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch (arg1) {
				case R.id.shouye:

					fragment = new HomeFragment();
					break;
				case R.id.waimai:

					fragment = new WaimaiFragment();

					break;

				case R.id.xiaoxi:

					fragment = new XiaoxiFragment();

					break;
				case R.id.wode:

					Bundle b = new Bundle();
					b.putString("flag", MyApplication.myflag);
					b.putString("uid", uid);
					fragment = new WodeFragment();
					fragment.setArguments(b);
					break;
				default:
					break;
				}
				replace(fragment);
			}
		});
	}

	private void replace(Fragment fragment) {
		transaction = manager.beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.commit();
	}

	private void initDatas() {
		manager = getSupportFragmentManager();
	}

	// 此方法用来回调首页右上角搜索单击事件，并调用搜索fragment
	public void cat() {
		fragment = new WaimaiFragment();
		replace(fragment);
		waimai.setChecked(true);
	}

	@Override
	protected void onResume() {
		firstShow();
		super.onResume();
	}

}