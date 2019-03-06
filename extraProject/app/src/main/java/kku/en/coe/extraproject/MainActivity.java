package kku.en.coe.extraproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button LoginBtn;
    EditText UserName;
    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginBtn = findViewById(R.id.loginBtn);
        LoginBtn.setOnClickListener(this);
        UserName = findViewById(R.id.UserName);
    }


    @Override
    public void onClick(View view) {
        if (view == LoginBtn) {
            Intent intent = new Intent(this, SecondActivity.class);
            String getUserName = UserName.getText().toString();
            intent.putExtra("userName", getUserName);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String rtv =  data.getStringExtra("rtv");
            TextView txtview = (TextView) findViewById(R.id.appName);
            txtview.setText("User : " + rtv);
            Log.d("chk", "onActivityResult: " + rtv);
        }
    }
}
