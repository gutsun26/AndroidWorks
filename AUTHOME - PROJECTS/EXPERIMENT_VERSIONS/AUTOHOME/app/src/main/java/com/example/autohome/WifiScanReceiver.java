package com.example.autohome;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class WifiScanReceiver extends BroadcastReceiver
{
    String gopranWifi = "AUTO_HOME";
    String ssid="";
    Button gopranButton;
    WifiManager mainWifiObj;
    WifiInfo wifiInfo;
    ListView listView;
    String wifis[],wifiList[];
    Intent intent = new Intent();
    TextView textView, msg;
    EditText pass;

    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;
    DhcpInfo dhcpInfo;
    @SuppressLint("UseValueOf")
    public void onReceive(Context c, Intent intent)
    {
        List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
        wifis = new String[wifiScanList.size()];
        for(int i = 0; i < wifiScanList.size(); i++)
        {
            wifis[i] = ((wifiScanList.get(i)).toString());
        }
        String filtered[] = new String[wifiScanList.size()];
        int counter = 0;
        for (String eachWifi : wifis)
        {
            String[] temp = eachWifi.split(",");
            filtered[counter] = temp[0].substring(5).trim();
            counter++;
        }
        setList(filtered);
    }
    public void setList(String filtered[])
    {
        this.wifiList = filtered;
    }
    public String[] getWifiList()
    {
        return wifiList;
    }

}//end of inner class WifiScanReceiver