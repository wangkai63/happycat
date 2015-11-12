package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.PingjiaBean;
import com.happycat.util.MyApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class PingjiaAdapter extends BaseAdapter {
 List<PingjiaBean> list;
 Context context;
 LayoutInflater miInflater;
 RadioButton radioButton;
 ViewHolder mHolder;
 String imagurl="http://" + MyApplication.getIp()
			+ ":8080/happycat/upimage/";
 
	public PingjiaAdapter(List<PingjiaBean> list, Context context) {
	super();
	this.list = list;
	this.context = context;
	miInflater=LayoutInflater.from(context);
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

	class ViewHolder {
		TextView textView1, textView2, textView3;
		ImageView imageView;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// 第一次进入应用，初始化listview每一行的布局
			convertView = miInflater.inflate(R.layout.pingjiaitem, null);
			mHolder = new ViewHolder();
			mHolder.textView1 = (TextView) convertView
					.findViewById(R.id.pjuname);
			mHolder.textView2 = (TextView) convertView
					.findViewById(R.id.pjtime);
			mHolder.textView3 = (TextView) convertView
					.findViewById(R.id.pjneirong);
			mHolder.imageView = (ImageView) convertView
					.findViewById(R.id.pjimg);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.textView1.setText(list.get(position).getUname());
		mHolder.textView2.setText(list.get(position).getTtime());
		mHolder.textView3.setText(list.get(position).getTtext());

	

		MyApplication.bitmapUtils.display(mHolder.imageView, imagurl
				+ list.get(position).getCimg());

		return convertView;
	}

	public List<PingjiaBean> getList() {
		return list;
	}

	public void setList(List<PingjiaBean> list) {
		this.list = list;
	}

}
