package com.cj.photo;



import java.util.ArrayList;
import java.util.HashMap;









import com.cj.smart.CFSApplication;
import com.cj.smart.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class PhotoWallActivity extends Activity {

	private GridView mPhotoWall;
	private PhotoWallAdapter adapter;
	private ArrayList<String> photoIDList=new ArrayList<String>();
	private CFSApplication app;
	private Button btnLookMode,btnDeleteMode;
	private String ownerID;
	private String action;
	private String[] objects;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photo_wall);
		mPhotoWall=(GridView)findViewById(R.id.gv_photo);
		adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);
		mPhotoWall.setAdapter(adapter);
		setViewEvent();
	}
	
	void getPhotoIDList()
	{
		
		adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);
		mPhotoWall.setAdapter(adapter);
		
		
	}
	private void setViewStatus()
	{
		if(action==null)return ;
		if(action.equals("look"))
		{
			btnDeleteMode.setBackgroundColor(Color.GRAY);
			btnLookMode.setBackgroundColor(Color.TRANSPARENT);
			btnLookMode.setEnabled(false);
			btnDeleteMode.setEnabled(false);
		}
	}
	void deletePhoto(int position)
	{
		/*String photoID;
		if(position>=0&&position<photoIDList.size())
		{
			photoID=photoIDList.get(position);
		}else return;
		
		if(photoID==null)return;
			
		HashMap<String,String> item=new HashMap<String, String>();
		item.put("fileID", photoID);
		
		String res=UseInterface.GetCommonInterface("70030", item);
		WSResultParse parse=new WSResultParse();
		parse.parseData(res);
		if(parse.getResultCode()==0)
		{
			Toast.makeText(PhotoWallActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
			photoIDList.remove( position);
			adapter.setPhotoIDList(photoIDList);
			adapter.notifyDataSetChanged();
		}else 
		{
			Tools.showMsg2DiaLog(this, parse.getMessageText());
		}*/
	}
	void initView()
	{
		mPhotoWall = (GridView) findViewById(R.id.gv_photo);
		btnLookMode=(Button)findViewById(R.id.btnLook_photoWall);
		btnDeleteMode=(Button)findViewById(R.id.btnDelete_photoWall);
		btnDeleteMode.setBackgroundColor(Color.GRAY);
		btnLookMode.setBackgroundColor(Color.GRAY);
		btnLookMode.setEnabled(false);
		btnDeleteMode.setEnabled(false);
	}
	void setViewEvent()
	{
		
		mPhotoWall.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {

				Intent intent = new Intent();
				intent.putExtra("position", position);

				intent.setClass(PhotoWallActivity.this,
						PhotoGalleryActivity.class);
				startActivityForResult(intent, 1);

			}
		});
	
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 退出程序时结束所有的下载任务
		if(adapter!=null)
		adapter.cancelAllTasks();
	}
	public void imageBack(View v) {
		this.finish();
	}
}
