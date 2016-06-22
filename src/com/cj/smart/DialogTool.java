package com.cj.smart;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;

public class DialogTool {
	/*private Dialog mDialog;
	private String mType;
	private Context mContext;
	private AlertDialog.Builder builder; //������
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
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //�ȵõ�������  
        builder.setTitle("��ʾ"); //���ñ���  
        builder.setMessage("�Ƿ�ȷ���˳�?"); //��������  
        builder.setIcon(R.mipmap.ic_launcher);//����ͼ�꣬ͼƬid����  
        builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { //����ȷ����ť  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss(); //�ر�dialog  
                Toast.makeText(MainActivity.this, "ȷ��" + which, Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() { //����ȡ����ť  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "ȡ��" + which, Toast.LENGTH_SHORT).show();  
            }  
        });  
  
        builder.setNeutralButton("����", new DialogInterface.OnClickListener() {//���ú��԰�ť  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "����" + which, Toast.LENGTH_SHORT).show();  
            }  
        });  
        //��������������ˣ���������ʾ����  
        builder.create().show();  
    }  
    private void dialog1_1(){  
        //��new��һ�������������úü���  
        DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){  
  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                switch(which){  
                    case Dialog.BUTTON_POSITIVE:  
                        Toast.makeText(MainActivity.this, "ȷ��" + which, Toast.LENGTH_SHORT).show();  
                        break;  
                    case Dialog.BUTTON_NEGATIVE:  
                        Toast.makeText(MainActivity.this, "ȡ��" + which, Toast.LENGTH_SHORT).show();  
                        break;  
                    case Dialog.BUTTON_NEUTRAL:  
                        Toast.makeText(MainActivity.this, "����" + which, Toast.LENGTH_SHORT).show();  
                        break;  
                }  
            }  
        };  
        //dialog��������  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //�ȵõ�������  
        builder.setTitle("��ʾ"); //���ñ���  
        builder.setMessage("�Ƿ�ȷ���˳�?"); //��������  
        builder.setIcon(R.mipmap.ic_launcher);//����ͼ�꣬ͼƬid����  
        builder.setPositiveButton("ȷ��",dialogOnclicListener);  
        builder.setNegativeButton("ȡ��", dialogOnclicListener);  
        builder.setNeutralButton("����", dialogOnclicListener);  
        builder.create().show();  
    }  
    private void dialog2() {  
        final String items[]={"����","����","����"};  
        //dialog��������  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //�ȵõ�������  
        builder.setTitle("��ʾ"); //���ñ���  
        //builder.setMessage("�Ƿ�ȷ���˳�?"); //��������  
        builder.setIcon(R.mipmap.ic_launcher);//����ͼ�꣬ͼƬid����  
        //�����б���ʾ��ע���������б���ʾ�Ͳ�Ҫ����builder.setMessage()�ˣ������б������á�  
        builder.setItems(items,new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();  
  
            }  
        });  
        builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "ȷ��", Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.create().show();  
    }  
    private void dialog3(){  
        final String items[]={"��","Ů"};  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //�ȵõ�������  
        builder.setTitle("��ʾ"); //���ñ���  
        builder.setIcon(R.mipmap.ic_launcher);//����ͼ�꣬ͼƬid����  
        builder.setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                //dialog.dismiss();  
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "ȷ��", Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.create().show();  
    }  
    private void dialog4(){  
        final String items[]={"����","����","����"};  
        final boolean selected[]={true,false,true};  
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //�ȵõ�������  
        builder.setTitle("��ʾ"); //���ñ���  
        builder.setIcon(R.mipmap.ic_launcher);//����ͼ�꣬ͼƬid����  
        builder.setMultiChoiceItems(items,selected,new DialogInterface.OnMultiChoiceClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {  
               // dialog.dismiss();  
                Toast.makeText(MainActivity.this, items[which]+isChecked, Toast.LENGTH_SHORT).show();  
            }  
        });  
        builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                Toast.makeText(MainActivity.this, "ȷ��", Toast.LENGTH_SHORT).show();  
                //android���Զ�������ѡ��ĸı�selected�����ֵ��  
                for (int i=0;i<selected.length;i++){  
                    Log.e("hongliang",""+selected[i]);  
                }  
            }  
        });  
        builder.create().show();  
    }  */
}
