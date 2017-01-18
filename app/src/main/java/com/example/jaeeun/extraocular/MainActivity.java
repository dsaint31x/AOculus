package com.example.jaeeun.extraocular;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void FMButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), FollowMe.class);
        startActivity(intent);

    }

    void FDButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), FifteenDots.class);
        startActivity(intent);

    }
}
