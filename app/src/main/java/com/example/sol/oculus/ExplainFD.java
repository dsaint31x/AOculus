package com.example.sol.oculus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExplainFD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_fd);
    }

    public void explainFClicked(View view) {
        finish();
    }
}
