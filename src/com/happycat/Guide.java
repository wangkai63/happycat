package com.happycat;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;


import com.example.happucat.R;
import com.happycat.util.CacheUtils;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

//用户引导页面
public class Guide extends Activity implements OnPageChangeListener,
		OnClickListener {
	private ViewPager Pager;// viewpager
	private Button button;// 开启按钮
	private LinearLayout pointContainer;// 小圆点的容器
	private View selectpoint;// 选中的点
	private List<ImageView> image;// 存储viewpager中的imageview
	private int space;// 点与点之间的距离

	// 存储viewpager的imageview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// 设置布局
		setContentView(R.layout.guide);
		Pager = (ViewPager) findViewById(R.id.guide);
		button = (Button) findViewById(R.id.guide_btn);
		pointContainer = (LinearLayout) findViewById(R.id.guide_point);
		selectpoint = findViewById(R.id.guide_point_select);
		button.setOnClickListener(this);
		initData();// 初始化数据方法
		// 计算点与点之间的距离
		selectpoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// 当整个UI的树布局完成时调用
						if (image == null) {
							return;
						}
						selectpoint.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						space = pointContainer.getChildAt(1).getLeft()
								- pointContainer.getChildAt(0).getLeft();
					}
				});
	}

	// 初始化数据
	private void initData() {
		// 添加图片
		int[] images = new int[] { R.drawable.shou1, R.drawable.shou2,
				R.drawable.shou3 };
		// 完善list
		image = new ArrayList<ImageView>();
		for (int i = 0; i < images.length; i++) {
			// 新建imageview
			ImageView iv = new ImageView(this);
			iv.setImageResource(images[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			// 给list添加image
			image.add(iv);
			// 添加圆点
			View point = new View(this);
			point.setBackgroundResource(R.drawable.guide_point_normal);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);// 10px
			if (i != 0) {
				params.leftMargin = 10;
			}
			pointContainer.addView(point, params);

		}
		// 给viewpager数据，条件反射的要想到adapter，接着list
		Pager.setAdapter(new GuideAdapter());
		Pager.setOnPageChangeListener(this);
	}

	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if (image != null) {
				return image.size();
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = image.get(position);
			// 添加到viewpager中
			container.addView(iv);
			// 需要返回的是显示的imageview
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 从viewpager中移除imageview
			ImageView iv = image.get(position);
			// iv=image.get(position);
			// container.removeView(iv);
			container.removeView((View) object);
		}
	}

	// 当viewpager正在滑动时的回调，position是当前所处的页面，
	// positionOffset指的是百分比，
	// positionOffsetPixels指的是实际滑动的距离px
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// 1、对滑动的点做操作 2、设置marginleft
		RelativeLayout.LayoutParams params = (LayoutParams) selectpoint
				.getLayoutParams();
		params.leftMargin = (int) (space * position + space * positionOffset + 0.5f);// 四舍五入
		selectpoint.setLayoutParams(params);
	}

	// 当viewpager页面选中时的回调，position当前选中的位置
	@Override
	public void onPageSelected(int position) {
		// if (position==image.size()-1) {
		// //显示button
		// button.setVisibility(View.VISIBLE);
		// }else {
		// //隐藏button
		// button.setVisibility(View.GONE);
		// }
		// 上面代码等同于:
		button.setVisibility(position == image.size() - 1 ? View.VISIBLE
				: View.GONE);
	}

	// 当viewpager的滑动状态改变时的回调，state是状态值
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		if (v == button) {
			goSecondActivity();
		}
	}

	private void goSecondActivity() {
		//保存已经不是第一次登录了
		CacheUtils.setBoolean(this, Welcome.KEY_isfirst, false);
		Intent intent=new Intent(
				Guide.this,MainActivity.class);
		startActivity(intent);
	}
}
