package kku.en.coe.extraproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener {

    Button teacher, student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
//        getSupportActionBar().hide();
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        student = findViewById(R.id.btn_student);
        teacher = findViewById(R.id.btn_teacher);
        student.setOnClickListener(this);
        teacher.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == student) {
            Intent intent = new Intent(StatusActivity.this,JoinClassActivity.class);
            startActivity(intent);
        }
        else if (v == teacher) {
            Intent intent = new Intent(StatusActivity.this,TeacherMainActivity.class);
            startActivity(intent);
        }
    }
}
