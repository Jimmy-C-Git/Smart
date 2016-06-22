package com.cj.smart;

import android.content.Context;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyItemContainer extends LinearLayout{

	public MyItemContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return true;
	}
	MyHScrollView myHScrollView;
	int startx=-1;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			myHScrollView=(MyHScrollView) this.findViewById(R.id.horizontalScrollView1);
			startx=(int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			myHScrollView.smoothScrollTo((int)event.getX(),(int) event.getY());
			break;
		case MotionEvent.ACTION_UP:
			return false;
			
		}
		return false;
		
	}
}
