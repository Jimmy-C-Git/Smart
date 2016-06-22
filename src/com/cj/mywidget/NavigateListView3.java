package com.cj.mywidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class NavigateListView3 extends ViewGroup{
	private Context context;
	private ListView lvNavigate,lvData;
	
	public int parentId = 0;//已经选中的parentID

	public int childId = -1; //已经选中的childID
	public int firstVisiblePosition;//childList中的能看到的第一个位置
	public int visibleItemCount;
	public int childSumCount=0;
	public NavigateListAdapter mAdapter;
	private int[]childGroupCount;

	private List<List<ItemInfo>> childList;
	private List<ItemInfo> parentList;
	private int width=0,height=0;

	public NavigateListView3(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		lvNavigate=new ListView(context);
		lvData=new ListView(context);
		lvNavigate.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		lvData.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		addView(lvNavigate);
		addView(lvData);
		
	}
	public void prepare(List<ItemInfo> parentList,List<List<ItemInfo>> childList)
	{
		this.parentList=parentList;
		this.childList=childList;
		childGroupCount=new int[childList.size()];
		for(int i=0;i<childGroupCount.length;i++)
		{
			childGroupCount[i]=childList.get(i).size();
		}
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int childCount=getChildCount();
		if(childCount ==2)
		{
			View childView=getChildAt(0);
			measureChild(childView, (int) (widthMeasureSpec*0.1), heightMeasureSpec);
			lvData.measure((int) (widthMeasureSpec*0.9), heightMeasureSpec);
		}
	}
	boolean isInitView=false;
	@Override
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		if(!isInitView)
		{
			isInitView=true;
			setTouchEvent2List();
			bindListView();
		}
		if(getChildCount()==2){
			
			lvNavigate.layout(0, 0,(r-l)*2/10, b);
			lvData.layout((r-l)*2/10,0, r,b);
			
		}
		
	}
	private void setTouchEvent2List()
	{
		lvNavigate.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				parentId=position;
				int naviPosition=0;
				for(int i=0;i<position;i++)
				{
					naviPosition+=childGroupCount[i];
				}
			    lvData.setSelection(naviPosition);  
				((SimpleAdapter)(lvNavigate.getAdapter())).notifyDataSetChanged();
			}
		});
		lvData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				childId=position;
				SimpleAdapter adapter=(SimpleAdapter)(lvData.getAdapter());
				if(adapter!=null)
					adapter.notifyDataSetChanged();
			}
		});
		lvData.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int scrollState) {
				// TODO Auto-generated method stub
				if(scrollState==OnScrollListener.SCROLL_STATE_IDLE){
					int count =0;
					int i;
					for(i=0;count<=firstVisiblePosition;i++)
						count+=childGroupCount[i];
					parentId=i-1;
					
					SimpleAdapter adapter=(SimpleAdapter)(lvNavigate.getAdapter());
					if(adapter!=null)
						adapter.notifyDataSetChanged();
					lvNavigate.setSelection(parentId);
				}
				
			}
			
			@Override
			public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				firstVisiblePosition=firstVisibleItem;
				NavigateListView3.this.visibleItemCount=visibleItemCount;
				
			}
		});
	}
	private void bindListView()
	{
		List<HashMap<String,Object>>data=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<parentList.size();i++)
		{	
				HashMap<String ,Object >item=new HashMap<>();
				item.put("DM", parentList.get(i).getProperty("XLMC"));
				data.add(item);
		}
		SimpleAdapter adapter=new SimpleAdapter(context,data,android.R.layout.simple_list_item_1,new String[]{"DM"},new int[]{android.R.id.text1})
		{
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View view= super.getView(position, convertView, parent);
				view.setLayoutParams(new AbsListView.LayoutParams(lvNavigate.getWidth(),LayoutParams.WRAP_CONTENT));
				
				if(parentId==position)
					view.setBackgroundColor(Color.TRANSPARENT);
				else view.setBackgroundColor(Color.LTGRAY);
				return view;
			}
		};
		lvNavigate.setAdapter(adapter);
		data=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<childList.size();i++)
			for(int j=0;j<childList.get(i).size();j++)
			{
				HashMap<String ,Object >item=new HashMap<>();
				item.put("DM", childList.get(i).get(j).getProperty("MC")+"/"+childList.get(i).get(j).getProperty("GG"));
				data.add(item);
			}
		lvData.setAdapter(new SimpleAdapter(context,data,android.R.layout.simple_list_item_1,new String[]{"DM"},new int[]{android.R.id.text1})
		{
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View view = super.getView(position, convertView, parent);
				
				view.setLayoutParams(new AbsListView.LayoutParams(lvData.getWidth(),LayoutParams.WRAP_CONTENT));
				if(childId==position)
					view.setBackgroundColor(Color.RED);
				else
					view.setBackgroundColor(Color.TRANSPARENT);
				return view;
				
			}
		});
	}

}
