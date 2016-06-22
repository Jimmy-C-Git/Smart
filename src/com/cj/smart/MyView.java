package com.cj.smart;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("WrongCall") public class MyView extends View{

	public MyView(Context context) {
		super(context);
	}
	public MyView(Context context,AttributeSet as)
	{
		super(context, as);
	}
	public MyView(Context context,AttributeSet as,int ds)
	{
		super(context,as,ds);
	}
	class Ball
	{
		
		int radius=0;
		int color;
		Location location=new Location();
		Velocity v=new Velocity();
		class Location
		{
			float x,y;
		}
		class Velocity
		{
			float vx;
			float vy;
		}
		boolean isMoving()
		{
			if(v.vx==0&&v.vy==0)
				return false;
			else return true;
		}
		boolean collisionListenner()
		{
			for(int i=0;i<4;i++)
				;
			return false;
		}
		/*class 
		{
			
		}*/
	}
	class Wall
	{
		float startX=0,starY=0,endX=0,endY=0;
	}
	Random random=new Random(255);
	ArrayList<Ball> balls=new ArrayList<MyView.Ball>();
	ArrayList<Wall> walls=new ArrayList<Wall>();
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

			
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			
			for(int i=0;i<balls.size();i++)
			{
				
					paint.setColor(balls.get(i).color);
					canvas.drawCircle(balls.get(i).location.x, balls.get(i).location.y, balls.get(i).radius, paint);
			}
		super.onDraw(canvas);
	}
	
	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		run();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Ball ball=new Ball();
			ball.location.x=event.getX();
			ball.location.y=event.getY();
			ball.color= Color.argb(random.nextInt(), random.nextInt(),
					random.nextInt(), random.nextInt());
			ball.radius=random.nextInt(255);
			ball.v.vx=random.nextInt(1000)-500;
			ball.v.vy=random.nextInt(1000)-500;
			balls.add(ball);
			haveMove=true;
			break;
		case MotionEvent.ACTION_MOVE:
			ball=new Ball();
			ball.location.x=event.getX();
			ball.location.y=event.getY();
			ball.color= Color.argb(random.nextInt(), random.nextInt(),
					random.nextInt(), random.nextInt());
			ball.radius=random.nextInt(255);
			ball.v.vx=random.nextInt(1000)-500;
			ball.v.vy=random.nextInt(1000)-500;
			balls.add(ball);
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			break;
		}
		
		return false;
		
		
	}
	boolean  haveMove=true;
	
	Handler handler=new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what)
			{
			case 100:
				invalidate();
				break;
			case 200:
				
				break;
			}
			return false;
		}
	});

	void run() 
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				while (true) 
				{
					move();
					
					handler.sendEmptyMessage(100);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	void move()
	{
		if (haveMove) {
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).location.x += (balls.get(i).v.vx / (1000 / 50));
				balls.get(i).location.y += (balls.get(i).v.vy / (1000 / 50));
			}

			haveMove = false;
			for (int i = 0; i < balls.size(); i++) {
				if (balls.get(i).isMoving())
					haveMove = true;
			}
		}
	}
}
