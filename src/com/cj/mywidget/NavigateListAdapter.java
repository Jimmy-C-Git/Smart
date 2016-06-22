package com.cj.mywidget;

import java.util.List;





import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public  class NavigateListAdapter {

	private int childCount=0;
	private int parentCount=0;
	private int[] eachGroupCount;
	private BaseAdapter navigateAdapter,dataAdapter;
	public NavigateListAdapter()
	{
		navigateAdapter=new BaseAdapter() {
			
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
	protected void setEachGroupCount(int[] eachGroupCount)
	{
		this.eachGroupCount=eachGroupCount;
	}
	protected View getGroupView(int position )
	{
		return null;
	}
	
}
