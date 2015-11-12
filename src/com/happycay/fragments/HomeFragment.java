package com.happycay.fragments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.DayActivity;
import com.happycat.MainActivity;
import com.happycat.MerchatDataActivity;
import com.happycat.NightActivity;
import com.happycat.SyJsActivity;
import com.happycat.Bean.MerchatBean;
import com.happycat.Bean.TuiJianbean;
import com.happycat.util.DanjiUtils;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements OnClickListener,
		AMapLocationListener {
	View homeView;
	Spinner spinner;
	LocationManagerProxy mLocationManagerProxy;
	ImageView imageView, imageView1, imageView2, imageView3, down;
	TextView textView1, textView2, textView3, textView4, day, night, dingwei;
	Button button;
	RadioButton wmrButton, syrButton;
	List<TuiJianbean> mAdverbeans;
	HttpUtils httpUtils;
	private String url;
	MainActivity activity;
	String[] dingweiStrings = new String[] { "苏州市", "南阳市", "濮阳市", "安阳市" };
	static int id1 = 0;
	static int id2 = 0;
	static int id3 = 0;
	int mid;
	String mname, mpsf, mpjsu, mphone, ming;
	double mqsj;
	List<MerchatBean> list1, list2, list3;

	GestureDetector mDetector;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub

		super.onAttach(activity);
		this.activity = (MainActivity) activity;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// mDetector = new GestureDetector(getActivity(), DanjiUtils(this));

		// 初始化定位对象
		mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());
		// 注册定位
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork,// 定位类型
				-1, // 定位周期,2秒调用一次 .-1只定位一次
				15, // 移动多大距离回调， 无效的
				this);// 毁掉监听

		initData();
	}

	
	private OnGestureListener DanjiUtils(HomeFragment homeFragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeView = inflater.inflate(R.layout.home, null);
		initView();

		return homeView;
	}

	private void initView() {

		imageView1 = (ImageView) homeView.findViewById(R.id.img1);
		imageView1.setOnClickListener(this);
		imageView2 = (ImageView) homeView.findViewById(R.id.img2);
		imageView2.setOnClickListener(this);
		imageView3 = (ImageView) homeView.findViewById(R.id.img3);
		imageView3.setOnClickListener(this);
		down = (ImageView) homeView.findViewById(R.id.down);
		down.setOnClickListener(this);
		textView1 = (TextView) homeView.findViewById(R.id.ite1);
		textView1.setOnClickListener(this);
		textView2 = (TextView) homeView.findViewById(R.id.ite2);
		textView2.setOnClickListener(this);
		textView3 = (TextView) homeView.findViewById(R.id.ite3);
		textView3.setOnClickListener(this);
		dingwei = (TextView) homeView.findViewById(R.id.dingwei);
		dingwei.setOnClickListener(this);
		list1 = new ArrayList<MerchatBean>();
		list2 = new ArrayList<MerchatBean>();
		list3 = new ArrayList<MerchatBean>();

		day = (TextView) homeView.findViewById(R.id.day);
		day.setOnClickListener(this);
		night = (TextView) homeView.findViewById(R.id.neight);
		night.setOnClickListener(this);
		textView4 = (TextView) homeView.findViewById(R.id.mao);
		wmrButton = (RadioButton) homeView.findViewById(R.id.waimai);
		syrButton = (RadioButton) homeView.findViewById(R.id.shouye);
		textView4.setOnClickListener(this);
		imageView = (ImageView) homeView.findViewById(R.id.edit);
		imageView.setOnClickListener(this);

	}

	private void initData() {
		
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		// 客户端向服务器端发送请求
		// MyApplication.getIp()
		url = "http://" + MyApplication.getIp()
				+ ":8080/happycat/GetUpload?key=1";
		httpUtils.send(HttpMethod.GET, // 请求方式
				url, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						System.out.println(result);
						Type listType = new TypeToken<LinkedList<TuiJianbean>>() {
						}.getType();
						Gson gson = new Gson();
						mAdverbeans = gson.fromJson(result, listType);
						for (int i = 0; i < mAdverbeans.size(); i++) {

						}

						String path = "http://" + MyApplication.getIp()
								+ ":8080/happycat/img/";
						MyApplication.bitmapUtils.display(imageView1, path
								+ mAdverbeans.get(0).getImg());
						MyApplication.bitmapUtils.display(imageView2, path
								+ mAdverbeans.get(1).getImg());
						MyApplication.bitmapUtils.display(imageView3, path
								+ mAdverbeans.get(2).getImg());

						textView1.setText(mAdverbeans.get(0).getGname());
						textView2.setText(mAdverbeans.get(1).getGname());
						textView3.setText(mAdverbeans.get(2).getGname());

						// 获取商家的mid
						id1 = mAdverbeans.get(0).getMid();
						id2 = mAdverbeans.get(1).getMid();
						id3 = mAdverbeans.get(2).getMid();

						// 获取商家的具体信息。
						list1 = initmerchat1();
						list2 = initmerchat2();
						list3 = initmerchat3();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Log.e("home推荐", "修改吧");
					}
				});
	}

	public List<MerchatBean> initmerchat1() {
		HttpUtils wk = new HttpUtils();
		String url = "http://" + MyApplication.getIp()
				+ ":8080/happycat/SelectMerchat?oid=12&mid=" + id1;

		wk.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				// 使用GSon框架进行json解析
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<MerchatBean>>() {
				}.getType();
				List<MerchatBean> resultlist = gson.fromJson(result, typeOfT);
				list1.addAll(resultlist);

			}
		});
		return list1;
	}

	public List<MerchatBean> initmerchat2() {
		HttpUtils wk = new HttpUtils();
		String url2 = "http://" + MyApplication.getIp()
				+ ":8080/happycat/SelectMerchat?oid=12&mid=" + id2;

		wk.send(HttpMethod.GET, url2, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				// 使用GSon框架进行json解析
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<MerchatBean>>() {
				}.getType();
				List<MerchatBean> resultlist = gson.fromJson(result, typeOfT);
				list2.addAll(resultlist);

			}
		});
		return list2;
	}

	public List<MerchatBean> initmerchat3() {
		HttpUtils wk = new HttpUtils();
		String url3 = "http://" + MyApplication.getIp()
				+ ":8080/happycat/SelectMerchat?oid=12&mid=" + id3;

		wk.send(HttpMethod.GET, url3, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				// 使用GSon框架进行json解析
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<MerchatBean>>() {
				}.getType();
				List<MerchatBean> resultlist = gson.fromJson(result, typeOfT);
				list3.addAll(resultlist);
				// Log.e("list3", list3.toString());

			}
		});
		return list3;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.img1:
			int mid = list1.get(0).getMid();
			String mname = list1.get(0).getMname();

			String mpsf = "" + list1.get(0).getTip();
			String mpjsu = list1.get(0).getLongtime();
			double mqsj = list1.get(0).getMprice();
			String mphone = list1.get(0).getMphone();
			String mimg = list1.get(0).getMimg();
			String mtime = list1.get(0).getMtime();
			Intent intent1 = new Intent(getActivity(),
					MerchatDataActivity.class);
			intent1.putExtra("mid", mid);
			intent1.putExtra("name", mname);
			intent1.putExtra("psf", mpsf);
			intent1.putExtra("pjsu", mpjsu);
			intent1.putExtra("phone", mphone);
			intent1.putExtra("qsj", mqsj);
			intent1.putExtra("img", mimg);
			intent1.putExtra("mtime", mtime);
			startActivity(intent1);
			break;
		case R.id.img2:
			mid = list2.get(0).getMid();
			Log.e("---11-------", "" + mid);
			mname = list2.get(0).getMname();
			Log.e("id++++", "" + mname);
			mpsf = "" + list2.get(0).getTip();
			mpjsu = list2.get(0).getLongtime();
			mqsj = list2.get(0).getMprice();
			mphone = list2.get(0).getMphone();
			mimg = list2.get(0).getMimg();
			mtime = list2.get(0).getMtime();
			Intent intent2 = new Intent(getActivity(),
					MerchatDataActivity.class);
			intent2.putExtra("mid", mid);
			intent2.putExtra("name", mname);
			intent2.putExtra("psf", mpsf);
			intent2.putExtra("pjsu", mpjsu);
			intent2.putExtra("phone", mphone);
			intent2.putExtra("qsj", mqsj);
			intent2.putExtra("img", mimg);
			intent2.putExtra("mtime", mtime);
			startActivity(intent2);
			break;
		case R.id.img3:
			mid = list3.get(0).getMid();
			Log.e("---22-------", "" + mid);
			mname = list3.get(0).getMname();
			Log.e("id++++", "" + mname);
			mpsf = "" + list3.get(0).getTip();
			mpjsu = list3.get(0).getLongtime();
			mqsj = list3.get(0).getMprice();
			mphone = list3.get(0).getMphone();
			mimg = list3.get(0).getMimg();
			mtime = list3.get(0).getMtime();
			Intent intent3 = new Intent(getActivity(),
					MerchatDataActivity.class);
			intent3.putExtra("mid", mid);
			intent3.putExtra("name", mname);
			intent3.putExtra("psf", mpsf);
			intent3.putExtra("pjsu", mpjsu);
			intent3.putExtra("phone", mphone);
			intent3.putExtra("qsj", mqsj);
			intent3.putExtra("img", mimg);
			intent3.putExtra("mtime", mtime);
			startActivity(intent3);
			break;
		case R.id.ite1:
			mid = list1.get(0).getMid();
			mname = list1.get(0).getMname();

			mpsf = "" + list1.get(0).getTip();
			mpjsu = list1.get(0).getLongtime();
			mqsj = list1.get(0).getMprice();
			mphone = list1.get(0).getMphone();
			mimg = list1.get(0).getMimg();
			mtime = list1.get(0).getMtime();
			intent1 = new Intent(getActivity(), MerchatDataActivity.class);
			intent1.putExtra("mid", mid);
			intent1.putExtra("name", mname);
			intent1.putExtra("psf", mpsf);
			intent1.putExtra("pjsu", mpjsu);
			intent1.putExtra("phone", mphone);
			intent1.putExtra("qsj", mqsj);
			intent1.putExtra("img", mimg);
			intent1.putExtra("mtime", mtime);
			startActivity(intent1);
			break;
		case R.id.ite2:
			mid = list2.get(0).getMid();
			Log.e("---11-------", "" + mid);
			mname = list2.get(0).getMname();
			Log.e("id++++", "" + mname);
			mpsf = "" + list2.get(0).getTip();
			mpjsu = list2.get(0).getLongtime();
			mqsj = list2.get(0).getMprice();
			mphone = list2.get(0).getMphone();
			mimg = list2.get(0).getMimg();
			mtime = list2.get(0).getMtime();
			intent2 = new Intent(getActivity(),
					MerchatDataActivity.class);
			intent2.putExtra("mid", mid);
			intent2.putExtra("name", mname);
			intent2.putExtra("psf", mpsf);
			intent2.putExtra("pjsu", mpjsu);
			intent2.putExtra("phone", mphone);
			intent2.putExtra("qsj", mqsj);
			intent2.putExtra("img", mimg);
			intent2.putExtra("mtime", mtime);
			startActivity(intent2);
			break;
		case R.id.ite3:
			mid = list3.get(0).getMid();
			Log.e("---22-------", "" + mid);
			mname = list3.get(0).getMname();
			Log.e("id++++", "" + mname);
			mpsf = "" + list3.get(0).getTip();
			mpjsu = list3.get(0).getLongtime();
			mqsj = list3.get(0).getMprice();
			mphone = list3.get(0).getMphone();
			mimg = list3.get(0).getMimg();
			mtime = list3.get(0).getMtime();
			intent3 = new Intent(getActivity(),
					MerchatDataActivity.class);
			intent3.putExtra("mid", mid);
			intent3.putExtra("name", mname);
			intent3.putExtra("psf", mpsf);
			intent3.putExtra("pjsu", mpjsu);
			intent3.putExtra("phone", mphone);
			intent3.putExtra("qsj", mqsj);
			intent3.putExtra("img", mimg);
			intent3.putExtra("mtime", mtime);
			startActivity(intent3);
			break;
		case R.id.day:
			intent = new Intent(getActivity(), DayActivity.class);
			startActivity(intent);
			break;
		case R.id.neight:
			intent = new Intent(getActivity(), NightActivity.class);
			startActivity(intent);
			break;
		case R.id.mao:
			activity.cat();
			break;
		case R.id.edit:
			intent = new Intent(getActivity(), SyJsActivity.class);
			startActivity(intent);
			break;
		case R.id.dingwei:
			new AlertDialog.Builder(getActivity())
					.setTitle("城市切换")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setSingleChoiceItems(dingweiStrings, 0,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									dingwei.setText(dingweiStrings[which]);
								}
							}).setNegativeButton("取消", null).show();
			break;
		case R.id.down:
			new AlertDialog.Builder(getActivity())
					.setTitle("城市切换")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setSingleChoiceItems(dingweiStrings, 0,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									dingwei.setText(dingweiStrings[which]);
								}
							}).setNegativeButton("取消", null).show();
			break;
		default:
			break;
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 定位退出时，需要 onDestroy
		mLocationManagerProxy.destroy();
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationChanged(AMapLocation arg0) {
		// 定位回调
		// 处理定位结果
		if (arg0 != null && arg0.getAMapException().getErrorCode() == 0) {
			dingwei.setText(arg0.getCity());
			Toast.makeText(getActivity(),
					"当前定位到  " + arg0.getCity() + " " + arg0.getDistrict(), 1)
					.show();
		}

	}

}
