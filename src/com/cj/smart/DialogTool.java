package com.cj.smart;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;

public class DialogTool {
	/*private Dialog mDialog;
	private String mType;
	private Context mContext;
	private AlertDialog.Builder builder; //构造器
	public DialogTool(Context context)
	{
		mContext =context;
		show();
	}
	public DialogTool(Context context,String type)
	{
		mContext=context;
		mType=type;
		show();
	}
	private void show()
	{
		builder=new AlertDialog.Builder(mContext);
		builder.set
	}
	
	
	private void dialog1(){  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器  
        builder.setTitle("提示"); //设置标题  
        builder.setMessage("是否确认退出?"); //设置内容  
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可  
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss(); //关闭dialog  
                Toast.makeText(MainActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();  
            }  
        });  
  
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {//设置忽略按钮  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "忽略" + which, Toast.LENGTH_SHORT).show();  
            }  
        });  
        //参数都设置完成了，创建并显示出来  
        builder.create().show();  
    }  
    private void dialog1_1(){  
        //先new出一个监听器，设置好监听  
        DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){  
  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                switch(which){  
                    case Dialog.BUTTON_POSITIVE:  
                        Toast.makeText(MainActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();  
                        break;  
                    case Dialog.BUTTON_NEGATIVE:  
                        Toast.makeText(MainActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();  
                        break;  
                    case Dialog.BUTTON_NEUTRAL:  
                        Toast.makeText(MainActivity.this, "忽略" + which, Toast.LENGTH_SHORT).show();  
                        break;  
                }  
            }  
        };  
        //dialog参数设置  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器  
        builder.setTitle("提示"); //设置标题  
        builder.setMessage("是否确认退出?"); //设置内容  
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可  
        builder.setPositiveButton("确认",dialogOnclicListener);  
        builder.setNegativeButton("取消", dialogOnclicListener);  
        builder.setNeutralButton("忽略", dialogOnclicListener);  
        builder.create().show();  
    }  
    private void dialog2() {  
        final String items[]={"张三","李四","王五"};  
        //dialog参数设置  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器  
        builder.setTitle("提示"); //设置标题  
        //builder.setMessage("是否确认退出?"); //设置内容  
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可  
        //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。  
        builder.setItems(items,new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();  
  
            }  
        });  
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.create().show();  
    }  
    private void dialog3(){  
        final String items[]={"男","女"};  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器  
        builder.setTitle("提示"); //设置标题  
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可  
        builder.setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                //dialog.dismiss();  
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.create().show();  
    }  
    private void dialog4(){  
        final String items[]={"篮球","足球","排球"};  
        final boolean selected[]={true,false,true};  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器  
        builder.setTitle("提示"); //设置标题  
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可  
        builder.setMultiChoiceItems(items,selected,new DialogInterface.OnMultiChoiceClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {  
               // dialog.dismiss();  
                Toast.makeText(MainActivity.this, items[which]+isChecked, Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();  
                //android会自动根据你选择的改变selected数组的值。  
                for (int i=0;i<selected.length;i++){  
                    Log.e("hongliang",""+selected[i]);  
                }  
            }  
        });  
        builder.create().show();  
    }  */
}
