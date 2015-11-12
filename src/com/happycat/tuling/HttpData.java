package com.happycat.tuling;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String> {
	private HttpClient mhttpClient;// 请求连接
	private HttpGet mHttpGet;// get请求方法
	private String url;// 请求网址
	private HttpResponse mHttpResponse;// 获取返回值
	private HttpEntity mHttpEntity;// 创建实体
	private InputStream in;
	private HttpGetDataListener listener;

	public HttpData(String url,HttpGetDataListener listener) {
		this.url = url;
		this.listener=listener;
	}

	@Override
	protected String doInBackground(String... arg0) {
		try {
			mhttpClient = new DefaultHttpClient();
			mHttpGet = new HttpGet(url);
			// 通过客户端连接
			mHttpResponse = mhttpClient.execute(mHttpGet);
			// 获取实体
			mHttpEntity = mHttpResponse.getEntity();
			in = mHttpEntity.getContent();
			// 缓冲区进行读取
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
            
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		//通过HttpGetDataListener接口返回数据
		listener.getDataUrl(result);
		super.onPostExecute(result);
	}
}
