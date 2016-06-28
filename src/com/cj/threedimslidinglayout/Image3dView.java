package com.cj.threedimslidinglayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class Image3dView extends View {

	/**
	 * Դ��ͼ����������ͼƬ����
	 */
	private View sourceView;

	/**
	 * ���ݴ����Դ��ͼ���ɵ�ͼƬ����
	 */
	private Bitmap sourceBitmap;

	/**
	 * Դ��ͼ�Ŀ�ȡ�
	 */
	private float sourceWidth;

	/**
	 * Matrix�������ڶ�ͼƬ���о��������
	 */
	private Matrix matrix = new Matrix();

	/**
	 * Camera�������ڶ�ͼƬ������ά������
	 */
	private Camera camera = new Camera();

	/**
	 * Image3dView�Ĺ��캯��
	 * 
	 * @param context
	 * @param attrs
	 */
	public Image3dView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * �ṩ�ⲿ�ӿڣ�������Image3dView����Դ��ͼ��
	 * 
	 * @param view
	 *            �����Դ��ͼ
	 */
	public void setSourceView(View view) {
		sourceView = view;
		sourceWidth = sourceView.getWidth();
	}

	/**
	 * ����������ͼƬ����
	 */
	public void clearSourceBitmap() {
		if (sourceBitmap != null) {
			sourceBitmap = null;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (sourceBitmap == null) {
			getSourceBitmap();
		}
		// ����ͼƬ��Ҫ��ת�ĽǶ�
		float degree = 90 - (90 / sourceWidth) * getWidth();
		camera.save();
		camera.rotateY(degree);
		camera.getMatrix(matrix);
		camera.restore();
		// ����ת�����ĵ��ƶ�����Ļ���Ե���м�λ��
		matrix.preTranslate(0, -getHeight() / 2);
		matrix.postTranslate(0, getHeight() / 2);
		canvas.drawBitmap(sourceBitmap, matrix, null);
	}

	/**
	 * ��ȡԴ��ͼ��Ӧ��ͼƬ����
	 */
	private void getSourceBitmap() {
		if (sourceView != null) {
			sourceView.setDrawingCacheEnabled(true);
			sourceView.layout(0, 0, sourceView.getWidth(), sourceView.getHeight());
			sourceView.buildDrawingCache();
			sourceBitmap = sourceView.getDrawingCache();
		}
	}

}