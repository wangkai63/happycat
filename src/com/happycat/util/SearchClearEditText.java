package com.happycat.util;




import com.example.happucat.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

public class SearchClearEditText extends EditText implements
		OnFocusChangeListener, TextWatcher {

	
	private Drawable mClearDrawable;
	
	private boolean hasFocus;

	public SearchClearEditText(Context context) {
		this(context, null);
	}

	public SearchClearEditText(Context context, AttributeSet attrs) {
		
		this(context, attrs, android.R.attr.editTextStyle);
	}

	
	public SearchClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		
		mClearDrawable = getCompoundDrawables()[2]; // [2]=right
		if (mClearDrawable == null) {
			mClearDrawable = getResources()
					.getDrawable(R.drawable.search);
		}
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
				mClearDrawable.getIntrinsicHeight()); 
		
		setClearIconVisible(false);
		
		setOnFocusChangeListener(this);
		
		addTextChangedListener(this);

	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) { // ACTION_UP�ǰ���̧��
			if (getCompoundDrawables()[2] != null) {

				
				boolean touchable = (event.getX() > (getWidth() - getTotalPaddingRight()))
						&& (event.getX() < (getWidth() - getPaddingRight()));
				if (touchable) {
					setText("");
				}
			}
		}
		return super.onTouchEvent(event);
	}
	private void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		this.hasFocus = hasFocus;
		
		if (hasFocus) {
			setClearIconVisible(getText().length() > 0);
		} else {
			setClearIconVisible(false);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	
	@Override
	public void onTextChanged(CharSequence text, int start, int lengthBefore,
			int lengthAfter) {
		// super.onTextChanged(text, start, lengthBefore, lengthAfter);
		if (hasFocus) {
			setClearIconVisible(text.length() > 0);
		}
	}

	
	public void setShakeAnimation() {
		this.setAnimation(shakeAnimation(5));
	}

	
	private Animation shakeAnimation(int counts) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}

}
