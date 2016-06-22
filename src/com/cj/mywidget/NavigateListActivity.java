package com.cj.mywidget;

import java.util.ArrayList;
import java.util.List;

import com.cj.smart.R;
import com.cj.smart.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NavigateListActivity extends Activity {
	NavigateListView3 nav;

	private List<List<ItemInfo>> childList=new ArrayList<List<ItemInfo>>();
	private List<ItemInfo> parentList=new ArrayList<ItemInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigate_list);
		nav=(NavigateListView3) findViewById(R.id.navigateListView);
		ItemInfo temp=new ItemInfo();
		temp.setProperty("XLMC", "test1");
		parentList.add(temp);
		temp=new ItemInfo();
		temp.setProperty("XLMC", "test2");
		parentList.add(temp);
		temp=new ItemInfo();
		temp.setProperty("XLMC", "test3");
		parentList.add(temp);
		temp=new ItemInfo();
		temp.setProperty("XLMC", "test4");
		parentList.add(temp);
		temp=new ItemInfo();
		temp.setProperty("XLMC", "test5");
		parentList.add(temp);
		temp=new ItemInfo();
		temp.setProperty("XLMC", "test6");
		parentList.add(temp);
		temp=new ItemInfo();
		temp.setProperty("XLMC", "test7");
		parentList.add(temp);
		List<ItemInfo> childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child11");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child12");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child13");
		childItem.add(temp);
		childList.add(childItem);
		
		childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child21");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child22");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child23");
		childItem.add(temp);
		childList.add(childItem);
		
		childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child31");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child32");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child33");
		childItem.add(temp);
		childList.add(childItem);
		
		childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child41");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child42");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child43");
		childItem.add(temp);
		childList.add(childItem);
		
		childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child51");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child52");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child53");
		childItem.add(temp);
		childList.add(childItem);
		
		childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child61");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child62");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child63");
		childItem.add(temp);
		childList.add(childItem);
		
		childItem=new ArrayList<ItemInfo>();
		temp=new ItemInfo();
		temp.setProperty("MC", "child71");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child72");
		childItem.add(temp);
		temp=new ItemInfo();
		temp.setProperty("MC", "child73");
		childItem.add(temp);
		childList.add(childItem);
		
		nav.prepare(parentList, childList);
	}
}
