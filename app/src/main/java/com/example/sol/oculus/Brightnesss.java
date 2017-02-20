package com.example.sol.oculus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Brightnesss extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressHandler handler;
    RelativeLayout relativeLayout;
    boolean flag = true;
    boolean runflag = true;          //쓰레드 종료 플래그
    float brightnessNum;
    int num = -1;                     //운동횟수
    int time = 0;
    int timeNum = 0;
    WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);                              //전체화면 만들기
        setContentView(R.layout.activity_brightnesss);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);               //화면 안꺼짐

        LoadData("BN");

        TextView BNView = (TextView) findViewById(R.id.BNView);
        BNView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        params = getWindow().getAttributes();
        handler = new ProgressHandler();

        relativeLayout = (RelativeLayout) findViewById(R.id.pauseBN);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
                flag = true;
            }
        });


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runflag) {
                    if(flag) {
                        time++;

                        Log.d("Brightness", "운동중  "+time + "  " + brightnessNum);

                        if (time > 60) {
                            Log.d("Brightness", "운동완료");
                            params.screenBrightness = 0.8f;
                            time = 0;
                            num++;
                            SaveData("BN", num);
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
    protected void onStart() {
        super.onStart();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runflag){
                    if(flag){
                        try {
                            Thread.sleep(10);

                            Message msg = handler.obtainMessage();
                            handler.sendMessage(msg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread.start();
    }

    @Override
    public void onBackPressed() {
        runflag = false;
        super.onBackPressed();
    }

    public class ProgressHandler extends Handler{
        public void handleMessage(Message msg){
            timeNum++;

            if(timeNum < 1000){
                params.screenBrightness = 0.5f;
            } else if(timeNum < 1300){
                params.screenBrightness = 0.2f;
            } else if(timeNum < 2350){
                brightnessNum += 0.0006;
                params.screenBrightness = brightnessNum;
            } else if(timeNum < 3500){
                brightnessNum -= 0.0005;
                params.screenBrightness = brightnessNum;
            } else if(timeNum < 4650){
                brightnessNum += 0.0006;
                params.screenBrightness = brightnessNum;
            } else if(timeNum < 6000){
                brightnessNum -= 0.0005d;
                params.screenBrightness = brightnessNum;
            }

            getWindow().setAttributes(params);
            progressBar.setProgress(time);
        }
    }

    public void SaveData(String Exercise, int num) {
        Calendar calendar = new GregorianCalendar();
        String today = calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1) + "" + calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences exerciseData = getSharedPreferences("ExerciseData", MODE_PRIVATE);
        SharedPreferences.Editor editor = exerciseData.edit();
        editor.putInt(Exercise + today, num);
        editor.commit();
    }

    public void LoadData(String Exercise){
        Calendar calendar = new GregorianCalendar();
        String today = calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1) + "" + calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences exerciseData = getSharedPreferences("ExerciseData", MODE_PRIVATE);
        num = exerciseData.getInt(Exercise + today, 0);
    }
}
