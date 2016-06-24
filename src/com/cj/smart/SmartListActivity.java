package com.cj.smart;

import java.util.ArrayList;

import com.cj.mywidget.SmartListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SmartListActivity extends Activity {

	SmartListView smartList;
	Button btnRefresh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_list);
		//smartList=new SmartListView(this);
		smartList=(SmartListView) findViewById(R.id.smartList);
		btnRefresh=(Button)findViewById(R.id.btnRefresh);
		
		ArrayList<String> title=new ArrayList<>();
		ArrayList<String> columnWidth=new ArrayList<>();
		final ArrayList<ArrayList<String>> data=new ArrayList<>();
		for(int i=0;i<10;i++)
		{
			title.add(String.valueOf(i)+"title");
			columnWidth.add(String.valueOf(i*10+100));
		}
		for(int i=0;i<100;i++)
		{
			ArrayList<String>item =new ArrayList<String>();
			
			for(int j=0;j<10;j++)
			{
				item.add(String.format("%d-%d", i,j));
			}
			data.add(item);
		}
		
		btnRefresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ArrayList<String>item =new ArrayList<String>();
				item.add("sum");
				data.add(item);
				smartList.loadList();
				
			}
		});
		smartList.init(title, columnWidth, data);
		smartList.loadList();
	
		
		//smartList.setAdapter(null);
	}
}
