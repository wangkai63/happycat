package com.happycat;

//import cn.smssdk.EventHandler;
//import cn.smssdk.SMSSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.example.happucat.R;
import com.happycat.global.GlobalContacts;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.StringUtils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity implements OnClickListener {
	
	EditText et_phone,et_code,et_password;
	Button btn_getCode, registerButton;
	//private String phString;
	String username;
	private String password;
	
	
	//填写从短信sdk应用后台注册得到的APPkey。
	private static String APPKEY="b6f91b24020b";
	//填写从短信sdk应用后台注册得到的APPSECRET
	private static String APPSECRET="b6d3d9b2908868d8e95d8728f45746a9";
	private Handler handler;
	
	
	

	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_register);
		 SMSSDK.initSDK(RegisterActivity.this, APPKEY, APPSECRET);
		 SMSSDK.registerEventHandler(eh); //注册短信回调
		ActivitiyUtils.setActionBarLayout(getActionBar(), this, R.layout.title_bar_register);
		initview();
		 //启动SDK
     
     
      
      handler=new Handler(){
    	  @Override
			public void handleMessage(Message message) {
			switch (message.what) {
			case 0:
				StringUtils.showToast(RegisterActivity.this, message.getData().getString("msg"));
				break;
			default:
				break;
			}
		}};
      
	}
	

	private void initview() {
		et_phone=(EditText) findViewById(R.id.ed_phone);
		
		et_password=(EditText) findViewById(R.id.ed_password);
		et_code=(EditText) findViewById(R.id.ed_code);
		registerButton=(Button) findViewById(R.id.btn_register);
		btn_getCode=(Button) findViewById(R.id.btn_getCode);
		registerButton.setOnClickListener(this);
	    btn_getCode.setOnClickListener(this);
		findViewById(R.id.btn_back).setOnClickListener(this); 
	}
	


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 点击了返回
			StringUtils.showToast(this, "返回");
			ActivitiyUtils.finish(this);
			break;
		
	
		case R.id.btn_getCode://获取验证码
	
		getCode();
			break;
		case R.id.btn_register:// 校验验证码
			registerStart();
			addUser();
			break;
			default:
				break;		
			}	
	}

	
	
	 private void registerStart() {
		 if (!StringUtils.fastLoginCheck(this, et_phone, et_code)) {
			
				return;
		}
//		 String code = et_code.getText().toString();
			//String phoneNumber = et_phone.getText().toString();
			
		//	String password=et_password.getText().toString();
		
	}


	private void getCode() {
		 if (!StringUtils.isPhone(this, et_phone)) {
				return;
			}
			String phone = et_phone.getText().toString();
			
			SMSSDK.getVerificationCode("86",phone);
			btn_getCode.setEnabled(false);
			new DownTimer(60000, 1000).start();
		
	}



	EventHandler eh=new EventHandler(){

	        @Override
	        public void afterEvent(int event, int result, Object data) {

	            if (result == SMSSDK.RESULT_COMPLETE) {
	                //回调完成
	                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
	                    //提交验证码成功
	                    Log.e("event", "提交验证码成功");
	                    Message message=new Message();
	                    message.what=0;
	                    message.obj="提交验证码成功";
	                    handler.sendMessage(message);

	                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
	                    //获取验证码成功
	                    Log.e("event", "获取验证码成功");
	                    Message message=new Message();
						Bundle bundle=new Bundle();
						bundle.putString("msg", "验证码已发送至您的手机,有效时间5分钟，请及时填写！");
						message.setData(bundle);
						handler.sendMessage(message);

	                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
	                    //返回支持发送验证码的国家列表
	                }
	            }else{
	                ((Throwable)data).printStackTrace();
	                System.err.println("------获取验证码出错---------");
					Message message=new Message();
					message.what=0;
					Bundle bundle=new Bundle();
					bundle.putString("msg", "验证码发送失败，请检查网络稍候重试！");
					message.setData(bundle);
					handler.sendMessage(message);
	            }
	        }
	    };
	    
	    //倒计时验证码获取
	    class DownTimer extends CountDownTimer {

			public DownTimer(long millisInFuture, long countDownInterval) {
				super(millisInFuture, countDownInterval);
			}

			@Override
			public void onFinish() {
				btn_getCode.setText("重新获取");
				btn_getCode.setEnabled(true);
			}

			@Override
			public void onTick(long millisUntilFinished) {
				btn_getCode.setText(millisUntilFinished/1000+"秒");
			}
			}
	    //将用户名和密码添加到数据库中
	    public void addUser(){
	  /* 	phoneNumber = et_phone.getText().toString();
	   	password=et_password.getText().toString(); 
	   	RequestParams params=new RequestParams();
	   	params.addQueryStringParameter("uphone",phoneNumber);
		params.addQueryStringParameter("upassword",password);
		params.addQueryStringParameter("key","1");
	    	HttpUtils http=new HttpUtils();
	    	String url=GlobalContacts.SERVER_URL+"/GetUploadServlet";*/
	    	//上传数据
	    	username=et_phone.getText().toString();
	    	password=et_password.getText().toString();
	    	
	    	
	    	//ip
			String url=GlobalContacts.SERVER_URL+"GetUpload";
			HttpUtils httpUtils =new HttpUtils();
			RequestParams params=new RequestParams();
			params.addQueryStringParameter("key","11");
			params.addBodyParameter("uphone",username);
			params.addBodyParameter("password",password);
	    	
			httpUtils.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					//Toast.makeText(RegisterActivity.this, "注册失败，请重试", Toast.LENGTH_SHORT).show();
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					String result=arg0.result;
					if(result.equals("success")){
						   Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
					       startActivity(intent);
					}else {
						Toast.makeText(RegisterActivity.this,"账号已被注册，请重新注册", 0).show();
					}
						
				}
			});
	    }
	    
	   // registerEventHandler必须和unregisterEventHandler配套使用，否则可能造成内存泄漏。
	    @Override
	    protected void onDestroy() {
	        // TODO Auto-generated method stub
	        super.onDestroy();
	        SMSSDK.unregisterAllEventHandler();
	    }
	    
	}
