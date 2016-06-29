package com.cj.smart;

import java.util.Properties;

import com.cj.mywidget.NavigateListActivity;
import com.cj.mywidget.SmartListActivity;
import com.cj.other.AsyncTestActivity;
import com.cj.other.SizeActivity;
import com.cj.photo.PhotoWallActivity;
import com.cj.threedimslidinglayout.ThreeDSlidingLayoutActivity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class AActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("test", "A creat!");
		setContentView(R.layout.activity_a);
		Log.v("test",CFSApplication.getInstance().userInfo.getProperty("userId"));
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new MyView()).commit();
		}
		new MyView().setBtnView();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("test", "A start!");

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.v("test", "A restart!");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("test", "A pause!");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("test", "A resume!");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("test", "A destory!");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("test", "A stop!");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.a, menu);
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

	
	public static class Model extends Properties
	{
		//负责从网络获取数据，将数据存取到application中
		
		
		
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class MyView extends Fragment {

		public MyView() {
		}
		RadioButton rabtn ;
		CheckBox chk;
		Spinner sp;
		TextView tv1;
		AActivity a;
		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			a=(AActivity)activity;
			
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_a, container,
					false);
			rabtn =(RadioButton)rootView.findViewById(R.id.radioButton1);
			chk=(CheckBox)rootView.findViewById(R.id.checkBox1);;
			sp=(Spinner)rootView.findViewById(R.id.spinner1);
			Button btncargo=(Button) rootView.findViewById(R.id.button1);
			btncargo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent =new Intent(a,ExpContCargoActivity.class);
					startActivity(intent);
				}
			});
			Button btnNavigateListView=(Button)rootView.findViewById(R.id.btnNavigateList);
			btnNavigateListView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent =new Intent(a,NavigateListActivity.class);
					startActivity(intent);
				}
			});
			Button btnAsyncTask=(Button)rootView.findViewById(R.id.btnAsyncTask);
			btnAsyncTask.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent i=new Intent(a,AsyncTestActivity.class);
					startActivity(i);
					
				}
			});
			Button btnMetrics=(Button)rootView.findViewById(R.id.btnMetrics);
			btnMetrics.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent =new Intent(a,SizeActivity.class);
					startActivity(intent);
				}
			});
			Button btnSmartList=(Button)rootView.findViewById(R.id.btnSmartList);
			btnSmartList.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent =new Intent(a,SmartListActivity.class);
					startActivity(intent);
				}
			});
			Button btnPhotoWall=(Button)rootView.findViewById(R.id.btnPhotoWall);
			btnPhotoWall.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent =new Intent(a,PhotoWallActivity.class);
					startActivity(intent);
				}
			});
			Button btnThreeDSlidingLayout=(Button)rootView.findViewById(R.id.btnThreeDSlidingLayout);
			btnThreeDSlidingLayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent =new Intent(a,ThreeDSlidingLayoutActivity.class);
					startActivity(intent);
				}
			});
			tv1=(TextView)rootView.findViewById(R.id.textView1_a);
			ArrayAdapter<String> clothAdapter = new ArrayAdapter<>(rootView.getContext(),
					android.R.layout.simple_spinner_item,new String[] {"1","2"});
			sp.setAdapter(clothAdapter);
			sp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// 
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						Intent intent=new Intent(a,DrawActivity.class);
						a.startActivityForResult(intent, 1);
					}else 
					{
						
						tv1.setText(sp.getSelectedItem().toString());
						Intent i=new Intent(a,ListViewActivity.class);
						a.startActivityForResult(i, 2);
					}
					
					
				}
			});
			
			return rootView;
		}
		public void setBtnView()
		{
			
		}
	}

}
