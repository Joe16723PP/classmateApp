package kku.en.coe.extraproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateClassActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private String TAG = "database";
    private TextView startDate, endDate, startTime, endTime;
    private EditText eventName, eventCount;
    private CheckBox cbMon, cbTue, cbWed, cbThu, cbFri, cbSat, cbSun;
    private DatePickerDialog.OnDateSetListener startDateListener, endDateListener;
    private TimePickerDialog.OnTimeSetListener startTimeListener, endTimeListener;
    String AmPm, sTime, eTime, sDate, eDate;
    private FirebaseAuth mAuth;
    FirebaseUser cur_user;
    Double lat,lng;
    int evt_counter;
    Button sendEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        eventName = findViewById(R.id.etName);
        eventCount = findViewById(R.id.etEventCount);
        startDate = findViewById(R.id.start_datePicker);
        endDate = findViewById(R.id.end_datePicker);
        startTime = findViewById(R.id.start_timePicker);
        endTime = findViewById(R.id.end_timePicker);
        cbMon = findViewById(R.id.cbMon);
        cbTue = findViewById(R.id.cbTue);
        cbWed = findViewById(R.id.cbWed);
        cbThu = findViewById(R.id.cbThu);
        cbFri = findViewById(R.id.cbFri);
        cbSat = findViewById(R.id.cbSat);
        cbSun = findViewById(R.id.cbSun);
        sendEvent = findViewById(R.id.sendEvent);


        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        sendEvent.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        cur_user = mAuth.getCurrentUser();

        getInit();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
//        startTime.setText(String.format("%02d:%02d", 0, 0) + " " + AmPm);
//        endTime.setText(String.format("%02d:%02d", 0, 0) + " " + AmPm);
//        startDate.setText("01/01/2019");
//        endDate.setText("01/01/2019");

        
    }

    private void getInit() {
        Bundle bn = getIntent().getExtras();
        lat = bn.getDouble("lat");
        lng = bn.getDouble("lng");
        Toast.makeText(CreateClassActivity.this, "lat: " + lat + " lng: " + lng, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

//        Intent intent = new Intent(CreateClassActivity.this, TeacherMainActivity.class);
//        intent.putExtra("startTime" , sTime);
//        intent.putExtra("endTime" , eTime);
//        intent.putExtra("startDate" , sDate);
//        intent.putExtra("endDate" , eDate);
//        intent.putExtra("cbMon" , cbMon.isChecked());
//        intent.putExtra("cbTue" , cbTue.isChecked());
//        intent.putExtra("cbWed" , cbWed.isChecked());
//        intent.putExtra("cbThu" , cbThu.isChecked());
//        intent.putExtra("cbFri" , cbFri.isChecked());
//        intent.putExtra("cbSat" , cbSat.isChecked());
//        intent.putExtra("cbSun" , cbSun.isChecked());
//        startActivity(intent);


        Calendar cal_StartTime = Calendar.getInstance();
        Calendar cal_StartDate = Calendar.getInstance();
        int hour = cal_StartTime.get(Calendar.HOUR);
        int minute = cal_StartTime.get(Calendar.MINUTE);
        int year = cal_StartDate.get(Calendar.YEAR);
        int month = cal_StartDate.get(Calendar.MONTH);
        int day = cal_StartDate.get(Calendar.DAY_OF_MONTH);

        if (v == sendEvent) {

            evt_counter = 5;
            String name = cur_user.getDisplayName();
            String email = cur_user.getEmail();
            String evName = eventName.getText().toString();

            String mergeTime = sTime+"," +eTime;
            myRef = database.getReference("users");

            ObjectEvent obj_evt = new ObjectEvent(email , evName,  sDate ,eDate ,mergeTime,evt_counter,lat,lng,0);
            Map<String, Object> Obj_val1 = obj_evt.toMap();

            Map<String, Object> childUpdates = new HashMap<>();
//            childUpdates.put("/users/"+ evName + "/", Obj_val1);
            childUpdates.put( name + "/" + evName + "/", Obj_val1);
            myRef.updateChildren(childUpdates);



            Toast.makeText(CreateClassActivity.this,
                    "Write new user : " + name ,
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CreateClassActivity.this, TeacherMainActivity.class);
            intent.putExtra("name" , cur_user.getDisplayName());
            intent.putExtra("email" , cur_user.getEmail());
            startActivity(intent);
        }

        else if (v == startTime) {
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
                    sTime = startTime.getText().toString();
                }
            };
        }

        else if (v == endTime){
            TimePickerDialog dialog_StartTime = new TimePickerDialog(
                    CreateClassActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog,
                    endTimeListener, hour, minute, false);

            dialog_StartTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_StartTime.show();

            endTimeListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (hourOfDay >= 12){
                        AmPm = "PM";
                    } else {
                        AmPm = "AM";
                    }
                    endTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + AmPm);
                    eTime = endTime.getText().toString();
                }
            };
        }

        else if (v == startDate) {
            DatePickerDialog dialog_StartDate = new DatePickerDialog(
                    CreateClassActivity.this,
                    android.R.style.Theme_DeviceDefault_Light_Dialog, startDateListener, year, month, day);

            dialog_StartDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_StartDate.show();

            startDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date_start = dayOfMonth + "/" + month + "/" + year;
                        startDate.setText(date_start);
                        Toast.makeText(getApplicationContext(), date_start, Toast.LENGTH_LONG).show();

                        sDate = startDate.getText().toString();
                }
            };
        }
        else if (v == endDate) {
            DatePickerDialog dialog_EndDate = new DatePickerDialog(
                    CreateClassActivity.this,
                    android.R.style.Theme_DeviceDefault_Light_Dialog,endDateListener,year,month,day);

            dialog_EndDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            dialog_EndDate.show();

            endDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date_end = dayOfMonth + "/" + month + "/" + year;
                        endDate.setText(date_end);
                        Toast.makeText(getApplicationContext(), date_end, Toast.LENGTH_LONG).show();

                        eDate = endDate.getText().toString();
                }
            };

        }
    }



    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.cbMon:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Monday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cbTue:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Tuesday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cbWed:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Wednesday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cbThu:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Thursday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cbFri:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Friday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cbSat:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Saturday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cbSun:
                if (checked){
                    Toast.makeText(getApplicationContext(), "Choose Sunday", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Un-Checked", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}
