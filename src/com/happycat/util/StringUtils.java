package com.happycat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.util.Config;
import android.widget.EditText;
import android.widget.Toast;

public class StringUtils {
	public static boolean loginCheck(Context context, EditText et_phone,
			EditText et_password) {// 验证手机号
		boolean isPass = true;
		if(!isPhone(context, et_phone)){
			isPass=false;
		}else if(!isPassword(context, et_password)){
			isPass=false;
		}
		return isPass;
	}
	public static boolean fastLoginCheck(Context context, EditText et_phone,
			EditText et_code) {// 验证手机号
		boolean isPass = true;
		if(!isPhone(context, et_phone)){
			isPass=false;
		}else if(!isVerCode(context, et_code)){
			isPass=false;
		}
		return isPass;
	}
	public static boolean isPhone(Context context, EditText et_phone) {
		boolean isPass=true;
		String phone = et_phone.getText().toString();
		if (phone.length() == 0) {
			showToast(context, "手机号码不能为空");
			isPass = false;
		} else if (!phone.matches("^[1][3,5,7,8][0-9]{9}$")) {
			showToast(context, "手机号码格式错误");
			isPass = false;
		}
		return isPass;
	}
	public static boolean isPassword(Context context, EditText et_password) {
		boolean isPass = true;
		String password = et_password.getText().toString();
		if (password.length() == 0) {
			showToast(context, "密码不能为空");
			isPass = false;
		} else if (password.length() < 6 || password.length() > 16) {
			showToast(context, "密码长度为6-16位");
			isPass = false;
		}
		return isPass;
	}
	public static boolean isVerCode(Context context, EditText et_code) {
		boolean isPass = true;
		String code = et_code.getText().toString();
		if (code.length() == 0) {
			showToast(context, "验证码不能为空");
			isPass = false;
		} else if (code.length() != 4) {
			showToast(context, "验证码为4位数字");
			isPass = false;
		}
		return isPass;
	}

	// 消息提示
	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static String MD5(String string) { //字符串MD5加密
		byte[] hash;

		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString();
	}
}
