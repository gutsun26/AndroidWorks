package com.example.autohome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
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
import java.util.concurrent.TimeUnit;

public class GetRoomConfiguration extends AppCompatActivity
{
    /*VIEW COMPONENTS OF THE PRESENT ACTIVITY*/
    Button getRoomConfig,enterRoom;
    TextView configuration,show,conStatMsg;
    String connectionStatusString=null;

    /*CONTROL COMPONENTS OF THE PRESENT ACTIVITY*/
    Socket socket = new Socket();
    String wifiIpAddress        =   "";
    int    wifiPortNumb        =   80;
    DataOutputStream dataOutputStream;
    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    String mobileIpAddress="";

    /*MODEL COMPONENTS OF THE PRESENT ACTIVITY*/
    String message="TEST";
    String wait = "PLEASE WAIT...";
    String responseString="";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomconfig);
        /*MAPPING UI WITH XML*/
        getRoomConfig = findViewById(R.id.getRoomConfig);
        configuration = findViewById(R.id.configuration);
        conStatMsg = findViewById(R.id.conStatMsg);
        show = findViewById(R.id.show);
        enterRoom=findViewById(R.id.enterRoom);

        getRoomConfig.setTextColor(Color.RED);
        getRoomConfig.setBackgroundColor(Color.WHITE);

        Intent connectionStatus = getIntent();
        Bundle connectionStatusBundle = connectionStatus.getExtras();
        connectionStatusString = connectionStatusBundle.getString("SSID");

        show.setVisibility(View.INVISIBLE);
        enterRoom.setVisibility(View.INVISIBLE);

        /*SETTING UP EVENT MANAGEMENT*/
        getRoomConfig.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getRoomConfiguration();
            }
        });

        mainWifiObj = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        dhcpInfo = mainWifiObj.getDhcpInfo();
        int ip = dhcpInfo.gateway;
        ip = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ? Integer.reverseBytes(ip) : ip;
        byte[] ipAddressByte = BigInteger.valueOf(ip).toByteArray();
        try
        {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            wifiIpAddress = myAddr.getHostAddress();
            //Toast.makeText(this,"ROUTER:"+wifiIpAddress, Toast.LENGTH_SHORT).show();
            WifiInfo wifiInfo = mainWifiObj.getConnectionInfo();
            int phoneIp = wifiInfo.getIpAddress();
            mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            if(mobileIpAddress != null)
            {
                conStatMsg.setText("CONNECTION SUCCESSFUL!\nCONNECTED TO:"+connectionStatusString);
            }
            Toast.makeText(this,"YOUR IP ADDRESS:"+mobileIpAddress, Toast.LENGTH_SHORT).show();
        }
        catch(UnknownHostException uhe)
        {
            conStatMsg.setText("CONNECTION FAILED");
            conStatMsg.setTextColor(Color.RED);
            getRoomConfig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goBack();
                }
            });
            //Toast.makeText(this,"APP IS DISCONNECTED FROM THE AUTOMATION DEVICE!", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack()
    {
        finish();
        Intent basicIntent = new Intent(this, MainActivity.class);
        startActivity(basicIntent);
    }

    public void getRoomConfiguration()
    {
        //Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        getRoomConfig.setTextColor(Color.WHITE);
        getRoomConfig.setBackgroundColor(Color.RED);
        Toast.makeText(this,wait, Toast.LENGTH_SHORT).show();
        MyTask myTask = new MyTask();
        myTask.execute(message);
    }

    class MyTask extends AsyncTask<String, Void, String>
    {
        String result=null,exception=null;
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
                //TimeUnit.MILLISECONDS.sleep(500);
                TimeUnit.SECONDS.sleep(1);
                byte[] b = formattedMessage.getBytes();
                dataOutputStream .write(b);
                dataOutputStream .flush();
                //TimeUnit.MILLISECONDS.sleep(500);
                TimeUnit.SECONDS.sleep(2);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String returnedMessage = input.readLine();
                responseString = returnedMessage;
                dataOutputStream .close();
                //String all = "RESPONSE:"+responseString;
                final String all = responseString;
                //String all = message;
                socket.close();
                return all;
            }
            catch(InterruptedException ie){return ie.toString()+" Inside doInBackground()";}
            catch(SocketException se){return se.toString()+" Inside doInBackground()";}
            catch(IOException ioe){return ioe.toString()+" Inside doInBackground()";}
        }
        protected void onPostExecute(String responseStringReceived)
        {
            //Toast.makeText(getApplicationContext(), "SENT:"+message, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "RECEIVED:"+responseStringReceived, Toast.LENGTH_LONG).show();
            try
            {
                TimeUnit.SECONDS.sleep(1);
                int i=0;
                StringBuffer sbb = new StringBuffer();
                for(i=0; i<responseStringReceived.length(); i++)
                {
                    if(responseStringReceived.charAt(i) == '0' || responseStringReceived.charAt(i) == '1' || responseStringReceived.charAt(i) == '2' || responseStringReceived.charAt(i) == '3' || responseStringReceived.charAt(i) == '4' || responseStringReceived.charAt(i) == '5' || responseStringReceived.charAt(i) == '6')
                    {
                        sbb.append(responseStringReceived);
                        configuration.setText("RECEIVED SWITCH STATUS");
                        show.setVisibility(View.VISIBLE);
                        show.setText(responseStringReceived);
                        enterRoom.setVisibility(View.VISIBLE);
                        enterRoom.setTextColor(Color.RED);
                        enterRoom.setBackgroundColor(Color.WHITE);
                        enterRoom.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                enterRoom();
                            }
                        });
                    }
                    else
                    {
                        configuration.setText("SWITCH STATUS NOT ESTABLISED! CLICK AGAIN!");
                    }
                }

            }
            catch(InterruptedException ie){configuration.setText("onPostExecute() "+ie.toString()+" "+responseString);}
            //catch(SocketException se){hv.setText("onPostExecute() "+se.toString()+" "+responseString);}
            //catch (IOException ioe){hv.setText("onPostExecute() "+ioe.toString()+" "+responseString);}
        }
    }
    public void enterRoom()
    {
        enterRoom.setTextColor(Color.WHITE);
        enterRoom.setBackgroundColor(Color.RED);
        String receivedString = responseString;
        Toast.makeText(getApplicationContext(), "ENTERING APPLIANCE CONTROL SCREEN...", Toast.LENGTH_LONG).show();
        /*
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N)//Version less than Nougat (API Level 24, Android 7.0/7.1)
        {
            moveToEnterAutoHome(receivedString);
        }
        else
        {
            moveToHigherVersionSubActivity(receivedString);
        }

         */

        moveToEnterAutoHome(receivedString);
    }
    public void moveToEnterAutoHome(String data)
    {
        Bundle roomConfigBundle = new Bundle();
        roomConfigBundle.putString("ROOM_CONFIG", data);
        //Toast.makeText(getApplicationContext(), "ENTERING AUTO_HOME...", Toast.LENGTH_LONG).show();
        Intent basicIntent = new Intent(this, SubActivity.class);
        basicIntent.putExtras(roomConfigBundle);
        startActivity(basicIntent);
    }
}
