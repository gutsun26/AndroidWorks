package com.web.udni_sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //imageView = (ImageView)findViewById(R.id.sphasImage);
        //toast("Splash Screen");
        getSupportActionBar().hide();
        //imageView.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                startActivity(new Intent(SplashActivity.this, CreateUserActivity.class));
                //SplashActivity.this.finish();
                //toast("My Splash Screen");
            }
        }, 5000);

    }
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }//end of toast
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
