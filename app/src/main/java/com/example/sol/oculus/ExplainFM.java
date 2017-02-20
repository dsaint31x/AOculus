package com.example.sol.oculus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExplainFM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_fm);

        TextView FMExplain = (TextView) findViewById(R.id.ExplainFMText);
        FMExplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowMe.flag = true;
                finish();
            }
        });

    }
}
