package com.happycay.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.example.happucat.R;
import com.happycat.tuling.HttpData;
import com.happycat.tuling.HttpGetDataListener;
import com.happycat.tuling.ListData;
import com.happycat.tuling.TextAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class XiaoxiFragment extends Fragment implements HttpGetDataListener,OnClickListener{
	private View view;
	private HttpData httpData;
	private List<ListData> lists;
	private ListData listData ;
	private ListView listView;
	private EditText editText;
	private Button button;
	private String content_xiaoxi;
	private TextAdapter adapter;//用来显示文本
	private String [] welcome_array;//小猫欢迎语
	private double currentTime,oldTime=0;//显示时间
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.xiaoxi, null);
		initView();
		return view;
	}
	//初始化initview
	private void initView(){
		listView=(ListView) view.findViewById(R.id.xiaoxi_listview);
		editText=(EditText) view.findViewById(R.id.xiaoxi_input);
		button=(Button) view.findViewById(R.id.xiaoxi_send);
		lists=new ArrayList<ListData>();
		button.setOnClickListener(this);
		adapter=new TextAdapter(lists,this.getActivity());
		listView.setAdapter(adapter);
		listData=new ListData(getRandomWelcomeTips(), listData.RECEIVER,getTime());
	    lists.add(listData);
	}
	@Override
	public void getDataUrl(String data) {
		//输出获取的数据System.out.println(data);
        parseText(data);		
	}
	public void parseText(String str){
		try {
			JSONObject jb=new JSONObject(str);
		    //数据的封装
			listData =new ListData(jb.getString("text"),ListData.RECEIVER,getTime());
			lists.add(listData);
			//系统返回的时候进行适配
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
            e.printStackTrace();
		}
	}
    //随机得到欢迎语的方法
	private String getRandomWelcomeTips(){
		String welcome_tip=null;
		welcome_array=this.getResources().getStringArray(R.array.xiaoxi_welcome);
		int index=(int) (Math.random()*(welcome_array.length));
		welcome_tip=welcome_array[index];
		return welcome_tip;
	}
	@Override
	public void onClick(View v) {
		//获取时间
		getTime();
		//从发出的消息中获取数据
		content_xiaoxi=editText.getText().toString();
		//输出以后设置为空
		editText.setText("");
		//去掉空格
		String dropk=content_xiaoxi.replace(" ", "");
		//去掉回车
		String droph=dropk.replace("\n", "");
		listData=new ListData(content_xiaoxi, ListData.SEND,getTime());
		lists.add(listData);
		//移除过多的语句
		if (lists.size() > 30) {
			for (int i = 0; i < lists.size(); i++) {
				lists.remove(i);
			}
		}
		//刷新
		adapter.notifyDataSetChanged();
		httpData=(HttpData) new HttpData(
				 "http://www.tuling123.com/openapi/api?key=2de8502273d374a778533ae686618d35&info="+content_xiaoxi,
				 this).execute();
	}
        private String getTime(){
        	SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss");
        	Date date=new Date();
        	String time=format.format(date);
        	if (currentTime-oldTime>=5*60*1000) {
				oldTime=currentTime;
				return time;
			}else {
				return "";
			}
        }
}
