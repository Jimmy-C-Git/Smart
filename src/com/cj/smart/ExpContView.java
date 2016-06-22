package com.cj.smart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("DrawAllocation")
public class ExpContView extends View {

	public int contSize = 20;// 箱子的尺寸
	public float focusedX = 0, focusedY = 0, focusedZ = 0;

	public ExpContView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public int getTotalColume() {
		// 返回总共有多少列
		return contSize > 20 ? 10 : 5;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 将背景填充白色
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);

		int width = this.getWidth();
		int height = this.getHeight();
		paint.setStrokeWidth(2);
		canvas.drawRect(0, 0, width, height, paint);
		canvas.drawLine(0, height / 2, width, height / 2, paint);

		paint.setStrokeWidth(1);
		paint.setColor(Color.LTGRAY);
		canvas.drawLine(0, height / 4, width, height / 4, paint);
		canvas.drawLine(0, height * 3 / 4, width, height * 3 / 4, paint);
		//画竖线
		paint.setColor(Color.BLACK);
		int n = getTotalColume();
		int p = width / n;
		for (int i = 1; i < n; i++) 
		{
			canvas.drawLine(i * p, 0, i * p, height, paint);
		}
		//画选中的库位
		int focusedSX,focusedSY;
		if(focusedX!=0&&focusedY!=0&&focusedZ!=0)
		{
			paint.setColor(Color.BLUE);
			paint.setStyle(Paint.Style.FILL);
			focusedSX=(int) ((focusedX-1)*p);
			focusedSY=(int) (height-(focusedZ-1)*height/2-focusedY*height/4);
			canvas.drawRect(focusedSX, focusedSY, focusedSX+p, focusedSY+height/4, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int width = this.getWidth();
			int height = this.getHeight();
			int n = getTotalColume();
			int p = width / n;
			
			float x = event.getX();
			float y = event.getY();
			int touchX=0,touchY=0,touchZ=0;
			touchX=(int) Math.ceil(x/p);
			if(y>height/2)
			{
				touchZ=1;
				touchY=(int) Math.ceil((height-y)/(height/4));
			}
			else 
			{
				touchZ=2;
				touchY=(int) Math.ceil((height/2-y)/(height/4));
			}
			if(touchX==focusedX&&touchY==focusedY&&touchZ==focusedZ)
			{
				focusedX=0;
				focusedY=0;
				focusedZ=0;
			}else 
			{
				focusedX=touchX;
				focusedY=touchY;
				focusedZ=touchZ;
			}
			invalidate();
		}
		return false;
	}

}
