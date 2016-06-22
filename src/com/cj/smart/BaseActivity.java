package com.cj.smart;

import java.io.File;




import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{
	
	public BackgroundTask backgroundTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		backgroundTask=new BackgroundTask();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (backgroundTask != null && backgroundTask.getStatus() != AsyncTask.Status.FINISHED)
			backgroundTask.cancel(true);
	}
	
	protected abstract  String  doBackgroundTask(String... params);
	protected abstract  boolean doAfterBackgroundTask(String result);
	public class BackgroundTask extends AsyncTask<String, Integer, String>{

		@Override
		protected void onProgressUpdate(Integer ... values) {
			
			super.onProgressUpdate(values);
		}
		@Override
		protected String doInBackground(String... arg0) {
			
			
			return doBackgroundTask(arg0);
			
		}
		@Override
		protected void onPostExecute(String result) {
			// 
			doAfterBackgroundTask(result);
		}
		
	}
}
