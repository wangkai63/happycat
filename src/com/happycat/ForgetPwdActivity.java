package com.happycat;

import com.example.happucat.R;
import com.happycat.util.ActivitiyUtils;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ForgetPwdActivity extends Activity {
	private TextView action_bar_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this, R.layout.title_bar_login);
		initView();
	}

	private void initView() {
		action_bar_title=(TextView) findViewById(R.id.action_bar_title);
		Button btn_reSetPwd=(Button) findViewById(R.id.btn_register);
		btn_reSetPwd.setText("重设密码");
		action_bar_title.setText("找回密码");
		findViewById(R.id.register_rules).setVisibility(View.INVISIBLE);
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivitiyUtils.finish(ForgetPwdActivity.this);
			}
		});
	
		
	}



}
