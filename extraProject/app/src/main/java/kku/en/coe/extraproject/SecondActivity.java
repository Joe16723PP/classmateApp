package kku.en.coe.extraproject;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.EditText;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setIntentData();


    }
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
        if (intent != null) {
            startActivity(intent);
        }

    }
    public void setIntentData() {
        Bundle extras = getIntent().getExtras();
        String inputString = extras.getString("userName");
        TextView view = (TextView) findViewById(R.id.userName);
        view.setText(inputString);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        TextView returnVal = findViewById (R.id.userName);
        String rtv = returnVal.getText().toString();
        intent.putExtra("rtv", rtv);
        // return the Intent to the application
        setResult(RESULT_OK, intent);
        super.finish();

    }
}
