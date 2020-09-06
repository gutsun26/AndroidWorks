package com.web.wifilanscan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

public class HomeWifiToDevice extends AppCompatActivity
{
    /*UI*/
    TextView homeRouterID, homeRouterPWD;
    EditText homeRouterIDText, homeRouterPWDText;
    Button setButton,checkButton,roomListButton;

    /*CONNECT & COMMUNICATE*/
    Socket socket = new Socket();
    DataOutputStream dataOutputStream;


    /*WIFI RELATED*/
    NetworkInfo networkInfoWiFi;
    NetworkInfo[] allNetworkInfo;
    ConnectivityManager connectivityManager;
    WifiInfo wifiInfo;
    WifiManager wifiManager;
    int     networkID           =   0;
    String wifiName=null;
    String unknownSSID = "<unknown ssid>";
    String wifiIpAddress        =   "";
    String ssid=null,pwd=null;

    /*GENERAL*/
    boolean connectionStatus=false;


    /*DATABASE RELATED*/
    String defaultPWD = "15081947";
    public static final String ALTERNATE_TABLE_NAME = "alternates";
    boolean rowFlag=false;
    boolean checkRowAgainFlag=false;
    Controllerdb db =new Controllerdb(this);
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_wifi_to_device);
        homeRouterID        = (TextView)findViewById(R.id.homeRouterID);
        homeRouterIDText    = (EditText) findViewById(R.id.homeRouterIDText);
        homeRouterPWD       = (TextView)findViewById(R.id.homeRouterPWD);
        homeRouterPWDText   = (EditText)findViewById(R.id.homeRouterPWDText);
        setButton           = (Button)findViewById(R.id.setButton);
        checkButton         = (Button)findViewById(R.id.checkButton);
        roomListButton      = (Button)findViewById(R.id.roomListButton);

        homeRouterID.setVisibility(View.INVISIBLE);
        homeRouterPWD.setVisibility(View.INVISIBLE);
        homeRouterIDText.setVisibility(View.INVISIBLE);
        homeRouterPWDText.setVisibility(View.INVISIBLE);
        setButton.setVisibility(View.INVISIBLE);
        roomListButton.setVisibility(View.INVISIBLE);

        checkButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //toastMessage("OK");
                checkAll();
            }
        });

        roomListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToListOfRooms();
            }
        });
    }
    public void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
    public void toastMessageLong(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }

    public void checkAll()
    {
        //toastMessage("HERE");

        connectionStatus = consStatus();
        if(connectionStatus == true)
        {
            //toastMessageLong("CONNECTED");
            checkButton.setVisibility(View.INVISIBLE);
            setButton.setVisibility(View.VISIBLE);
            roomListButton.setVisibility(View.VISIBLE);
            homeRouterID.setVisibility(View.VISIBLE);
            homeRouterPWD.setVisibility(View.VISIBLE);
            homeRouterIDText.setVisibility(View.VISIBLE);
            homeRouterPWDText.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
            wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifiInfo = wifiManager.getConnectionInfo();
            networkID = wifiManager.getConnectionInfo().getNetworkId();

            wifiName = wifiInfo.getSSID();
            wifiName = wifiName.replace("\"","");
            if(wifiName.equalsIgnoreCase(unknownSSID))
            {
                toastMessage("TYPE THE NAME OF THE SSID");
                homeRouterIDText.setEnabled(true);
                homeRouterIDText.setFocusable(true);
                setButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        getInfo();
                    }
                });
            }
            else
            {
                toastMessage("YOU ARE CONNECTED TO "+wifiName);
                ssid = wifiName;
                homeRouterIDText.setText(wifiName);
                homeRouterIDText.setEnabled(false);
                homeRouterPWDText.setFocusable(true);
                setButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        getInformation();
                    }
                });
            }

        }
        else
        {
            toastMessage("PLEASE CONNECT TO HOME WIFI ROUTER");
        }
    }
    public void getInfo()
    {
        ssid = homeRouterIDText.getText().toString();
        pwd =  homeRouterPWDText.getText().toString();
        toastMessage("GOT IT:"+ssid+" "+pwd);
        renameWifiSSIDToRoomName(ssid, pwd);
    }
    public void getInformation()
    {
        pwd =  homeRouterPWDText.getText().toString();
        toastMessage("GOT IT:"+ssid+" "+pwd);
        renameWifiSSIDToRoomName(wifiName, pwd);
    }
    public void goToListOfRooms()
    {
        toastMessage("ROOMS");
        Intent intent = new Intent(HomeWifiToDevice.this,RoomList.class);
        startActivity(intent);
    }
    public boolean consStatus()
    {
        /*connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        allNetworkInfo = connectivityManager.getAllNetworkInfo();
        networkInfoWiFi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfoWiFi.isConnected())
        {
            wifiInfo = wifiManager.getConnectionInfo();
            wifiName = wifiInfo.getSSID();
            wifiName = wifiName.replace("\"","");
        }*/
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
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
            //toastMessage("YOUR ROUTER's IP ADDRESS IS:"+wifiIpAddress+" YOUR IP ADDRESS IS:"+mobileIpAddress);
            return true;
        }
        catch(UnknownHostException uhe)
        {
            Toast.makeText(this,"IP NOT ALLOCATED BY:"+wifiName, Toast.LENGTH_SHORT).show();
            return false;
        }
    }//end of consStatus()

    public void renameWifiSSIDToRoomName(String theSelectedSSID, String ssidName)
    {
        toastMessage(theSelectedSSID+","+ssidName);
        Controllerdb controllerdb = new Controllerdb(getApplicationContext());
        String theSSID = controllerdb.getROOMNAMEFromAlternateTable(theSelectedSSID);
        if(theSSID.isEmpty())
        {
            database=db.getWritableDatabase();
            database.execSQL("INSERT INTO ROOM_NAMES(SSID, ROOM_NAME, PWD) VALUES('" + theSelectedSSID + "','" + ssidName  + "','" + defaultPWD  + "')" );
            toastMessage("RENAMED THE WIFI TO "+ssidName);
        }
        else
        {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("ALREADY RENAMED. WOULD YOU LIKE TO RENAME AGAIN?");
            alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    removeRow(theSelectedSSID);
                }
            });
            alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }//end of renameWIfiSSIDToRoomName()
    public void removeRow(String theSSIDToBeRemoved)
    {
        toastMessage("REMOVING:"+theSSIDToBeRemoved);
        database=db.getWritableDatabase();
        database.execSQL("DELETE FROM ROOM_NAMES WHERE SSID='" + theSSIDToBeRemoved + "'" );
        toastMessage("REMOVED:"+theSSIDToBeRemoved);
        renameWifiSSIDToRoomName(wifiName, pwd);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.row_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                showTheChangedNames();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void showTheChangedNames()
    {
        Intent intent=new Intent(this,ShowdataListView.class);
        startActivity(intent);
    }
}
