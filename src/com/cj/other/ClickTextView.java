package com.cj.other;

import com.cj.smart.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClickTextView extends LinearLayout{

	private TextView tv;
	private ImageView iv;
	public ClickTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		
		tv=new TextView(context,attrs);
		iv=new ImageView(context);
		iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		iv.setBackground(getResources().getDrawable(R.drawable.clicktext_unclicking));
		addView(tv);
		addView(iv);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			iv.setBackground(getResources().getDrawable(R.drawable.clicktext_clicking));
			break;
		case MotionEvent.ACTION_UP:
			iv.setBackground(getResources().getDrawable(R.drawable.clicktext_unclicking));
			break;
		}
		super.onTouchEvent(event);
		return true;
	}
	
}
