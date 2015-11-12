package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.goodsclassify;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Dc_fenleiAdapter extends BaseAdapter {
	List<goodsclassify> list;
	Context context;
	LayoutInflater mInflater;
	holderView mhoView;
	View oldView = null;

	public Dc_fenleiAdapter(List<goodsclassify> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public List<goodsclassify> getList() {
		return list;
	}

	public void setList(List<goodsclassify> list) {
		this.list = list;
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
		TextView name;

		public void setBackgroundDrawable(Drawable drawable) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.waimai_merchat_dingcan_fengleil_listitem, null);
			mhoView = new holderView();
			mhoView.name = (TextView) convertView.findViewById(R.id.dc_fenlie);
			convertView.setTag(mhoView);
		} else {
			mhoView = (holderView) convertView.getTag();
		}
		mhoView.name.setText(list.get(position).getGcname());
		
		
		return convertView;
		/*
		 * convertView.setTag(position); convertView.setOnClickListener(new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub if(mhoView == null){ //第一次点击 oView = v;
		 * v.setBackgroundDrawable(context
		 * .getResources().getDrawable(R.drawable.bt)); }else{ //非第一次点击
		 * //把上一次点击的 变化
		 * mhoView.setBackgroundDrawable(context.getResources().getDrawable
		 * (R.drawable.listviewselector));
		 * v.setBackgroundDrawable(context.getResources
		 * ().getDrawable(R.drawable.bt1)); //保存oldView oView = v; }
		 * 
		 * } });
		 * 
		 * if(mhoView != null && (position == (Integer)oView.getTag())){// 为点击
		 * item
		 * convertView.setBackgroundDrawable(context.getResources().getDrawable
		 * (R.drawable.bt1)); }else{
		 * convertView.setBackgroundDrawable(context.getResources
		 * ().getDrawable(R.drawable.bt)); }
		 */
	}

}
