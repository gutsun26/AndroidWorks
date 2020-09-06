package com.web.autohomenet.wifirouter;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.web.autohomenet.R;

public class GetRouterDetailsActivity extends AppCompatActivity
{
    EditText ssid,pwd;
    Button setButton;
    String wifiSSID=null,wifiPWD=null,routerIP=null;
    NetworkInfo networkInfoWiFi;
    NetworkInfo[] allNetworkInfo;
    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.getwifidetails);

        ssid        = (EditText)findViewById(R.id.ssid);
        pwd         = (EditText)findViewById(R.id.pwd);
        setButton   = (Button)findViewById(R.id.setButton);
        Intent connectionStatus = getIntent();
        Bundle connectionStatusBundle = connectionStatus.getExtras();
        routerIP = connectionStatusBundle.getString("ROUTER_IP");
        toastMessage(routerIP);
        setButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                wifiSSID = ssid.getText().toString();
                wifiPWD  = pwd.getText().toString();
                Snackbar.make(view, "ROUTER:"+wifiSSID+"ROUTER IP:"+routerIP+"\n"+"PASSWORD:"+wifiPWD, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}
