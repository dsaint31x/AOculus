package com.example.sol.oculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }

    public void FMButtonClicked(View view) {
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

    public void FDButtonClicked(View view) {
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

    public void BNButtonClicked(View view) {
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

    public void EDButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), MonthData.class);
        startActivity(intent);
    }

    public void FMStartClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), FollowMe.class);
        startActivity(intent);

        layoutClose("FM");
    }

    public void FDStartClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), FifteenDots.class);
        startActivity(intent);

        layoutClose("FD");
    }

    public void BNStartClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), Brightnesss.class);
        startActivity(intent);

        layoutClose("BN");
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
