package com.cj.threedimslidinglayout;

import com.cj.smart.R;
import com.cj.smart.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ThreeDSlidingLayoutActivity extends Activity {

	
	 private ThreeDSlidingLayout slidingLayout;  
	  
	    /** 
	     * menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局。 
	     */  
	    private Button menuButton;  
	  
	    /** 
	     * 放在content布局中的ListView。 
	     */  
	    private ListView contentListView;  
	  
	    /** 
	     * 作用于contentListView的适配器。 
	     */  
	    private ArrayAdapter<String> contentListAdapter;  
	  
	    /** 
	     * 用于填充contentListAdapter的数据源。 
	     */  
	    private String[] contentItems = { "Content Item 1", "Content Item 2", "Content Item 3",  
	            "Content Item 4", "Content Item 5", "Content Item 6", "Content Item 7",  
	            "Content Item 8", "Content Item 9", "Content Item 10", "Content Item 11",  
	            "Content Item 12", "Content Item 13", "Content Item 14", "Content Item 15",  
	            "Content Item 16" };  
	  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	    	super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_three_dsliding_layout); 
	        slidingLayout = (ThreeDSlidingLayout) findViewById(R.id.slidingLayout);  
	        menuButton = (Button) findViewById(R.id.menuButton);  
	        contentListView = (ListView) findViewById(R.id.contentList);  
	        contentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  
	                contentItems);  
	        contentListView.setAdapter(contentListAdapter);  
	        // 将监听滑动事件绑定在contentListView上  
	        slidingLayout.setScrollEvent(contentListView);  
	        menuButton.setOnClickListener(new OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                if (slidingLayout.isLeftLayoutVisible()) {  
	                    slidingLayout.scrollToRightLayout();  
	                } else {  
	                    slidingLayout.scrollToLeftLayout();  
	                }  
	            }  
	        });  
	        contentListView.setOnItemClickListener(new OnItemClickListener() {  
	            @Override  
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  
	                String text = contentItems[position];  
	                Toast.makeText(ThreeDSlidingLayoutActivity.this, text, Toast.LENGTH_SHORT).show();  
	            }  
	        });  
	    }  
}
