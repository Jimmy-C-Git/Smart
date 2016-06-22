package com.cj.other;

import com.cj.smart.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditTextWithLabel extends LinearLayout{

	private TextView tvLabel; 
	private EditText edtLabel;
	public EditTextWithLabel(Context context, AttributeSet attrs) {
		super(context, attrs);
		int resouceId = -1;
		TypedArray typeArray = context.obtainStyledAttributes(attrs,
	                R.styleable.EditTextWithLabel);
		tvLabel=new TextView(context);
		edtLabel=new EditText(context){
			@Override
			protected void onDraw(Canvas canvas) {
				canvas.scale(0.8f, 0.8f, 0f, getHeight()/2);
				super.onDraw(canvas);
			}
		};
		edtLabel.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	    edtLabel.setBackground(getResources().getDrawable(R.drawable.bkg_edt));
	     
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
	    	}
	    }
	    typeArray.recycle();
	    addView(tvLabel);
	    addView(edtLabel);
	}
	public void setEdtText(String edtText)
	{
		if(edtText!=null)
			edtLabel.setText(edtText);
	}
	public String getEdtText()
	{
		return edtLabel.getText().toString();
	}

}
