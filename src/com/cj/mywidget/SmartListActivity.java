package com.cj.mywidget;

import java.util.ArrayList;

import com.cj.smart.R;
import com.cj.smart.R.id;
import com.cj.smart.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SmartListActivity extends Activity {

	SmartListView smartList;
	SmartListViewExFromViewGroup smartListViewExFromViewGroup;
	Button btnRefresh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_list);
		//smartList=new SmartListView(this);
		//smartList=(SmartListView) findViewById(R.id.smartList);
		smartListViewExFromViewGroup=(SmartListViewExFromViewGroup)findViewById(R.id.smartListViewExFromViewGroup);
		
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
				//smartList.loadList();
				smartListViewExFromViewGroup.loadList();
			}
		});
		/*smartList.init(title, columnWidth, data);
		smartList.loadList();*/
		smartListViewExFromViewGroup.init(title, columnWidth, data);
		smartListViewExFromViewGroup.loadList();
		
		//smartList.setAdapter(null);
	}
}
