package com.example.jaeeun.extraocular;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;

/**
 * Created by Sol on 2016-11-27.
 */
public class Explain_follow extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain_follow);
    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            FollowMe.flag = true;

            finish();
        }
        return super.onTouchEvent(event);
    }
}
