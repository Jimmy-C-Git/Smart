package com.cj.other;

import com.cj.smart.R;
import com.cj.smart.R.id;
import com.cj.smart.R.layout;
import com.cj.tools.Tools;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class SizeActivity extends Activity {
	EditTextWithLabel edtlWidth,edtlHeight,edtlWidthDP,edtlHeightDP,edtlWidthCM,edtlHeightCM;
	
	EditText edtTest;
	ClickTextView clickText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_size);
		
		initView();
		WindowManager wm1 = this.getWindowManager();
		int width1 = wm1.getDefaultDisplay().getWidth();
		int height1 = wm1.getDefaultDisplay().getHeight();
		final DisplayMetrics dm = getResources().getDisplayMetrics();
		final float density1 = dm.density;
		final int width3 = dm.widthPixels;
		final int height3 = dm.heightPixels;
		edtlHeight.setEdtText(String.valueOf(height3));
		edtlWidth.setEdtText(String .valueOf(width3));
		edtlHeightDP.setEdtText(String.valueOf(height3/density1));
		edtlWidthDP.setEdtText(String.valueOf(width3/density1));
		edtlHeightCM.setEdtText(String.valueOf(height3/dm.ydpi)+"(inch)");
		edtlWidthCM.setEdtText(String.valueOf(width3/dm.xdpi+"(inch)"));
		edtTest.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				if(arg0.length()>0)
				{
					edtlHeightCM.setEdtText(String.valueOf(edtTest.getHeight()/dm.ydpi*2.45));
					edtlWidthCM.setEdtText(String.valueOf(edtTest.getWidth()/dm.xdpi*2.45));
					edtlHeight.setEdtText(String.valueOf(edtTest.getHeight()));
					edtlWidth.setEdtText(String .valueOf(edtTest.getWidth()));
					edtlHeightDP.setEdtText(String.valueOf(edtTest.getHeight()/density1));
					edtlWidthDP.setEdtText(String.valueOf(edtTest.getWidth()/density1));
				}else if(arg0.length()==0)
				{

					edtlHeightCM.setEdtText(String.valueOf(height3/dm.ydpi*2.45));
					edtlWidthCM.setEdtText(String.valueOf(width3/dm.xdpi*2.45));
					edtlHeight.setEdtText(String.valueOf(height3));
					edtlWidth.setEdtText(String .valueOf(width3));
					edtlHeightDP.setEdtText(String.valueOf(height3/density1));
					edtlWidthDP.setEdtText(String.valueOf(width3/density1));
				}
			}
		});
		clickText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Tools.showMsg2DiaLog(SizeActivity.this, "");
			}
		});
	}
	private void initView()
	{
		
		clickText=(ClickTextView)findViewById(R.id.edtClickTest);
		edtlHeight=(EditTextWithLabel) findViewById(R.id.edtlHeight);
		edtlHeightDP=(EditTextWithLabel) findViewById(R.id.edtlHeightDP);
		edtlWidth=(EditTextWithLabel) findViewById(R.id.edtlWidth);
		edtlWidthDP=(EditTextWithLabel) findViewById(R.id.edtlWidthDP);
		edtlWidthCM=(EditTextWithLabel) findViewById(R.id.edtlWidthCM);
		edtlHeightCM=(EditTextWithLabel) findViewById(R.id.edtlHeightCM);
		edtTest=(EditText)findViewById(R.id.edtTest);
	}
	
}
