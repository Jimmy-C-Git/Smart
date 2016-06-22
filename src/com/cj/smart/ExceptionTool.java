package com.cj.smart;

import java.io.File;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.Toast;

public class ExceptionTool {/*
	private  Context mContext;
	public File createLogFile() {
		File fileDir = new File(Environment.getExternalStorageDirectory(),
				mContext.getPackageName());
		if (!fileDir.exists()) {
			if (!fileDir.mkdirs()) {
				//String errorMsg = "无法创建照片存放目录：" + fileDir.toString();
				//Toast.makeText(mContext, errorMsg, Toast.LENGTH_LONG).show();
				return null;
			}
		}
		return new File(fileDir, "log.txt");
	}
	public void postLog()
	{
		//上传到服务器
		
	}
	public void postToLogFile(String errorMsg)
	{
		//提交到手机本地Log文件
		File fileDir = new File(Environment.getExternalStorageDirectory(),
				context.getPackageName());
	}
	
	 public voidwriteFileData(String fileName,String message){ 

	       try{ 

	        FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);

	        byte [] bytes = message.getBytes(); 

	        fout.write(bytes); 

	         fout.close(); 

	        } 

	       catch(Exception e){ 

	        e.printStackTrace(); 

	       } 

	   }
	public String getErrorFromFile()
	{
		String res="";     

		try{ 

		FileInputStream fin = new FileInputStream(fileName);

		//FileInputStream fin = openFileInput(fileName);  

		//用这个就不行了，必须用FileInputStream

		    int length = fin.available(); 

		    byte [] buffer = new byte[length]; 

		    fin.read(buffer);     

		    res = EncodingUtils.getString(buffer, "UTF-8"); 

		    fin.close();     

		    }catch(Exception e){ 

		           e.printStackTrace(); 

		} 
		return res;
	}*/
	
}
