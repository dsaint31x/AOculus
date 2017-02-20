package com.example.sol.oculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.cachapa.expandablelayout.ExpandableLayout;

public class MainActivity extends AppCompatActivity {
    ExpandableLayout FMlayout;
    boolean FMflag = false;
    ExpandableLayout FDlayout;
    boolean FDflag = false;
    ExpandableLayout BNlayout;
    boolean BNflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FMlayout = (ExpandableLayout) findViewById(R.id.explainFM);
        FDlayout = (ExpandableLayout) findViewById(R.id.explainFD);
        BNlayout = (ExpandableLayout) findViewById(R.id.explainBN);

        Button FMbutton = (Button) findViewById(R.id.button_follow);
        Button FDbutton = (Button) findViewById(R.id.button_fifteen);
        Button BNbutton = (Button) findViewById(R.id.button_brightness);
        Button EDbutton = (Button) findViewById(R.id.button_exercisedata);

        Button FMstart = (Button) findViewById(R.id.FMstart);
        Button FDstart = (Button) findViewById(R.id.FDstart);
        Button BNstart = (Button) findViewById(R.id.BNstart);

        FMbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FMflag == false) {
                    FMlayout.expand();
                    FMflag = true;
                    layoutClose("FD");
                    layoutClose("BN");
                }
                else {
                    layoutClose("FM");
                }
            }
        });

        FDbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FDflag == false) {
                    FDlayout.expand();
                    FDflag = true;
                    layoutClose("FM");
                    layoutClose("BN");
                }
                else {
                    layoutClose("FD");
                }
            }
        });

        BNbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BNflag == false) {
                    BNlayout.expand();
                    BNflag = true;
                    layoutClose("FM");
                    layoutClose("FD");
                }
                else {
                    layoutClose("BN");
                }
            }
        });

        EDbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonthData.class);
                startActivity(intent);

                layoutClose("FM");
                layoutClose("FD");
                layoutClose("BN");
            }
        });

        FMstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FollowMe.class);
                startActivity(intent);

                layoutClose("FM");
            }
        });

        FDstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FifteenDots.class);
                startActivity(intent);

                layoutClose("FD");
            }
        });

        BNstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Brightnesss.class);
                startActivity(intent);

                layoutClose("BN");
            }
        });
    }

    void layoutClose(String exercise) {
        switch (exercise) {
            case "FM" : FMflag = false;
                        FMlayout.collapse();
                break;
            case "FD" : FDflag = false;
                        FDlayout.collapse();
                break;
            case "BN" : BNflag = false;
                        BNlayout.collapse();
                break;
        }
    }
}
