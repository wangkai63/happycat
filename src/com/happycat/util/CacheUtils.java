package com.happycat.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

//通过sharePreference存储数据
public class CacheUtils {
	private static final String SP_name = "hy";
	private static SharedPreferences sp;

	private static SharedPreferences getPreferences(Context context) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_name, Context.MODE_PRIVATE);
		}
		return sp;
	}

	// 获取Boolean类型的缓存数据，没有的话默认值是false
	public static boolean getBoolean(Context context, String key) {
		SharedPreferences sp = getPreferences(context);
		return sp.getBoolean(key, false);
	}

	// 没有时的默认值
	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		SharedPreferences sp = getPreferences(context);
		return sp.getBoolean(key, defValue);
	}
	//设置boolean类型的缓存
	public static void setBoolean(Context context
			,String key,boolean value){
		SharedPreferences sp=getPreferences(context);
		Editor editor=sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}
