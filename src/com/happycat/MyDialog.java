package com.happycat;

import java.util.List;

import com.example.happucat.R;
import com.happycat.Bean.Goods;
import com.happycat.adapter.goodsAdapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

@SuppressLint("InflateParams")
public class MyDialog extends Dialog {
Context context;
List<Goods> gList;
MerchatDataActivity merchatDataActivity;
goodsAdapter adapter;
	public MyDialog(Context context,List<Goods> gList) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.gList = gList;
		merchatDataActivity = (MerchatDataActivity) context;
		adapter = merchatDataActivity.getgAdapter();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("购物车");
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.shopcat_alterdialog, null);
		ListView catListView=(ListView) view.findViewById(R.id.dingdan_deta);		
		goodsAdapter sAdapter=new goodsAdapter(gList, context);
		sAdapter.setgList(gList);
		catListView.setAdapter(sAdapter);
		setContentView(view);
	}
	@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			adapter.notifyDataSetChanged();
		}

}
