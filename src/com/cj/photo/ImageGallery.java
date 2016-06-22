package com.cj.photo;


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;
import android.widget.Toast;

public class ImageGallery extends Gallery {

	boolean is_first = false;
	boolean is_last = false;

	public ImageGallery(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ImageGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2)

	{
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float distanceX,

	float distanceY) {

		// 通过重构onFling方法，使Gallery控件滑动一次只加载一张图片
		// 获取适配器
		PhotoGalleryAdapter ia =  (PhotoGalleryAdapter) this.getAdapter();
		// 得到当前图片在图片资源中的位置
		int p =this.getSelectedItemPosition();
		// 图片的总数量
		int count = ia.getCount();
        int kEvent;

		if (isScrollingLeft(e1, e2)) {
			// Check if scrolling left
			if (p == 0 ) {
			// 在第一页并且再往左移动的时候，提示
				Toast.makeText(this.getContext(), "已到第一页", Toast.LENGTH_SHORT)
						.show();
			} 
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			if (p == count - 1 ) {
				Toast.makeText(this.getContext(), "已到最后一页", Toast.LENGTH_SHORT)
						.show();
			}
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}

		onKeyDown(kEvent, null);
		return true;
	}
}