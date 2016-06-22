package com.cj.smart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cj.tools.Tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;
import android.provider.Settings;

public class MainActivity extends Activity {
	private static Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("test", "Main creat!");
		String[] items=new String[0];
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.activity_main);
		if(CFSApplication.getInstance().userInfo==null)CFSApplication.getInstance().userInfo=new UserInfo();
		CFSApplication.getInstance().userInfo.setProperty("userId", "aaaaaa");
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		btn1 = (Button) findViewById(R.id.button1);
		isOpen();
		/*
		 * btn1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub Log.v("test", "Button click!"); Intent i =new
		 * Intent(MainActivity.this,AActivity.class);
		 * MainActivity.this.startActivity(i); } });
		 */
	}

	@SuppressLint("NewApi") 
	void isOpen()
	{
		int alwaysFinish = Settings.Global.getInt(getContentResolver(),
				Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0);
		if (alwaysFinish == 1) 
		{
			Dialog dialog = null;
			dialog = new AlertDialog.Builder(this)
					.setMessage(
							"由于您已开启'不保留活动',导致部分功能无法正常使用.我们建议您点击左下方'设置'按钮,在'开发者选项'中关闭'不保留活动'功能.")
					.setNegativeButton("取消", null)
					.setPositiveButton("设置",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
									startActivity(intent);
								}
							}).create();
			dialog.show();
		}
	 }
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("test", "main start!");

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.v("test", "main restart!");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("test", "main pause!");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("test", "main resume!");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("test", "main destory!");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("test", "main stop!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}
		TextView tv1;
		EditText edt;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,false);
			btn1 = (Button) rootView.findViewById(R.id.button1);
			tv1=(TextView)rootView.findViewById(R.id.textView1);
			tv1.setText(String.format("%s%02d-%d", "1A", 2, 9));
			edt=(EditText)rootView.findViewById(R.id.editText1);
			edt.addTextChangedListener(new TextWatcher() {
				private String temp="";

				@Override
				public void onTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub
					Log.v("Edt",
							"onTextChanged" + ":" + s.toString() + ",start:"
									+ String.valueOf(start) + ",after:"
									+ String.valueOf(after) + ",count:"
									+ String.valueOf(count));
					Pattern pattern = Pattern.compile("(\\+|-)?[0-9]*(\\.)?[0-9]*");
					Matcher matcher = pattern.matcher(s.toString());
					if (matcher.matches()) {
						return;

					} else {
						edt.setText(temp);
						edt.setSelection(edt.getText().length());
					}

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int before, int count) {
					Log.v("Edt",
							"beforeTextChanged" + ":" + s.toString()
									+ ",start:" + String.valueOf(start)
									+ ",before:" + String.valueOf(before)
									+ ",count:" + String.valueOf(count));
					temp = s.toString();
				}

				@Override
				public void afterTextChanged(Editable s) {
					Log.v("Edt", "afterTextChanged" + ":" + s.toString());

					
					return;
				}
				
			});
			btn1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Log.v("test", "Button click!");
					Intent i = new Intent(arg0.getContext(), AActivity.class);
					arg0.getContext().startActivity(i);
				}
			});
			return rootView;

		}
	}

}
