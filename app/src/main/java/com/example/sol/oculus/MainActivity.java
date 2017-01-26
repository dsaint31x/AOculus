package com.example.sol.oculus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void FMButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), FollowMe.class);
        startActivity(intent);
    }

    public void FDButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), FifteenDots.class);
        startActivity(intent);
    }

    public void BNButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), Brightnesss.class);
        startActivity(intent);
    }

    public void EDButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), MonthData.class);
        startActivity(intent);
    }

}
