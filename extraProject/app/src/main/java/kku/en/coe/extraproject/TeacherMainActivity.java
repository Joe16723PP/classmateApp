package kku.en.coe.extraproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.net.URL;

public class TeacherMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    FloatingActionButton fab;
    TextView user_name , user_email;
    NavigationView navigationView;
    private FirebaseAuth mAuth;
    FirebaseUser cur_user;
    ImageView profile_pic;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        layout = findViewById(R.id.layout_cardView);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getInitUser();
//        createCardView();
////        for (int i = 0; i < 2; i++) {
////            createCardView();
////        }
    }
    private void createCardView(){
        CardView cardview = new CardView(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        cardview.setLayoutParams(p);
//        cardView.setRadius(12);
//        cardView.setMaxCardElevation(30);
//        cardView.setMaxCardElevation(6);
//        cardView.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
//        layout.addView(cardView , p);
        cardview.setRadius(15);

//        cardview.setPadding(25, 25, 25, 25);

//        cardview.setCardBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));

        cardview.setMaxCardElevation(30);

        cardview.setMaxCardElevation(6);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setWeightSum(3);

        TextView textView = new TextView(this);
        textView.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        textView.setLayoutParams(p);

        LinearLayout sub_linear = new LinearLayout(this);
        sub_linear.setLayoutParams(p);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sub_linear.setElevation(2);
        }
        sub_linear.setPadding(10,10,10,10);

        TextView tv2 = new TextView(this);
        tv2.setLayoutParams(p);
        tv2.setText("CardView Programmatically");
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        tv2.setTextColor(Color.WHITE);
        tv2.setPadding(25,25,25,25);
        tv2.setGravity(Gravity.CENTER);

        sub_linear.addView(tv2);
        linearLayout.addView(textView);
        linearLayout.addView(sub_linear);
        cardview.addView(linearLayout);


//        TextView textview = new TextView(this);
//
//        textview.setLayoutParams(p);
//
//        textview.setText("CardView Programmatically");
//
//        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
//
//        textview.setTextColor(Color.WHITE);
//
//        textview.setPadding(25,25,25,25);
//
//        textview.setGravity(Gravity.CENTER);
//
//        cardview.addView(textview);
        layout.addView(cardview);

    }
    private void getInitUser() {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String email = extras.getString("email");
//        String name = cur_user.getDisplayName();
//        String email = cur_user.getEmail();
        cur_user = mAuth.getCurrentUser();
        View header = navigationView.getHeaderView(0);
        user_name = header.findViewById(R.id.user_name);
        user_email = header.findViewById(R.id.user_email);
        profile_pic = header.findViewById(R.id.imageView);
        Uri selectedImg = cur_user.getPhotoUrl();
        new DownLoadImageTask(profile_pic).execute(String.valueOf(selectedImg));
        user_name.setText(name);
        user_email.setText(email);
    }
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teacher_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            doSignOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void doSignOut() {
        Toast.makeText(TeacherMainActivity.this, "กำลังออกจากระบบ",
                Toast.LENGTH_LONG).show();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
