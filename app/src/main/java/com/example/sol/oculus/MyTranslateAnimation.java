package com.example.sol.oculus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;


public class MyTranslateAnimation extends TranslateAnimation {
    private long mTimePause, mTimeTotal;
    private boolean mPause;


    public MyTranslateAnimation(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    @Override
    public boolean getTransformation(long currentTime, Transformation outTransformation) {
        updateTime(currentTime);
        return super.getTransformation(mTimeTotal - mTimePause, outTransformation);
    }

    private void updateTime(long currentTime) {
        long dt = currentTime - mTimeTotal;
        mTimeTotal += dt;
        if (mPause) {
            mTimePause += dt;
        }
    }

    public void pause() {
        mPause = true;
    }

    public void resume() {
        mPause = false;
    }

}
