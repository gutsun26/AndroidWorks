package com.example.autohome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SpashScreen extends AppCompatActivity
{
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SpashScreen.this.startActivity(new Intent(SpashScreen.this, MainActivity.class));
                SpashScreen.this.finish();
            }
        }, 3000);
    }
}
