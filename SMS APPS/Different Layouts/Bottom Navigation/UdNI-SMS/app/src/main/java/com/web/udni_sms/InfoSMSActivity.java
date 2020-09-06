package com.web.udni_sms;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoSMSActivity extends AppCompatActivity {
    TextView smsInfo;
    String message= "1) This screen has two views"+
                        "\n\n\t\ta) The status of SMS received, organized \n\t\tin HORIZONTAL scroll" +
                        "\n\n\t\tb) The Command Texts/Icons, organized \n\t\tin VERTICAL SCROLL\n\n"+
                    "2) Click on the Command Texts/Icons to send SMS and get the status update";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        smsInfo = (TextView)findViewById(R.id.info);
        smsInfo.setText(message);

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.exit(0);
    }
}
