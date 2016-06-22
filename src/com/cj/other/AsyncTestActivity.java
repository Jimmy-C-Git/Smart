package com.cj.other;

import com.cj.smart.BaseActivity;
import com.cj.smart.R;
import com.cj.smart.R.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class AsyncTestActivity extends BaseActivity {
	ProgressBar pr1;
	FrameLayout pr2;
	Button btnDownload;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_test);
		pr1=(ProgressBar)findViewById(R.id.progressBar1);
		pr2=(FrameLayout)findViewById(R.id.progressBar2);
		btnDownload=(Button)findViewById(R.id.btnDownload);
		this.backgroundTask.execute("");
		btnDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new DownLoadTask().execute("");
			}
		});
	}

	@Override
	protected String doBackgroundTask(String... arg0) {
		
		pr1.setVisibility(View.VISIBLE);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected boolean doAfterBackgroundTask(String result) {

		pr1.setVisibility(View.GONE);
		return false;
	}
	
	public class DownLoadTask extends AsyncTask<String, Integer, Boolean>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pr2.setVisibility(View.VISIBLE);
			((ProgressBar) pr2.getChildAt(1)).setMax(100);
		}
		@Override
		protected Boolean doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			for(int i=1;i<=100;i++)
			{
				publishProgress(i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			pr2.setVisibility(View.INVISIBLE);
			super.onPostExecute(result);
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			((ProgressBar)pr2.getChildAt(1)).setProgress(values[0]);
			super.onProgressUpdate(values);
		}
		
	}
}
