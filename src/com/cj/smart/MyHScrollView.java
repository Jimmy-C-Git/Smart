package com.cj.smart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class MyHScrollView extends  HorizontalScrollView{
//	
	private ScrollViewListener scrollViewListener = null;
	
	public MyHScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public void setOnScrollViewListener(ScrollViewListener scrollViewListener) {

		this.scrollViewListener = scrollViewListener;

		}

	@Override

	protected void onScrollChanged(int x, int y, int oldx, int oldy) {

	//super.onScrollChanged(x, y, oldx, oldy);

	if (scrollViewListener != null) {

	scrollViewListener.onScrollChanged( x, y, oldx, oldy);

		}

	}

}
