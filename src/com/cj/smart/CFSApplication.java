package com.cj.smart;



import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class CFSApplication extends Application{
	private static CFSApplication instance;
	public UserInfo userInfo;
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
		userInfo=new UserInfo();
        Log.v("test", "appCreate");
        CrashHandler crashHandler = CrashHandler.getInstance();  
        crashHandler.init(getApplicationContext()); 
	}
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		//super.onLowMemory();
		int i=this.getSharedPreferences("test", Activity.MODE_PRIVATE)
		.getInt("onLowMemory",0 );
		
		this.getSharedPreferences("test", Activity.MODE_PRIVATE)
		.edit().putInt("onLowMemory",++i )
		.commit();
		this.getSharedPreferences("test", Activity.MODE_PRIVATE)
		.edit().putString("onLowMemory"+String.valueOf(i),userInfo.getProperty("userId") )				
		.commit();
		Log.v("test", "ÖØÐÂµÇÂ¼£¡");
	}
	@Override
	public void onTrimMemory(int level) {
		//super.onTrimMemory(level);
		int i=this.getSharedPreferences("test", Activity.MODE_PRIVATE)
				.getInt("onTrimMemory",0 );
			this.getSharedPreferences("test", Activity.MODE_PRIVATE)
			.edit().putString("onTrimMemory:"+String.valueOf(i),userInfo.getProperty("userId") )				
			.commit();
				this.getSharedPreferences("test", Activity.MODE_PRIVATE)
				.edit().putInt("onTrimMemory",++i )
				.commit();
				
	}
	public static CFSApplication getInstance()
	{
		return instance;
	}


}
