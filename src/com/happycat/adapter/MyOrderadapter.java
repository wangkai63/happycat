package com.happycat.adapter;

import java.util.List;
import com.example.happucat.R;
import com.happycat.Bean.MyOrderBean;
import com.happycat.util.MyApplication;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOrderadapter extends BaseAdapter {
	List<MyOrderBean> list;
	Context context;
	LayoutInflater mInflater;
	holderView mholder;
	String imagurl = " http://" + MyApplication.getIp() + ":8080/happycat/img/";

	public MyOrderadapter(List<MyOrderBean> list, Context context) {
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
		ImageView gimg;
		TextView mname, gname, istatus, sumprice, number, iphone, identifier,
				fs;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.dingdan_all_listviewitem,
					null);
			mholder = new holderView();
			mholder.gimg = (ImageView) convertView.findViewById(R.id.dd_gimg);

			mholder.mname = (TextView) convertView.findViewById(R.id.dd_mname);
			mholder.fs = (TextView) convertView.findViewById(R.id.dd_fs);
			mholder.gname = (TextView) convertView.findViewById(R.id.dd_gname);
			mholder.identifier = (TextView) convertView
					.findViewById(R.id.dd_identifier);
			mholder.iphone = (TextView) convertView.findViewById(R.id.dd_phone);
			mholder.number = (TextView) convertView
					.findViewById(R.id.dd_number);
			mholder.sumprice = (TextView) convertView
					.findViewById(R.id.dd_sumprice);
			mholder.istatus = (TextView) convertView
					.findViewById(R.id.dd_istatus);
			convertView.setTag(mholder);

		} else {
			mholder = (holderView) convertView.getTag();
		}

		mholder.mname.setText("订单名称：" + list.get(position).getMname());
		mholder.gname.setText("购买商品： " + list.get(position).getGname());
		mholder.identifier
				.setText("订单号： " + list.get(position).getIdentifier());
		mholder.iphone.setText("下单电话： " + list.get(position).getiPhone());
		mholder.number.setText("x " + list.get(position).getNum());
		mholder.fs.setText("支付方式：  " + list.get(position).getType());
		mholder.sumprice.setText("￥" + list.get(position).getPrice());
		mholder.istatus.setText(list.get(position).getIstatus());

		MyApplication.bitmapUtils.display(mholder.gimg,
				imagurl + list.get(position).getGimg());
		return convertView;
	}

	public List<MyOrderBean> getList() {
		return list;
	}

	public void setList(List<MyOrderBean> list) {
		this.list = list;

	}

}
