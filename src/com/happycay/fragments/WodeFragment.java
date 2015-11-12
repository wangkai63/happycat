package com.happycay.fragments;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.AddAddressActivity;
import com.happycat.AddressActivity;
import com.happycat.LoginActivity;
import com.happycat.MerchatDataActivity;
import com.happycat.MyCollectionActivity;
import com.happycat.MyinstallActivity;
import com.happycat.OrderActivity;
import com.happycat.QieHuanLoginActivity;
import com.happycat.ShareActivity;
import com.happycat.UserActivity;
import com.happycat.WalletActivity;
import com.happycat.Bean.MyShouYeBean;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WodeFragment extends Fragment implements OnClickListener {
	HttpUtils httpUtils;
	String flag = "";
	List<MyShouYeBean> mlist;
	TextView ncTextView, sexTextView, zhTextView;
	ImageView Button;
	WodeFragment fragment;
	private String url;
	private View myLayout;
	private ImageButton loginButton;
	// private ImageButton rtnButton;
	private RelativeLayout rl_shoucang;
	private RelativeLayout rl_qianbao;
	private RelativeLayout rl_dingdan;
	private RelativeLayout rl_shezhi;
	private RelativeLayout rl_ziliao;
	private RelativeLayout rl_fenxiang;
	private RelativeLayout rl_login;
	private RelativeLayout rl_dizhi;
	String uid=MyApplication.SP_user_id+"";

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		myLayout = inflater.inflate(R.layout.wode, container, false);
		initView();
		
		/*Bundle b = getArguments();
		flag = b.getString("flag");
		Log.e("我的flag", flag);*/
		if (!"0".equals(uid)) {
			initdata();
			
		}

		return myLayout;
	}

	private void initdata() {

		/*Bundle b = getArguments();
		uid = b.getString("uid");*/

		if (uid != null) {
			// 开始使用XUtils框架从服务器上下载大类表数据
			httpUtils = new HttpUtils();
			RequestParams params = new RequestParams();
			params.addBodyParameter("key", "6");
			params.addBodyParameter("uid", uid);
			Log.e("我的uid", uid);
			// 客户端向服务器端发送请求
			// MyApplication.getIp()
			url = "http://" + MyApplication.getIp()
					+ ":8080/happycat/myServlet";

			httpUtils.send(HttpMethod.POST, // 请求方式
					url, params, new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {

							// 开始使用XUtils框架从服务器上下载大类表数据
							String result = responseInfo.result;
							System.out.println(result);

							Type listType = new TypeToken<LinkedList<MyShouYeBean>>() {
							}.getType();
							Gson gson = new Gson();
							mlist = gson.fromJson(result, listType);
							for (int i = 0; i < mlist.size(); i++) {

							}
							String path = "http://" + MyApplication.getIp()
									+ ":8080/happycat/img/";
							MyApplication.bitmapUtils.display(loginButton, path
									+ mlist.get(0).getUimg());
							//sexTextView.setText(mlist.get(0).getSex());
							if ("男".equals(mlist.get(0).getSex())) {
								Button.setImageResource(R.drawable.icon_pop_boy);
								
							} else {
								Button.setImageResource(R.drawable.icon_pop_girl);
							}
							zhTextView
									.setText("账号：" + mlist.get(0).getUphone());
							ncTextView.setText("昵称：" + mlist.get(0).getUname());

						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// 访问网络失败调用方法
							// TODO Auto-generated method stub
							Log.e("我的", "修改吧");
						}

					});// 服务器回调函数

		}

	}

	private void initView() {

		ncTextView = (TextView) myLayout.findViewById(R.id.dlzc);
		Button=(ImageView) myLayout.findViewById(R.id.dlsex);
		zhTextView = (TextView) myLayout.findViewById(R.id.dlzh);

		loginButton = (ImageButton) myLayout.findViewById(R.id.tixing);
		rl_login = (RelativeLayout) myLayout
				.findViewById(R.id.rl_login_register);
		rl_shoucang = (RelativeLayout) myLayout.findViewById(R.id.wode_sc);
		rl_qianbao = (RelativeLayout) myLayout.findViewById(R.id.wode_qb);
		rl_dingdan = (RelativeLayout) myLayout.findViewById(R.id.wode_dd);
		rl_shezhi = (RelativeLayout) myLayout.findViewById(R.id.wode_sz);
		rl_ziliao = (RelativeLayout) myLayout.findViewById(R.id.wode_zz);
		rl_fenxiang = (RelativeLayout) myLayout.findViewById(R.id.wode_fx);
		rl_dizhi = (RelativeLayout) myLayout.findViewById(R.id.wode_dz);
		rl_login.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		rl_shoucang.setOnClickListener(this);
		rl_qianbao.setOnClickListener(this);
		rl_dingdan.setOnClickListener(this);
		rl_shezhi.setOnClickListener(this);
		rl_ziliao.setOnClickListener(this);
		rl_fenxiang.setOnClickListener(this);
		rl_dizhi.setOnClickListener(this);

	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tixing:
			/*if ("1".equals(flag)) {
				startActivity(new Intent(getActivity(), UserActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}*/

			// Log.i("111", "onclick");
			// Intent intent=new Intent(getActivity(),LoginActivity.class);
			
			break;
		case R.id.rl_login_register:
			if (!"0".equals(MyApplication.SP_user_id)) {
				startActivity(new Intent(getActivity(), QieHuanLoginActivity.class));
			}else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
				
			}
			break;
		case R.id.wode_zz:
			startActivity(new Intent(getActivity(), UserActivity.class));
			break;

		case R.id.wode_sc:
			Intent intent1 = new Intent(getActivity(), MyCollectionActivity.class);
			//intent1.putExtra("uid", uid);
			startActivity(intent1);
			break;

		case R.id.wode_dd:
			Intent intent = new Intent(getActivity(), OrderActivity.class);
			//intent.putExtra("uid", uid);
			startActivity(intent);
			break;

		case R.id.wode_qb:
			// Log.i("111", "onclick");

			// startActivity(new Intent(getActivity(), WalletActivity.class));
			Intent intent2 = new Intent(getActivity(), WalletActivity.class);
			//intent2.putExtra("uid", uid);
			startActivity(intent2);
			break;

		case R.id.wode_sz:

			startActivity(new Intent(getActivity(), MyinstallActivity.class));
			break;

		case R.id.wode_fx:

			startActivity(new Intent(getActivity(), ShareActivity.class));
			break;
		case R.id.wode_dz:

			startActivity(new Intent(getActivity(), AddressActivity.class));
			break;
		default:

			break;

		}

	}
}
