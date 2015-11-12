package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.aps.v;
import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.User;
import com.happycat.Bean.VersionBean;
import com.happycat.global.GlobalContacts;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.happycat.util.StringUtils;
import com.happycay.fragments.WodeFragment;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyinstallActivity extends Activity implements OnClickListener {
	boolean flag1=false,flag2=false,flag3=false;
	ImageButton imageButton1,imageButton2,imageButton3;
	
	private TextView textView;
	private static String version;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_install);
		MyApplication appState = (MyApplication)this.getApplication();  
		appState.addActivity(this);
		imageButton1=(ImageButton)
				findViewById(R.id.install_liuliangright);
		imageButton2=(ImageButton)
				findViewById(R.id.install_dingweiright);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this, R.layout.title_bar_install);
		initView();
	}
	//初始化视图，绑定事件监听器
	private void initView() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.install_check).setOnClickListener(this);
		findViewById(R.id.install_gyym).setOnClickListener(this);
		findViewById(R.id.install_qctphc).setOnClickListener(this);
		findViewById(R.id.install_tcdl).setOnClickListener(this);
        findViewById(R.id.install_dingwei).setOnClickListener(this);
        findViewById(R.id.install_liuliang).setOnClickListener(this);
        textView=(TextView) findViewById(R.id.install_checkright);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 点击了返回
			
			Intent intent1 = new Intent(MyinstallActivity.this, MainActivity.class);

			// MyApplication.myflag = "1";
			// Log.e("资料===flag", MyApplication.myflag);
			startActivity(intent1);
			ActivitiyUtils.finish(this);
			break;

		case R.id.install_check:// 点击了版本
			//version=textView.getText().toString();
			update();
			//StringUtils.showToast(this, "当前已经是最新版本");
			break;
		case R.id.install_qctphc:// 点击了清除图片缓存
			StringUtils.showToast(this, "清除图片缓存成功！");
			break;
		case R.id.install_gyym:// 点击了关于夜猫
			startActivity(new Intent(getApplicationContext(), GuanyuYemaoActivity.class));
			break;
		case R.id.install_tcdl:// 点击了退出账号
			StringUtils.showToast(this, "退出当前账号");
			openNewDialog();
			//MyApplication.finishAll();
			
	/*		if (MyApplication.SP_login_status.equals("login")) {
				MyApplication.saveLogoutStatus();
			}
			startActivityForResult(MyinstallActivity.this,WodeFragment.class);*/
			break;
		default:
			break;
		}
		
	}
	
	
	
	private void startActivityForResult(MyinstallActivity myinstallActivity,
			Class<WodeFragment> class1) {
		// TODO Auto-generated method stub
		
	}
	//非wifi下省流量模式图标的转换
	public void click1(View view){
		if (flag1) {
			//默认是绿色图标
			imageButton1.setImageResource(R.drawable.yuan);
		}else {
			//单击以后是白色图标
			imageButton1.setImageResource(R.drawable.yuan1);
		}
		flag1=!flag1;
	}
      //自动定位到前位置的图标转换 
		public void click2(View view){
			if (flag2) {
				//默认是绿色图标
				imageButton2.setImageResource(R.drawable.yuan);
			}else {
				//单击以后是白色图标
				imageButton2.setImageResource(R.drawable.yuan1);
			}
			flag2=!flag2;
		}
		
		
		private void openNewDialog(){
			AlertDialog.Builder builder=new AlertDialog.Builder(MyinstallActivity.this);
			
			builder.setTitle("您确定要退出吗？");
			builder.setNegativeButton("否", null);
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which)
		            {
		                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
		                   MyApplication.finishAll();
		                  // System.exit(0);
		                    break;
		                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
		                    break;
		                default:
		                    break;
		            }
				}
			}).show();
		}
		
		public void update(){
			  version=textView.getText().toString();
			HttpUtils httpUtils=new HttpUtils();
			RequestParams params=new RequestParams();
			params.addBodyParameter("key","16");		
			params.addBodyParameter("version",version);
			String url = GlobalContacts.SERVER_URL + "GetUpload";
			httpUtils.send(HttpMethod.POST, url,params, new RequestCallBack<String>() {

			

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					StringUtils.showToast(MyinstallActivity.this, "获取版本信息失败！");
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					String result=arg0.result;
					Gson gson=new Gson();
					Type typeOfT = new TypeToken<VersionBean>() {
					}.getType();
					
					VersionBean versionBean=gson.fromJson(result, typeOfT);
				//	String a=versionBean.getVersion();
					if (versionBean.getVersion().equals(version)) {
						StringUtils.showToast(MyinstallActivity.this, "已是最新版本！");
					}else {
						StringUtils.showToast(MyinstallActivity.this, "已有最新版本，请下载最先版本");
					}
				}
			});
			
		}
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			MyApplication.myflag = "1";
		}
}
	/*	private void showDialog(){		
			  AlertDialog isExit = new AlertDialog.Builder(this).create();
	            // 设置对话框标题
	            isExit.setTitle("SweetHouse");
	            // 设置对话框消息
	            isExit.setMessage("您确定要退出吗");
	            // 添加选择按钮并注册监听
	            isExit.setButton("确定", listener);
	            isExit.setButton2("取消", listener);
	            isExit.setButton(whichButton, text, listener)
	            // 显示对话框
	            isExit.show();
	     
	    }*/
	  /*  *//**监听对话框里面的button点击事件*//*
	    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
	    {
	        public void onClick(DialogInterface dialog, int which)
	        {
	            switch (which)
	            {
	                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
	                    finish();
	                    break;
	                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
	                    break;
	                default:
	                    break;
	            }
	        }

		}
*/



