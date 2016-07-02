package com.cj.slidedownrefresh;

import com.cj.smart.R;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SlideDoownRefreshLayout extends LinearLayout implements OnTouchListener{


	
	public static final int STATUS_HIDE_HEADER=0;
	public static final int STATUS_NOTREACH_REFRESH=1;
	public static final int STATUS_REACH_REFRESH=2;
	public static final int STATUS_MOVING=3;
	
	/*public static final int STATUS_HIDE_EVENTDOWN=3;
	public static final int STATUS_REFRESHING_EVENTDOWN=4;
	
	public static final int STATUS_NOTREACH_MOVING=5;
	public static final int STATUS_REACH_MOVING=6;
	*/
	public static final int STATUS_REFRESHING=7;
	
	private int startY;
//	private int endY;
	private int firstViewPosition=0,firstViewTop=0;
	private int headerVisibleHeight=0;
	private int status=0;
	private int moveHeight=0;//正的为向下滑,负的为向上滑
	String[]statusDescription={"下拉刷新","释放立即刷新","正在刷新"};
	private boolean loadOnce=false;
	private RelativeLayout header;
	private TextView tvDescription;
	private ProgressBar pbRefresh;
	private ListView listView;
	
	private boolean isPullHeader=false;
	public SlideDoownRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		header=(RelativeLayout) LayoutInflater.from(context).inflate(R.layout.refreshheader,null);
		header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 200));
		tvDescription=(TextView) (header.findViewById(R.id.tvRefereshStatus));
		pbRefresh= (ProgressBar) header.findViewById(R.id.pbRefresh);
		setOrientation(VERTICAL);
		addView(header,0);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed && !loadOnce)
		{
			setHeaderVisualHeight(0);
			loadOnce=true;
			listView = (ListView) getChildAt(1);  
            listView.setOnTouchListener(this);  
		}
	}
	private void setHeaderVisualHeight(int visualHeight)
	{
		MarginLayoutParams lp=(MarginLayoutParams)header.getLayoutParams();
		lp.topMargin=Math.min(0,visualHeight-header.getHeight());
		header.setLayoutParams(lp);
		
	}
	private void refreshHeader()
	{
		
		switch (status) {
		case STATUS_NOTREACH_REFRESH:
			tvDescription.setText(statusDescription[0]);
			pbRefresh.setVisibility(View.INVISIBLE);
			break;
		case STATUS_REACH_REFRESH:
			tvDescription.setText(statusDescription[1]);
			pbRefresh.setVisibility(View.INVISIBLE);
			break;
		case STATUS_REFRESHING:
			tvDescription.setText(statusDescription[2]);
			pbRefresh.setVisibility(VISIBLE);
			//doRefresh();
			break;
		/*case STATUS_NOTREACH_MOVING:
			tvDescription.setText(statusDescription[0]);
			pbRefresh.setVisibility(View.INVISIBLE);
			break;
		case STATUS_REACH_MOVING:
			tvDescription.setText(statusDescription[1]);
			pbRefresh.setVisibility(View.INVISIBLE);
			break;
		case STATUS_NOTREACH_REFRESH_EVENTUP:
			new MovingTask().execute(headerVisibleHeight, 0);
			break;
		case STATUS_REACH_REFRESH_EVENTUP:
			new MovingTask().execute(headerVisibleHeight, header.getHeight());
			status=STATUS_REFRESHING;
			break;*/
		
			
		}

	}
	void doRefresh()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MovingTask().execute(headerVisibleHeight, 0);
	}
	
	
	boolean isListOnTop()
	{
		firstViewPosition=listView.getFirstVisiblePosition();
		View view1 =listView.getChildAt(0);
		if(view1!=null)
			firstViewTop=view1.getTop();
		if(firstViewPosition==0&&firstViewTop==0)
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent ev) {
		if(status==STATUS_MOVING ||status==STATUS_REFRESHING)
		{
			//屏蔽事件
			return true;
		}
		if(!isListOnTop())
			return false;
		
		switch (ev.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			
			startY=(int)ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			moveHeight=(int)ev.getRawY()-startY;
			startY=(int)ev.getRawY();
			headerVisibleHeight+=moveHeight;
			if(headerVisibleHeight<0)
				headerVisibleHeight=0;
			else if(headerVisibleHeight>header.getHeight())
				headerVisibleHeight=header.getHeight();
			
			if(headerVisibleHeight>(header.getHeight()*2/3))
				status=STATUS_REACH_REFRESH;
			else 
				status=STATUS_NOTREACH_REFRESH;
			
			setHeaderVisualHeight(headerVisibleHeight);
			
			break;
			
		case MotionEvent.ACTION_UP:
			isPullHeader=false;
			moveHeight=(int)ev.getRawY()-startY;
			startY=(int)ev.getRawY();
			headerVisibleHeight+=moveHeight;
			
			if(headerVisibleHeight<0)
				headerVisibleHeight=0;
			else if(headerVisibleHeight>header.getHeight())
				headerVisibleHeight=header.getHeight();
			
			if(headerVisibleHeight>(header.getHeight()*2/3)){
				status=STATUS_REACH_REFRESH;
				new MovingTask().execute(headerVisibleHeight, header.getHeight());
			}
			else {
				status=STATUS_NOTREACH_REFRESH;
				new MovingTask().execute(headerVisibleHeight, 0);
			}
				
			
			setHeaderVisualHeight(headerVisibleHeight);
			break;
			
			
		}
		
		refreshHeader();
		if(isPullHeader)
			return true;
		if(moveHeight<=0 )//向上
			return false;
		else {
			isPullHeader=true;
		}
		
			return true;
	}
	public class RefreshTask extends AsyncTask<Integer, Void, Void>{

		@Override
		protected Void doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			status=STATUS_NOTREACH_REFRESH;
			new MovingTask().execute(headerVisibleHeight, 0);
		}
		
	}
	public class MovingTask extends AsyncTask<Integer, Integer, Void>
	{
		int from=0;
		int to =0;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			status=STATUS_MOVING;
		}
		@Override
		protected Void doInBackground(Integer... arg0) {
			from=arg0[0];
			to =arg0[1];
			int speed=1;
			speed=from<to?1:-1;
			int progress=from ;
			
			while (progress!=to){
				progress+=speed;
				publishProgress(progress);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			super.onProgressUpdate(values);
			setHeaderVisualHeight(values[0]);
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(to==header.getHeight()){
				status=STATUS_REFRESHING;
				refreshHeader();
				new RefreshTask().execute(1);
			}else if(to==0)
			{
				status=STATUS_NOTREACH_REFRESH;
				refreshHeader();
			}
			headerVisibleHeight=to;
		}
	}
}
