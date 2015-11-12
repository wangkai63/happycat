package com.happycay.fragments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.MerchatDataActivity;
import com.happycat.Bean.Lunbobean;
import com.happycat.Bean.MerchatBean;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果； 既支持自动轮播页面也支持手势滑动切换页面
 * 
 * 
 */

public class LunboFragment extends FrameLayout {

	// 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
	private ImageLoader imageLoader = ImageLoader.getInstance();

	// 轮播图图片数量
	private final static int IMAGE_COUNT = 5;
	// 自动轮播的时间间隔
	private final static int TIME_INTERVAL = 5;
	// 自动轮播启用开关
	private final static boolean isAutoPlay = true;
	private List<Integer> p_ids;

	List<Lunbobean> list = new ArrayList<Lunbobean>();
	private List<String> mname;
	private List<String> longtime1;
	private List<String> mprice1;
	private List<String> tip1;
	private List<String> mimg1;
	private List<String> mtime1;

	// 自定义轮播图的资源
	private List<String> imageUrls;
	// 放轮播图片的ImageView 的list
	private List<ImageView> imageViewsList;
	// 放圆点的View的list
	private List<View> dotViewsList;

	private ViewPager viewPager;
	// 当前轮播页
	private int currentItem = 0;
	// 定时任务
	private ScheduledExecutorService scheduledExecutorService;

