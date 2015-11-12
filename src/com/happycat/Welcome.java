package com.happycat;


import com.example.happucat.R;
import com.happycat.util.CacheUtils;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
//欢迎页面
public class Welcome extends Activity {
	private final static long duration = 2000;
	private View mcontainer;// 外侧容器
    public final static String KEY_isfirst="isFirst";//第一次登录的标记
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		mcontainer = findViewById(R.id.welcome_container);
		// 实现动画
		AnimationSet set = new AnimationSet(false);// 动画集合
		// 旋转动画
		RotateAnimation ra = new RotateAnimation(0,// 开始的角度
				360,// 结束的角度
				Animation.RELATIVE_TO_SELF,// x轴方向相对于自己为中心轴旋转
				0.5f,// 取值范围（0-1）
				Animation.RELATIVE_TO_SELF,// y轴方向相对于自己为中心轴旋转
				0.5f);
		ra.setFillEnabled(true);// 动画保持状态
		ra.setFillAfter(true);// 保持为之后的状态
		ra.setDuration(duration);// 动画时间
		// 缩放
		ScaleAnimation sa = new ScaleAnimation(0,// x方向开始的缩放比例
				1f,// x方向结束的缩放比例
				0f,// y方向开始的缩放比例
				1f,// y方向开始的缩放比例
				Animation.RELATIVE_TO_SELF,// x轴方向相对于自己为中心轴旋转
				0.5f,// 取值范围（0-1）
				Animation.RELATIVE_TO_SELF,// y轴方向相对于自己为中心轴旋转
				0.5f);
		sa.setFillEnabled(true);// 动画保持状态
		sa.setFillAfter(true);// 保持为之后的状态
		sa.setDuration(duration);// 动画时间

		// 添加动画
		set.addAnimation(ra);// 旋转
		set.addAnimation(sa);// 缩放

		// 开启动画
		mcontainer.startAnimation(set);
		// 设置动画的监听
		set.setAnimationListener(new WelcomeAnimationListener());

	}

	class WelcomeAnimationListener implements AnimationListener {
		@Override
		public void onAnimationEnd(Animation animation) {
			// 当动画结束时调用
			//默认是第一次打开
			boolean isFirst=
			CacheUtils.getBoolean(Welcome.this, KEY_isfirst,true);
			if (isFirst) {
			//页面跳转,当应用程序第一次进入时，需要跳转到引导页面
				Intent intent=new Intent(
						Welcome.this,Guide.class);
				startActivity(intent);
			}else {
			//否则，跳转到主页面
				Intent intent=new Intent(
						Welcome.this,MainActivity.class);
				startActivity(intent);
			}
			finish();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
		}
	}
}
