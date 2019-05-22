package kku.en.coe.extraproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailsEvent extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser cur_user;
    FirebaseAuth mAuth;

    String name, cur_evtName ,days_log;
    Double lat, lng;

    TextView eventNameHeader, eventName, dayEvent, timeEvent, startDate, endDate, countEvent ,evt_log;
    Button checkBtn, mapBtn;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_event);

        mAuth = FirebaseAuth.getInstance();
        cur_user = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        name = cur_user.getDisplayName();

        eventNameHeader = findViewById(R.id.tv_eventImg);
        eventName = findViewById(R.id.tv_event2);
        dayEvent = findViewById(R.id.tv_day2);
        timeEvent = findViewById(R.id.tv_time2);
        startDate = findViewById(R.id.tv_strdate2);
        endDate = findViewById(R.id.tv_enddate2);
        countEvent = findViewById(R.id.tv_eventCount2);
        checkBtn = findViewById(R.id.checkEventBtn);
        mapBtn = findViewById(R.id.mapBtn);
        evt_log = findViewById(R.id.days_log);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        getInitUser();


        myRef.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count" ,""+dataSnapshot.getChildrenCount());
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    String evt_name = keyNode.getValue(ObjectEvent.class).getEvName().toString();
                    if (cur_evtName.equals(evt_name)) {
//                        Log.e("fbdb", keyNode.getKey());
                        ObjectEvent objectEvent = keyNode.getValue(ObjectEvent.class);
                        Log.d("evtn",objectEvent.getEvName());
                        eventNameHeader.setText(objectEvent.getEvName());
                        eventName.setText(objectEvent.getEvName());
                        dayEvent.setText(objectEvent.getDayofclass());
                        timeEvent.setText(objectEvent.getTime());
                        startDate.setText(objectEvent.getStart_date());
                        endDate.setText(objectEvent.getEnd_date());
                        countEvent.setText(String.valueOf(objectEvent.getEvn_cnt()));
                        evt_log.setText(String.valueOf(objectEvent.getDays_log()));

                        try {
                            days_log = String.valueOf(objectEvent.getDays_log());
                        }catch (Exception e) {
                            Log.e("daylog","err with : " + e);
                        }

                        count = objectEvent.getEvn_cnt();
                        lat = objectEvent.getLat();
                        lng = objectEvent.getLng();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        checkBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);
    }

    private void getInitUser() {

        Bundle extras = getIntent().getExtras();
        cur_evtName = extras.getString("event_name");

    }

    @Override
    public void onClick(View v) {
        if (v == checkBtn){
            count = count - 1;
            countEvent.setText(String.valueOf(count));

            Toast.makeText(this,"Checked ! Event have " + count + " times left.",Toast.LENGTH_LONG).show();
            checkBtn.setEnabled(false);

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);

            days_log = days_log + "\n" + formattedDate;

            myRef.child(name).child(cur_evtName).child("evn_cnt").setValue(count);
            myRef.child(name).child(cur_evtName).child("days_log").setValue(days_log);



        }
        else if (v == mapBtn){
            Intent intent = new Intent(this,MapsActivity_DB.class);
            intent.putExtra("lat" ,lat);
            intent.putExtra("lng" , lng);
            startActivity(intent);
        }
    }
}
