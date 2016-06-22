package com.cj.mywidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.SimpleAdapter;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NavigateListView2 extends View {
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
	public NavigateListView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		lvNavigate=new ListView(context){
			@Override
			public boolean onTouchEvent(MotionEvent ev) {
				// TODO Auto-generated method stub
				return super.onTouchEvent(ev);
			}
		};
		
		lvNavigate.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		lvData=new ListView(context);
		lvData.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		
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
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getX()<width*2/7)
		{
			lvNavigate.dispatchTouchEvent(event);
		}
		
		else 
			lvData.dispatchTouchEvent(event);
		
		return super.dispatchTouchEvent(event);
		//return super.dispatchTouchEvent(event);
	}
	float startx=0,starty=0,endx=0,endy=0;
	int touch2Which=0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			startx=event.getX();
			starty=event.getY();
			if(event.getX()<width*2/7)
				touch2Which=0;
			else 
				touch2Which=1;
			break;
		case MotionEvent.ACTION_MOVE:
			if(touch2Which==0)
				lvNavigate.scrollBy(0, (int)(event.getY()-starty));
			else 
				lvData.scrollBy(0, (int)(event.getY()-starty));
			startx=event.getX();
			starty=event.getY();
			break;
		case MotionEvent.ACTION_UP:
			if(touch2Which==0)
				lvNavigate.scrollBy(0, (int)(event.getY()-starty));
			else 
				lvData.scrollBy(0, (int)(event.getY()-starty));
			endx=event.getX();
			endy=event.getY();
			
			break;
		}
		invalidate();
		return true;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		lvNavigate.measure(widthMeasureSpec*2/7, heightMeasureSpec);
		lvData.measure(widthMeasureSpec*5/7, heightMeasureSpec);
	}
	private boolean isInitView=false;
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
		width=getWidth();
		height=getHeight();
		lvNavigate.layout(left, top, left+width*2/7, bottom);
		lvData.layout(left+width*2/7, top, right, bottom);
		if(!isInitView)
		{
			isInitView=true;
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
			lvNavigate.setOnScrollListener(new OnScrollListener() {
				
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
					} 
				}
				
				@Override
				public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
					firstVisiblePosition=firstVisibleItem;
					NavigateListView2.this.visibleItemCount=visibleItemCount;
					
				}
			});
			bindData2ListView();
		}
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		lvNavigate.draw(canvas);
		canvas.translate(width*2/7, 0);
		lvData.draw(canvas);
		
	}
	private void bindData2ListView()
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
				if(childId==position)
					view.setBackgroundColor(Color.RED);
				else
					view.setBackgroundColor(Color.TRANSPARENT);
				return view;
				
			}
		});
	}
	
}
