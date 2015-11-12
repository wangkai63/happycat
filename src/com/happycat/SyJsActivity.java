package com.happycat;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.SouSuoBean;
import com.happycat.util.MyApplication;
import com.happycat.util.RefreshListView;
import com.happycat.util.RefreshListView.OnRefreshListener;
import com.happycat.util.SearchClearEditText;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import android.os.Bundle;
import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SyJsActivity extends Activity {
	RefreshListView listView;

	MyAdapter adapter;
	List<SouSuoBean> list = new ArrayList<SouSuoBean>();
	HttpUtils httpUtils;
	Intent intent;
	View footer;// 底部布局
	int pageNow = 1;
	SearchClearEditText editText;
	List<SouSuoBean> beans;
	private String url = "http://" + MyApplication.getIp()
			+ ":8080/happycat/GetUpload";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sy_js);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		initView();
		initData();
	}

	public void searchfh(View view) {
		finish();
	}

	public void sousuo(View view) {

		String text = editText.getText().toString().trim();

		try {
			String result = URLEncoder.encode(text, "utf-8");
			httpUtils = new HttpUtils();
			RequestParams params = new RequestParams();
			params.addBodyParameter("key", 6 + "");
			params.addBodyParameter("pageNow", 1 + "");
			params.addBodyParameter("goodsname", result);
			httpUtils.send(HttpMethod.POST, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String result = responseInfo.result;
							Gson gson = new Gson();
							Type type = new TypeToken<List<SouSuoBean>>() {
							}.getType();
							List<SouSuoBean> getList = gson.fromJson(result,
									type);
							list.clear();
							list.addAll(getList);
							listView.onRefreshComplete(true);
							adapter.notifyDataSetChanged();
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// TODO Auto-generated method stub
							listView.onRefreshComplete(false);
						}
					});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		listView = (RefreshListView) findViewById(R.id.sou);

		editText = (SearchClearEditText) findViewById(R.id.etext);
		adapter = new MyAdapter(SyJsActivity.this, list);
		// 搜索成功界面，显示全部

		// 初始界面，分页显示
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pageNow = 1;
				initData();

				;
			}

			@Override
			public void onLoadMore() {
				String text = editText.getText().toString().trim();

				try {
					int ys = adapter.getCount() / 5 + 1;
					String result = URLEncoder.encode(text, "utf-8");
					httpUtils = new HttpUtils();
					RequestParams params = new RequestParams();
					params.addBodyParameter("key", 6 + "");
					params.addBodyParameter("pageNow", ys + "");
					params.addBodyParameter("goodsname", result);
					httpUtils.send(HttpMethod.POST, url, params,
							new RequestCallBack<String>() {

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {
									String result = responseInfo.result;
									Gson gson = new Gson();
									Type type = new TypeToken<List<SouSuoBean>>() {
									}.getType();
									List<SouSuoBean> getList = gson.fromJson(
											result, type);
									list.clear();
									list.addAll(getList);
									listView.onRefreshComplete(true);
									adapter.notifyDataSetChanged();
								}

								@Override
								public void onFailure(HttpException error,
										String msg) {
									// TODO Auto-generated method stub
									listView.onRefreshComplete(false);
								}
							});
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int mid = list.get(position).getMid();
				String mname = list.get(position).getMname();
				String mpsf = "" + list.get(position).getTip();
				String mpjsu = list.get(position).getLongtime();
				String mqsj = list.get(position).getMprice();
				String mtime = list.get(position).getMtime();
				String mimg = list.get(position).getMimg();

				intent = new Intent(SyJsActivity.this,
						MerchatDataActivity.class);
				intent.putExtra("mid", mid);
				intent.putExtra("name", mname);
				intent.putExtra("psf", mpsf);
				intent.putExtra("pjsu", mpjsu);
				intent.putExtra("mtime", mtime);
				intent.putExtra("qsj", mqsj);
				intent.putExtra("img", mimg);

				intent.putExtra("gid", list.get(position).getGid() + "");
				startActivity(intent);

			}
		});

	}

	// 初始化页面数据
	private void initData() {

		String text = editText.getText().toString().trim();

		try {
			String result = URLEncoder.encode(text, "utf-8");
			httpUtils = new HttpUtils();
			RequestParams params = new RequestParams();
			params.addBodyParameter("key", 6 + "");
			params.addBodyParameter("pageNow", 1 + "");
			params.addBodyParameter("goodsname", result);
			httpUtils.send(HttpMethod.POST, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String result = responseInfo.result;
							Gson gson = new Gson();
							Type type = new TypeToken<List<SouSuoBean>>() {
							}.getType();
							List<SouSuoBean> getList = gson.fromJson(result,
									type);
							list.clear();
							list.addAll(getList);
							listView.onRefreshComplete(true);
							adapter.notifyDataSetChanged();
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// TODO Auto-generated method stub
							listView.onRefreshComplete(false);
						}
					});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 左侧详情的Adapter
	class MyAdapter extends BaseAdapter {
		Context context;
		List<SouSuoBean> lista;
		LayoutInflater minflater;
		TextView textView;
		RadioButton radioButton;
		ViewHolder mHolder;

		public MyAdapter(Context context, List<SouSuoBean> lista) {
			super();
			this.context = context;
			this.lista = list;
			minflater = LayoutInflater.from(context);
		}

		public void addAll(List<SouSuoBean> picture) {

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lista.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return lista.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		class ViewHolder {
			TextView textView1, textView2, textView3, textView4, textView5;
			ImageView imageView;
			// RadioButton rButton;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				// 第一次进入应用，初始化listview每一行的布局
				convertView = minflater.inflate(R.layout.sxitem, null);
				// convertView=View.inflate(context, R.layout.sxitem, null);
				mHolder = new ViewHolder();
				mHolder.textView1 = (TextView) convertView
						.findViewById(R.id.ssmname);
				mHolder.textView2 = (TextView) convertView
						.findViewById(R.id.sslongtime);
				mHolder.textView3 = (TextView) convertView
						.findViewById(R.id.ssgname);
				mHolder.textView4 = (TextView) convertView
						.findViewById(R.id.ssgtext);
				mHolder.textView5 = (TextView) convertView
						.findViewById(R.id.ssmaddress);
				mHolder.imageView = (ImageView) convertView
						.findViewById(R.id.ssgimg);

				convertView.setTag(mHolder);
			} else {
				mHolder = (ViewHolder) convertView.getTag();
			}

			mHolder.textView1.setText(list.get(position).getMname());
			mHolder.textView2.setText("配送时长："
					+ list.get(position).getLongtime());
			mHolder.textView3.setText(lista.get(position).getGname());
			mHolder.textView4.setText("商品描述：" + lista.get(position).getGtext());
			mHolder.textView5.setText("商家地址："
					+ list.get(position).getMaddress());

			String path = "http://" + MyApplication.getIp()
					+ ":8080/happycat/img/";

			MyApplication.bitmapUtils.display(mHolder.imageView, path
					+ lista.get(position).getGimg());
			return convertView;
		}

	}

}
