package org.androidtown.oculus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Random;

/**
 * Created by Sol on 2016-11-27.
 */
public class  FollowMe extends FragmentActivity {
    ProgressBar progressBar;
    int time = 0;                           //시간
    static boolean timeFlag = false;            //프로그레스바 플래그
    static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_me);

        Intent intent = new Intent(this, Explain.class);
        startActivity(intent);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //if(timeFlag) {
                    if(flag) {
                        time++;

                        if (time > 100) {
                            flag = false;
                            finish();
                        }

                        progressBar.setProgress(time);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t.start();
    }



}

class Exercise1 extends View implements Runnable {
    private int x= 100;
    private int y = 150;
    int speedX, speedY;                                              // 캐릭터가 이동할 방향과 거리
    //static boolean flag = false;
    Bitmap img;

    public Exercise1(Context context){
        super(context);

        speedX  = 20;
        speedY = 20;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        Thread thread = new Thread(this);
        thread.start();
    }

    public Exercise1(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        speedX  = 20;
        speedY = 20;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        Thread thread = new Thread(this);
        thread.start();
    }

    public Exercise1(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);

        speedX  = 20;
        speedY = 20;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        Thread thread = new Thread(this);
        thread.start();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = (int)getDip(10);
        int height = (int)getDip(10);

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED: // unspecified
                width = widthMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:  // wrap_content
                break;
            case MeasureSpec.EXACTLY:  // match_parent
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }

        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED: // unspecified
                height = heightMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:  // wrap_content
                break;
            case MeasureSpec.EXACTLY:  // match_parent
                height = MeasureSpec.getSize(heightMeasureSpec);
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
