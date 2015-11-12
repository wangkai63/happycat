package com.happycat;



import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.MyBurseBean;
import com.happycat.util.MyApplication;
import com.happycay.fragments.XiaoxiFragment;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
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
import android.widget.TextView;
import android.widget.Toast;

public class WaiMai_zhifuActivity extends Activity {
    TextView name,numer,phone,type,money;
    Button fanhu,lianxi,quxiao,qianbao;
    String mname,dcphone,address, tcl,sunaddress,state, yuer,www;

    boolean flag;
    int inumber=0,mid=0,uid=0,wb=0;
	double price=0;
     String fs,zt,srt,iph,wk,goods;
     EditText pass;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wai_mai_zhifu);
		initview();
		initclick();
	}
	
	private void initview() {
		 WalletActivity walletActivity=new WalletActivity();
	  
		// TODO Auto-generated method stub
		fanhu=(Button) findViewById(R.id.fanhu);
		lianxi=(Button) findViewById(R.id.lianxi);
		quxiao=(Button) findViewById(R.id.quxiao);
		qianbao=(Button) findViewById(R.id.user_qianbao);
		//订单名称
		name=(TextView) findViewById(R.id.name);
		//订单编号
		numer=(TextView) findViewById(R.id.numer);
		//应付金额
		money=(TextView) findViewById(R.id.money);
		//支付方式
		type=(TextView) findViewById(R.id.type);
		//订单状态
	//	state=(TextView) findViewById(R.id.state);
		//订单电话
		phone=(TextView) findViewById(R.id.phone);
		uid=MyApplication.SP_user_id;
		//获取商家名称，将其设置为订单名称
		Intent intent =getIntent();
		mid=Integer.parseInt(intent.getStringExtra("mid"));
		inumber=Integer.parseInt(intent.getStringExtra("inumber"));
		price=Double.parseDouble(intent.getStringExtra("price"));
		mname=intent.getStringExtra("mname");
		name.setText("订单名称:"+mname);
		dcphone=intent.getStringExtra("phone");
		//获取购买商品的gson
		goods=intent.getStringExtra("goods");
		
		money.setText("应付金额:"+price+"元");
		
		//获取地址中的联系电话，如果订餐时联系电话为空，将此设为联系电话
		address=intent.getStringExtra("address");
		int lenght=address.length();
		
		 tcl=address.substring(lenght-11, lenght);
		
		 sunaddress=address.substring(0, lenght-16);
		 
	
		 Log.e("地址", address);
		 Log.e("sunadress", sunaddress);
		 Log.e("mid", ""+mid);
		 Log.e("uid", ""+uid);
		 Log.e("inumber", ""+inumber);

		 if(dcphone.isEmpty())
		{	phone.setText("联系电话:"+tcl);}
		else {
			phone.setText("联系电话:"+dcphone);
		}
		
		flag=intent.getExtras().getBoolean("flag");
		
		if(flag){
			state="待评价";
			type.setText("支付方式: 钱包支付");
			yuer=intent.getStringExtra("yuer");
			
			
		} 
		else{
			 state="待支付";
			 type.setText("支付方式: 货到付款");
				qianbao.setVisibility(View.GONE);
			
		}
		 zt=state;
		 fs=type.getText().toString().substring(6);
		 iph=phone.getText().toString().substring(5);
		Log.e("支付状态", zt);
		Log.e("支付方式", fs);
		//获取当前系统时间将其设置为订单编号
	   long time= System.currentTimeMillis();
		String numer1=""+time;
		 wk=numer1.substring(4, 12);
		numer.setText("订单编号:"+wk);
		 wb=Integer.parseInt(wk);
		//获取当前系统时间将上传到数据库
		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日HH:mm");       
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
		    srt    =    formatter.format(curDate); 
		Log.e("shijian", srt);
	}
	private void initclick() {
		fanhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(WaiMai_zhifuActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
		lianxi.setOnClickListener(new OnClickListener() {		
			
			@Override
			public void onClick(View v) {
				//上传订单
				HttpUtils httpUtils=new HttpUtils();
				String url="http://"+MyApplication.getIp()+":8080/happycat/Addindent?iid="+wb+"&uid="+uid+
						"&mid="+mid+"&inumber="+inumber+"&sumprice="+price+"&type="+fs+"&istatus="+zt+"&time="+srt+"&iphone="+iph+"&iprovide="+sunaddress;
						;
				httpUtils.send(HttpMethod.GET, url,
						new RequestCallBack<Object>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {	
							}

							@Override
							public void onSuccess(ResponseInfo<Object> arg0) {
								// TODO Auto-generated method stub
								Log.e("wkwkwk", "i love me");
							}
						});
				
				
			
				RequestParams params = new RequestParams();  
				params.addBodyParameter("goodsGson", goods);
				params.addBodyParameter("iid", wk);
				HttpUtils ee=new HttpUtils();
				String url1="http://"+MyApplication.getIp()+":8080/happycat/Addgoodsorder";
				ee.send(HttpMethod.POST, url1,params,
					new RequestCallBack<Object>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
					}

					@Override
					public void onSuccess(ResponseInfo<Object> arg0) {
						Toast.makeText(WaiMai_zhifuActivity.this, "订单已提交", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		qianbao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				showDialog(yuer);
				
			}

			
		});
		quxiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	public void showDialog(final String X) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.alterdialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("                   确认支付");
		builder.setMessage(money.getText()+"\n"+"您的余额:"+X+"元");
		
		builder.setView(view);
		 pass=(EditText) view.findViewById(R.id.qb_password);
		 www=pass.getText().toString();
		
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			        if(!pass.getText().toString().equals(MyApplication.SP_user_password)){
			        	Toast.makeText(WaiMai_zhifuActivity.this, "密码输错了", Toast.LENGTH_SHORT).show();
			        }
			        else{
			        	double qian=Double.parseDouble(X);
			        	final double shengyu=qian-price;
			        	if(shengyu<0){
			        		Toast.makeText(WaiMai_zhifuActivity.this, "余额不足，请充值", Toast.LENGTH_SHORT).show();
			        	}
			        	else {
			        	RequestParams params=new RequestParams();
			        	params.addBodyParameter("key", ""+3);
			        	params.addBodyParameter("Uid", ""+MyApplication.SP_user_id);
			        	params.addBodyParameter("Sum", ""+shengyu);
			        	String url2="http://"+MyApplication.getIp()+":8080/happycat/MG";
			        	HttpUtils wk=new HttpUtils();
			        	wk.send(HttpMethod.POST, url2, params,
			        			new RequestCallBack<Object>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										Toast.makeText(WaiMai_zhifuActivity.this, "支付失败，请检查网络", Toast.LENGTH_SHORT)
										.show();
									}

									@Override
									public void onSuccess(
											ResponseInfo<Object> arg0) {
										  yuer=""+shengyu;
									}
								});
				Toast.makeText(WaiMai_zhifuActivity.this, "支付成功,请提交订单", Toast.LENGTH_SHORT)
						.show();}

			}
			        }
		
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(WaiMai_zhifuActivity.this, "取消支付", Toast.LENGTH_SHORT)
						.show();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
}
