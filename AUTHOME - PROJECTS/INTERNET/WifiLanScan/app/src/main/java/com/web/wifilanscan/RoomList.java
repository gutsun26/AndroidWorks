package com.web.wifilanscan;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.os.Handler;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RoomList extends AppCompatActivity
{
    /*DATABASE RELATED*/
    Controllerdb controllerdb = new Controllerdb(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> ROOM_NAME = new ArrayList<String>();
    ListView lv;
    String roomName=null;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;
    String theRenamedSSID=null;
    String theStoredPassword=null;

    /*WIFI RELATED*/
    WifiManager wifiManager;
    NetworkInfo networkInfoWiFi;
    NetworkInfo[] allNetworkInfo;
    ConnectivityManager connectivityManager;
    DhcpInfo dhcpInfo;
    boolean tripleClickFlag=false;
    int clickCount=0;
    String theMessage="TEST";
    Socket socket = new Socket();
    DataOutputStream dataOutputStream;
    String responseString=null;
    String wifiIpAddress       =   "";
    int    wifiPortNumb        =   80;
    Intent i;
    SocketHandler socketHandler;
    final String connectedIpAddressSnippet="192.168.4.";

    /*GENERAL*/
    ProgressDialog progressDialog;
    boolean readyToRead = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_list);
        /*
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

         */

        lv = (ListView) findViewById(R.id.roomList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ROOM_NAME);
        displayData();
    }
    @Override
    protected void onResume()
    {
        displayData();
        super.onResume();
    }


    private void displayData()
    {
        db = controllerdb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Id, ROOM_NAME FROM  ROOM_NAMES",null);
        Id.clear();
        ROOM_NAME.clear();
        if (cursor.moveToFirst())
        {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                //SSID.add(cursor.getString(cursor.getColumnIndex("SSID")));
                ROOM_NAME.add(cursor.getString(cursor.getColumnIndex("ROOM_NAME")));
                adapter.notifyDataSetChanged();
                //PASSWORD.add(cursor.getString(cursor.getColumnIndex("PWD")));
            } while (cursor.moveToNext());
        }
        DisplayAdapter ca = new DisplayAdapter(RoomList.this,Id,ROOM_NAME);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setBackgroundColor(Color.BLUE);
                TextView ssidText = (TextView)view;
                ssidText.setTextColor(Color.WHITE);
                roomName = ((TextView) view).getText().toString();
                toastMessage("ROOM:" + roomName);
                //connectToWifi(roomName);
                getRoomConfiguration();
            }
        });
        //code to set adapter to populate list
        cursor.close();
    }

    public void connectToWifi(String roomName)
    {
        Controllerdb controllerdb = new Controllerdb(getApplicationContext());
        theRenamedSSID = controllerdb.getSSIDOfNamedRoom(roomName);
        toastMessage("SSID:"+theRenamedSSID);
        theStoredPassword=controllerdb.getPassword(theRenamedSSID);
        toastMessage("PWD:"+theStoredPassword);
        /*
        try
        {
            WifiConfiguration wifiConfig = new WifiConfiguration();
            wifiConfig.SSID = String.format("\"%s\"", theRenamedSSID);
            wifiConfig.preSharedKey = String.format("\"%s\"", theStoredPassword);
            int netId = wifiManager.addNetwork(wifiConfig);
            wifiManager.disconnect();
            wifiManager.enableNetwork(netId, true);
            wifiManager.reconnect();
            autoHome.setVisibility(View.VISIBLE);
            autoHome.setTextColor(Color.WHITE);
            autoHome.setBackgroundColor(Color.RED);
            autoHome.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    getRoomConfiguration();
                }
            });
        }catch(Exception e){tv.setText(e.toString());}*/
    }
    public void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
    public void toastMessageLong(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
    public void getRoomConfiguration()
    {
        boolean connectionStatus = consStatus();

        if(connectionStatus == true)
        {
            //setProgressDialog("GETTING STATUS","PLEASE WAIT...!");
            MyTask myTask = new MyTask();
            myTask.execute(theMessage);
            /*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            {
                MyNewTask myNewTask = new MyNewTask();
                myNewTask.doTask();
            }
            else
            {
                MyTask myTask = new MyTask();
                myTask.execute(theMessage);
            }*/

        }
        else
        {
            ++clickCount;
            Toast.makeText(this,"APP NOT CONNECTED!", Toast.LENGTH_SHORT).show();
            if(clickCount >= 2)
            {
                this.tripleClickFlag = true;
                Toast.makeText(this, "Please click BACK again to enlist rooms", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        tripleClickFlag=false;
                        //goToMainPage();

                    }
                }, 1000);
            }
        }
    }//end of getRoomConfiguration()

    public boolean consStatus()
    {
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        dhcpInfo = wifiManager.getDhcpInfo();
        int ip = dhcpInfo.gateway;
        ip = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ? Integer.reverseBytes(ip) : ip;
        byte[] ipAddressByte = BigInteger.valueOf(ip).toByteArray();
        try
        {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            wifiIpAddress = myAddr.getHostAddress();
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int phoneIp = wifiInfo.getIpAddress();
            String mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            if(mobileIpAddress.contains(connectedIpAddressSnippet))
            {
                toastMessage(mobileIpAddress);
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(UnknownHostException uhe)
        {
            return false;
        }
    }//end of consStatus()

    /********************************* HELPER   CLASS FOR ANDROID VERSION 10 & ABOVE*****************************************/
    class MyNewTask
    {
        public MyNewTask()
        {
            //toastMessage("OPENING SOCKET CONNECTION");
        }

        public void doTask()
        {
            //while (true)
            {
                try
                {
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            i = new Intent(RoomList.this, SocketHandler.class);
                            socketHandler = new SocketHandler();
                            socketHandler.onCreate();
                            socketHandler.onHandleIntent(i);
                            //startService(i);
                        }
                    });
                }
                catch (Exception e)
                {
                    toastMessage("YAHOO:"+e.toString());
                }
            }
        }
    }//end of class MyNewTask

    class SocketHandler extends IntentService
    {
        public SocketHandler()
        {
            super("SocketHandler");
        }
        @Override
        public void onCreate()
        {
            super.onCreate();

            //toastMessage("SOCKET HANDLER CREATED");

        }
        @Override
        protected void onHandleIntent(Intent intent)
        {
            try
            {
                socket = new Socket();
                socket.connect(new InetSocketAddress(wifiIpAddress, wifiPortNumb),5000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                StringBuilder stringBuilder = new StringBuilder(theMessage);
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

                //toastMessage("SENT TO WIFI");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String returnedMessage = input.readLine();
                responseString = returnedMessage;

                //toastMessage(responseString);

                dataOutputStream .close();
                //toastMessage(responseString+"_"+roomName);
                socket.close();                             /*CLOSED SOCKET CONNECTION*/
                //toastMessage("SOCKET CLOSED!");
                moveToEnterAutoHomeNoAync(responseString+"_"+roomName);
            }
            catch(InterruptedException ie)
            {
                try
                {
                    socket.close();                             /*CLOSED SOCKET CONNECTION*/
                }catch(IOException ioe){toastMessage("SOCKET EXCEPTION:"+ioe.toString());}
                toastMessage("TIMEUNIT:"+ie.toString());
            }
            catch (IOException e)
            {
                try
                {
                    socket.close();                             /*CLOSED SOCKET CONNECTION*/
                }catch(IOException ioe){toastMessage("SOCKET EXCEPTION:"+ioe.toString());}
            }
        }
    }//end of class SocketHandler

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
                StringBuilder stringBuilder = new StringBuilder(theMessage);
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
                readyToRead = input.ready();
                if(readyToRead == true)
                {
                    String returnedMessage = input.readLine();
                    responseString = returnedMessage;
                    if(responseString.isEmpty() || (!responseString.isEmpty()))
                    {
                        dataOutputStream .close();
                        socket.close();
                    }
                }
                else
                {
                    dataOutputStream.close();
                    responseString = "LOST";
                    socket.close();
                }
                return responseString;
            }
            catch(InterruptedException ie){return ie.toString()+" Inside doInBackground()";}
            catch(SocketException se){return se.toString()+" Inside doInBackground()";}
            catch(IOException ioe){return ioe.toString()+" Inside doInBackground()";}
        }

        // This is called each time you call publishProgress()
        protected void onPostExecute(String responseStringReceived)
        {
            if(responseStringReceived.equalsIgnoreCase("LOST"))
            {
                toastMessage("DEVICE REJECTED COMMUNICATION");
            }
            else if(responseStringReceived.equalsIgnoreCase("HAYAA HO!"))
            {
                toastMessage("SAB CHANGA!");
                Intent intent = new Intent(RoomList.this, HomeWifiToDevice.class);
                startActivity(intent);
            }
            else
            {
                toastMessage("YEEYAW:"+responseStringReceived);
                moveToEnterAutoHome(responseStringReceived+"_"+roomName);
            }
        }
    }


    public void moveToEnterAutoHome(String data)
    {
        /*
        try
        {
            stopService(i);
            toastMessage("SERVICE STOPPED");
        }catch(Exception e){toastMessage(e.toString());}*/
        Bundle roomConfigBundle = new Bundle();
        roomConfigBundle.putString("ROOM_CONFIG", data);
        Intent basicIntent = new Intent(this, SubActivity2.class);
        basicIntent.putExtras(roomConfigBundle);
        startActivity(basicIntent);
    }//end of moveToEnterAutoHome()


    public void moveToEnterAutoHomeNoAync(String data)
    {
        /*
        try
        {
            stopService(i);
            toastMessage("SERVICE STOPPED");
        }catch(Exception e){toastMessage(e.toString());}*/
        Bundle roomConfigBundle = new Bundle();
        roomConfigBundle.putString("ROOM_CONFIG", data);
        Intent basicIntent = new Intent(this, SubActivity2.class);
        basicIntent.putExtras(roomConfigBundle);
        startActivity(basicIntent);
    }//end of moveToEnterAutoHomeNoAync()
    public void setProgressDialog(String title, String mess)
    {
        progressDialog = ProgressDialog.show(this, title, mess, true);
        CountDownTimer timer = new CountDownTimer(3000, 1000)
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
}