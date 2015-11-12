package com.happycat;

import image.SmartImageView;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happycat.Bean.User;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.happycat.util.StringUtils;
import com.happycat.view.Circleimage;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity implements OnClickListener {
	private BitmapUtils bitmapUtils;
	private SmartImageView civ_photo;
	private LayoutInflater mInflater;
	Dialog dialog;
	private String filepath;
	private RelativeLayout my_password;
	private String filename = System.currentTimeMillis() + ".jpg";
	// private String user_id = 1 + "";
	private Uri uri;
	List<User> userList = new ArrayList<User>();
	String imagurl = "http://" + MyApplication.getIp() + ":8080/happycat/img/";
	TextView name, phone, sex, address;
	String province, city, district, detail;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdetail);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_user);

		initView();
		initData();

	}

	private void initData() {

		bitmapUtils = new BitmapUtils(this);
		mInflater = LayoutInflater.from(this);

		HttpUtils utils = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("uphone", MyApplication.SP_user_phone);
		String url = "http://" + MyApplication.getIp()
				+ ":8080/happycat/Selectuserall";
		utils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				Gson gson = new Gson();
				// UserBean userList = gson.fromJson(result, User.class);
				Type typeOfT = new TypeToken<List<User>>() {
				}.getType();
				List<User> resultlist = gson.fromJson(result, typeOfT);
				userList.addAll(resultlist);
				System.out.println(userList.get(0).getUname());
				name.setText(userList.get(0).getUname());
				sex.setText(userList.get(0).getSex());
				phone.setText(MyApplication.SP_user_phone);
				province = (userList.get(0).getUprovince());
				city = (userList.get(0).getUcity());
				district = (userList.get(0).getUcountry());
				detail = (userList.get(0).getUdetail());

				MyApplication.bitmapUtils.display(civ_photo, imagurl
						+ userList.get(0).getUimg());

				address.setText(province + city + district + detail);
				Log.e("个人资料", sex + "");

			}
		});

	}

	// 初始化视图 绑定事件监听器
	private void initView() {

		findViewById(R.id.btn_backgrzl).setOnClickListener(this);
		civ_photo = (SmartImageView) findViewById(R.id.img_user_touxiang);
		civ_photo.setOnClickListener(this);
		findViewById(R.id.rl_username).setOnClickListener(this);
		findViewById(R.id.rl_gender).setOnClickListener(this);
		findViewById(R.id.rl_address).setOnClickListener(this);
		// 修改密码
		my_password = (RelativeLayout) findViewById(R.id.rl_password_xiugai);
		my_password.setOnClickListener(this);

		findViewById(R.id.user_xiugaibutton).setOnClickListener(this);
		address = (TextView) findViewById(R.id.user_address);
		name = (TextView) findViewById(R.id.tv_user_namevalue);
		phone = (TextView) findViewById(R.id.tv_uphonevalue);
		sex = (TextView) findViewById(R.id.tv_gendervalue);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_backgrzl:// 点击了返回
			Intent intent1 = new Intent(UserActivity.this, MainActivity.class);
			startActivity(intent1);
			ActivitiyUtils.finish(this);

			break;
		case R.id.img_user_touxiang:// 点击了头像图片
			showPhotoDialog();
			break;
		case R.id.user_xiugaibutton:// 点击了修改资料按钮

			Intent intent = new Intent(UserActivity.this,
					UserDataActivity.class);
			startActivityForResult(intent, 0);

			break;
		case R.id.openCamera:// 点击相机
			openCamera();
			break;
		case R.id.open_phones_pic:// 打开图库
			openPhones();
			break;
		case R.id.cancel:// 取消
			dialog.cancel();
			break;

		case R.id.rl_password_xiugai:// 修改密码
			Intent it = new Intent(UserActivity.this,
					UserPasswordActivity.class);
			startActivityForResult(it, 4);
			break;

		default:
			break;
		}

	}

	private void openPhones() {
		dialog.dismiss();
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		startActivityForResult(intent, 2);
	}

	private void openCamera() {
		dialog.dismiss();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
				Environment.getExternalStorageDirectory(), filename)));
		uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
				filename));
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("resultCode:" + resultCode);
		switch (requestCode) {
		// 返回用户更新信息
		case 0:
			if (resultCode == Activity.DEFAULT_KEYS_DIALER)

				address.setText(data.getStringExtra("address"));
			name.setText(data.getStringExtra("name"));
			sex.setText(data.getStringExtra("sex"));

			break;
		// 如果是直接从相册获取
		case 1:
			if (resultCode == RESULT_OK)
				startPhotoZoom(uri);
			break;
		// 如果是调用相机拍照时
		case 2:
			System.out.println("----" + data);
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				startPhotoZoom(uri);
			}
			break;

		// 取得裁剪后的图片
		case 3:

			Bitmap bitmap;
			filepath = Environment.getExternalStorageDirectory().getPath()
					+ "/" + filename;
			bitmap = BitmapFactory.decodeFile(Environment
					.getExternalStorageDirectory().getPath() + "/" + filename);
			Circleimage image1 = new Circleimage();
			civ_photo.setImageBitmap(image1.toRoundBitmap(bitmap));// 显示图片
			Log.e("图片路径", filepath);
			// 将头像存入数据库
			saveData();

			break;
		case 4:

			if (resultCode == Activity.DEFAULT_KEYS_DISABLE) {
				// finish();
			}
			break;
		default:
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 500);
		intent.putExtra("outputY", 500);
		intent.putExtra("return-data", true);
		intent.putExtra("output", Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), filename)));
		startActivityForResult(intent, 3);
	}

	private void showPhotoDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog,
				null);
		view.findViewById(R.id.openCamera).setOnClickListener(this);
		view.findViewById(R.id.open_phones_pic).setOnClickListener(this);
		view.findViewById(R.id.cancel).setOnClickListener(this);
		dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}

	private void saveData() {
		HttpUtils utils = new HttpUtils();
		RequestParams params = new RequestParams();
		// params.addBodyParameter("key", "6");
		// persondata中的主键尚未获取
		params.addBodyParameter("key", "2");
		params.addBodyParameter("uid", MyApplication.SP_user_id + "");
		System.out.println(filepath);
		File file = new File(filepath);
		System.out.println(file.length());
		params.addBodyParameter("img1", file); // filePath是手机获取的图片地址
		Log.e("img1:", filepath);
		String url = "http://" + MyApplication.getIp() + ":8080/happycat/User1";
		utils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Toast.makeText(UserActivity.this, "头像设置成功", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(UserActivity.this, "头像设置失败，请检查网络",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MyApplication.myflag = "1";

	}
}
