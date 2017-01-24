package com.example.sol.oculus;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class Brightnesss extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressHandler handler;
    RelativeLayout relativeLayout;
    boolean flag = true;
    boolean runflag = true;          //쓰레드 종료 플래그
    float brightnessNum;
    int time = 0;
    int timeNum = 0;
    WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightnesss);

        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        params = getWindow().getAttributes();
        handler = new ProgressHandler();
        relativeLayout = (RelativeLayout) findViewById(R.id.pauseBN);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runflag) {
                    if(flag) {
                        time++;

                        Log.d("Brightness", "운동중  "+time + "  " + brightnessNum);

                        if (time > 100) {
                            Log.d("Brightness", "운동완료");
                            params.screenBrightness = 0.8f;
                            time = 0;
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

    public void pauseClicked(View view) {
        flag = false;
        relativeLayout.setVisibility(View.VISIBLE);
    }

    public void startClicked(View view) {
        relativeLayout.setVisibility(View.GONE);
        flag = true;
    }

    public class ProgressHandler extends Handler{
        public void handleMessage(Message msg){
            timeNum++;

            if(timeNum < 1500){
                params.screenBrightness = 0.5f;
            } else if(timeNum < 2000){
                params.screenBrightness = 0.1f;
            } else if(timeNum < 4000){
                brightnessNum += 0.0003;
                params.screenBrightness = brightnessNum;
            } else if(timeNum < 6000){
                brightnessNum -= 0.0003;
                params.screenBrightness = brightnessNum;
            } else if(timeNum < 8000){
                brightnessNum += 0.0003;
                params.screenBrightness = brightnessNum;
            } else if(timeNum < 10000){
                brightnessNum -= 0.0003;
                params.screenBrightness = brightnessNum;
            }

            getWindow().setAttributes(params);
            progressBar.setProgress(time);
        }
    }
}
