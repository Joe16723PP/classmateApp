package kku.en.coe.extraproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateClassActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, View.OnClickListener {


    private TextView startDate, endDate, tv_numberPicker;
    private DatePickerDialog.OnDateSetListener starListener, endListener;

    TextView startTime, endTime;
    TimePickerDialog.OnTimeSetListener startTimeListener, endTimeLisener;
    NumberPicker noPicker = null;

    String AmPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);

        startTime = findViewById(R.id.start_timePicker);
        endTime = findViewById(R.id.end_timePicker);
        //NumberPicker
        tv_numberPicker = findViewById(R.id.tv_numberPicker);
        noPicker = findViewById(R.id.nopicker);
        noPicker.setMaxValue(60);
        noPicker.setMinValue(0);
        noPicker.setWrapSelectorWheel(true);
        noPicker.setOnValueChangedListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        tv_numberPicker.setText("= " + newVal);
    }

    @Override
    public void onClick(View v) {
        Calendar cal_StartTime = Calendar.getInstance();
        Calendar cal_StartDate = Calendar.getInstance();
        int hour = cal_StartTime.get(Calendar.HOUR);
        int minute = cal_StartTime.get(Calendar.MINUTE);
        int year = cal_StartDate.get(Calendar.YEAR);
        int month = cal_StartDate.get(Calendar.MONTH);
        int day = cal_StartDate.get(Calendar.DAY_OF_MONTH);

        if (v == startTime) {
            TimePickerDialog dialog_StartTime = new TimePickerDialog(
            CreateClassActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog,
            startTimeListener, hour, minute, false);

            dialog_StartTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_StartTime.show();

            startTimeListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (hourOfDay >= 12){
                        AmPm = "PM";
                    } else {
                        AmPm = "AM";
                    }
                    startTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + AmPm);
                }
            };
        }
            else if (v == endTime){
            TimePickerDialog dialog_StartTime = new TimePickerDialog(
                    CreateClassActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog,
                    endTimeLisener, hour, minute, false);

            dialog_StartTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_StartTime.show();

            endTimeLisener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (hourOfDay >= 12){
                        AmPm = "PM";
                    } else {
                        AmPm = "AM";
                    }
                    endTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + AmPm);
                }
            };
        }
        else if (v == startDate) {
            DatePickerDialog dialog_StartDate = new DatePickerDialog(
                    CreateClassActivity.this,
                    android.R.style.Theme_DeviceDefault_Light_Dialog, starListener, year, month, day);

            dialog_StartDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_StartDate.show();

            starListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date_start = dayOfMonth + "/" + month + "/" + year;
                    startDate.setText(date_start);
                    Toast.makeText(getApplicationContext(),date_start,Toast.LENGTH_LONG).show();
                }
            };
        }
        else if (v == endDate) {
            DatePickerDialog dialog_EndDate = new DatePickerDialog(
                    CreateClassActivity.this,
                    android.R.style.Theme_DeviceDefault_Light_Dialog,endListener,year,month,day);

            dialog_EndDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_EndDate.show();

            endListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date_end = dayOfMonth + "/" + month + "/" + year;
                    endDate.setText(date_end);
                    Toast.makeText(getApplicationContext(),date_end,Toast.LENGTH_LONG).show();
                }
            };

        }

    }
}
