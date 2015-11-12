package com.happycat.adapter;

import java.util.List;

import com.example.happucat.R;
import com.happycat.AddressActivity;
import com.happycat.Bean.address;
import com.happycat.adapter.PingjiaAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class addressAdapter extends BaseAdapter {
	List<address> list;
	Context context;
	LayoutInflater miInflater;
	ViewHolder mHolder;

	public addressAdapter(List<address> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		miInflater = LayoutInflater.from(context);
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
		TextView province, city, country, detail, phone;
		Button button;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = miInflater.inflate(R.layout.address_item, null);
			mHolder.province = (TextView) convertView
					.findViewById(R.id.province);
			mHolder.city = (TextView) convertView.findViewById(R.id.city);
			mHolder.country = (TextView) convertView.findViewById(R.id.country);
			mHolder.phone = (TextView) convertView.findViewById(R.id.phone);
			mHolder.detail = (TextView) convertView.findViewById(R.id.detail);
			mHolder.button = (Button) convertView.findViewById(R.id.queren);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.province.setText(list.get(position).getAprovince() + "省");
		mHolder.city.setText(list.get(position).getAcity() + "市");
		mHolder.country.setText(list.get(position).getAcountry());
		mHolder.phone.setText("联系电话： " + list.get(position).getAphone());
		mHolder.detail.setText(list.get(position).getAdetail());
		mHolder.button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				String address =list.get(position).getAprovince()+"省"
						+list.get(position).getAcity()+"市"
						+list.get(position).getAcountry()
						+list.get(position).getAdetail()+"\n"
						+"联系电话："+list.get(position).getAphone();
				intent.putExtra("address", address);
				AddressActivity shouhuoAddressActivity = (AddressActivity) context;
				shouhuoAddressActivity.setResult(Activity.RESULT_OK, intent);
				shouhuoAddressActivity.finish();

			}
		});

		return convertView;
	}

	public List<address> getList() {
		return list;
	}

	public void setList(List<address> list) {
		this.list = list;
	}

}
