package com.happycat.tuling;

import java.util.List;

import com.example.happucat.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter{
    private List<ListData> lists;
    private Context context;
    private RelativeLayout layout;
    
    public TextAdapter(List<ListData> lists,Context context){
    	this.lists=lists;
    	this.context=context;
    }
	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater=LayoutInflater.from(context);
		
		if (lists.get(position).getFlag()==ListData.RECEIVER) {
			layout=(RelativeLayout) 
					inflater.inflate(R.layout.xiaoxi_item_left, null);
		}
	    if(lists.get(position).getFlag()==ListData.SEND){
			layout=(RelativeLayout)
					inflater.inflate(R.layout.xiaoxi_item_right, null);
		}
	    TextView textView=(TextView) layout.findViewById(R.id.tv);
	    TextView time=(TextView) layout.findViewById(R.id.xiaoxi_time);
	    textView.setText(lists.get(position).getContent());
		return layout;
	}

}
