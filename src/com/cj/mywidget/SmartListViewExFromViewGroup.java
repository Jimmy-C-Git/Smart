package com.cj.mywidget;

import java.util.ArrayList;
import java.util.HashMap;

import com.cj.mywidget.SmartListView.MyListAdapter;
import com.cj.tools.Tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class SmartListViewExFromViewGroup extends ViewGroup implements OnScrollListener{

	private ArrayList<String> mTitle;
	private ArrayList<String> mColumnWidth;
	private HashMap<Integer,String> mFooter; 
	private ArrayList<ArrayList<String>> mData;
	private int columnCount=0;
	private View mTitleView,mFooterView;
	private ListView mListView;
	private boolean isHaveTitel=false;
	int firstViewPosition=0;
	int firstViewTop=0;
	int totalWidth=0;
	public SmartListViewExFromViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		mListView=new ListView(context);
		mListView.setOnScrollListener(this);
		
		
	}
	public void init(ArrayList<String> title,ArrayList<String> columnWidth,ArrayList<ArrayList<String>> data)
	{
		mTitle=title;
		mColumnWidth=columnWidth;
		mData=data;
		if(mData!=null)
		{
			if(mData.size()>0)
				columnCount=mData.get(0).size();
		}
		
	
		if(mTitle!=null)
		{
			if(!isHaveTitel)
			{
				mTitleView=getItemView();
				addView(mTitleView);
				isHaveTitel=true;
			}
			setItemData((LinearLayout)mTitleView,mTitle);
		}
		addView(mListView);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int measureWidth=0;
		for(int i=0;i<getChildCount();i++)
		{
			 View childView = getChildAt(i);  
	         measureChild(childView, widthMeasureSpec, heightMeasureSpec); 
	         measureWidth=Math.max(measureWidth, childView.getMeasuredWidth());
		}
		
		setMeasuredDimension(measureWidth, getMeasuredHeight());
		
	}
	public void loadList()
	{
		mListView.setAdapter(new MyListAdapter());
		mListView.setSelectionFromTop(firstViewPosition,firstViewTop);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		
		super.onSizeChanged(w, h, oldw, oldh);
	}
	private void setItemData(LinearLayout ll,ArrayList<String> data)
	{
		int childCount =ll.getChildCount();
		for(int i=0;i<childCount;i++)
		{
			View view=ll.getChildAt(i);
			if(i<data.size())
				((TextView)view).setText(data.get(i));
			else 
				((TextView)view).setText("");
		}
	}
	
	public View getItemView()
	{
		LinearLayout ll=new LinearLayout(getContext());
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setMinimumHeight(40);
		totalWidth=0;
		for(int i=0;i<columnCount;i++)
		{
			
			TextView tv=new TextView(getContext());
			if(i<mColumnWidth.size())
			{
				int width=Tools.str2int(mColumnWidth.get(i));
				tv.setLayoutParams(new LayoutParams(width==0?50:width, LayoutParams.WRAP_CONTENT));
				totalWidth+=(width==0?50:width);
			}else 
			{
				tv.setLayoutParams(new LayoutParams(50, LayoutParams.WRAP_CONTENT));
				totalWidth+=50;
			}
				
			
			ll.addView(tv);
		}
		return ll;
	}
	@Override
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {
		if(isHaveTitel)
		{
			mTitleView.layout(l, t, r, t+mTitleView.getMeasuredHeight());
			mListView.layout(l, t+mTitleView.getMeasuredHeight(), r, b);
		}else {
			mListView.layout(l, t, r, b);
		}
			
		
	}
	public class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
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
			setItemData(ll, mData.get(position));
				

			return convertView;
		}
		
		
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int scrollState) {
		
		if (scrollState == SCROLL_STATE_IDLE) {
			firstViewPosition=mListView.getFirstVisiblePosition();
			View view1 =mListView.getChildAt(0);
			if(view1!=null)
				firstViewTop=view1.getTop();
		}
	}

}
