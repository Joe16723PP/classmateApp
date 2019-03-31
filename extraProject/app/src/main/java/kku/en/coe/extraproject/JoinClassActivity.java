package kku.en.coe.extraproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JoinClassActivity extends AppCompatActivity {

    Button joinClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);
        joinClass = findViewById(R.id.btn_addCode);
        joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinClassActivity.this,ClassDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
