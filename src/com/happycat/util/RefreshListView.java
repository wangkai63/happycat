package com.happycat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.happucat.R;





import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.ProgressBar;
import android.widget.TextView;

//下拉刷新listview
public class RefreshListView extends ListView implements OnScrollListener,android.widget.AdapterView.OnItemClickListener{

	private static final int START_PULL_REDRESH = 0;// 下拉刷新
	private static final int START_RELEASE_REDRESH = 1;// 松开刷新
	private static final int START_REDRESHING = 2;// 正在刷新

	private View mHeaderView,mFooterView;
	private int startY = -1;// 滑动起点的y坐标
	private int mHeaderViewHeight,mFooterViewHeight;// 下拉刷新的头布局高度,上垃加载的脚布局高度

	private int mCurrentState = START_PULL_REDRESH;// 当前状态
	
	private TextView tvTitle,tvTime;
	private ImageView ivArrow;
	private ProgressBar pbProgressBar;
	
	private RotateAnimation animUP;//向上动画
	private RotateAnimation animDown;//向下动画

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeaderView();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
		initFooterView();
	}

	public RefreshListView(Context context) {
		super(context);
		initHeaderView();
		initFooterView();
	}

	// 初始化下拉头布局
	private void initHeaderView() {

		mHeaderView = View.inflate(getContext(), R.layout.refresh_header, null);
		this.addHeaderView(mHeaderView);
		tvTitle=(TextView) mHeaderView.findViewById(R.id.refresh_title);
		tvTime=(TextView) mHeaderView.findViewById(R.id.refresh_time);
		ivArrow=(ImageView) mHeaderView.findViewById(R.id.refresh_down);
		pbProgressBar=(ProgressBar) mHeaderView.findViewById(R.id.pb_progress);

		mHeaderView.measure(0, 0);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();

		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);// 隐藏头布局
		
		initArrowAnim();
		
		//设置默认时间
		tvTime.setText("最后刷新时间："+getCurrentTime());

	}

	
	
	//初始化上垃加载脚布局
	private void initFooterView(){
		
		mFooterView=View.inflate(getContext(), R.layout.loadingbottom, null);
		this.addFooterView(mFooterView);
		
		mFooterView.measure(0, 0);
		mFooterViewHeight=mFooterView.getMeasuredHeight();
		
		mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);//隐藏
		
		this.setOnScrollListener(this);
	}
	
	
	
	// 处理手指下拉刷新事件
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (startY == -1) {
				startY = (int) ev.getRawY();// 确保startY有效
			}
			
			if(mCurrentState==START_REDRESHING){
				//正在刷新时不做任何处理
				break;
			}

			int endY = (int) ev.getRawY();
			int dy = endY - startY; // 移动的偏移量

			if (dy > 0 && getFirstVisiblePosition() == 0) {
				// 只有下拉并且当前是第一个item，才允许下拉
				int padding = dy - mHeaderViewHeight;
				mHeaderView.setPadding(0, padding, 0, 0);// 设置当前padding

				if (padding > 0 && mCurrentState != START_RELEASE_REDRESH) {
					// 状态改为松开刷新
					mCurrentState = START_RELEASE_REDRESH;
					refreshstate();
				} else if (padding < 0 && mCurrentState != START_PULL_REDRESH) {
					// 改为下拉刷新状态
					mCurrentState = START_PULL_REDRESH;
					refreshstate();
				}
				super.onTouchEvent(ev);
				return true;
			}

			break;
		case MotionEvent.ACTION_UP:
			startY = -1;// 重置
			
			if(mCurrentState==START_RELEASE_REDRESH){
				mCurrentState=START_REDRESHING;//正在shuaxin
				mHeaderView.setPadding(0, 0, 0, 0);//显示
				refreshstate();
			}else if(mCurrentState==START_PULL_REDRESH){
				mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);//隐藏
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	// 刷新下拉控件的布局
	private void refreshstate() {
		switch (mCurrentState) {
		case START_PULL_REDRESH:
            tvTitle.setText("下拉刷新");
            ivArrow.setVisibility(View.VISIBLE);
			 pbProgressBar.setVisibility(View.INVISIBLE);
			 ivArrow.startAnimation(animDown);
			break;
		case START_RELEASE_REDRESH:
			 tvTitle.setText("松开刷新");
			 ivArrow.setVisibility(View.VISIBLE);
			 pbProgressBar.setVisibility(View.INVISIBLE);
			 ivArrow.startAnimation(animUP);
			break;
		case START_REDRESHING:
			 tvTitle.setText("正在刷新...");
			 ivArrow.clearAnimation();//必须先清楚动画，才能隐藏
			 ivArrow.setVisibility(View.INVISIBLE);
			 pbProgressBar.setVisibility(View.VISIBLE);
			 //调用下拉刷新接口
			 if(mListener!=null){
				 mListener.onRefresh();
				 
			 }
			break;

		default:
			break;
		}

	}
	
	
	//初始化箭头动画
	private void initArrowAnim(){
	
		//箭头向上的动画
	    animUP=new RotateAnimation(
			0, 
			-180, 
			Animation.RELATIVE_TO_SELF, 
			0.5f, 
			Animation.RELATIVE_TO_SELF, 
			0.5f);
		animUP.setDuration(200);
		animUP.setFillAfter(true);
		
		//箭头向下的动画
		animDown=new RotateAnimation(
				-180, 
				0, 
				Animation.RELATIVE_TO_SELF, 
				0.5f, 
				Animation.RELATIVE_TO_SELF, 
				0.5f);
		animDown.setDuration(200);
		animDown.setFillAfter(true);
	
	}
	
	OnRefreshListener mListener;//定义接口
	public void setOnRefreshListener(OnRefreshListener syJsActivity){
		mListener=syJsActivity;
	}
	
	//下拉刷新与上拉加载接口,
	public interface OnRefreshListener{
		public void onRefresh();
		
		public void onLoadMore();
	}
	
	//收起下拉刷新控件
	public void onRefreshComplete(boolean success){
		
		if(isLoadingMore){
			//正在加载更多
			mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
			isLoadingMore=false;
		}else{
			
			mCurrentState=START_PULL_REDRESH;
			
			tvTitle.setText("下拉刷新");
	        ivArrow.setVisibility(View.VISIBLE);
			 pbProgressBar.setVisibility(View.INVISIBLE);
			 
			 mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);//隐藏
			 //更新时间
			 if(success){
				 tvTime.setText("最后刷新时间："+getCurrentTime());
			 }
		}
		
		 
	}
	
	//获取当前时间
	public String getCurrentTime(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	private boolean isLoadingMore;
	//实现监听时间的抽象方法状态变化
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	    if(scrollState==SCROLL_STATE_IDLE || scrollState==SCROLL_STATE_FLING){
	    	if(getLastVisiblePosition()==getCount()-1 && !isLoadingMore){
	    		//滑动到最后
	    		System.out.println("到底了......");
	    		mFooterView.setPadding(0, 0, 0, 0);//显示
	    		setSelection(getCount()-1);//改变Listview的显示位置
	    		
	    		isLoadingMore=true;
	    		
	    		if(mListener!=null){
	    			mListener.onLoadMore();
	    			
	    		}
	    	}
	    }
		
	}

	//滑动变化
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	OnItemClickListener mItemClickListener;
	@Override
	public void setOnItemClickListener(
			android.widget.AdapterView.OnItemClickListener listener) {
		// TODO Auto-generated method stub
		super.setOnItemClickListener(this);
		mItemClickListener=listener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(mItemClickListener!=null){
			mItemClickListener.onItemClick(parent, view, position-getHeaderViewsCount(), id);
		}
		
	}

	

}
