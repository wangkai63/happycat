package com.happycat;
import com.example.happucat.R;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.StringUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class GuanyuYemaoActivity extends Activity implements OnClickListener {
	//关于夜猫界面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.install_gyym);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_yemao);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.gyym_shang);
        findViewById(R.id.gyym_zhong);
        findViewById(R.id.gyym_xia);
	}

	@Override
	public void onClick(View v) {
        if (v.getId()==R.id.btn_back) {
        	StringUtils.showToast(this, "返回");
			ActivitiyUtils.finish(this);
		}
	}

}
