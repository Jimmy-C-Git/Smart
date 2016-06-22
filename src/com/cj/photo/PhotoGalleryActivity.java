package com.cj.photo;

import java.util.ArrayList;


















import com.cj.smart.CFSApplication;
import com.cj.smart.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoGalleryActivity extends Activity {
	public int i_position = 0;
	private Button btnClockWise;
	private ImageGallery gallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photo_gallery);
		initView();
		setClickListener();
		
		Intent intent = getIntent();
		i_position = intent.getIntExtra("position", 0);
		PhotoGalleryAdapter ia=new PhotoGalleryAdapter(this,0, Images.imageThumbUrls,gallery);
		gallery.setAdapter(ia);
		gallery.setSelection(i_position);
        gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = PhotoGalleryActivity.this.getIntent();
				PhotoGalleryActivity.this.setResult(1, intent);
				PhotoGalleryActivity.this.finish();
			}
		});
	}
	private void initView()
	{
		btnClockWise=(Button)findViewById(R.id.btnClockWise);
		gallery=(ImageGallery) findViewById(R.id.imageGallery);
		
	}
	private void setClickListener()
	{
		btnClockWise.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ImageView view=(ImageView) gallery.getSelectedView();
				Drawable drawable=view.getDrawable();
				Matrix matrix=new Matrix();
				matrix.setRotate(90,view.getWidth()/2,view.getHeight()/2);
				Bitmap bitmap =drawableToBitmap(drawable);// new Bitmap(drawable);//.createBitmap(bitmap, 0, 0, view.getWidth(), view.getHeight(), matrix, true);
				bitmap=bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				view.setImageBitmap(bitmap);
			}
		});
	}
	public static Bitmap drawableToBitmap(Drawable drawable) {

	       

        Bitmap bitmap = Bitmap.createBitmap(

                                        drawable.getIntrinsicWidth(),

                                        drawable.getIntrinsicHeight(),

                                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                                                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

}
}
