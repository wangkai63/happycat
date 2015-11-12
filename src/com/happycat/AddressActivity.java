package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.address;
import com.happycat.adapter.addressAdapter;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
public class AddressActivity extends Activity {
ListView listView;
List<address>list=new ArrayList<address>();
addressAdapter adapter;
ImageButton iButton;
Button	add;
private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.address);
		initview();
		initdata();
	}
	
	private void initview() {
		listView=(ListView) findViewById(R.id.address_listview);
		
		adapter=new addressAdapter(list, this);
		listView.setAdapter(adapter);
		iButton=(ImageButton) findViewById(R.id.photo1);
		iButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent1 = new Intent(AddressActivity.this, MainActivity.class);

				// MyApplication.myflag = "1";
				// Log.e("资料===flag", MyApplication.myflag);
				startActivity(intent1);
				finish();
			}
		});
        add=(Button) findViewById(R.id.add_address);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent=new Intent(AddressActivity.this,AddAddressActivity.class);
				startActivityForResult(intent,0);
			
				
			}
		});
			
		}
		
	

	private void initdata() {
		url="http://" + MyApplication.getIp()
			+ ":8080/happycat/Selectaddress?uid="+MyApplication.SP_user_id;
	HttpUtils httpUtils=new HttpUtils();
	httpUtils.configCurrentHttpCacheExpiry(1000);
	httpUtils.send(HttpMethod.GET, url, 
			new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					String result=arg0.result;
					Gson gson = new Gson();
					Type typeOfT = new TypeToken<List<address>>() {
					}.getType();
					List<address> resultlist = gson.fromJson(result, typeOfT);
					list.addAll(resultlist);
					Log.e("hua", list.toString());
					adapter.setList(list);
					adapter.notifyDataSetChanged();
				}
			});
	
    }
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
	       list.clear();
		initdata();
		}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MyApplication.myflag = "1";
	}
}
