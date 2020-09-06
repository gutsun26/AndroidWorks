package com.web.wifilanscan;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ScanAutoHome extends AppCompatActivity
{

    WifiManager wifiManager;
    ListView listView;
    Button buttonScan, autoHome;
    List<ScanResult> results;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;
    String AUTO_HOME = "AUTO_HOME";
    ProgressDialog progressDialog;
    String ssid=null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanwifi);
        autoHome = findViewById(R.id.autoHome);
        autoHome.setVisibility(View.INVISIBLE);
        listView = findViewById(R.id.wifiList);
        buttonScan = findViewById(R.id.buttonScan);

        /*ENABLING WIFI*/
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanWifi();
        setProgressDialog();
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanWifi();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setBackgroundColor(Color.BLUE);
                TextView ssidText = (TextView)view;
                ssidText.setTextColor(Color.WHITE);
                ssid = ((TextView) view).getText().toString();
            }
        });
    }
    public void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
    public void setProgressDialog()
    {
        progressDialog = ProgressDialog.show(this, "LOADING WIFIS...", "PLEASE WAIT...!", true);
        CountDownTimer timer = new CountDownTimer(8000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }
            @Override
            public void onFinish()
            {
                progressDialog.dismiss();
            }
        }.start();
    }
    private void scanWifi()
    {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.setWifiEnabled(true);
        boolean message = wifiManager.startScan();
        if(message == true)
        {
            toastMessage("Scanning WiFi...");
        }
        else
        {
            toastMessage("Cannot Scan...!");
        }

    }
    BroadcastReceiver wifiReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for (ScanResult scanResult : results)
            {
                arrayList.add(scanResult.SSID);
                adapter.notifyDataSetChanged();
            }
        }
    };
}