package com.example.jaeeun.extraocular;


/**
 * Created by Jae Eun on 2016-09-26.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class FifteenDots extends Activity {

    ImageView mImgView_left;
    ImageView mImgView_right;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Explain_fifteen.class);
        startActivity(intent); //how to use 창 띄우기



        mImgView_left = (ImageView) findViewById(R.id.left_icon);
        mImgView_right = (ImageView) findViewById(R.id.right_icon); //이미지 정의

        final Animation animTransLeft = AnimationUtils.loadAnimation(
                this,R.anim.translate);
        final Animation animTransRight = AnimationUtils.loadAnimation(
                this,R.anim.translate_right);

        Button start = (Button) findViewById(R.id.extra_start);
        Button pause = (Button) findViewById(R.id.extra_pause);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mImgView_left.startAnimation(animTransLeft);
                mImgView_right.startAnimation(animTransRight);


            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

    }
}
