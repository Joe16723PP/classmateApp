package kku.en.coe.extraproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.google.android.gms.common.api.ApiException;
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
//    LoginButton loginButton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button testLoginBtn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 101;
    ImageButton googleSignIn;
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
//            loginButton = findViewById(R.id.login_button);
//            loginButton.setOnClickListener(this);
//            testLoginBtn = findViewById(R.id.testLogin);
//            testLoginBtn.setOnClickListener(this);
            callbackManager = CallbackManager.Factory.create();
            googleSignIn = findViewById(R.id.gmail_login_button);
//            loginButton.setReadPermissions(Arrays.asList("email"));
            googleSignIn.setOnClickListener(this);
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
                Toast.makeText(getApplicationContext(), "Logging in to ClassMate.", Toast.LENGTH_LONG).show();
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
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
//        updateUser(currentUser);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }


//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();
//            String str = "name: " + name +
//                    "\nemail: " + email +
//                    "\nuid: " + uid +
//                    "\nphotoUrl: " + photoUrl;
//            Toast.makeText(MainActivity.this, str,
//                    Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, SecondActivity.class);
//            intent.putExtra("email", email);
//            startActivity(intent);
//        }
//    }

    private void updateUser(FirebaseUser myUserObj) {
        Intent intent = new Intent(this, TeacherMainActivity.class);
        String email = myUserObj.getEmail();
        String name = myUserObj.getDisplayName();
        intent.putExtra("email", email);
        intent.putExtra("name", name);
        Toast.makeText(MainActivity.this, email + name,
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
        //startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
//        if (v == loginButton) {
////            Intent intent = new Intent(this,FacebookLogin.class);
////            startActivity(intent);
//            btnClickFBLogin(v);
//        }
        if (v == googleSignIn){
            Intent intent = new Intent(this,GmailLogin.class);
            startActivity(intent);
        }
//        else if (v == testLoginBtn) {
//            Intent intent = new Intent(this, StatusActivity.class);
//            startActivity(intent);
//        }
    }
}