package com.web.udni_sms;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoSettingsActivity extends AppCompatActivity {
    TextView settingsInfo;
    String message="Click on the SIM Card Icons to change numbers";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        settingsInfo = (TextView)findViewById(R.id.info);
        settingsInfo.setText(message);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.exit(0);
    }
}