	private Context context;
	private String IP = MyApplication.getIp();
	String MyUrl = "http://" + IP + ":8080/happycat/GetUpload";
	// Handler
	HttpPost httpRequest = new HttpPost(MyUrl);
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			viewPager.setCurrentItem(currentItem);
		}

	};

	public LunboFragment(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public LunboFragment(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public LunboFragment(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		initImageLoader(context);

		initData();
		if (isAutoPlay) {
			startPlay();
		}

	}

	/**
	 * 开始轮播图切换
	 */
	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4,
				TimeUnit.SECONDS);
	}

	/**
	 * 停止轮播图切换
	 */
	private void stopPlay() {
		scheduledExecutorService.shutdown();
	}

	/**
	 * 初始化相关Data
	 */
	private void initData() {
		p_ids = new ArrayList<Integer>();
		imageViewsList = new ArrayList<ImageView>();
		dotViewsList = new ArrayList<View>();
		imageUrls = new ArrayList<String>();
		mname = new ArrayList<String>();
		longtime1 = new ArrayList<String>();
		mprice1 = new ArrayList<String>();
		tip1 = new ArrayList<String>();
		mimg1 = new ArrayList<String>();
		mtime1 = new ArrayList<String>();
		// 一步任务获取图片
		new GetListTask().execute("");

	}

	/**
	 * 初始化Views等UI
	 */
	private void initUI(Context context) {
		if (imageUrls == null || imageUrls.size() == 0)
			return;

		LayoutInflater.from(context).inflate(R.layout.lunbo, this, true);

		LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
		dotLayout.removeAllViews();

		// 热点个数与图片特殊相等
		for (int i = 0; i < imageUrls.size(); i++) {
			ImageView view = new ImageView(context);
			view.setTag(imageUrls.get(i));
			if (i == 0)// 给一个默认图
				view.setBackgroundResource(R.drawable.szg);
			view.setScaleType(ScaleType.FIT_XY);
			imageViewsList.add(view);

			ImageView dotView = new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
			dotViewsList.add(dotView);
		}

		viewPager = (ViewPager) findViewById(R.id.lb);
		viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());

		viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	/**
	 * 填充ViewPager的页面适配器
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			// ((ViewPag.er)container).removeView((View)object);
			((ViewPager) container).removeView(imageViewsList.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			final Intent intent = new Intent(getContext(),
					MerchatDataActivity.class);

			final Bundle bundle = new Bundle();
			switch (position) {
			case 0:
				imageViewsList.get(0).setOnClickListener(
						new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								bundle.putInt("mid", p_ids.get(0));

								bundle.putString("name", mname.get(0));
								bundle.putString("pjsu", longtime1.get(0));
								bundle.putString("qsf", mprice1.get(0));
								bundle.putString("mtime", mtime1.get(0));
								bundle.putString("psf", tip1.get(0));
								bundle.putString("img", mimg1.get(0));
								intent.putExtras(bundle);
								getContext().startActivity(intent);
							}
						});

				break;
			case 1:
				imageViewsList.get(1).setOnClickListener(
						new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								bundle.putInt("mid", p_ids.get(1));
						
								bundle.putString("name", mname.get(1));
								bundle.putString("pjsu", longtime1.get(1));
								bundle.putString("qsf", mprice1.get(1));
								bundle.putString("mtime", mtime1.get(1));
								bundle.putString("psf", tip1.get(1));
								bundle.putString("img", mimg1.get(1));
								intent.putExtras(bundle);
								getContext().startActivity(intent);
							}
						});
				break;
			case 2:
				imageViewsList.get(2).setOnClickListener(
						new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								bundle.putInt("mid", p_ids.get(2));
						
								bundle.putString("name", mname.get(2));
								bundle.putString("pjsu", longtime1.get(2));
								bundle.putString("qsf", mprice1.get(2));
								bundle.putString("mtime", mtime1.get(2));
								bundle.putString("psf", tip1.get(2));
								bundle.putString("img", mimg1.get(2));

								intent.putExtras(bundle);
								getContext().startActivity(intent);
							}
						});
				break;
			case 3:
				imageViewsList.get(3).setOnClickListener(
						new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								bundle.putInt("mid", p_ids.get(3));
							
								bundle.putString("name", mname.get(3));
								bundle.putString("pjsu", longtime1.get(3));
								bundle.putString("qsf", mprice1.get(3));
								bundle.putString("mtime", mtime1.get(3));
								bundle.putString("psf", tip1.get(3));
								bundle.putString("img", mimg1.get(3));
								;
								intent.putExtras(bundle);
								getContext().startActivity(intent);
							}
						});

				break;
			default:

				break;
			}

			return super.instantiateItem(container, position);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView imageView = imageViewsList.get(position);

			imageLoader.displayImage(imageView.getTag() + "", imageView);

			((ViewPager) container).addView(imageViewsList.get(position));
			return imageViewsList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (viewPager.getCurrentItem() == viewPager.getAdapter()
						.getCount() - 1 && !isAutoPlay) {
					viewPager.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
					viewPager
							.setCurrentItem(viewPager.getAdapter().getCount() - 1);
				}
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int pos) {

			currentItem = pos;
			for (int i = 0; i < dotViewsList.size(); i++) {
				if (i == pos) {
					((View) dotViewsList.get(pos))
							.setBackgroundResource(R.drawable.dot_focus);
				} else {
					((View) dotViewsList.get(i))
							.setBackgroundResource(R.drawable.dot_blur);
				}
			}
		}

	}

	/**
	 * 执行轮播图切换任务
	 * 
	 */
	private class SlideShowTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViewsList.size();
				handler.obtainMessage().sendToTarget();
			}
		}

	}

	/**
	 * 销毁ImageView资源，回收内存
	 * 
	 */
	private void destoryBitmaps() {

		for (int i = 0; i < IMAGE_COUNT; i++) {
			ImageView imageView = imageViewsList.get(i);
			Drawable drawable = imageView.getDrawable();
			if (drawable != null) {
				// 解除drawable对view的引用
				drawable.setCallback(null);
			}
		}
	}

	/**
	 * 异步任务,获取数据
	 * 
	 */
	class GetListTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String user = "";
			try {
				// 这里一般调用服务端接口获取一组轮播图片，
				HttpPost httpRequest = new HttpPost(MyUrl);
				List<NameValuePair> params1 = new ArrayList<NameValuePair>();

				params1.add(new BasicNameValuePair("key", "9"));

				httpRequest.setEntity(new UrlEncodedFormEntity(params1,
						HTTP.UTF_8));
				HttpResponse httpResponse = new DefaultHttpClient()
						.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					user = EntityUtils.toString(httpResponse.getEntity());
					JSONArray array = new JSONArray(user);
					for (int i = 0; i < array.length(); i++) {
						JSONObject object = (JSONObject) array.get(i);
						String res = object.getString("img");
						int p_id = object.getInt("mid");
						String name = object.getString("mname");
						String longtime = object.getString("longtime");
						String mprice = object.getString("mprice");
						String tip = object.getString("tip");
						String mimg = object.getString("mimg");
						String mtime = object.getString("mtime");
						String img = "http://" + IP + ":8080/happycat/img/"
								+ res;

						mname.add(name);
					    longtime1.add(longtime);
					    mprice1.add(mprice);
						tip1.add(tip);
						mimg1.add(mimg);
						mtime1.add(mtime);
						imageUrls.add(img);
						p_ids.add(p_id);

					}
				}

				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				initUI(context);
			}
		}
	}

	/**
	 * ImageLoader 图片组件初始化
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove
									// for
									// release
									// app
				.build();

		ImageLoader.getInstance().init(config);
	}
}