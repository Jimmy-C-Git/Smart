package com.cj.photo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;











import com.cj.smart.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Gallery.LayoutParams;

public class PhotoGalleryAdapter  extends BaseAdapter implements OnScrollListener{

	private Context mContext;
	private ImageGallery mGallery;
	private String mUserID;
	private LruCache<String, Bitmap> mMemoryCache;
	private Set<BitmapWorkerTask> taskCollection;
	private int mPosition=0;
	private String[] urls;
	
	public PhotoGalleryAdapter( Context context,int textViewResourceId, String[] objects, ImageGallery gallery) 
	{
		super();
		urls=objects;
		mContext=context;
		mGallery=gallery;
		taskCollection=new  HashSet<BitmapWorkerTask>();
		// ��ȡӦ�ó����������ڴ�
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		// ����ͼƬ�����СΪ�����������ڴ��1/8
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) 
			{
				return bitmap.getByteCount();
			}
		};
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urls==null?0:urls.length;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return urls[position];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		final String photoID=getItem(position);
		ImageView view;
		mPosition=position;
		if(convertView==null)
		{
			view = new ImageView(mContext);
			view.setLayoutParams(new ImageGallery.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			view.setScaleType(ImageView.ScaleType.FIT_XY);
			view.setAdjustViewBounds(true);
			view.setPadding(0, 0, 0, 0);
		}else 
		{
			view=(ImageView) convertView;
		}
		view.setTag(photoID);
		setImageView(photoID,view);
		return view;
	}
	
	private void setImageView(String photoID,ImageView imageView)
	{
		Bitmap bitmap = getBitmapFromMemoryCache(photoID);
		if(bitmap!=null){
			imageView.setImageBitmap(bitmap);
		}else {
			loadBitmaps(mPosition, 1);
			imageView.setImageResource(R.drawable.empty_photo);
		}
	}
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemoryCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}
	public Bitmap getBitmapFromMemoryCache(String key) {
		return mMemoryCache.get(key);
	}
	
	
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	private void loadBitmaps(int firstVisibleItem, int visibleItemCount) {
		try {
			for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) 
			{
				String photoID = urls[i];
				Bitmap bitmap = getBitmapFromMemoryCache(photoID);
				if (bitmap == null) {
					BitmapWorkerTask task = new BitmapWorkerTask();
					taskCollection.add(task);
					task.execute(photoID);
				} else {
					ImageView imageView = (ImageView) mGallery.findViewWithTag(photoID);
					if (imageView != null && bitmap != null) {
						imageView.setImageBitmap(bitmap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

		/**
		 * ͼƬ��ID
		 */
		private String photoID;

		@Override
		protected Bitmap doInBackground(String... params) {
			photoID = params[0];
			// �ں�̨��ʼ����ͼƬ
			Bitmap bitmap = downloadBitmap(params[0]);
			if (bitmap != null) {
				// ͼƬ������ɺ󻺴浽LrcCache��
				addBitmapToMemoryCache(params[0], bitmap);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			// ����Tag�ҵ���Ӧ��ImageView�ؼ��������غõ�ͼƬ��ʾ������
			ImageView imageView = (ImageView) mGallery.findViewWithTag(photoID);
			if (imageView != null && bitmap != null) {
				imageView.setImageBitmap(bitmap);
			}
			taskCollection.remove(this);
		}

		/**
		 * ����HTTP���󣬲���ȡBitmap����
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ
		 * @return �������Bitmap����
		 */
		private Bitmap downloadBitmap(String imageUrl) {
			Bitmap bitmap = null;
			HttpURLConnection con = null;
			try {
				URL url = new URL(imageUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5 * 1000);
				con.setReadTimeout(10 * 1000);
				con.setDoInput(true);
				con.setDoOutput(true);
				bitmap = BitmapFactory.decodeStream(con.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
			return bitmap;
			
		}
		/*public Bitmap prasePhotoData(String b64Photo,String photoId)
		{
			Bitmap bmPhoto;
			BASE64Decoder decode=new BASE64Decoder();
			try {
				byte[] decodeByte=decode.decodeBuffer(b64Photo);
				bmPhoto =decodeSampledBitmapFromByteArray(decodeByte,600,800);
						//BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);;
			}catch (Exception e)
			{
				e.printStackTrace();
				bmPhoto=null;
			}
			return bmPhoto;
		}*/
		public int calculateInSampleSize(BitmapFactory.Options options,  
		        int reqWidth, int reqHeight) {  
		    // ԴͼƬ�ĸ߶ȺͿ��  
		    final int height = options.outHeight;  
		    final int width = options.outWidth;  
		    int inSampleSize = 1;  
		    if (height > reqHeight || width > reqWidth) {  
		        // �����ʵ�ʿ�ߺ�Ŀ���ߵı���  
		        final int heightRatio = Math.round((float) height / (float) reqHeight);  
		        final int widthRatio = Math.round((float) width / (float) reqWidth);  
		        // ѡ���͸�����С�ı�����ΪinSampleSize��ֵ���������Ա�֤����ͼƬ�Ŀ�͸�  
		        // һ��������ڵ���Ŀ��Ŀ�͸ߡ�  
		        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;  
		    }  
		    return inSampleSize;  
		}  
		
		public  Bitmap decodeSampledBitmapFromByteArray(byte[] decodeByte,  
		        int reqWidth, int reqHeight) {  
		    // ��һ�ν�����inJustDecodeBounds����Ϊtrue������ȡͼƬ��С  
		    final BitmapFactory.Options options = new BitmapFactory.Options();  
		    options.inJustDecodeBounds = true;  
		    BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length,options);  
		    // �������涨��ķ�������inSampleSizeֵ  
		    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
		    // ʹ�û�ȡ����inSampleSizeֵ�ٴν���ͼƬ  
		    options.inJustDecodeBounds = false;  
		    return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length,options);
		}
	}
}
