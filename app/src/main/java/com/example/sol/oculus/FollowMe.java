package com.example.sol.oculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class FollowMe extends AppCompatActivity {
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    int time = 0;                           //시간
    static boolean flag = false;            //프로그레스바 플래그
    static boolean runflag = true;          //쓰레드 종료 플래그

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);                              //전체화면 만들기
        setContentView(R.layout.activity_follow_me);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);               //화면 안꺼짐

        runflag = true;

        relativeLayout = (RelativeLayout) findViewById(R.id.pauseFM);

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

                        if (time > 100) {
                            Log.d("FollowMe", "운동완료");
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
}
