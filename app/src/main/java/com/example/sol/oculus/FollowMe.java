package com.example.sol.oculus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FollowMe extends AppCompatActivity {
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    int time = 0;                           //시간
    int num = -1;                            //운동 횟수
    static boolean flag = false;            //프로그레스바 플래그
    static boolean runflag = true;          //쓰레드 종료 플래그

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);                              //전체화면 만들기
        setContentView(R.layout.activity_follow_me);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);               //화면 안꺼짐

        relativeLayout = (RelativeLayout) findViewById(R.id.pauseFM);

        LoadData("FM");

        Intent intent = new Intent(this, ExplainFM.class);
        startActivity(intent);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runflag) {
                    if(flag) {
                        time++;
                        Log.d("FollowMe", "운동중");

                        if (time > 60) {
                            Log.d("FollowMe", "운동완료");
                            time = 0;
                            num++;
                            SaveData("FM", num);
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

    public void pauseFMClicked(View view) {
        flag = false;
        relativeLayout.setVisibility(View.VISIBLE);
    }

    public void startFMClicked(View view) {
        flag = true;
        relativeLayout.setVisibility(View.GONE);
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
