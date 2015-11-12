package com.happycat;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.example.happucat.R;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.happycat.util.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShareActivity extends Activity implements OnClickListener {
	Button sharetoxinlang;
	private String imagepath;

	// 分享界面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShareSDK.initSDK(getApplicationContext());
		setContentView(R.layout.activity_share);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_share);
		sharetoxinlang=(Button) this.findViewById(R.id.share_weibo);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.share_weibo).setOnClickListener(this);	
	}

	@Override
	public void onClick(View v) {
        switch (v.getId()) {
		case R.id.btn_back:
			Intent intent1 = new Intent(ShareActivity.this, MainActivity.class);
			startActivity(intent1);
			ActivitiyUtils.finish(this);
			break;
			
		case R.id.share_weibo:
			/*OnekeyShare onekeyShare=new OnekeyShare();
			//设置标题
			onekeyShare.setTitle("新浪微博分享");
			onekeyShare.setText("输入你想分享的内容");
			onekeyShare.setImageUrl( "http://" + MyApplication.getIp()
					+ ":8080/happycat/img/yemao.png");
		
			onekeyShare.show(ShareActivity.this);*/
			ShareSDK.initSDK(this);
			OnekeyShare oks = new OnekeyShare();
			// 关闭sso授权
			oks.disableSSOWhenAuthorize();
			// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
			oks.setTitle(getString(R.string.share));
			// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
			oks.setTitleUrl("http://sharesdk.cn ");
			// text是分享文本，所有平台都需要这个字段
			oks.setText(getString(R.string.text));
			// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
			oks.setImageUrl( "http://" + MyApplication.getIp()
					+ ":8080/happycat/img/yemao.png");
			//oks.setImagePath("/sdcard/text.jpg");// 确保SDcard下面存在此张图片
			// url仅在微信（包括好友和朋友圈）中使用
			oks.setUrl("http://sharesdk.cn ");
			// comment是我对这条分享的评论，仅在人人网和QQ空间使用
			oks.setComment("我是测试评论文本");
			// site是分享此内容的网站名称，仅在QQ空间使用
			oks.setSite(getString(R.string.app_name));
			// siteUrl是分享此内容的网站地址，仅在QQ空间使用
			oks.setSiteUrl("http://sharesdk.cn ");
			// 启动分享GUI
			oks.show(this);

			break;

		default:
			break;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MyApplication.myflag = "1";
	}
}
