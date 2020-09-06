package com.example.autohome;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigInteger;
import java.net.InetAddress;

import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestWifiCon extends AppCompatActivity
{
    TextView receivedText, signal;
    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;

    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    String mobileIpAddress;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wificontest);
        receivedText = findViewById(R.id.receivedText);
        signal = findViewById(R.id.signal);
        mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        dhcpInfo = mainWifiObj.getDhcpInfo();
        int ip = dhcpInfo.gateway;
        ip = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ? Integer.reverseBytes(ip) : ip;
        byte[] ipAddressByte = BigInteger.valueOf(ip).toByteArray();
        try
        {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            wifiIpAddress = myAddr.getHostAddress();
            Toast.makeText(this, "ROUTER:" + wifiIpAddress, Toast.LENGTH_SHORT).show();
            WifiInfo wifiInfo = mainWifiObj.getConnectionInfo();
            int phoneIp = wifiInfo.getIpAddress();
            mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            Toast.makeText(this, "YOUR IP:" + mobileIpAddress, Toast.LENGTH_SHORT).show();
            if(!mobileIpAddress.isEmpty())
            {
                receivedText.setText("CONNECTED TO AUTO_HOME"+" YOUR IP ADDRESS IS:"+mobileIpAddress);
            }
            else
            {
                receivedText.setText("NOT CONNECTED TO AUTO_HOME");
            }
        }
        catch (UnknownHostException uhe)
        {
            Toast.makeText(this, "UNKNOWN HOST EXCEPTION", Toast.LENGTH_SHORT).show();
            receivedText.setText("NOT CONNECTED TO AUTO_HOME");
        }
    }
    public void checkStrength(View view)
    {
        signal.setText(" ");
        try
        {
            TimeUnit.SECONDS.sleep(4);
            int rssi = mainWifiObj.getConnectionInfo().getRssi();
            int strength = WifiManager.calculateSignalLevel(rssi, 5);
            signal.setText("Signal strength is " + strength + " out of 5");
        }
        catch(InterruptedException ie){}
    }

    public void receive(View view)
    {
        Intent testReception = new Intent(this, TestReception.class);
        startActivity(testReception);
    }
}