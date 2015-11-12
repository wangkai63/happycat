package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.DayMerchatBean;
import com.happycat.Bean.Goods;
import com.happycat.util.MyApplication;

import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DayMerchatadapter extends BaseAdapter {
	List<DayMerchatBean> list;
	Context context;
	LayoutInflater mInflater;
	holderView mholder;
	String imagurl=" http://" + MyApplication.getIp()
			+ ":8080//happycat/upimage/";
	public DayMerchatadapter(List<DayMerchatBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class holderView {
		ImageView logo;
		TextView name;
		TextView peisongfei;
		TextView qisongjia;
		TextView time;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.waimai_listitem, null);
		   mholder = new holderView();
		   mholder.logo=(ImageView)convertView.findViewById(R.id.logo);
		   
			mholder.name = (TextView) convertView.findViewById(R.id.name);
			
			mholder.qisongjia = (TextView) convertView
					.findViewById(R.id.qisongjia);
			mholder.peisongfei = (TextView) convertView
					.findViewById(R.id.peisongfei);
			mholder.time=(TextView) convertView.findViewById(R.id.time);
			convertView.setTag(mholder);
		} else {
			mholder = (holderView) convertView.getTag();
		}
		
		mholder.name.setText(list.get(position).getMname());
		mholder.peisongfei.setText("配送费： " + list.get(position).getTip()+ "元");
		mholder.qisongjia.setText("起送价： " + list.get(position).getMprice() + "元");
		mholder.time.setText("送达时间： "+list.get(position).getLongtime());
	  MyApplication.bitmapUtils.display(mholder.logo, imagurl+list.get(position)
				.getMimg());
		return convertView;
	}

	public List<DayMerchatBean> getList() {
		return list;
	}

	public void setList(List<DayMerchatBean> list) {
		this.list = list;
	}


}
