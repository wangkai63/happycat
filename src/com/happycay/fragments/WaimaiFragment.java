package com.happycay.fragments;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;
import com.example.happucat.R.layout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.MainActivity;
import com.happycat.MerchatDataActivity;
import com.happycat.SyJsActivity;
import com.happycat.WaiMAIMainActivity;
import com.happycat.Bean.Goods;
import com.happycat.Bean.MerchatBean;
import com.happycat.adapter.Merchatadapter;
import com.happycat.adapter.Myadapter;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.raw;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.StaticLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class WaimaiFragment<GroupButton> extends Fragment {

	ListView listView;
	ImageView imageView;
	// 数据来源
	List<MerchatBean> list = new ArrayList<MerchatBean>();
	Merchatadapter adapter;
	HttpUtils httpUtils;
	private String url;
	TextView textView;
	RadioGroup group;
	ImageButton iButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.waimai, null);
		/*iButton = (ImageButton) listView.findViewById(R.id.photo);

		iButton.setVisibility(view.GONE);*/
		listView = (ListView) view.findViewById(R.id.waimai_main_listview);
		// 监听单击listview事件
		// 监听每个item的事件。
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int mid = list.get(position).getMid();
				String mname = list.get(position).getMname();
				String mpsf = "" + list.get(position).getTip();
				String mpjsu = list.get(position).getLongtime();
				double mqsj = list.get(position).getMprice();
				String mtime = list.get(position).getMtime();
				String mimg = list.get(position).getMimg();
				Intent intent = new Intent(getActivity(),
						MerchatDataActivity.class);
				intent.putExtra("mid", mid);
				intent.putExtra("name", mname);
				intent.putExtra("psf", mpsf);
				intent.putExtra("pjsu", mpjsu);
				intent.putExtra("mtime", mtime);
				intent.putExtra("qsj", mqsj);
				intent.putExtra("img", mimg);
				// Intent intent=new Intent(getActivity(),
				// MerchatDataActivity.class);
				// intent.putExtra("bean", list.get(position));
				startActivity(intent);

			}
		});
		imageView = (ImageView) view.findViewById(R.id.tongzhi);

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), SyJsActivity.class);

				startActivity(intent);
			}
		});

		// 监听单击Groupbutton
		View g1 = view.findViewById(R.id.ppkc);
		g1 = view.findViewById(R.id.ppkc);
		g1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String url = "http://" + MyApplication.getIp()
						+ ":8080/happycat/SelectMerchat?oid=3";
				Intent intent = new Intent(getActivity(),
						WaiMAIMainActivity.class);
				intent.putExtra("weburl", url);
				startActivity(intent);
			}
		});
		View g2 = view.findViewById(R.id.zc);
		g2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://" + MyApplication.getIp()
						+ ":8080/happycat/SelectMerchat?oid=2";
				Intent intent = new Intent(getActivity(),
						WaiMAIMainActivity.class);
				intent.putExtra("weburl", url);
				startActivity(intent);
			}
		});
		View g3 = view.findViewById(R.id.kcjc);

		g3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://" + MyApplication.getIp()
						+ ":8080/happycat/SelectMerchat?oid=4";
				Intent intent = new Intent(getActivity(),
						WaiMAIMainActivity.class);
				intent.putExtra("weburl", url);
				startActivity(intent);
			}
		});
		View g4 = view.findViewById(R.id.tdyp);

		g4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://" + MyApplication.getIp()
						+ ":8080/happycat/SelectMerchat?oid=6";
				Intent intent = new Intent(getActivity(),
						WaiMAIMainActivity.class);
				intent.putExtra("weburl", url);
				startActivity(intent);
			}
		});
		View g5 = view.findViewById(R.id.xgg);

		g5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://" + MyApplication.getIp()
						+ ":8080/happycat/SelectMerchat?oid=1";
				Intent intent = new Intent(getActivity(),
						WaiMAIMainActivity.class);
				intent.putExtra("weburl", url);
				startActivity(intent);
			}
		});
		View g6 = view.findViewById(R.id.czxc);

		g6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://" + MyApplication.getIp()
						+ ":8080/happycat/SelectMerchat?oid=5";
				Intent intent = new Intent(getActivity(),
						WaiMAIMainActivity.class);
				intent.putExtra("weburl", url);
				startActivity(intent);
			}
		});

		initDatas();
		if (adapter == null) {
			Log.e("adapater", "adapter is null");
			textView = (TextView) view.findViewById(R.id.emptyview);
			textView.setText("访问网址不存在!!!");
			listView.setEmptyView(textView);
		}
		return view;
	}

	private void initDatas() {
		adapter = new Merchatadapter(list, getActivity());
		listView.setAdapter(adapter);
		// 向服务器发送请求，查询数据
		url = "http://" + MyApplication.getIp()
				+ ":8080/happycat/SelectMerchat?oid=9";
		httpUtils = new HttpUtils();

		httpUtils.send(HttpMethod.GET, url,

		new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				// 使用GSon框架进行json解析
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<MerchatBean>>() {
				}.getType();
				List<MerchatBean> resultlist = gson.fromJson(result, typeOfT);
				list.addAll(resultlist);
				adapter.setList(list);
				adapter.notifyDataSetChanged();
			}
		});

	}

}
