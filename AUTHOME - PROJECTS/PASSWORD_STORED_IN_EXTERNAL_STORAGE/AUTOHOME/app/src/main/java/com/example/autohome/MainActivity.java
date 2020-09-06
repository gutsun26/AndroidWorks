package com.example.autohome;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.Settings;
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


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
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
    String gopranWifi = "AUTO_HOME";
    String ssid="";
    Button theButton, selectRoom, writeButton;
    WifiScanReceiver wifiScanReceiver;
    WifiManager mainWifiObj;
    WifiInfo wifiInfo;
    ListView listView;
    String wifis[];
    TextView myText, ssidText, msg;
    EditText pass;

    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;
    DhcpInfo dhcpInfo;
    int wifiFlag=0;
    AlertDialog alertDialog;
    String manufacturer = Build.MANUFACTURER;
    String model = Build.MODEL;
    int version = Build.VERSION.SDK_INT;
    String versionRelease = Build.VERSION.RELEASE;
    static float vers ;//= Build.VERSION.SDK_INT;
    TextView manuf, mod, ver, myTxt;
    String deviceVersion= Build.VERSION.RELEASE;
    String limitVersion="4.0";
    String result="";
    RelativeLayout relativeLayout;
    String appVersion = "47";
    String message = "This App requires: \nGPS to be SWITCHED ON and \nMobile-Data to be SWITCHED OFF!";

    int status;//To check whether MOBILEDATA is on or not

    /*VARIABLES FOR PROGRAMMATICALLY SWITHCHING ON GPS*/
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
    /*END OF VARIABLES FOR GPS*/


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relativeLayout = (RelativeLayout)findViewById(R.id.bg);
        //relativeLayout.setBackgroundResource(R.drawable.h2);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        selectRoom = (Button)findViewById(R.id.selectRoom);
        //writeButton = (Button)findViewById(R.id.writeButton);
        manuf   = (TextView)findViewById(R.id.manuf);
        mod     = (TextView)findViewById(R.id.model);
        ver     = (TextView)findViewById(R.id.ver);
        myTxt   = (TextView)findViewById(R.id.myTxt);

        manuf.setText(manufacturer);
        mod.setText(model);
        ver.setText(versionRelease);
        myTxt.setText("App Version:" + appVersion +"\n" +message);

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

        //getLocation();
        /*END OF GPS ON BLOCK OF CODE*/

        /*
        Toast.makeText(this, "ENABLE LOCATION & NOW CLICK BACK BUTTON OF YOUR PHONE!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        else
        {
            //Toast.makeText(this, "PHONE's VERSION IS LOW!", Toast.LENGTH_LONG).show();
        }

         */

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
                    //Toast.makeText(getApplicationContext(),"MOBILE DATA IS NOT ON! GOOD!",Toast.LENGTH_SHORT).show();
                    chooseRoom();
                }
            }
        });
    }
    public void showDialogBox()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("ENABLE GPS!");
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
            Intent scanAutoHome = new Intent(this, RoomChoice.class);
            startActivity(scanAutoHome);
        }
    }

    public int checkNetworkConnection()
    {
        Toast.makeText(this, "FINDING INTERNET CONNECTION TYPE!",Toast.LENGTH_SHORT).show();
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
    /***************************OBSOLETE CODE BLOCK STARTS HERE AFTER***************************************************/
    private void getLocation()
    {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},AppConstants.LOCATION_REQUEST);

        }
        else
        {
            if (isContinue)
            {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            }
            else
            {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location ->
                {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                    } else {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                });
            }
        }
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1000:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (isContinue)
                    {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                    else
                    {
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location ->
                        {
                            if (location != null)
                            {
                                wayLatitude = location.getLatitude();
                                wayLongitude = location.getLongitude();
                                txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                            }
                            else
                            {
                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        });
                    }
                }
                else
                {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    /***************************OBSOLETE CODE BLOCK ENDS HERE & ABOVE***************************************************/



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
    public void onPause()
    {
        super.onPause();
        //Toast.makeText(this,message + "onPause() method",Toast.LENGTH_SHORT).show();
    }
    public void onStop()
    {
        super.onStop();
        //Toast.makeText(this,message + "onStop() method",Toast.LENGTH_SHORT).show();
    }
    public void onDestroy()
    {
        super.onDestroy();
        //Toast.makeText(this,message + "onDestroy() method",Toast.LENGTH_SHORT).show();
    }
}
