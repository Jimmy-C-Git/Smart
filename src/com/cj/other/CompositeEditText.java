package com.cj.other;

import com.cj.smart.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CompositeEditText extends ViewGroup{
	private TextView tvLabel; 
	
	private LinearLayout ll;
	
	private EditText edtText;
	private ImageButton imbtn;
	
	private float labelFraction=1f;
	public CompositeEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		tvLabel=new TextView(context);
		edtText=new EditText(context);
		imbtn=new ImageButton(context);
		imbtn.setImageDrawable(getResources().getDrawable(R.drawable.btnpulldown));
		ll=new LinearLayout(context);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setBackground(getResources().getDrawable(R.drawable.bkg_edt));
		ll.addView(edtText);
		ll.addView(imbtn);
		addView(tvLabel);
		addView(ll);
		
		int resouceId = -1;
		TypedArray typeArray = context.obtainStyledAttributes(attrs,
	                R.styleable.EditTextWithLabel);
		 int N = typeArray.getIndexCount();
		    for (int i = 0; i < N; i++){
		    	int attr = typeArray.getIndex(i);
		    	switch (attr) {
		    	
		    	case R.styleable.EditTextWithLabel_LabelText:
		    		resouceId = typeArray.getResourceId(
		    				R.styleable.EditTextWithLabel_LabelText, 0);
		    		tvLabel.setText(resouceId > 0 ? typeArray.getResources()
		    				.getText(resouceId) : typeArray
		    				.getString(R.styleable.EditTextWithLabel_LabelText));
		    		break;
		    	case R.styleable.EditTextWithLabel_LabelWidth:
		    		resouceId = typeArray.getResourceId(
		    				R.styleable.EditTextWithLabel_LabelWidth, 0);
		    		
		    		tvLabel.setWidth((int) (resouceId > 0 ? typeArray.getResources()
		    				.getDimension(resouceId) : typeArray
		    				.getDimension(R.styleable.EditTextWithLabel_LabelWidth, 0f)));
		    		break;
		    	case R.styleable.EditTextWithLabel_LabelWeight:
		    		resouceId = typeArray.getResourceId(
		    				R.styleable.EditTextWithLabel_LabelWidth, 0);
		    		
		    		labelFraction=typeArray
		    				.getFloat(R.styleable.EditTextWithLabel_LabelWidth, 1f);
		    		break;
		    	}
		    }
		    typeArray.recycle();
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//int measuredWidth=getMeasuredWidth();
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int llh=ll.getMeasuredHeight();
		int llw=ll.getMeasuredWidth();
		int width=r-l;
		int height=b-t;
		if(changed)
		{
				tvLabel.layout(l, t, l+(int) (width*labelFraction), b);
				ll.layout(l+(int) (width*labelFraction), t, r,b);
		}
		
	}

}
