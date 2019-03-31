package kku.en.coe.extraproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CallbackManager callbackManager;
    LoginButton loginButton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView status;
    private static final String TAG = "FacebookLogin";
    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser == null){
            setContentView(R.layout.activity_main);
            FacebookSdk.sdkInitialize(getApplicationContext());
            loginButton = findViewById(R.id.login_button);
            loginButton.setOnClickListener(this);
            callbackManager = CallbackManager.Factory.create();
            status = findViewById(R.id.status);
            loginButton.setReadPermissions(Arrays.asList("email"));
        } else {
            FirebaseUser myUserObj = mAuth.getCurrentUser();
//            updateUI(myUserObj);
            updateUser(myUserObj);
        }
    }

    public void btnClickFBLogin (View v) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
                Toast.makeText(getApplicationContext(), "Success onSuccess", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "User cancelled it", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser myUserObj = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Success OnHandle", Toast.LENGTH_LONG).show();
                            updateUser(myUserObj);
                        } else {
                            Toast.makeText(getApplicationContext(),"Could not register to firebase" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser myUserObj) {
        status.setText(myUserObj.getEmail());
        Log.d(TAG, "updateUI: " + myUserObj.getEmail());
    }

    private void updateUser(FirebaseUser myUserObj) {
        Intent intent = new Intent(this, SecondActivity.class);
        String getName = myUserObj.getEmail();
        intent.putExtra("name", getName);
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            btnClickFBLogin(v);
        }
    }
}
