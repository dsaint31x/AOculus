package com.example.sol.oculus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthData extends AppCompatActivity {
    TextView selectDayView;
    CalendarView calendarView;
    Calendar calendar = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_data);

        //handler = new TimeHandler();
        selectDayView = (TextView)findViewById(R.id.selectDay);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectDayView.setText("선택한 날짜 : "+ year +" / "+(month+1)+" / "+dayOfMonth);

            }
        });

    }
}
