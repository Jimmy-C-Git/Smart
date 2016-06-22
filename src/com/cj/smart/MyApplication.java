package com.cj.smart;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v("test", "Application Start!");
	}

}
