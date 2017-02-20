package com.example.sol.oculus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExplainFD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_fd);

        TextView FDExplain = (TextView) findViewById(R.id.ExplainFDText);
        FDExplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FifteenDots.flag = true;
                finish();
            }
        });
    }
}
