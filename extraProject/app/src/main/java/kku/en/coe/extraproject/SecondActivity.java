package kku.en.coe.extraproject;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button signOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setIntentData();
        signOutBtn = findViewById(R.id.sign_out);
        signOutBtn.setOnClickListener(this);

    }

    public void setIntentData() {
        Bundle extras = getIntent().getExtras();
        String inputString = extras.getString("name");
        TextView view = (TextView) findViewById(R.id.curUser);
        view.setText(inputString);
    }

    public void signOut() {
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // [END auth_sign_out]
    }

    @Override
    public void onClick(View v) {
        if ( v == signOutBtn ) {
            signOut();
        }
    }
}
