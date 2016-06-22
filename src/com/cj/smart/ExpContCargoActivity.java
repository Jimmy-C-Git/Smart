package com.cj.smart;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.cj.smart.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ExpContCargoActivity extends Activity  {
	int activeposition=-1;
	Set<MyHScrollView> scrollList=new HashSet<MyHScrollView>();
	private OnScrollsChangedListener scroolsChanged=new OnScrollsChangedListener();
	
	private ListView lvExpContCargo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exp_cont_cargo);
		initView();
		setClickListener();
		bindListView();
		MyHScrollView headScrool=(MyHScrollView) findViewById(R.id.horizontalScrollView1);
		headScrool.setOnScrollViewListener(scroolsChanged);
		scrollList.add(headScrool);
	}
	private void bindListView()
	{
		lvExpContCargo.setAdapter(new MyAdapter());
	}
	private void initView()
	{
		lvExpContCargo=(ListView)findViewById(R.id.lvExpContCargo);
		lvExpContCargo.setOnTouchListener(new OnTouchListener() {
			MyHScrollView myHScrollView;
			int startx=0;
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					myHScrollView=(MyHScrollView) arg0.findViewById(R.id.horizontalScrollView1);
					startx=(int) event.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					
					
					myHScrollView.scrollBy(startx-(int) event.getX(), (int) event.getY());//((int)event.getX(),(int) event.getY());
					startx= (int) event.getX();
					break;
				case MotionEvent.ACTION_UP:
					return false;
				}
				
				return false;
			}
		});
	}
	private void setClickListener()
	{
		lvExpContCargo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				activeposition=position;
				MyAdapter adapter=(MyAdapter) lvExpContCargo.getAdapter();
				adapter.notifyDataSetChanged();
			}
		});
	}
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 50;
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
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder=null;
			if(convertView==null)
			{
				convertView=LayoutInflater.from(ExpContCargoActivity.this).inflate(R.layout.activity_exp_cont_cargo_item,null);
				holder=new ViewHolder();
				final MyItemContainer container=(MyItemContainer) convertView.findViewById(R.id.container);
				container.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent event) {
						// TODO Auto-generated method stub
					
						container.onTouchEvent(event);
							
							return false;
						
					}
				});
				holder.mhScrollView=(MyHScrollView) convertView.findViewById(R.id.horizontalScrollView1);
				holder.mhScrollView.scrollTo(scroolsChanged.nowX, scroolsChanged.nowY);
				holder.mhScrollView.setOnScrollViewListener(scroolsChanged);
			/*	holder.mhScrollView.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent event) {
						// TODO Auto-generated method stub
					
							onTouchEvent(event);
							
							return false;
						
					}
				});*/
				holder.tvCargoRemark=(TextView) convertView.findViewById(R.id.tvCargoRemark);
				holder.tvJCBH=(TextView)convertView.findViewById(R.id.tvJCBH);
				holder.tvKW=(TextView)convertView.findViewById(R.id.tvKW);
				holder.tvPXJS=(TextView)convertView.findViewById(R.id.tvPXJS);
				holder.tvVolume=(TextView)convertView.findViewById(R.id.tvVolume);
				holder.tvYZJS=(TextView)convertView.findViewById(R.id.tvYZJS);
				
				convertView.setTag(holder);
			}else 
			{
				holder=(ViewHolder) convertView.getTag();
				final ViewHolder h=holder;
				new Handler().post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						h.mhScrollView.scrollTo(scroolsChanged.nowX, scroolsChanged.nowY);
					}
				});
			}
			scrollList.add(holder.mhScrollView);
			holder.tvJCBH.setText(String.valueOf(position));
			if(position==activeposition)
				convertView.setBackgroundColor(Color.RED);
			else convertView.setBackgroundColor(Color.TRANSPARENT);
			return convertView;
		}
		
	}
	public class ViewHolder{
		TextView tvJCBH,tvPXJS,tvYZJS,tvWeight,tvVolume,tvKW,tvCargoRemark;
		MyHScrollView mhScrollView;
	}
	
	
	public class OnScrollsChangedListener implements ScrollViewListener
	{
		int nowX=0,nowY=0;
		@Override
		public void onScrollChanged( int x, int y,
				int oldx, int oldy) {
			// TODO Auto-generated method stub
			nowX=x;
			nowY=y;
			for(MyHScrollView h:scrollList)
			{
				h.scrollTo(x, y);
			}
		}
		
	}
}
