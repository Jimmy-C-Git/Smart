package com.cj.slidedownrefresh;

import com.cj.smart.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SlideDoownRefreshLayout extends LinearLayout implements OnTouchListener{

	public static final int STATUS_HIDE_HEADER=0;
	public static final int STATUS_NOT_REACH_REFRESH=1;
	public static final int STATUS_REACH_REFRESH=2;
	public static final int STATUS_REFRESHING=3;
	private int startY;
//	private int endY;
	private int firstVisibleItemFromTop=0;
	private int headerVisibleHeight=0;
	private int status=0;
	private int moveHeight=0;//正的为向下滑,负的为向上滑
	String[]statusDescription={"","下拉刷新","释放立即刷新","正在刷新"};
	private boolean loadOnce=false;
	private RelativeLayout header;
	private TextView tvDescription;
	private ListView listView;
	public SlideDoownRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		header=(RelativeLayout) LayoutInflater.from(context).inflate(R.layout.refreshheader,null);
		tvDescription=(TextView) (header.findViewById(R.id.tvRefereshStatus));
		setOrientation(VERTICAL);
		addView(header,0);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed && !loadOnce)
		{
			setHeaderVisualHeight(0);
			loadOnce=true;
			listView = (ListView) getChildAt(1);  
            listView.setOnTouchListener(this);  
		}
	}
	private void setHeaderVisualHeight(int visualHeight)
	{
		MarginLayoutParams lp=(MarginLayoutParams)header.getLayoutParams();
		lp.topMargin=Math.min(0,visualHeight-header.getHeight());
		header.setLayoutParams(lp);
	}
	@Override
	public boolean onTouch(View arg0, MotionEvent ev) {
		switch (ev.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			startY=(int)ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			
		case MotionEvent.ACTION_UP:
			moveHeight=(int)ev.getRawY()-startY;
			startY=(int)ev.getRawY();
			if(firstVisibleItemFromTop==0 && moveHeight>0)
			{
				headerVisibleHeight+=moveHeight;
				setHeaderVisualHeight(headerVisibleHeight);
				
			}
			//endY=(int)ev.getRawY();
			break;
		}
		firstVisibleItemFromTop=listView.getChildAt(0).getTop();
		return false;
	}

}
