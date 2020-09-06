package com.example.autohome;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.InetAddress;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WifiCon extends AppCompatActivity
{
    Button checkReception,roomChoice;
    TextView connectionStatus, signal,receivedText;
    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;

    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    String mobileIpAddress;

    Socket socket;
    DataOutputStream dataOutputStream;
    String message = "TEST";
    String responseString;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wificontest);
        connectionStatus = findViewById(R.id.constat);
        receivedText = findViewById(R.id.receivedText);
        signal = findViewById(R.id.signal);
        checkReception = findViewById(R.id.checkReception);
        roomChoice = findViewById(R.id.roomChoice);
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
                //connectionStatus.setText("APP CONNECTED TO AUTO_HOME WIFI"+" YOUR IP ADDRESS IS:"+mobileIpAddress);
                connectionStatus.setText("CONNECTED TO AUTO_HOME WIFI");
            }
            else
            {
                connectionStatus.setText("NOT CONNECTED TO AUTO_HOME");
            }
        }
        catch (UnknownHostException uhe)
        {
            Toast.makeText(this, "UNKNOWN HOST EXCEPTION", Toast.LENGTH_SHORT).show();
        }

        checkReception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                checkReception();
            }
        });
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

    public void checkReception()
    {
        ConnectionTask connectionTask = new ConnectionTask();
        connectionTask.execute(message);
    }

    class ConnectionTask extends AsyncTask<String, Void, String>
    {
        String ipadd="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ipadd=wifiIpAddress;
            Toast.makeText(getApplicationContext(), "IP:"+ipadd, Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                socket = new Socket();
                socket.connect(new InetSocketAddress(wifiIpAddress, wifiPortNumb),5000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                StringBuilder stringBuilder = new StringBuilder(message);
                String formattedMessage = stringBuilder.append("\r").toString();
                int length = formattedMessage.length();
                String intToLength = String.valueOf(length);
                TimeUnit.SECONDS.sleep(1);
                byte[] b = formattedMessage.getBytes();
                dataOutputStream .write(b);
                dataOutputStream .flush();
                //dataInputStream = new DataInputStream(socket.getInputStream());
                //byte response = dataInputStream.readByte();
                //responseString = Byte.toString(response);
                TimeUnit.SECONDS.sleep(2);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = input.readLine();
                responseString = message;
                char a[] = responseString.toCharArray();
                String displayMessage = Character.toString(a[0])+Character.toString(a[1])+Character.toString(a[2])+Character.toString(a[3])+
                            Character.toString(a[4])+Character.toString(a[5])+Character.toString(a[6])+Character.toString(a[7])+
                            Character.toString(a[8])+Character.toString(a[9])+Character.toString(a[10])+Character.toString(a[11])+
                            Character.toString(a[12])+Character.toString(a[13])+Character.toString(a[14])+Character.toString(a[15])+
                            Character.toString(a[16])+Character.toString(a[17])+Character.toString(a[18])+Character.toString(a[19])+
                            Character.toString(a[20])+Character.toString(a[21]);
                String all = "RESPONSE:"+displayMessage;
                dataOutputStream .close();
                //dataInputStream.close();
                //String all = ipadd+"-Length:"+intToLength+" "+"RESPONSE:"+responseString;
                //String all = "RESPONSE:"+displayMessage;
                socket.close();
                return all;
            }
            catch(InterruptedException ie){return ie.toString()+" Inside doInBackground()";}
            catch(SocketException se){return se.toString()+" Inside doInBackground()";}
            catch(IOException ioe){return ioe.toString()+" Inside doInBackground()";}
        }

        @Override
        protected void onPostExecute(String ipadd)
        {
            Toast.makeText(getApplicationContext(), "SENT:"+message+" TO "+ipadd, Toast.LENGTH_LONG).show();
            try
            {
                TimeUnit.SECONDS.sleep(1);
                receivedText.setText(ipadd);
            }
            catch(InterruptedException ie){receivedText.setText("onPostExecute() "+ie.toString()+" "+responseString);}
            //catch(SocketException se){hv.setText("onPostExecute() "+se.toString()+" "+responseString);}
            //catch (IOException ioe){hv.setText("onPostExecute() "+ioe.toString()+" "+responseString);}

        }
    }
}