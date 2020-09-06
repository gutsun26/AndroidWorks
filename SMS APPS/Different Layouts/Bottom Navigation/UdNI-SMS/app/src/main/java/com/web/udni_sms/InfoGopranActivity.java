package com.web.udni_sms;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoGopranActivity extends AppCompatActivity {
    TextView textInfo;
    String message = "UdNi-SMS ver 1.0\n\nDeveloped by Gopran Industries\n\nA31,32, MIDC Phase I, Dombivli(E)\n\nPin-421203\n\nContact: 75069 35680, 99203 59154\n\nwwww.gopranindustries.in";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        textInfo        =       (TextView)findViewById(R.id.info);
        textInfo.setText(message);
        textInfo.setTextColor(Color.WHITE);
        textInfo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }//end of onCreate()

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.exit(0);
    }
}
