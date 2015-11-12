package com.happycay.fragments;

import java.util.ArrayList;
import java.util.List;

import com.example.happucat.R;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Merchat_dingcanfragment extends Fragment {
	List<String>list= new ArrayList<String>();
	ListView fenlieListView;
	View meacharView;
	ArrayAdapter<String> adapter;
 @Override
public void onAttach(Activity activity) {
	// TODO Auto-generated method stub
	super.onAttach(activity);
}
 @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initData();
	}
 private void initData() {
	// TODO Auto-generated method stub
	list.add("分类一");
	list.add("分类二");
	list.add("分类三");
	list.add("分类四");
	list.add("分类五");
	list.add("分类六");
	adapter=new ArrayAdapter<String>(getActivity(),
		android.R.layout.simple_list_item_1, 
			list);
}
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	meacharView=inflater.inflate(R.layout.merchat_dingcan,null);
	  initview();
		return meacharView;
	}
private void initview() {
	// TODO Auto-generated method stub
fenlieListView=(ListView) meacharView.findViewById(R.id.dc_fenlielistview);
	fenlieListView.setAdapter(adapter);
}
}
