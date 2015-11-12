package com.happycat;

import com.example.happucat.R;
import com.happycat.util.ActivitiyUtils;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class UserDataActivity extends Activity {

	private EditText eTname, eTdetail;
	private RadioGroup radioGroup;
	private Spinner s1, s2, s3;
	static int provincePosition = 0;
	String text = "男", province = "江苏省", city = "南京市", country = "玄武区",
			address;
	Button ok;
	ImageButton fanhui;
	Intent intent;
	LinearLayout linearLayout;
	ArrayAdapter<String> s1Adapter, s2Adapter, s3Adapter;
	TextView t1, t2, t3, t4, t5, t6;

	private String[] pro = new String[] { "江苏省", "河南省", "北京市" };
	private String[][] cit = new String[][] {
			{ "南京市", "镇江市", "常州市", "无锡市 ", "苏州市 ", "连云港市 ", "盐城市 ", "南通市 ",
					"徐州市 ", "宿迁市 ", "淮安市 ", "扬州市 ", "泰州市 " },
			{ "郑州市", "开封市", "洛阳市", "平顶山市", "安阳市", "鹤壁市", "新乡市", "焦作市", "濮阳市",
					"漯河市", "许昌市", "三门峡市", "商丘市", "驻马店市", "南阳市", "信阳市", "济源市" },
			{ "无", "无", "无", "无", "无", "无", "无", "无", "无" } };
	private String[][][] coun = new String[][][] {
			{ { "玄武区", "白下区", "秦淮区", "建邺区", "鼓楼区", "下关区", "浦口区" },
					{ "京口区 ", "润州区", "丹徒区" },
					{ "武进区", "天宁区", "钟楼区", "新北区", "戚墅堰区" },
					{ "崇安区", "南长区", "北塘区", "滨湖区", "无锡新区", "惠山区", "锡山区" },
					{ "金阊区", "沧浪区", "平江区", "虎丘区", "吴中区", "相城区" },
					{ "新浦区", "连云区", "海州区" }, { "亭湖区", "盐都区" },
					{ "崇川区", "港闸区", "通州区" },
					{ "云龙区", "鼓楼区", "九里区", "贾汪区", "泉山区" }, { "宿城区", "宿豫区" },
					{ "清河区", "清浦区", "楚州区", "淮阴区" }, { "广陵区", "邗江区", "江都区" },
					{ "海陵区", "高港区" } },
			{
					{ "管城回族区", "金水区", "二七区", "上街区", "中原区", "西北高新区", "东南高新区",
							"郑东新区", "惠济区（邙山区）" },
					{ "龙庭区", "金明区", "顺河区", "鼓楼区", "禹王台区" },
					{ "涧西区 ", "西工区", "老城区", "瀍河回族区", "吉利区", "洛龙区" },
					{ "新华区卫东区", "新城区", "高新区", "石龙区", "湛河区" },
					{ "殷都区 ", "北关区", "文峰区", "龙安区 " },
					{ "淇滨区", "山城区", "鹤山区" },
					{ "卫滨区", "红旗区", "牧野区", "凤泉区", "高新技术产业开发区", "西工区", "小店工业区" },
					{ "山阳区", "解放区", "中站区", "马村区" }, { "华龙区", "高新区" },
					{ "郾城区", "源汇区", "召陵区" }, { "魏都区" }, { "湖滨区" },
					{ "睢阳区", "梁园区", "开发区" }, { "驿城区" }, { "宛城区", "卧龙区" },
					{ "浉河区", "平桥区" },
					{ "克井镇", "五龙口镇", "轵城镇", "承留镇", "邵原镇", "坡头镇", "梨林镇", "大峪镇" } },
			{ { "东城区", "西城区", "崇文区", "宣武区" }, { "朝阳区", "丰台区", "石景山区", "海淀区" },
					{ "门头沟区", "房山区", "通州区", "顺义区" },
					{ "昌平区 ", "大兴区", "怀柔区", "平谷区" } } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_xgzl);
		ActivitiyUtils.setActionBarLayout(getActionBar(), this,
				R.layout.title_bar_xiugaiuser);

		initView();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		address = province + city + country + eTdetail.getText().toString();
	}

	private void initView() {
		fanhui = (ImageButton) findViewById(R.id.btn_backxiugai);
		t1 = (TextView) findViewById(R.id.tv_user_nickname);
		t2 = (TextView) findViewById(R.id.tv_user_xiugaigender);
		t3 = (TextView) findViewById(R.id.tv_user_xiugaiprovince);
		t4 = (TextView) findViewById(R.id.tv_user_xiugaicity);
		t5 = (TextView) findViewById(R.id.tv_user_xiugaidistrict);
		t6 = (TextView) findViewById(R.id.tv_user_xiugaidetail);

		ok = (Button) findViewById(R.id.user_xiugaifinish);
		eTname = (EditText) findViewById(R.id.et_user_nickname);
		eTdetail = (EditText) findViewById(R.id.et_user_xiugaidetail);

		radioGroup = (RadioGroup) findViewById(R.id.user_RGgender);
		int id = radioGroup.getCheckedRadioButtonId();
		// 根据选中的id找到选中的RadioButton
		RadioButton radioButton1 = (RadioButton) findViewById(R.id.user_gendernan);
		// 取出RadioButton上面的文本
		radioButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				text = "男";
			}
		});

		RadioButton radioButton = (RadioButton) findViewById(R.id.user_gendernv);

		radioButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text = "女";

			}
		});

		s1 = (Spinner) findViewById(R.id.user_xiugais1);
		s1Adapter = new ArrayAdapter<String>(UserDataActivity.this,
				android.R.layout.simple_spinner_item, pro);
		s1.setAdapter(s1Adapter);
		s1Adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		s1.setSelection(0, true); // 设置默认选中项，此处为默认选中第4个值
		s1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				s2Adapter = new ArrayAdapter<String>(UserDataActivity.this,
						android.R.layout.simple_spinner_item, cit[position]);
				s2.setAdapter(s2Adapter);
				s2Adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				provincePosition = position;
				province = parent.getItemAtPosition(position).toString();
				Log.e("province", province);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		s2 = (Spinner) findViewById(R.id.user_xiugais2);
		s2Adapter = new ArrayAdapter<String>(UserDataActivity.this,
				android.R.layout.simple_spinner_item, cit[0]);
		s2.setAdapter(s2Adapter);
		s2Adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		s2.setSelection(0, true);
		s2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				s3Adapter = new ArrayAdapter<String>(UserDataActivity.this,
						android.R.layout.simple_spinner_item,
						coun[provincePosition][position]);
				s3.setAdapter(s3Adapter);
				s3Adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				city = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		s3 = (Spinner) findViewById(R.id.user_xiugais3);
		s3Adapter = new ArrayAdapter<String>(UserDataActivity.this,
				android.R.layout.simple_spinner_item, coun[0][0]);
		s3.setAdapter(s3Adapter);
		s3.setSelection(0, true);
		s3Adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		s3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// position：用户选择了哪一行，从0开始
				// parent：表示整个下拉列表
				country = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if ((2 < eTname.getText().toString().trim().length() && eTname
						.getText().toString().trim().length() < 8)
						&& (2 < eTdetail.getText().toString().trim().length() && eTdetail
								.getText().toString().trim().length() < 60)) {
					RequestParams params = new RequestParams();
					params.addBodyParameter("key", "" + 3);
					params.addBodyParameter("uphone",
							MyApplication.SP_user_phone);
					params.addBodyParameter("uname", eTname.getText()
							.toString());
					params.addBodyParameter("sex", text);
					params.addBodyParameter("uprovince", province);
					params.addBodyParameter("ucity", city);
					params.addBodyParameter("ucountry", country);
					params.addBodyParameter("udetail", eTdetail.getText()
							.toString());

					HttpUtils httpUtils = new HttpUtils();
					String url = "http://" + MyApplication.getIp()
							+ ":8080/happycat/User";
					httpUtils.send(HttpMethod.POST, url, params,
							new RequestCallBack<String>() {

								@Override
								public void onFailure(HttpException error,
										String msg) {
									Toast.makeText(UserDataActivity.this,
											"请检查网络", Toast.LENGTH_SHORT).show();

								}

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {

									Toast.makeText(UserDataActivity.this,
											"修改成功", Toast.LENGTH_SHORT).show();

								}
							});

					address = province + city + country
							+ eTdetail.getText().toString();
					Log.e("address", address);
					// finish();
					Intent intent = new Intent();
					intent.putExtra("name", eTname.getText().toString());
					Log.e("fff", eTname.getText().toString());
					intent.putExtra("sex", text);
					intent.putExtra("address", address);
					setResult(Activity.DEFAULT_KEYS_DIALER, intent);
					finish();

				} else {

					Toast.makeText(UserDataActivity.this, "请输入昵称、地址", 0).show();

				}
			}
		});

		// Log.e("city", city);
		//
		fanhui.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(UserDataActivity.this, UserActivity.class);
				startActivity(intent);
				intent.putExtra("name", eTname.getText().toString());
				intent.putExtra("sex", text);
				intent.putExtra("address", address);
				setResult(Activity.DEFAULT_KEYS_DIALER, intent);

				finish();

			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			intent = new Intent(UserDataActivity.this, UserActivity.class);
			startActivity(intent);
			intent.putExtra("name", eTname.getText().toString());
			intent.putExtra("sex", text);
			intent.putExtra("address", address);
			setResult(Activity.DEFAULT_KEYS_DIALER, intent);

			finish();

		}
		return false;

	}

}
