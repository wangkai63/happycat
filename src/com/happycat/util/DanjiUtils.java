package com.happycat.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Toast;

public class DanjiUtils extends SimpleOnGestureListener {
	private Context mContext;

	public DanjiUtils(Context context) {
		mContext = context;
	}

	/*// 单击，触摸屏按下时立刻触发
	@Override
	public boolean onDown(MotionEvent e) {
		Toast.makeText(mContext, "DOWN " + e.getAction(), Toast.LENGTH_SHORT)
				.show();
		return false;
	}*/
/*
	// 短按，触摸屏按下后片刻后抬起，会触发这个手势，如果迅速抬起则不会
	@Override
	public void onShowPress(MotionEvent e) {
		Toast.makeText(mContext, "SHOW " + e.getAction(), Toast.LENGTH_SHORT)
				.show();
	}*/

	// 抬起，手指离开触摸屏时触发(长按、滚动、滑动时，不会触发这个手势)
/*	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Toast.makeText(mContext, "SINGLE UP " + e.getAction(),
				Toast.LENGTH_SHORT).show();
		return false;
	}*/

/*	// 滚动，触摸屏按下后移动
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Toast.makeText(mContext, "SCROLL " + e2.getAction(), Toast.LENGTH_SHORT)
				.show();
		return false;
	}*/
/*
	// 长按，触摸屏按下后既不抬起也不移动，过一段时间后触发
	@Override
	public void onLongPress(MotionEvent e) {
		Toast.makeText(mContext, "LONG " + e.getAction(), Toast.LENGTH_SHORT)
				.show();
	}*/

/*	// 滑动，触摸屏按下后快速移动并抬起，会先触发滚动手势，跟着触发一个滑动手势
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Toast.makeText(mContext, "FLING " + e2.getAction(), Toast.LENGTH_SHORT)
				.show();
		return false;
	}*/

	// OnDoubleTapListener的接口有这几个：

/*	// 双击，手指在触摸屏上迅速点击第二下时触发
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		Toast.makeText(mContext, "DOUBLE " + e.getAction(), Toast.LENGTH_SHORT)
				.show();
		return false;
	}*/

/*	// 双击的按下跟抬起各触发一次
	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		Toast.makeText(mContext, "DOUBLE EVENT " + e.getAction(),
				Toast.LENGTH_SHORT).show();
		return false;
	}*/

	// 单击确认，即很快的按下并抬起，但并不连续点击第二下
/*	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		Toast.makeText(mContext, "单击 " + e.getAction(),
				Toast.LENGTH_SHORT).show();
		return true;
	}*/
	
}
