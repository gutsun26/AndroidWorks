package com.web.udni_sms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddSiteActivity extends AppCompatActivity
{
    EditText siteName, siteNumber, siteAddress_1, siteAddress_2;
    Button regBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        siteName        = (EditText)findViewById(R.id.siteName);
        siteNumber      = (EditText)findViewById(R.id.siteNumber);
        siteAddress_1   = (EditText)findViewById(R.id.siteAddress_1);
        siteAddress_2   = (EditText)findViewById(R.id.siteAddress_2);
        //regBtn          = (Button)findViewById(R.id.regBtn);
    }
}
