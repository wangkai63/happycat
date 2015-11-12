package com.happycat;

import com.example.happucat.R;
import com.example.happucat.R.id;
import com.example.happucat.R.layout;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MyaddressActivity extends Activity implements OnClickListener {
	ImageButton imageButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address);
		// 去掉标题栏
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		imageButton = (ImageButton) findViewById(R.id.photo1);
		imageButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.photo1:
			finish();
			break;

		default:
			break;
		}
	}

}
