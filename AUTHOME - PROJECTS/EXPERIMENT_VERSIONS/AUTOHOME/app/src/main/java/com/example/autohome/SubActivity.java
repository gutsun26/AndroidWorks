package com.example.autohome;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

public class SubActivity extends AppCompatActivity
{
    TextView textView, lightOneText, lightTwoText, lightThreeText, fanDisp;
    int time = 2;

    String lightOneOffStringText    =   "LIGHT ONE OFF";
    String lightTwoOffStringText    =   "LIGHT TWO OFF";
    String lightThreeOffStringText  =   "LIGHT THREE OFF";
    String fanOffStringText         =   "FAN OFF";

    String lightOneOnStringText    =   "LIGHT ONE ON";
    String lightTwoOnStringText    =   "LIGHT TWO ON";
    String lightThreeOnStringText  =   "LIGHT THREE ON";
    String fanOnStringText         =   "FAN ON";

    String lightOneOnString         =   "L1 ON";
    String lightOneOffString        =   "L1 OFF";
    String lightTwoOnString         =   "L2 ON";
    String lightTwoOffString        =   "L2 OFF";
    String lightThreeOnString       =   "L3 ON";
    String lightThreeOffString      =   "L3 OFF";
    String fanOnSpeedOneString      =   "FAN S1 ON";
    String fanOnSpeedTwoString      =   "FAN S2 ON";
    String fanOnSpeedThreeString    =   "FAN S3 ON";
    String fanOnSpeedFourString     =   "FAN S4 ON";
    String fanOnSpeedFiveString     =   "FAN S5 ON";
    String fanOffString             =   "F1 OFF";
    String message                  =   "";
    String getStatMessage           =   "TEST";
    int aFlag                       =   0;
    Socket socket = new Socket();
    Socket socket2 = new Socket();
    DataOutputStream dataOutputStream, dataOutputStream2;
    OutputStreamWriter outputStreamWriter;
    StringBuffer stringBuffer;
    //Button lightOneOn, lightOneOff, lightTwoOn, lightTwoOff, lightThreeOn, lightThreeOff, fanOn, fanOff;
    ToggleButton toggleLightOne;
    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;
    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    Switch switchButton1, switchButton2, switchButton3, fanButton;
    RadioButton speed1, speed2, speed3, speed4;
    String responseString=null, messageFromController=null,roomConfig=null;
    TextView hv,testPlace;
    String ON="1";
    String OFF="0";
    String speed1String = "2", speed2String = "3", speed3String = "4", speed4String = "5", speed5String = "6";
    int i;
    StringBuffer sbb = new StringBuffer();
    Button getStatBtn;

    Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    long startTime = 0L;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        testPlace = findViewById(R.id.testPlace);
        getStatBtn = findViewById(R.id.getStat);
        Intent roomConfigIntent = getIntent();
        Bundle roomConfigBundle = roomConfigIntent.getExtras();

        if(responseString == null)
        {
            roomConfig = roomConfigBundle.getString("ROOM_CONFIG");
        }
        else
        {
            roomConfig = responseString;
        }

        for(i=0; i<roomConfig.length(); i++)
        {
            if(roomConfig.charAt(i) == '0' || roomConfig.charAt(i) == '1' || roomConfig.charAt(i) == '2' || roomConfig.charAt(i) == '3' || roomConfig.charAt(i) == '4' || roomConfig.charAt(i) == '5' || roomConfig.charAt(i) == '6')
            {
                sbb.append(roomConfig.charAt(i));
            }
        }
        char[] roomStatusSplit = roomConfig.toCharArray();
        //testPlace.setText(roomStatusSplit[0]+"\n"+roomStatusSplit[1]+"\n"+roomStatusSplit[2]+"\n");
        testPlace.setText(sbb);

        /*ASSOCIATING UI CONTROLS FROM XML WITH JAVA OBJECTS*/
        textView        = (TextView)findViewById(R.id.txtView);
        lightOneText    = (TextView)findViewById(R.id.lightOneText);
        lightTwoText    = (TextView)findViewById(R.id.lightTwoText);
        lightThreeText  = (TextView)findViewById(R.id.lightThreeText);
        fanDisp         = (TextView)findViewById(R.id.fanText);

        speed1          = (RadioButton) findViewById(R.id.speedOne);
        speed2          = (RadioButton) findViewById(R.id.speedTwo);
        speed3          = (RadioButton) findViewById(R.id.speedThree);
        speed4          = (RadioButton) findViewById(R.id.speedFour);
        //speed5          = (RadioButton) findViewById(R.id.speedFive);

