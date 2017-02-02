package com.example.sol.oculus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthData extends AppCompatActivity {
    TextView selectDayView;
    TextView selecData;
    TextView todayData;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_data);

        //handler = new TimeHandler();
        selectDayView = (TextView)findViewById(R.id.selectDay);
        selecData = (TextView) findViewById(R.id.selectData);
        todayData = (TextView) findViewById(R.id.todayData);

        Calendar calendar = new GregorianCalendar();
        String today = calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1) + "" + calendar.get(Calendar.DAY_OF_MONTH);

        String todayFM = "FollowMe : "+LoadData("FM", today);
        String todayFD = "FifteenDots : "+LoadData("FD", today);
        String todayBN = "Brightness : "+LoadData("BN", today);

        todayData.setText(todayFM +"\n"+ todayFD +"\n"+ todayBN);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectDayView.setText("선택한 날짜 : "+ year +" / "+(month + 1)+" / "+dayOfMonth);

                String selectDay = year + "" + (month + 1) + "" + dayOfMonth;
                String FM = "FollowMe : "+LoadData("FM", selectDay);
                String FD = "FifteenDots : "+LoadData("FD", selectDay);
                String BN = "Brightness : "+LoadData("BN", selectDay);

                selecData.setText(FM +"\n"+ FD +"\n"+ BN);
            }
        });
    }


    public int LoadData(String Exercise, String day){
        SharedPreferences exerciseData = getSharedPreferences("ExerciseData", MODE_PRIVATE);
        return exerciseData.getInt(Exercise + day, 0);
    }
}
