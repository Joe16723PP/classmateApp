package kku.en.coe.extraproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class FacebookLogin extends AppCompatActivity {
    CallbackManager callbackManager;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private static final String TAG = "FacebookLogin";
    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
//        mUser = mAuth.getCurrentUser();
//        if (mUser == null){
//            FacebookSdk.sdkInitialize(getApplicationContext());
//            callbackManager = CallbackManager.Factory.create();
//            btnClickFBLogin();
//        } else {
//            FirebaseUser myUserObj = mAuth.getCurrentUser();
//            updateUser(myUserObj);
//        }
    }

    public void btnClickFBLogin () {
        Log.d(TAG,"err");
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
//                Toast.makeText(getApplicationContext(), "Success onSuccess", Toast.LENGTH_LONG).show();
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
    public void onStart() {
        Toast.makeText(getApplicationContext(), "Facebook login page", Toast.LENGTH_LONG).show();
        super.onStart();
        btnClickFBLogin();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        // updateUI(currentUser);
//        updateUser(currentUser);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateUser(FirebaseUser myUserObj) {
        Intent intent = new Intent(this, StatusActivity.class);
        String email = myUserObj.getEmail();
        intent.putExtra("email", email);
        Toast.makeText(FacebookLogin.this, email,
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
        //startActivityForResult(intent, REQUEST_CODE);
    }
}

