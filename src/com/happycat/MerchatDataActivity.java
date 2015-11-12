package com.happycat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.CountDto;
import com.happycat.Bean.Goods;
import com.happycat.Bean.MerchatBean;
import com.happycat.Bean.PingjiaBean;
import com.happycat.Bean.goodsclassify;
import com.happycat.adapter.Dc_fenleiAdapter;
import com.happycat.adapter.DingDan_indentAdapter;
import com.happycat.adapter.PingjiaAdapter;
import com.happycat.adapter.goodsAdapter;
import com.happycat.global.GlobalContacts;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class MerchatDataActivity extends Activity {
	ImageButton iButton;
	LinearLayout layout;
	Intent intent;
	ImageButton imaggood;
	int mid = 1;
	String mname;
	double mqsf;
	View oldView = null;
	String imagurl = "http://" + MyApplication.getIp()
			+ ":8080/happycat/upimage/";
	private ViewPager merchat_viewpager;
	private LayoutInflater mInflater;
	private ArrayList<View> views;
	private int count, collection;// 区分是收藏还是取消
	RadioGroup radioGroup;
	// viewpager标题
	List<String> title = new ArrayList<String>();

	TextView textView1, textView2, textView3, textView4, textView5, shop_cat;
	ImageView imageView;

	// 声明适配器 商品分类
	ListView dc_fenlielistview, dc_goodslistview, pjlistview;
	List<goodsclassify> flList = new ArrayList<goodsclassify>();
	Dc_fenleiAdapter adapter;
	HttpUtils httpUtils;
	String url,url1,gid;

	private List<String> wwk;
	// 声明适配器 具体商品
	List<Goods> gList = new ArrayList<Goods>();
	goodsAdapter gAdapter;
	// 声明适配器 评价
	List<PingjiaBean> pList = new ArrayList<PingjiaBean>();
	PingjiaAdapter pAdapter;
	// 购物车信息
	TextView buycat_jine, buycat_chae;
	Button buycat_tijiao;
	private ArrayList<Goods> sunGoods;
	protected ArrayList<Integer> collectionList;

	// 设置get、
	public TextView getBuycat_jine() {
		return buycat_jine;
	}

	public TextView getBuycat_chae() {
		return buycat_chae;
	}

	public double getMqsf() {
		return mqsf;
	}

	public Button getBuycat_tijiao() {
		return buycat_tijiao;
	}

	public List<Goods> getgList() {
		return gList;
	}

	public goodsAdapter getgAdapter() {
		return gAdapter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_merchat_data);
		mInflater = LayoutInflater.from(this);
		initgetIntent();
		initData();
		initView();
		iButton = (ImageButton) findViewById(R.id.photo);
		iButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

	private void initgetIntent() {
		// 接受上个activity传来的信息
		Intent intent = getIntent();
		// intent.getParcelableArrayListExtra("data");
		mid = intent.getExtras().getInt("mid");
		mname = intent.getStringExtra("name");
		String mpsf = intent.getStringExtra("psf");
		String mpjsu = intent.getStringExtra("pjsu");
		mqsf = intent.getExtras().getDouble("qsj");
		String mimg = intent.getStringExtra("img");
		String mtime = intent.getStringExtra("mtime");
		imageView = (ImageView) findViewById(R.id.logo);
		textView1 = (TextView) findViewById(R.id.phone);
		textView2 = (TextView) findViewById(R.id.Djbt);
		textView3 = (TextView) findViewById(R.id.pjsu);
		textView4 = (TextView) findViewById(R.id.qsf);
		textView5 = (TextView) findViewById(R.id.psf);
		textView1.setText("平均速度： " + mpjsu);
		textView2.setText(mname);
		textView3.setText("营业时间： " + mtime);
		textView5.setText("起送价： " + mqsf + "元");
		textView4.setText("配送费： " + mpsf + "元");

		MyApplication.bitmapUtils.display(imageView, imagurl + mimg);

	}

	private void initData() {
		// TODO Auto-generated method stub
		sunGoods = new ArrayList<Goods>();
		views = new ArrayList<View>();
		title.add("订餐");
		title.add("评价");

		merchat_viewpager = (ViewPager) findViewById(R.id.merchat_viewpager);
		// 监听viewpager状态改变事件

		merchat_viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				// 订餐
				case 0:
					radioGroup.check(R.id.dingcan1);
					break;
				// 评价
				case 1:
					radioGroup.check(R.id.pingjia1);
					break;
				default:
					break;
				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

		});

		View view_dingcan = mInflater.inflate(R.layout.merchat_dingcan, null);
		View view_pingjia = mInflater.inflate(R.layout.merchat_pingjia, null);
		layout = (LinearLayout) findViewById(R.id.tiaoxx);
		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MerchatDataActivity.this,
						DianPuActivity.class);
				intent.putExtra("mid", flList.get(0).getMid() + "");
				startActivity(intent);

			}
		});
		// 购物车
		buycat_jine = (TextView) view_dingcan.findViewById(R.id.buycat_jine);
		buycat_chae = (TextView) view_dingcan.findViewById(R.id.buycat_chae);
		buycat_tijiao = (Button) view_dingcan.findViewById(R.id.buycat_tijiao);
		shop_cat = (TextView) view_dingcan.findViewById(R.id.shopp_cat);
		// 监听购物车

		shop_cat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 调用购物对话框
				showDialog();
			}
		});

		buycat_tijiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String jine = (String) buycat_jine.getText();
				double bj = Double.parseDouble(jine.substring(0,
						jine.length() - 1));
				if (bj <= 0) {
					Toast.makeText(MerchatDataActivity.this, "您还未购买继续购物吧",
							Toast.LENGTH_SHORT).show();

				}

				else if (bj < mqsf) {
					Toast.makeText(MerchatDataActivity.this, "不足起送费 继续购物吧",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(getApplicationContext(),
							WaiMaiDingDan.class);
					intent.putExtra("mname", mname);
					intent.putExtra("jine", jine);
					sunGoods.clear();
					for (int i = 0; i < gList.size(); i++) {
						if (gList.get(i).getGnum() > 0) {
							sunGoods.add(gList.get(i));
						}
					}
					Type typeOfT = new TypeToken<List<Goods>>() {
					}.getType();
					Gson gson = new Gson();
					String str = gson.toJson(sunGoods, typeOfT);
					intent.putExtra("goodsList", str);
					startActivity(intent);

				}
			}
		});

		// 获取商品分类的信息
		url = "http://" + MyApplication.getIp()
				+ ":8080/happycat/selectgoodsclassifyservlet?mid=" + mid;
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<goodsclassify>>() {
				}.getType();
				List<goodsclassify> resultlist = gson.fromJson(result, typeOfT);
				flList.addAll(resultlist);
				adapter.setList(flList);
				adapter.notifyDataSetChanged();
			}
		});
		/*// 判断是否收藏
				url1 = "http://" + MyApplication.getIp() + ":8080/happycat/MG";
				HttpUtils utils = new HttpUtils();
				RequestParams param1 = new RequestParams();
				param1.addBodyParameter("key", "4");
				gid = getIntent().getStringExtra("gid");
				param1.addQueryStringParameter("uid", GlobalContacts.myid);
				param1.addBodyParameter("gid", gid);
				utils.send(HttpMethod.POST, url1, param1,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								String result = responseInfo.result;
								collection = Integer.parseInt(result);
								Log.e("collection", collection + "");
								if (collection == 0) {
									count = 0;
									imaggood.setImageResource(R.drawable.star2);
								} else if (collection == 1) {
									count = 1;
									imaggood.setImageResource(R.drawable.star1);
								}
							}

							@Override
							public void onFailure(HttpException error, String msg) {
								// TODO Auto-generated method stub

							}
						});
*/
		dc_fenlielistview = (ListView) view_dingcan
				.findViewById(R.id.dc_fenlielistview);
		adapter = new Dc_fenleiAdapter(flList, this);
		dc_fenlielistview.setAdapter(adapter);

		// 监听listview中item单击事件
		dc_fenlielistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (oldView != null) {
					oldView.setBackgroundResource(0);

				}
				oldView = view;
				view.setBackgroundResource(R.drawable.ab);

				int xid = flList.get(position).getGcid();

				Log.e("xid", "" + xid);
				sunGoods.clear();
				for (int i = 0; i < gList.size(); i++) {
					int yid = gList.get(i).getGcid();
					Log.e("yid", "" + yid);
					if (xid == yid) {
						sunGoods.add(gList.get(i));
					}

				}

				gAdapter.setGoodslist(sunGoods);
				gAdapter.notifyDataSetChanged();

			}
		});

		// 商品
		dc_goodslistview = (ListView) view_dingcan
				.findViewById(R.id.dc_goodslistview);
		gAdapter = new goodsAdapter(gList, MerchatDataActivity.this);

		// 获取商品信息

		String url1 = "http://" + MyApplication.getIp()
				+ ":8080/happycat/SelectGoods?mid=" + mid + "&gcid=0";
		HttpUtils h = new HttpUtils();
		h.send(HttpMethod.GET, url1, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<Goods>>() {
				}.getType();
				List<Goods> resultlist = gson.fromJson(result, typeOfT);
				gList.addAll(resultlist);
				gAdapter.setGoodslist(gList);
				gAdapter.setgList(gList);
				gAdapter.notifyDataSetChanged();
				getcollection();
			}
		});

		dc_goodslistview.setAdapter(gAdapter);
		// 获取店家评论
		pjlistview = (ListView) view_pingjia.findViewById(R.id.pjlistview);
		pAdapter = new PingjiaAdapter(pList, this);
		String url2 = "http://" + MyApplication.getIp()
				+ ":8080/happycat/selectpingjia?mid=" + mid;

		HttpUtils wk = new HttpUtils();
		wk.send(HttpMethod.GET, url2, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<PingjiaBean>>() {
				}.getType();
				List<PingjiaBean> resultlist = gson.fromJson(result, typeOfT);

				pList.addAll(resultlist);
				pAdapter.setList(pList);
				pAdapter.notifyDataSetChanged();
			}
		});
		pjlistview.setAdapter(pAdapter);

		// 页面加入viewpager中
		views.add(view_dingcan);
		views.add(view_pingjia);

		// 找到viewpager
		radioGroup = (RadioGroup) findViewById(R.id.top1);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			// 同步修改viewpager 值
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {

				switch (arg1) {
				case R.id.dingcan1:
					// viewpager指向订餐
					merchat_viewpager.setCurrentItem(0);
					break;
				case R.id.pingjia1:
					merchat_viewpager.setCurrentItem(1);

					break;
				default:
					break;
				}
			}
		});
		/*imaggood=(ImageButton) findViewById(R.id.goodshoucang);
		imaggood.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (count == 0) {
					count = 1;// 此时表示用户要收藏

					
				} else {
					count = 0;// 标识用户要取消收藏

					Log.e("count------", count + "");

				}
				getInsertFromServer(gid, count);
				
			}

			
		
		});*/

	}
	public void getcollection() {
		url1 = "http://" + MyApplication.getIp() + ":8080/happycat/MG";
		HttpUtils utils = new HttpUtils();
		final Gson gson = new Gson();
		List<Integer> userCollection = new ArrayList<Integer>();
		for (int i = 0; i < gList.size(); i++) {
			userCollection.add(gList.get(i).getId());
		}
		RequestParams params = new RequestParams();
		params.addBodyParameter("key", "4");
		params.addBodyParameter("gid", gson.toJson(userCollection));
		params.addBodyParameter("uid", MyApplication.SP_user_id+"");
		utils.send(HttpMethod.POST, url1, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						Type typeOfT = new TypeToken<List<Integer>>() {
						}.getType();
						collectionList = gson.fromJson(result, typeOfT);
						gAdapter.setCollectionList(collectionList);
						Log.e("-----------", collectionList.toString());
						gAdapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});

		
	}

	private void initView() {

		merchat_viewpager.setAdapter(new PagerAdapter() {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				// TODO Auto-generated method stub
				return title.get(position);
			}
		});

	}

	// 购物车Dialog显示内容
	public void showDialog() {

		sunGoods.clear();
		for (int i = 0; i < gList.size(); i++) {
			if (gList.get(i).getGnum() > 0) {
				sunGoods.add(gList.get(i));
			}
		}
		MyDialog dialog = new MyDialog(this, sunGoods);
		Log.e("sungoods", sunGoods.toString());

		dialog.show();
	}
}
