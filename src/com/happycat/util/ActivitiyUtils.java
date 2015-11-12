package com.happycat.util;



import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ActivitiyUtils {
	
	public static void setActionBarLayout(ActionBar actionBar,Context context,int layoutId) {
		if( null != actionBar ){
			actionBar.setDisplayShowHomeEnabled( false );
			actionBar.setDisplayShowCustomEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);

			LayoutInflater inflator = LayoutInflater.from(context);
			View v = inflator.inflate(layoutId, null);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			actionBar.setCustomView(v,layout);
		}
	}
	

	
	public static void finish(Activity activity){
		activity.finish();
		//activity.overridePendingTransition(R.anim.anim_left_to_right_in, R.anim.anim_right_to_left_out);
	}

}
