package com.cj.slidedownrefresh;

import com.cj.smart.R;
import com.cj.smart.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SlideDownRefreshActivity extends Activity {
	 String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" ,"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" ,"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_down_refresh);
		ListView listView = (ListView) findViewById(R.id.lv);
		listView.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
	}
}
