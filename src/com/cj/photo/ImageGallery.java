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

		// ͨ���ع�onFling������ʹGallery�ؼ�����һ��ֻ����һ��ͼƬ
		// ��ȡ������
		PhotoGalleryAdapter ia =  (PhotoGalleryAdapter) this.getAdapter();
		// �õ���ǰͼƬ��ͼƬ��Դ�е�λ��
		int p =this.getSelectedItemPosition();
		// ͼƬ��������
		int count = ia.getCount();
        int kEvent;

		if (isScrollingLeft(e1, e2)) {
			// Check if scrolling left
			if (p == 0 ) {
			// �ڵ�һҳ�����������ƶ���ʱ����ʾ
				Toast.makeText(this.getContext(), "�ѵ���һҳ", Toast.LENGTH_SHORT)
						.show();
			} 
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			if (p == count - 1 ) {
				Toast.makeText(this.getContext(), "�ѵ����һҳ", Toast.LENGTH_SHORT)
						.show();
			}
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}

		onKeyDown(kEvent, null);
		return true;
	}
}