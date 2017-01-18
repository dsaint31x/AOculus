package com.example.jaeeun.extraocular;

/**
 * Created by Jae Eun on 2016-09-08.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Explain_fifteen extends Activity {
    Button OkButton_extra;
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain_fifteen);
        OkButton_extra = (Button) findViewById(R.id.Ok_extra);

        //프로그램으로 돌아가기
        OkButton_extra.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        }



    }
