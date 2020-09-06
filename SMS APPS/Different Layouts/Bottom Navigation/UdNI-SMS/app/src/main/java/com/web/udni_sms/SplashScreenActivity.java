package com.web.udni_sms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreenActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, CreateUserActivity.class));
                SplashScreenActivity.this.finish();
            }
        }, 5000);
    }//end of onCreate()
    @Override
    public void onBackPressed() {
        SplashScreenActivity.this.finish(); // Remove this
        super.onBackPressed();
    }
}