package com.cj.smart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListViewActivity extends Activity {
	
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		initView();
		setViewEvent();
		
	}
	void initView()
	{
		List<String> data=new ArrayList<String>();
		data.add("1");
		data.add("12");
		data.add("13");
		data.add("14");
		data.add("15");
		data.add("16");
		data.add("17");
		data.add("18");
		data.add("19");
		data.add("110");
		data.add("111");
		data.add("112");
		data.add("113");
		data.add("114");data.add("115");
		data.add("116");
		data.add("117");
		data.add("118");data.add("119");
		data.add("120");
		data.add("121");
		data.add("122");data.add("123");
		data.add("124");
		data.add("125");
		lv=(ListView)findViewById(R.id.lv_listView);
		lv.setAdapter(new MyAdapter(data));
	}
	void setViewEvent()
	{
		
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
	}
	class MyAdapter extends BaseAdapter
	{
		List<String> data;
		
		public MyAdapter(List<String> data2) {
			data=data2;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View contentView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if(contentView==null)
			{
				EditText edt=new EditText(ListViewActivity.this);
				edt.setText(data.get(position));
				return edt;
			}else 
			((EditText )contentView).setText(data.get(position));
				return contentView;
			
		}
		
	}
}