        switchButton1    = (Switch)findViewById(R.id.lightOne);
        switchButton2    = (Switch)findViewById(R.id.lightTwo);
        switchButton3    = (Switch)findViewById(R.id.lightThree);
        fanButton        = (Switch)findViewById(R.id.fan);

        fanDisp.setTextColor(Color.parseColor("fuchsia"));

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
            String mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            if(mobileIpAddress == null)
            {
                Toast.makeText(this,"APP IS DISCONNECTED FROM THE AUTOMATION DEVICE!", Toast.LENGTH_SHORT).show();
            }
            else
            {

            }
            //Toast.makeText(this,"YOUR IP:"+mobileIpAddress, Toast.LENGTH_SHORT).show();
        }
        catch(UnknownHostException uhe)
        {
            Toast.makeText(this,"UNKNOWN HOST EXCEPTION", Toast.LENGTH_SHORT).show();
        }

        switchButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchButton2.setEnabled(false);
                switchButton3.setEnabled(false);
                fanButton.setEnabled(false);
                speed1.setEnabled(false);
                speed2.setEnabled(false);
                speed3.setEnabled(false);
                speed4.setEnabled(false);
                changeLightOne(switchButton1);
            }
        });
        switchButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchButton1.setEnabled(false);
                switchButton3.setEnabled(false);
                fanButton.setEnabled(false);
                speed1.setEnabled(false);
                speed2.setEnabled(false);
                speed3.setEnabled(false);
                speed4.setEnabled(false);
                changeLightTwo(switchButton2);
            }
        });
        switchButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchButton1.setEnabled(false);
                switchButton2.setEnabled(false);
                fanButton.setEnabled(false);
                speed1.setEnabled(false);
                speed2.setEnabled(false);
                speed3.setEnabled(false);
                speed4.setEnabled(false);
                changeLightThree(switchButton3);
            }
        });
        /*

        if(fanButton.isChecked() == false)
        {
            switchOffFan();
        }
        else
        {
            speedOne(fanButton);
        }

         */

        fanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                switchButton1.setEnabled(false);
                switchButton2.setEnabled(false);
                switchButton3.setEnabled(false);
                speedOne(fanButton);
            }
        });

        speed1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switchButton1.setEnabled(false);
                switchButton2.setEnabled(false);
                switchButton3.setEnabled(false);
                speedOne(speed1);
            }
        });
        speed2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switchButton1.setEnabled(false);
                switchButton2.setEnabled(false);
                switchButton3.setEnabled(false);
                speedTwo(speed2);
            }
        });
        speed3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switchButton1.setEnabled(false);
                switchButton2.setEnabled(false);
                switchButton3.setEnabled(false);
                speedThree(speed3);
            }
        });
        speed4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switchButton1.setEnabled(false);
                switchButton2.setEnabled(false);
                switchButton3.setEnabled(false);
                speedFour(speed4);
            }
        });

        getStatBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getStatusOfRoom();
            }
        });

        /*SHOWCASING SWITCH STATUS AS PER MESSAGE SENT FROM THE CONTROLLER IN THE PREVIOUS SCREEN*/
        if(String.valueOf(roomStatusSplit[0]).equalsIgnoreCase(ON))
        {
            lightOneText.setText(lightOneOnStringText);
            lightOneText.setTextColor(Color.parseColor("yellow"));
            switchButton1.setChecked(true);
        }
        else
        {
            lightOneText.setText(lightOneOffStringText);
            lightOneText.setTextColor(Color.parseColor("fuchsia"));
            switchButton1.setChecked(false);
        }

        if(String.valueOf(roomStatusSplit[1]).equalsIgnoreCase(ON))
        {
            lightTwoText.setText(lightTwoOnStringText);
            lightTwoText.setTextColor(Color.parseColor("yellow"));
            switchButton2.setChecked(true);
        }
        else
        {
            lightTwoText.setText(lightTwoOffStringText);
            lightTwoText.setTextColor(Color.parseColor("fuchsia"));
            switchButton2.setChecked(false);
        }

        if(String.valueOf(roomStatusSplit[2]).equalsIgnoreCase(ON))
        {
            lightThreeText.setText(lightThreeOnStringText);
            lightThreeText.setTextColor(Color.parseColor("yellow"));
            switchButton3.setChecked(true);
        }
        else
        {
            lightThreeText.setText(lightThreeOffStringText);
            lightThreeText.setTextColor(Color.parseColor("fuchsia"));
            switchButton3.setChecked(false);
        }
        if(String.valueOf(roomStatusSplit[3]).equalsIgnoreCase(speed1String))
        {
            textView.setText("FAN ON WITH SPEED 1");
            fanDisp.setText("SPEED 1");
            fanButton.setChecked(true);
            speed1.setChecked(true);
            speed2.setChecked(false);
            speed3.setChecked(false);
            speed4.setChecked(false);
            //speed5.setChecked(false);
            fanDisp.setTextColor(Color.parseColor("yellow"));
        }
        else if(String.valueOf(roomStatusSplit[3]).equalsIgnoreCase(OFF))
        {
            textView.setText("FAN OFF");
            fanDisp.setText("FAN OFF");
            fanButton.setChecked(false);
            speed1.setChecked(false);
            speed2.setChecked(false);
            speed3.setChecked(false);
            speed4.setChecked(false);
            //speed5.setChecked(false);
            fanDisp.setTextColor(Color.parseColor("fuchsia"));
        }
        if(String.valueOf(roomStatusSplit[3]).equalsIgnoreCase(speed2String))
        {
            textView.setText("FAN ON WITH SPEED 2");
            fanDisp.setText("SPEED 2");
            speed1.setChecked(false);
            speed2.setChecked(true);
            speed3.setChecked(false);
            speed4.setChecked(false);
            //speed5.setChecked(false);
            fanDisp.setTextColor(Color.parseColor("yellow"));
        }
        if(String.valueOf(roomStatusSplit[3]).equalsIgnoreCase(speed3String))
        {
            textView.setText("FAN ON WITH SPEED 3");
            fanDisp.setText("SPEED 3");
            speed1.setChecked(false);
            speed2.setChecked(false);
            speed3.setChecked(true);
            speed4.setChecked(false);
            //speed5.setChecked(false);
            fanDisp.setTextColor(Color.parseColor("yellow"));
        }
        if(String.valueOf(roomStatusSplit[3]).equalsIgnoreCase(speed4String))
        {
            textView.setText("FAN ON WITH SPEED 4");
            fanDisp.setText("SPEED 4");
            speed1.setChecked(false);
            speed2.setChecked(false);
            speed3.setChecked(false);
            speed4.setChecked(true);
            //speed5.setChecked(false);
            fanDisp.setTextColor(Color.parseColor("yellow"));
        }

        /*
        if(roomConfig.equalsIgnoreCase("110"))
        {
            lightOneText.setText(lightOneOnStringText);
            lightTwoText.setText(lightTwoOnStringText);
            lightThreeText.setText(lightThreeOffStringText);
        }
        */
    }
    public void onStart()
    {
        super.onStart();
        //Toast.makeText(this,message + "RECONNECTING TO HOME AUTOMATION",Toast.LENGTH_SHORT).show();
        if(!mainWifiObj.isWifiEnabled())
        {
            //mainWifiObj.setWifiEnabled(true);
            //mainWifiObj.reconnect();
        }

    }
    public void onRestart()
    {
        super.onRestart();
        //Toast.makeText(this,message + "RECONNECTING TO HOME AUTOMATION",Toast.LENGTH_SHORT).show();
        //mainWifiObj.setWifiEnabled(true);
        //mainWifiObj.reconnect();
        //Toast.makeText(this,message + "onRestart() method",Toast.LENGTH_SHORT).show();
    }

    public void changeLightOne(View view)
    {
        if(switchButton1.isChecked() == false)
        {
            //Toast.makeText(this,"L1", Toast.LENGTH_SHORT).show();
            MyTask myTask1 = new MyTask();
            textView.setText("LIGHT ONE OFF");
            lightOneText.setText("LIGHT ONE OFF");
            lightOneText.setTextColor(Color.parseColor("fuchsia"));
            message = lightOneOffString;
            myTask1.execute(message);
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
        }
        else if (switchButton1.isChecked() == true)
        {
            //Toast.makeText(this,"L1", Toast.LENGTH_SHORT).show();
            MyTask myTask2 = new MyTask();
            textView.setText("LIGHT ONE ON");
            lightOneText.setText("LIGHT ONE ON");
            lightOneText.setTextColor(Color.parseColor("yellow"));
            message = lightOneOnString;
            myTask2.execute(message);
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeLightTwo(View view)
    {
        if(switchButton2.isChecked() == false)
        {
            MyTask myTask3 = new MyTask();
            textView.setText("LIGHT TWO OFF");
            lightTwoText.setTextColor(Color.parseColor("fuchsia"));
            lightTwoText.setText("LIGHT TWO OFF");
            message = lightTwoOffString;
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            myTask3.execute(message);
        }
        else if (switchButton2.isChecked() == true)
        {
            //Toast.makeText(this,"L2 ON", Toast.LENGTH_SHORT).show();
            MyTask myTask4 = new MyTask();
            textView.setText("LIGHT TWO ON");
            lightTwoText.setText("LIGHT TWO ON");
            lightTwoText.setTextColor(Color.parseColor("yellow"));
            message = lightTwoOnString;
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            myTask4.execute(message);
        }
    }

    public void changeLightThree(View view)
    {
        if(switchButton3.isChecked() == false)
        {
            MyTask myTask5 = new MyTask();
            textView.setText("LIGHT THREE OFF");
            lightThreeText.setTextColor(Color.parseColor("fuchsia"));
            lightThreeText.setText("LIGHT THREE OFF");
            message = lightThreeOffString;
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            myTask5.execute(message);
        }
        else if (switchButton3.isChecked() == true)
        {
            MyTask myTask6 = new MyTask();
            textView.setText("LIGHT THREE ON");
            lightThreeText.setTextColor(Color.parseColor("yellow"));
            lightThreeText.setText("LIGHT THREE ON");
            message = lightThreeOnString;
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            myTask6.execute(message);
        }
    }
    public void speedOne(View view)
    {
        if(fanButton.isChecked() == false)
        {
            fanDisp.setText("FAN OFF");
            message = fanOffString;
            MyTask myTask11 = new MyTask();
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            myTask11.execute(message);
            fanDisp.setTextColor(Color.parseColor("fuchsia"));
            speed1.setChecked(false);
            speed2.setChecked(false);
            speed3.setChecked(false);
            speed4.setChecked(false);
            //speed5.setChecked(false);
        }
        else if(fanButton.isChecked() == true)
        {
            textView.setText("FAN ON WITH SPEED 1");
            fanDisp.setText("SPEED 1");
            message = fanOnSpeedOneString;
            MyTask myTask7 = new MyTask();
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            myTask7.execute(message);
            speed1.setChecked(true);
            speed2.setChecked(false);
            speed3.setChecked(false);
            speed4.setChecked(false);
            //speed5.setChecked(false);
            fanDisp.setTextColor(Color.parseColor("yellow"));
        }
    }

    public void speedTwo(View view)
    {
        textView.setText("FAN ON WITH SPEED 2");
        fanDisp.setText("SPEED 2");
        message = fanOnSpeedTwoString;
        MyTask myTask8 = new MyTask();
        Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
        myTask8.execute(message);
        speed1.setChecked(false);
        speed2.setChecked(true);
        speed3.setChecked(false);
        speed4.setChecked(false);
        fanButton.setChecked(true);
        //speed5.setChecked(false);
        fanDisp.setTextColor(Color.parseColor("yellow"));
    }


    public void speedThree(View view)
    {
        textView.setText("FAN ON WITH SPEED 3");
        fanDisp.setText("SPEED 3");
        message = fanOnSpeedThreeString;
        MyTask myTask9 = new MyTask();
        Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
        myTask9.execute(message);
        speed1.setChecked(false);
        speed2.setChecked(false);
        speed3.setChecked(true);
        speed4.setChecked(false);
        fanButton.setChecked(true);
        //speed5.setChecked(false);
        fanDisp.setTextColor(Color.parseColor("yellow"));
    }

    public void speedFour(View view)
    {
        textView.setText("FAN ON WITH SPEED 4");
        fanDisp.setText("SPEED 4");
        message = fanOnSpeedFourString;
        MyTask myTask10 = new MyTask();
        Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
        myTask10.execute(message);
        speed1.setChecked(false);
        speed2.setChecked(false);
        speed3.setChecked(false);
        speed4.setChecked(true);
        fanButton.setChecked(true);
        //speed5.setChecked(false);
        fanDisp.setTextColor(Color.parseColor("yellow"));
    }

    public void speedFive(View view)
    {
        textView.setText("FAN ON WITH SPEED 5");
        fanDisp.setText("SPEED 5");
        message = fanOnSpeedFiveString;
        MyTask myTask12 = new MyTask();
        try
        {
            Toast.makeText(this,"PLEASE WAIT...", Toast.LENGTH_SHORT).show();
            TimeUnit.SECONDS.sleep(time);
            myTask12.execute(message);
        }catch(InterruptedException ie){ie.printStackTrace();}
        speed1.setChecked(false);
        speed2.setChecked(false);
        speed3.setChecked(false);
        speed4.setChecked(false);
        fanButton.setChecked(true);
        //speed5.setChecked(true);
        fanDisp.setTextColor(Color.parseColor("yellow"));
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
                TimeUnit.SECONDS.sleep(1);
                byte[] b = formattedMessage.getBytes();
                dataOutputStream .write(b);
                dataOutputStream .flush();
                TimeUnit.SECONDS.sleep(2);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String returnedMessage = input.readLine();
                responseString = returnedMessage;
                dataOutputStream .close();
                //String all = "RESPONSE:"+responseString;
                final String all = responseString;
                socket.close();
                return all;
            }
            catch(InterruptedException ie){return ie.toString()+" Inside doInBackground()";}
            catch(SocketException se){return se.toString()+" Inside doInBackground()";}
            catch(IOException ioe){return ioe.toString()+" Inside doInBackground()";}
        }
        protected void onPostExecute(final String responseStringReceived)
        {
            Toast.makeText(getApplicationContext(), "RECEIVED:"+responseStringReceived, Toast.LENGTH_LONG).show();
            if(!responseStringReceived.isEmpty())
            {
                if(message.equalsIgnoreCase(lightOneOnString) || message.equalsIgnoreCase(lightOneOffString))
                {
                    switchButton2.setEnabled(true);
                    switchButton3.setEnabled(true);
                    fanButton.setEnabled(true);
                    speed1.setEnabled(true);
                    speed2.setEnabled(true);
                    speed3.setEnabled(true);
                    speed4.setEnabled(true);
                }
                if(message.equalsIgnoreCase(lightTwoOnString) || message.equalsIgnoreCase(lightTwoOffString))
                {
                    switchButton1.setEnabled(true);
                    switchButton3.setEnabled(true);
                    fanButton.setEnabled(true);
                    speed1.setEnabled(true);
                    speed2.setEnabled(true);
                    speed3.setEnabled(true);
                    speed4.setEnabled(true);
                }
                if(message.equalsIgnoreCase(lightThreeOnString) || message.equalsIgnoreCase(lightThreeOffString))
                {
                    switchButton1.setEnabled(true);
                    switchButton2.setEnabled(true);
                    fanButton.setEnabled(true);
                    speed1.setEnabled(true);
                    speed2.setEnabled(true);
                    speed3.setEnabled(true);
                    speed4.setEnabled(true);
                }
                if(message.equalsIgnoreCase(fanOnSpeedOneString)
                        || message.equalsIgnoreCase(fanOnSpeedTwoString)
                        || message.equalsIgnoreCase(fanOnSpeedThreeString)
                        || message.equalsIgnoreCase(fanOnSpeedFourString)
                        || message.equalsIgnoreCase(fanOnSpeedOneString)
                        || message.equalsIgnoreCase(fanOnSpeedOneString)
                        || message.equalsIgnoreCase(fanOffString))
                {
                    switchButton1.setEnabled(true);
                    switchButton2.setEnabled(true);
                    switchButton3.setEnabled(true);
                }
            }
        }
    }

    public void getStatusOfRoom()
    {
        //MyTask refreshRoom = new MyTask();
        //refreshRoom.execute(getStatMessage);
        finish();
        System.runFinalizersOnExit(true);
        System.exit(0);
        //Intent basicIntent = new Intent(this, GetRoomConfiguration.class);
        /*
        try
        {
            TimeUnit.SECONDS.sleep(2);
            Bundle roomConfigBundle = new Bundle();
            roomConfigBundle.putString("ROOM_CONFIG",responseString);
            Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_LONG).show();
            Intent basicIntent = new Intent(this, SubActivity.class);
            basicIntent.putExtras(roomConfigBundle);
            startActivity(basicIntent);
            finish();
        }catch(InterruptedException ie){ie.printStackTrace();}

         */
    }

    public void onPause()
    {
        super.onPause();
        //mainWifiObj.disconnect();
        //Toast.makeText(this,message + "onPause() method",Toast.LENGTH_SHORT).show();
    }
    public void onStop()
    {
        super.onStop();
        //Toast.makeText(this,message + "onStop() method",Toast.LENGTH_SHORT).show();
    }

}