package com.example.autohome;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Environment;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.PrecomputedText;
import android.util.EventLog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.util.Locale.US;
import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity
{
    /*DATABASE RELATED VARIABLES*/
    DBCreation dbCreation = new DBCreation(this);
    String databasePath=null;
    Context context;
    public static final String DATABASE_NAME = "AUTO_HOME.db";
    public static final String TABLE_NAME = "authentication";
    String dbName = "AUTO_HOME.db";
    boolean dbStat=false;
    boolean tableStat=false;
    boolean rowStat=false;
    static TextView dbPath;
    /*END OF DATABASE RELATED VARIABLES*/


    /*WIFI RELATED VARIABLES*/
    Socket socket = new Socket();
    DataOutputStream dataOutputStream;
    NetworkInfo networkInfoWiFi;
    NetworkInfo[] allNetworkInfo;
    ConnectivityManager connectivityManager;
    WifiInfo wifiInfo;
    WifiManager wifiManager;
    List<ScanResult> results;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;
    String messageDisplay="NOTHING";
    String fileName = "PASSWORD.txt";
    String directory = "Download";
    String subDirectory = "JEANIE";
    String defaultSSID="AUTO_HOME";
    String defaultPWD = "15081947";
    String wifiName=null;
    String unknownSSID = "<unknown ssid>";
    String nullSSID = "0x";
    String ssid="";
    String wifis[];
    Button theButton, selectRoom, writeButton;
    WifiScanReceiver wifiScanReceiver;
    WifiManager mainWifiObj;
    ListView listView;
    TextView myText, ssidText, msg;
    EditText pass;
    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;
    int     wifiFlag            =   0;
    int     networkID           =   0;
    /*END OF WIFI RELATED VARIABLES*/

    /*UI RELATED VARIABLES*/
    AlertDialog alertDialog;
    String manufacturer = Build.MANUFACTURER;
    String model = Build.MODEL;
    int version = Build.VERSION.SDK_INT;
    String versionRelease = Build.VERSION.RELEASE;
    static float vers ;//= Build.VERSION.SDK_INT;
    TextView manuf, mod, ver, myTxt, conStat;
    String deviceVersion= Build.VERSION.RELEASE;
    String limitVersion="4.0";
    String result="";
    RelativeLayout relativeLayout;
    String appVersion = "51";
    String message = "This App requires: \nGPS to be SWITCHED ON and \nMobile-Data to be SWITCHED OFF!";
    String wifiMessage = "CMD_FORGET_NETWORK";
    Bundle bundle;

    /*END OF UI RELATED VARIABLES*/

    int status;//To check whether MOBILEDATA is on or not

    /*GPS RELATED VARIABLES FOR PROGRAMMATICALLY SWITHCHING IT ON*/
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private android.widget.Button btnLocation;
    private TextView txtLocation;
    private android.widget.Button btnContinueLocation;
    private TextView txtContinueLocation;
    private StringBuilder stringBuilder;
    private boolean isContinue = false;
    private boolean isGPS = false;
    /*END OF GPS RELATED VARIABLES*/


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }

        /*MAPPING UI COMPONENTS*/
        selectRoom = (Button)findViewById(R.id.selectRoom);
        conStat = (TextView)findViewById(R.id.conStat);
        //manuf   = (TextView)findViewById(R.id.manuf);
        //mod     = (TextView)findViewById(R.id.model);
        //ver     = (TextView)findViewById(R.id.ver);
        myTxt   = (TextView)findViewById(R.id.myTxt);
        /*END OF MAPPING UI COMPONENTS*/

        /*DATABASE INSTANCE*/
        dbCreation = new DBCreation(this);
        dbPath = findViewById(R.id.myTxt);

        dbStat = doesDatabaseExist(this,DATABASE_NAME);
        if(dbStat == true)
        {
            //Toast.makeText(getApplicationContext(),"DATABASE EXISTS!",Toast.LENGTH_SHORT).show();
            tableStat = doesTableExist();
            if(tableStat == true)
            {
                //Toast.makeText(getApplicationContext(),"TABLE EXISTS!",Toast.LENGTH_SHORT).show();
                rowStat = doesRowExist();
                if(rowStat == true)
                {
                    //Toast.makeText(getApplicationContext(),"DEFAULT SSID & PASSWORD EXIST!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),"DEFAULT SSID & PASSWORD DO NOT EXIST!",Toast.LENGTH_SHORT).show();
                    dbCreation.insertData(defaultSSID, defaultPWD);
                    //Toast.makeText(getApplicationContext(),"INSERTED DEFAULT SSID AND PWD!",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                //Toast.makeText(getApplicationContext(),"TABLE DOES NOT EXIST!",Toast.LENGTH_SHORT).show();
                SQLiteDatabase myDB = dbCreation.getReadableDatabase();
                dbCreation.onCreate(myDB);
                //Toast.makeText(getApplicationContext(),"TABLE CREATED",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"DATABASE DOES NOT EXIST",Toast.LENGTH_SHORT).show();
            dbCreation = new DBCreation(this);
            //Toast.makeText(getApplicationContext(),"DATABASE CREATED!",Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"TABLE CREATED!",Toast.LENGTH_SHORT).show();
            dbCreation.insertData(defaultSSID, defaultPWD);
            //Toast.makeText(getApplicationContext(),"INSERTED DEFAULT SSID AND PWD!",Toast.LENGTH_SHORT).show();
        }
        /*END OF DATABASE INSTANCE*/

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /*PHONE RELATED INFORMATION*/
        //manuf.setText(manufacturer);
        //mod.setText(model);
        //ver.setText(versionRelease);
        myTxt.setText("App Version:" + appVersion +"\n" +message);
        /*END OF PHONE RELATED INFORMATION*/

        /*WIFI RELATED INFORMATION*/
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //wifiManager.disconnect();
        wifiManager.setWifiEnabled(true);

        wifiInfo = wifiManager.getConnectionInfo();
        networkID = wifiManager.getConnectionInfo().getNetworkId();
        //wifiManager.removeNetwork(networkID);
        Toast.makeText(this, "NETWORK ID:"+ networkID, Toast.LENGTH_SHORT).show();
        wifiName = wifiInfo.getSSID();
        wifiName = wifiName.replace("\"","");
        if(wifiName == unknownSSID || wifiName == nullSSID)
        {
            conStat.setText("PHONE/APPCONNECTED TO:"+messageDisplay);
        }
        else
        {
            conStat.setText("CONNECTED TO:"+wifiName);
            ssid=wifiName;
        }
        /*END OF WIFI RELATED INFORMATION*/


        showDialogBox();
        /*CHECK FOR MOBILEDATA BEING ON*/
        status = checkNetworkConnection();
        /*SWITCH ON GPS FOR WIFI SCANNING IN THIS APP*/
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener()
        {
            @Override
            public void gpsStatus(boolean isGPSEnable)
            {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });

        selectRoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(status == 1)
                {
                    Toast.makeText(getApplicationContext(),"SWITCH-OFF MOBILE DATA TO MAKE THIS APP WORK! RESTART THE APP AFTER SWITCHING OFF THE MOBILE DATA",Toast.LENGTH_LONG).show();
                }
                else
                {
                    chooseRoom();
                }
            }
        });
    }
    public void showDialogBox()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS SHOULD BE ENABLED!");
        alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                //alertDialog.closeOptionsMenu();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void chooseRoom()
    {
        //Intent scanAutoHome = new Intent(this, ScanAutoHome.class);
        if(isGPS != true)
        {
            showDialogBox();
        }
        else
        {
            Bundle connection = new Bundle();
            connection.putString("CON_STAT",ssid);
            Intent scanAutoHome = new Intent(this, ScanAutoHome.class);
            scanAutoHome.putExtras(connection);
            startActivity(scanAutoHome);
        }
    }

    public int checkNetworkConnection()
    {
        //Toast.makeText(this, "FINDING INTERNET CONNECTION TYPE!",Toast.LENGTH_SHORT).show();
        ConnectivityManager connectivitymanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connectivitymanager.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo)
        {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
            {
                if (netInfo.isConnected())
                {
                    return 0;
                }
            }
            else if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
            {
                if (netInfo.isConnected())
                {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static boolean doesDatabaseExist(Context context, String DATABASE_NAME)
    {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        String path = dbFile.getPath();
        dbPath.setText("DB PATH:"+path);
        return dbFile.exists();
    }

    private boolean doesTableExist()
    {
        DBCreation dbCreation = new DBCreation(this);
        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = dbCreation.getReadableDatabase();
        Cursor c = null;
        boolean tableExists = false;
        /* get cursor on it */
        try
        {
            c = mDatabase.query(TABLE_NAME, null,null, null, null, null, null);
            tableExists = true;
        }catch (SQLiteException e) {}
        return tableExists;
    }
    private boolean doesRowExist()
    {
        DBCreation dbCreation = new DBCreation(this);
        SQLiteDatabase mDatabase = dbCreation.getReadableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM "+TABLE_NAME;
        cursor= mDatabase.rawQuery(sql,null);
        if(cursor.getCount()>0)
        {
            cursor.close();
            return true;
        }
        else
        {
            cursor.close();
            return false;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == AppConstants.GPS_REQUEST)
            {
                isGPS = true; // flag maintain before get location
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                disconnectFromConnectedWiFi();
                return true;
            case R.id.item2:
                removeNetworks();
                return true;/*
            case R.id.item3:
                showSSIDAndPWD();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void disconnectFromConnectedWiFi()
    {
        if(ssid.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "PLEASE CONNECT FIRST", Toast.LENGTH_SHORT).show();
        }
        else
        {
            wifiManager.disconnect();
            Toast.makeText(this, "DISCONNECTED FROM "+ssid, Toast.LENGTH_SHORT).show();
            conStat.setText("CONNECTED TO:");
        }

    }
    public void showSSIDAndPWD()
    {
        Toast.makeText(getApplicationContext(), "DISPLAYING STORED SSIDs...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),ShowSSIDPWD.class);
        startActivity(intent);
    }

    public void removeNetworks()
    {
        if(ssid.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "PLEASE CONNECT FIRST", Toast.LENGTH_SHORT).show();
        }
        else if(!ssid.equalsIgnoreCase("AUTO_HOME"))
        {
            Toast.makeText(this, "NOT AUTO_HOME", Toast.LENGTH_SHORT).show();
        }
        else
        {
            List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
            connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            allNetworkInfo = connectivityManager.getAllNetworkInfo();
            networkInfoWiFi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(networkInfoWiFi.isConnected())
            {
                wifiInfo = wifiManager.getConnectionInfo();
                networkID = wifiManager.getConnectionInfo().getNetworkId();
                wifiName = wifiInfo.getSSID();
                wifiName = wifiName.replace("\"","");
                Toast.makeText(this, "GOT THE NETWORK ID:"+ networkID + "SSID:"+wifiName, Toast.LENGTH_SHORT).show();
                for( WifiConfiguration i : list )
                {
                    if(networkID == i.networkId)
                    {
                        wifiManager.removeNetwork(networkID);
                        MyTask myTask = new MyTask();
                        myTask.execute(wifiMessage);
                        Toast.makeText(this, "REMOVED:"+networkID, Toast.LENGTH_SHORT).show();
                        wifiManager.saveConfiguration();
                        Toast.makeText(this, "SAVED CONFIG", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }

    }
    public void onPause()
    {
        super.onPause();
        //removeNetworks();
        //Toast.makeText(this,message + "onPause() method",Toast.LENGTH_SHORT).show();
    }
    public void onStop()
    {
        super.onStop();
        //removeNetworks();
        //Toast.makeText(this,message + "onStop() method",Toast.LENGTH_SHORT).show();
    }
    public void onDestroy()
    {
        super.onDestroy();
        removeNetworks();
        //Toast.makeText(this,message + "onDestroy() method",Toast.LENGTH_SHORT).show();
    }
    /********************************************** HELPER   CLASS *****************************************/
    class MyTask extends AsyncTask<String, Void, String> {
        String result = null, exception = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(wifiIpAddress, wifiPortNumb), 5000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                StringBuilder stringBuilder = new StringBuilder(wifiMessage);
                String formattedMessage = stringBuilder.append("\r").toString();
                int length = formattedMessage.length();
                String intToLength = String.valueOf(length);
                TimeUnit.SECONDS.sleep(1);
                byte[] b = formattedMessage.getBytes();
                dataOutputStream.write(b);
                dataOutputStream.flush();
                TimeUnit.SECONDS.sleep(2);
                dataOutputStream.close();
                final String all = null;
                socket.close();
                return all;
            } catch (InterruptedException ie) {
                return ie.toString() + " Inside doInBackground()";
            } catch (SocketException se) {
                return se.toString() + " Inside doInBackground()";
            } catch (IOException ioe) {
                return ioe.toString() + " Inside doInBackground()";
            }
        }

        protected void onPostExecute(final String responseStringReceived)
        {
            Toast.makeText(getApplicationContext(), "SENT:" + wifiMessage, Toast.LENGTH_LONG).show();
        }
    }
}