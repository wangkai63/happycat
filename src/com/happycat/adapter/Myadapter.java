package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.Goods;
import com.happycat.util.MyApplication;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Myadapter extends BaseAdapter {
	List<Goods> list;
	Context context;
	LayoutInflater mInflater;
	holderView mholder;
	String imagurl="http://" + MyApplication.getIp()
			+ ":8080/happycat/upimage/";
	public Myadapter(List<Goods> list, Context context) {
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
		ImageView imageView;
		TextView name;
		TextView peisongfei;
		TextView qisongjia;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.waimai_listitem, null);
		   mholder = new holderView();
		   mholder.imageView=(ImageView)convertView.findViewById(R.id.logo);
		   
			mholder.name = (TextView) convertView.findViewById(R.id.name);
			
			mholder.qisongjia = (TextView) convertView
					.findViewById(R.id.qisongjia);
			mholder.peisongfei = (TextView) convertView
					.findViewById(R.id.peisongfei);
			convertView.setTag(mholder);
		} else {
			mholder = (holderView) convertView.getTag();
		}
		
		mholder.name.setText(list.get(position).getGname());
		mholder.peisongfei.setText("配送费" + list.get(position).getPrice() + "元");
		mholder.qisongjia.setText("起送价" + list.get(position).getNumber() + "元");
	  MyApplication.bitmapUtils.display(mholder.imageView, imagurl+list.get(position)
				.getGimg());
		return convertView;
	}

	public List<Goods> getList() {
		return list;
	}

	public void setList(List<Goods> list) {
		this.list = list;
	}


}
