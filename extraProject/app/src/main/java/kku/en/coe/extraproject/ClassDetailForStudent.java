package kku.en.coe.extraproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClassDetailForStudent extends AppCompatActivity {


    int attendance = 0 ;
    int numberOfRemin = 15 ;
    int total = 15 ;

    int praSent = 0 ;
    int abSent = 0 ;

    TextView tvPrasent, tvAbsent, tvAttendance, tvNumberOfRemin, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail_for_student);

        tvPrasent = (TextView)findViewById(R.id.tvPresent);
        tvPrasent.setText(praSent+getResources().getString(R.string.emty));

        tvAbsent = (TextView)findViewById(R.id.tvAbsent);
        tvAbsent.setText(abSent+getResources().getString(R.string.emty));

        tvAttendance = (TextView)findViewById(R.id.tvAttendance);
        tvAttendance.setText(attendance+getResources().getString(R.string.emty));

        tvNumberOfRemin = (TextView)findViewById(R.id.tvNumberOfRemin);
        tvNumberOfRemin.setText(numberOfRemin+getResources().getString(R.string.emty));

        tvTotal = (TextView)findViewById(R.id.tvTotal);
        tvTotal.setText(total+getResources().getString(R.string.emty));
    }
}
