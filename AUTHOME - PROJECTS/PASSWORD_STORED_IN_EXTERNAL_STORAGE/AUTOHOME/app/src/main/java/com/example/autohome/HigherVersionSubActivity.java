package com.example.autohome;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
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
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class HigherVersionSubActivity extends AppCompatActivity
{
    TextView textView, lightOneText, lightTwoText, lightThreeText, fanDisp;

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
    DataInputStream dataInputStream;
    OutputStreamWriter outputStreamWriter;
    StringBuffer stringBuffer;
    StringBuilder stringBuilder;
    ToggleButton toggleLightOne;
    String wifiIpAddress        =   "192.168.4.1";
    int     wifiPortNumb        =   80;
    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    Switch switchButton1, switchButton2, switchButton3, fanButton;
    RadioButton speed1, speed2, speed3, speed4;
    TextView hv,testPlace;
    String responseString;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_higher);

        /*ASSOCIATING UI CONTROLS FROM XML WITH JAVA OBJECTS*/
        textView        = (TextView)findViewById(R.id.txtView);
        lightOneText    = (TextView)findViewById(R.id.lightOneText);
        lightTwoText    = (TextView)findViewById(R.id.lightTwoText);
        lightThreeText  = (TextView)findViewById(R.id.lightThreeText);
        fanDisp         = (TextView)findViewById(R.id.fanText);

        lightOneText.setText("LIGHT ONE OFF");
        lightTwoText.setText("LIGHT TWO OFF");
        lightThreeText.setText("LIGHT THREE OFF");
        fanDisp.setText("FAN OFF");

        lightOneText.setTextColor(Color.parseColor("fuchsia"));
        lightTwoText.setTextColor(Color.parseColor("fuchsia"));
        lightThreeText.setTextColor(Color.parseColor("fuchsia"));


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



        switchButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLightOne(switchButton1);

            }
        });
        switchButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLightTwo(switchButton2);
            }
        });
        switchButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLightThree(switchButton3);
            }
        });
        fanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedOne(fanButton);
            }
        });
        speed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedOne(speed1);
            }
        });
        speed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedTwo(speed2);
            }
        });
        speed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedThree(speed3);
            }
        });
        speed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedFour(speed4);
            }
        });

    }
    public void higherVersion()
    {
        Intent myIntent = new Intent(this, TestForAboveVersionSeven.class);
        startActivity(myIntent);
    }
    public void changeLightOne(View view)
    {
        if(switchButton1.isChecked() == false)
        {
            Toast.makeText(this,"L1", Toast.LENGTH_SHORT).show();
            MyTask myTask1 = new MyTask();
            textView.setText("LIGHT ONE OFF");
            lightOneText.setText("LIGHT ONE OFF");
            lightOneText.setTextColor(Color.parseColor("fuchsia"));
            message = lightOneOffString;
            myTask1.execute(message);
            Toast.makeText(this,"MSG SENT", Toast.LENGTH_SHORT).show();
        }
        else if (switchButton1.isChecked() == true)
        {
            Toast.makeText(this,"L1", Toast.LENGTH_SHORT).show();
            MyTask myTask2 = new MyTask();
            textView.setText("LIGHT ONE ON");
            lightOneText.setText("LIGHT ONE ON");
            lightOneText.setTextColor(Color.parseColor("yellow"));
            message = lightOneOnString;
            myTask2.execute(message);
            Toast.makeText(this,"MSG SENT", Toast.LENGTH_SHORT).show();
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
            myTask3.execute(message);
            Toast.makeText(this,"MSG SENT", Toast.LENGTH_SHORT).show();
        }
        else if (switchButton2.isChecked() == true)
        {
            Toast.makeText(this,"L2 ON", Toast.LENGTH_SHORT).show();
            MyTask myTask4 = new MyTask();
            textView.setText("LIGHT TWO ON");
            lightTwoText.setText("LIGHT TWO ON");
            lightTwoText.setTextColor(Color.parseColor("yellow"));
            message = lightTwoOnString;
            myTask4.execute(message);
            Toast.makeText(this,"MSG SENT", Toast.LENGTH_SHORT).show();
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
            myTask5.execute(message);
        }
        else if (switchButton3.isChecked() == true)
        {
            MyTask myTask6 = new MyTask();
            textView.setText("LIGHT THREE ON");
            lightThreeText.setTextColor(Color.parseColor("yellow"));
            lightThreeText.setText("LIGHT THREE ON");
            message = lightThreeOnString;
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
        myTask12.execute(message);
        speed1.setChecked(false);
        speed2.setChecked(false);
        speed3.setChecked(false);
        speed4.setChecked(false);
        fanButton.setChecked(true);
        //speed5.setChecked(true);
        fanDisp.setTextColor(Color.parseColor("yellow"));
    }
    public void exitSubActivity(View view)
    {
        /*
        try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException ie){}
        */
        finish();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
    /*Following piece of code is cop-pasted from ListAndConnectWifi located at D:\Works\Android*/
    class MyTask extends AsyncTask<String, Void, String>
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
                TimeUnit.SECONDS.sleep(2);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String returnedMessage = input.readLine();
                responseString = returnedMessage;
                dataOutputStream .close();
                //String all = "RESPONSE:"+responseString;
                String all = responseString;
                socket.close();
                return all;
            }
            catch(InterruptedException ie){return ie.toString()+" Inside doInBackground()";}
            catch(SocketException se){return se.toString()+" Inside doInBackground()";}
            catch(IOException ioe){return ioe.toString()+" Inside doInBackground()";}
        }
        protected void onPostExecute(String responseStringReceived)
        {
            Toast.makeText(getApplicationContext(), "SENT:"+message, Toast.LENGTH_LONG).show();
            try
            {
                TimeUnit.SECONDS.sleep(1);
                hv.setText(responseStringReceived);
                if(     (responseStringReceived.equalsIgnoreCase("L1 OFF")) ||
                        (responseStringReceived.equalsIgnoreCase("L2 OFF")) ||
                        (responseStringReceived.equalsIgnoreCase("L3 OFF")) ||
                        (responseStringReceived.equalsIgnoreCase("F1 OFF")) )
                {
                    hv.setTextColor(Color.parseColor("fuchsia"));
                }
                else
                {
                    hv.setTextColor(Color.parseColor("yellow"));
                }
            }
            catch(InterruptedException ie){hv.setText("onPostExecute() "+ie.toString()+" "+responseString);}
            //catch(SocketException se){hv.setText("onPostExecute() "+se.toString()+" "+responseString);}
            //catch (IOException ioe){hv.setText("onPostExecute() "+ioe.toString()+" "+responseString);}

        }
    }
}
