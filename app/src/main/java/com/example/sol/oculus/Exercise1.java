package com.example.sol.oculus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

/**
 * Created by Sol on 2017-01-23.
 */

public class Exercise1 extends View implements Runnable{
    private int x= 100;
    private int y = 150;
    int speedX, speedY;                                              // 캐릭터가 이동할 방향과 거리
    //static boolean flag = false;
    Bitmap img;

    public Exercise1(Context context){
        super(context);

        speedX  = 20;
        speedY = 20;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.icon_img);

        Thread thread = new Thread(this);
        thread.start();
    }

    public Exercise1(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        speedX  = 20;
        speedY = 20;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.icon_img);

        Thread thread = new Thread(this);
        thread.start();
    }

    public Exercise1(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);

        speedX  = 20;
        speedY = 20;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.icon_img);

        Thread thread = new Thread(this);
        thread.start();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);

        int width = (int)getDip(10);
        int height = (int)getDip(10);

        switch (widthMode) {
            case View.MeasureSpec.UNSPECIFIED: // unspecified
                width = widthMeasureSpec;
                break;
            case View.MeasureSpec.AT_MOST:  // wrap_content
                break;
            case View.MeasureSpec.EXACTLY:  // match_parent
                width = View.MeasureSpec.getSize(widthMeasureSpec);
                break;
        }

        switch (heightMode) {
            case View.MeasureSpec.UNSPECIFIED: // unspecified
                height = heightMeasureSpec;
                break;
            case View.MeasureSpec.AT_MOST:  // wrap_content
                break;
            case View.MeasureSpec.EXACTLY:  // match_parent
                height = View.MeasureSpec.getSize(heightMeasureSpec);
                break;
        }


        setMeasuredDimension(width, height);
    }

    public float getDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }


    public  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Random rd = new Random();

        int speed = rd.nextInt(20)+15;
        int dir = rd.nextInt(2);

        x += speedX;
        y += speedY;

        if(x >= canvas.getWidth() - img.getWidth()){
            speedX = -speed;
            speedY = (dir == 0 ? speedY : -speedY);
        }

        if(y  >= canvas.getHeight() - img.getHeight()){
            speedY = -speed;
            speedX = (dir == 0 ? speedX : -speedX);
        }
        if(x <= 0){
            speedX = speed;
            speedY = (dir == 0 ? speedY : -speedY);
        }

        if(y <= 0){
            speedY = speed;
            speedX = (dir == 0 ? speedX : -speedX);
        }
        canvas.drawBitmap(img, x, y, null);
    }

/*
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(FollowMe.flag == true){          //일시정지
                FollowMe.flag = false;
                //FollowMe.timeFlag = false;
            }
            else{                       //다시시작
                FollowMe.flag = true;
                //FollowMe.timeFlag = true;
            }
        }
        return super.onTouchEvent(event);
    }
    */

    @Override
    public void run() {
        while(true) {
            if (FollowMe.flag) {
                postInvalidate();
            }
            try {
                Thread.sleep(10);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
