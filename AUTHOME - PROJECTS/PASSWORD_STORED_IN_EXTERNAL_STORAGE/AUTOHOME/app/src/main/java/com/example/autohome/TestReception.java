package com.example.autohome;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.format.Formatter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;

public class TestReception extends AppCompatActivity
{
    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    TextView receivedText;
    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;
    String mobileIpAddress;
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.receive);

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
        }
        catch (UnknownHostException uhe)
        {
            Toast.makeText(this, "UNKNOWN HOST EXCEPTION", Toast.LENGTH_SHORT).show();
        }
        Client client = new Client();
    }

    class Client extends Thread
    {

        public void run()
        {
            try
            {
                Socket socket = new Socket(wifiIpAddress, wifiPortNumb);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                receivedText.setText(response);
                socket.close();
            }
            catch(Exception e) {}
        }
    }
}