package com.cj.mywidget;

import java.util.ArrayList;
import java.util.HashMap;

import com.cj.tools.Tools;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class SmartListView extends ListView implements OnScrollListener{
	
	private ArrayList<String> mTitle;
	private ArrayList<String> mColumnWidth;
	private HashMap<Integer,String> mFooter; 
	private ArrayList<ArrayList<String>> mData;
	private int itemCount=0;
	private int columnCount=0;
	public SmartListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnScrollListener(this);
	}
	public SmartListView(Context context) {
		super(context);
		this.setOnScrollListener(this);
	}
	public void init(ArrayList<String> title,ArrayList<String> columnWidth,ArrayList<ArrayList<String>> data)
	{
		mTitle=title;
		mColumnWidth=columnWidth;
		mData=data;
	}
	public void init(ArrayList<String> title,ArrayList<String> columnWidth,ArrayList<ArrayList<String>> data,int type)
	{
		mTitle=title;
		mColumnWidth=columnWidth;
		mData=data;
	}
	public View getItemView()
	{
		LinearLayout ll=new LinearLayout(getContext());
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setMinimumHeight(40);
		for(int i=0;i<columnCount;i++)
		{
			
			TextView tv=new TextView(getContext());
			if(i<mColumnWidth.size())
			{
				int width=Tools.str2int(mColumnWidth.get(i));
				tv.setLayoutParams(new LayoutParams(width==0?0:width, LayoutParams.WRAP_CONTENT));
			}else 
				tv.setLayoutParams(new LayoutParams(50, LayoutParams.WRAP_CONTENT));
				
			
			ll.addView(tv);
		}
		return ll;
	}
	
	public void loadList()
	{
		
		if(mData==null)return ;
			itemCount=mData.size();
		if(mData.size()>0)
			columnCount=mData.get(0).size();
		if(mTitle!=null&&getHeaderViewsCount()==0)
		{
			LinearLayout ll=(LinearLayout) getItemView();
			
			int childCount =ll.getChildCount();
			for(int i=0;i<childCount;i++)
			{
				if(i<mTitle.size())
				{
					View view=ll.getChildAt(i);
					((TextView)view).setText(mTitle.get(i));	
				}
					
			}
			this.addHeaderView(ll);
		}
			
		setAdapter(new MyListAdapter());
		//setSelection(lastListPosition);
		//scrollTo(scrolledX, scrolledY);
		setSelectionFromTop(firstViewPosition,firstViewTop);
	}
	int firstViewPosition=0;
	int firstViewTop=0;
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.dispatchDraw(canvas);
		
	}
	
	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		//setSelection(lastListPosition);
		//
	}
	
	public class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			/*int count =totalItemCount;
			if(mTitle!=null)
				count += 1;
			if(mFooter!=null)
				count+=1;*/
			return itemCount;
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
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout ll;
			
			if(convertView==null)
			{
				convertView=getItemView();
			}
			ll=(LinearLayout)convertView;
			int childCount =ll.getChildCount();
			for(int i=0;i<childCount;i++)
			{
				View view=ll.getChildAt(i);
				if(i<mData.get(position).size())
					((TextView)view).setText(mData.get(position).get(i));
				else 
					((TextView)view).setText("");
			}
				

			return convertView;
		}
		
		
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
/*		lastListPosition=firstVisibleItem;
		View view1 =getChildAt(firstVisibleItem);
		if(view1!=null)
		inVisibleHeight=view1.getMeasuredHeight();*/
		
		
		
		
	}

	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		if (scrollState == SCROLL_STATE_IDLE) {
			firstViewPosition=getFirstVisiblePosition();
			View view1 =getChildAt(0);
			if(view1!=null)
				firstViewTop=view1.getTop();
		}
	}
	
	
}
