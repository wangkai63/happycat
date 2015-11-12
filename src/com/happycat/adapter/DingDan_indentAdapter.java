package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.example.happucat.R.string;
import com.happycat.Bean.Goods;
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

public class DingDan_indentAdapter extends BaseAdapter {
 List<Goods> list;
 Context context;
 LayoutInflater miInflater;
 
 ViewHolder mHolder;
 
	public DingDan_indentAdapter(List<Goods> list, Context context) {
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
		TextView goodsname, goodsnumer, goodsmoney;

	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// 第一次进入应用，初始化listview每一行的布局
			convertView = miInflater.inflate(R.layout.dingdan_data_item, null);
			mHolder = new ViewHolder();
			mHolder.goodsname= (TextView) convertView
					.findViewById(R.id.dingdan_goodsname);
			mHolder.goodsnumer = (TextView) convertView
					.findViewById(R.id.dingdan_goodsnumer);
			mHolder.goodsmoney = (TextView) convertView
					.findViewById(R.id.diangdan_money);
			
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		 
		mHolder.goodsname.setText(list.get(position).getGname());
		mHolder.goodsnumer.setText("X "+list.get(position).getGnum());
		mHolder.goodsmoney.setText(
			"¥ "+(list.get(position).getPrice()*list.get(position).getGnum())+"元");

		return convertView;
	}

	public List<Goods> getList() {
		return list;
	}

	public void setList(List<Goods> list) {
		this.list = list;
	}

}
