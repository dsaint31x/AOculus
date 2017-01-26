package com.example.sol.oculus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FifteenDots extends AppCompatActivity {
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    ImageView mImgView_left;
    ImageView mImgView_right;
    MyTranslateAnimation animTransLeft;
    MyTranslateAnimation animTransRight;

    int num = -1;                            //운동횟수
    int time = 0;                           //시간
    static boolean flag = false;            //프로그레스바 플래그
    boolean runflag = true;          //쓰레드 종료 플래그

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);                                  //전체화면 만들기
        setContentView(R.layout.activity_fifteen_dots);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);                   //화면 안꺼짐

        relativeLayout = (RelativeLayout) findViewById(R.id.pauseFD);

        LoadData("FD");

        Intent intent = new Intent(this, ExplainFD.class);
        startActivity(intent);

        mImgView_left = (ImageView) findViewById(R.id.left_icon);
        mImgView_right = (ImageView) findViewById(R.id.right_icon); //이미지 정의

        animTransRight = (MyTranslateAnimation) MyAnimationUtils.loadAnimation(this,R.anim.translate_right);
        animTransLeft = (MyTranslateAnimation) MyAnimationUtils.loadAnimation(this,R.anim.translate);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);

        mImgView_left.startAnimation(animTransLeft);
        mImgView_right.startAnimation(animTransRight);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runflag) {
                    if(flag) {
                        time++;
                        Log.d("FifteenDots", "운동중");

                        if (time > 60) {
                            Log.d("FifteenDots", "운동완료");
                            time = 0;
                            num++;
                            SaveData("FD", num);
                            runflag = false;
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

    @Override
    public void onBackPressed() {
        runflag = false;
        super.onBackPressed();
    }

    public void startFDClicked(View view) {
        animTransRight.resume();
        animTransLeft.resume();

        relativeLayout.setVisibility(View.GONE);
        flag = true;
    }

    public void pauseFDClicked(View view) {
        animTransLeft.pause();
        animTransRight.pause();

        flag = false;
        relativeLayout.setVisibility(View.VISIBLE);
    }

    public void SaveData(String Exercise, int num) {
        Calendar calendar = new GregorianCalendar();
        String today = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences exerciseData = getSharedPreferences("ExerciseData", MODE_PRIVATE);
        SharedPreferences.Editor editor = exerciseData.edit();
        editor.putInt(Exercise + today, num);
        editor.commit();
    }

    public void LoadData(String Exercise){
        Calendar calendar = new GregorianCalendar();
        String today = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences exerciseData = getSharedPreferences("ExerciseData", MODE_PRIVATE);
        num = exerciseData.getInt(Exercise + today, 0);
    }
}
