package com.example.user.lmsautovista.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.SessionManagement;

public class SplashActivity extends AppCompatActivity {

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Session class instance
        session = new SessionManagement(getApplicationContext());

        session.checkLogin();
        if (session.isLoggedIn()){
            launchLoginScreen();
        }

     /*   Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferenceManager.getInstance(SplashActivity.this).getPreference(Constants.ROLE_ID, "").length() == 0) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 3000);*/
    }

    private void launchLoginScreen(){
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, secondsDelayed * 3000);
    }

}
