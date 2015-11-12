package com.happycat.adapter;

import java.util.ArrayList;
import java.util.List;
import com.example.happucat.R;

import com.happycat.MerchatDataActivity;

import com.happycat.Bean.Goods;
import com.happycat.global.GlobalContacts;
import com.happycat.util.MyApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class goodsAdapter extends BaseAdapter {
	ImageButton sub, add;// 购买商品时加减按钮
	List<Goods> goodslist;// 商品集合
	holderview mholder;// 自定义类
	Context context; // 上下文
	LayoutInflater mInflater;
	double chae;// 起送差额
	TextView buycat_jine, buycat_chae;// 购物车中的购买金额，和起送差额
	TextView geshu;// 购买商品个数（数据库中的字段初始为零）
	TextView gname;// 商品名称
	TextView gprice;// 商品价格
	TextView nume;// 销量
	ImageButton imaggood;
	List<Goods> gList;
	private int count, collection;// 区分是收藏还是取消
	String url1;
	HttpUtils httpUtils;
	private MerchatDataActivity merchatDataActivity;
	List<Integer> collectionList = new ArrayList<Integer>();;

	public goodsAdapter(List<Goods> goodslist, Context context) {
		super();
		this.goodslist = goodslist;
		this.context = context;
		mInflater = LayoutInflater.from(context);
		/**
		 * 购物车在Activity中初始化，设置了get方法，
		 */
		merchatDataActivity = (MerchatDataActivity) context;
		buycat_chae = merchatDataActivity.getBuycat_chae();
		buycat_jine = merchatDataActivity.getBuycat_jine();
		chae = merchatDataActivity.getMqsf();// 获取店家的起送价
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodslist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return goodslist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class holderview {
		// 只缓存了购物车的信息
		TextView buycat_jine, buycat_chae;
		/*ImageButton imaggood;*/

	}

	@SuppressLint("CutPasteId")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// 修正后的方法

		convertView = mInflater.inflate(R.layout.goods_listitem, null);
		mholder = new holderview();

		gname = (TextView) convertView.findViewById(R.id.goods_name);
		gprice = (TextView) convertView.findViewById(R.id.goods_price);
		nume = (TextView) convertView.findViewById(R.id.goods_nume);
		geshu = (TextView) convertView.findViewById(R.id.geshu);
		mholder.buycat_jine = (TextView) convertView
				.findViewById(R.id.buycat_jine);
		mholder.buycat_chae = (TextView) convertView
				.findViewById(R.id.buycat_chae);
		// 星星 收藏美食
		imaggood = (ImageButton) convertView
				.findViewById(R.id.goodshoucang);
		convertView.setTag(mholder);


		if (MyApplication.SP_login_status.equals("login")) {
			if (collectionList.size() > 0 && collectionList != null ) {
				if (collectionList.get(position) == 0) {
					collection = 0;
					
					imaggood.setImageResource(R.drawable.star2);
				}else {
					collection = 1;
					imaggood.setImageResource(R.drawable.star1);
				}
			}
	
		}
		
		gname.setText(goodslist.get(position).getGname());
		gprice.setText("¥" + goodslist.get(position).getPrice() + "/份");
		nume.setText("月售" + goodslist.get(position).getNumber());
		geshu.setText("" + goodslist.get(position).getGnum());
		buycat_chae.setText("还差" + chae + "元");

		sub = (ImageButton) convertView.findViewById(R.id.goods_sub);
		add = (ImageButton) convertView.findViewById(R.id.goods_add);
		// 不加此句有时也出现错位，个人估计是缓存的原因，
		TextView tv = (TextView) convertView.findViewById(R.id.geshu);
		// 添加购买商品数量是的监听事件
		add.setOnClickListener(new MyListeneradd(position, tv));
		// 减少购买商品数量是的监听事件
		sub.setOnClickListener(new MyListenersub(position, tv));
		imaggood.setOnClickListener(new click(position,imaggood));
		return convertView;
	}

	public List<Goods> getGoodslist() {
		return goodslist;
	}

	public void setGoodslist(List<Goods> goodslist) {
		this.goodslist = goodslist;
		
		
	}

	// 添加购买商品数量時的监听事件
	private class MyListeneradd implements OnClickListener {
		int mPosition;
		TextView textView;

		public MyListeneradd(int inPosition, TextView t) {
			mPosition = inPosition;
			textView = t;
		}

		@Override
		public void onClick(View v) {
			// 如果用戶沒有登录跳回到登录页面
			if (!MyApplication.SP_login_status.equals("login")) {
				Toast.makeText(context, "请登录账号", 0).show();
			} else {
				// 获取商品购买数量初始为零
				int count = goodslist.get(mPosition).getGnum();
				count++;
				// 保存购买数量
				goodslist.get(mPosition).setGnum(count);
				// 显示购买数量
				textView.setText("" + count);
				// 把数据写到集合内
				goodslist.get(mPosition).setGnum(count);
				double j = 0;
				// 计算购买商品的金额
				for (int i = 0; i < gList.size(); i++) {
					j = j + gList.get(i).getPrice() * gList.get(i).getGnum();
				}
				// 显示商品的金额
				buycat_jine.setText("" + j + "元");
				double c = chae - j;
				if (c > 0) {
					buycat_chae.setText("还差" + c + "元");

				} else {
					buycat_chae.setText("不错呦");
				}
			}

		}

	}

	// 减少购买商品数量是的监听事件
	private class MyListenersub implements OnClickListener {
		int mPosition;
		TextView textView;

		public MyListenersub(int inPosition, TextView t) {
			mPosition = inPosition;
			textView = t;
		}

		@Override
		public void onClick(View v) {

			int count = goodslist.get(mPosition).getGnum();
			if (count > 0) {
				count--;
			}
			goodslist.get(mPosition).setGnum(count);
			textView.setText("" + count);
			// 把数据写到集合内
			goodslist.get(mPosition).setGnum(count);
			double j = 0;
			for (int i = 0; i < gList.size(); i++) {
				j = j + gList.get(i).getPrice() * gList.get(i).getGnum();
			}

			buycat_jine.setText("" + j + "元");
			double c = chae - j;
			if (c > 0) {
				buycat_chae.setText("还差" + c + "元");

			} else {
				buycat_chae.setText("不错呦");
			}
		}
	}

	public void setgList(List<Goods> gList) {
		this.gList = gList;
	}

	class click implements OnClickListener {
		int mPosition;
		ImageButton imageButton;
		public click(int inPosition ,ImageButton image) {
			mPosition = inPosition;
			imageButton=image;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (collection == 0) {
				collection = 1;// 此时表示用户要收藏
				imageButton.setImageResource(R.drawable.star1);
				Toast.makeText(merchatDataActivity, "收藏", 0)
				.show();
			Log.e("222++",""+gList.get(mPosition).getId()+"---->"+mPosition);
			
			} else {
				collection = 0;// 标识用户要取消收藏
				imageButton.setImageResource(R.drawable.star2);
			Log.e("22---",""+gList.get(mPosition).getId()+"---->"+mPosition);
						
				Toast.makeText(merchatDataActivity, "取消", 0)
				.show();
			}
			getInsertFromServer(goodslist.get(mPosition).getId(), collection);

		}

		private void getInsertFromServer(int id, int collection) {
			// TODO Auto-generated method stub
			url1 = "http://" + MyApplication.getIp() + ":8080/happycat/MG";
			HttpUtils utils = new HttpUtils();
			RequestParams params = new RequestParams();

			params.addBodyParameter("key", "5");

			params.addBodyParameter("gid", goodslist.get(mPosition).getId()
					+ "");
			// 用户id尚未获取
			params.addBodyParameter("uid", MyApplication.SP_user_id+"");
			params.addBodyParameter("count", "" + collection);
			utils.send(HttpMethod.POST, url1, params,
					new RequestCallBack<String>() {
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String result = responseInfo.result;
							if (result.equals("true")) {
								/*imaggood
										.setImageResource(R.drawable.star1);*/

								/*Toast.makeText(merchatDataActivity, "收藏", 0)
										.show();*/

							}
							if (result.equals("false")
									&& !"0".equals(MyApplication.SP_user_id)) {
							/*	mholder.imaggood
										.setImageResource(R.drawable.star2);*/
								/*Toast.makeText(merchatDataActivity, "取消", 0)
										.show();*/

							}
						
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// TODO Auto-generated method stub
                           Log.e("+++wk+++++++++", "失败");
						}

					});
		}
	}

	public void setCollectionList(List<Integer> collectionList) {
		this.collectionList = collectionList;
	}

}
