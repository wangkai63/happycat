package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.CollectBean;

import com.happycat.util.MyApplication;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopsCollectionAdapter extends BaseAdapter {
	
	private List<CollectBean> list;
	private LayoutInflater mInflater;
	Context context;
	HolderView mholder;
	String imagurl = " http://" + MyApplication.getIp() + ":8080/happycat/img/";
	
	public ShopsCollectionAdapter(List<CollectBean> list, Context context) {
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
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	class HolderView {
		ImageView gimg,mimg;
		TextView address, gname,ntime,mname;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView=mInflater.inflate(R.layout.goods_collect_fragment_item, null);
			mholder = new HolderView();
			
			mholder.mimg = (ImageView) convertView.findViewById(R.id.goods_collect_item_pic);
			mholder.address = (TextView) convertView.findViewById(R.id.goods_collect_item_price);
		
			mholder.ntime = (TextView) convertView.findViewById(R.id.goods_collect_item_time);
			mholder.mname = (TextView) convertView.findViewById(R.id.goods_collect_item_title);
			convertView.setTag(mholder);
		}else {
			mholder = (HolderView) convertView.getTag();
		}
		mholder.mname.setText("店家名称："+list.get(position).getMname());
		
		mholder.ntime.setText(list.get(position).getNtime()); 
		mholder.address.setText(list.get(position).getMaddress());
		
		MyApplication.bitmapUtils.display(mholder.mimg,
				imagurl + list.get(position).getMimg());
		return convertView;
	}
	
	public List<CollectBean> getList() {
		return list;
	}

	public void setList(List<CollectBean> list) {
		this.list = list;

	}


}
