package com.web.autohomenet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.format.Formatter;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.web.autohomenet.R;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import com.web.autohomenet.wifirouter.GetRouterDetailsActivity;


/*
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
*/
public class MainActivity extends AppCompatActivity
{
    /*LOCATION*/
    //FusedLocationProviderClient mFusedLocationClient;

    private AppBarConfiguration mAppBarConfiguration;
    WifiManager wifiManager;
    WifiInfo wifiInfo;
    int networkID=0;
    String wifiName=null,wifiName2=null;
    NetworkInfo networkInfoWiFi;
    NetworkInfo[] allNetworkInfo;
    ConnectivityManager connectivityManager;
    String wifiIpAddress=null;
    String mobileIpAddress=null;
    boolean isConn2Wifi=false;
    boolean isMobileDataOn=false;
    boolean isInternetAvaialble=false;
    String ipString=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkManyConnections(view);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)|| super.onSupportNavigateUp();
    }
    public void checkManyConnections(View view)
    {
        checkMobileData(view);
    }
    public void checkMobileData(View view)
    {
        isMobileDataOn = isMobileDataPackOn();
        if(isMobileDataOn == true)
        {
            Snackbar.make(view, "Switch of Mobile Data", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else
        {
            checkWifiCon(view);
        }
    }

    public void checkWifiCon(View view)
    {
        if(isMobileDataOn)
        {
            Snackbar.make(view, "Switch of Mobile Data", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else
        {
            isConn2Wifi = isConnectedToWifi();
            if(isConn2Wifi == true)
            {
                getSSIDPWD(view);
            }
            else
            {
                Snackbar.make(view, "Connect to Automation Device", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }
    public void getSSIDPWD(View view)
    {
        Bundle roomConfigBundle = new Bundle();
        roomConfigBundle.putString("ROUTER_IP", wifiIpAddress);
        Intent intent = new Intent(this, com.web.autohomenet.wifirouter.GetRouterDetailsActivity.class);
        intent.putExtras(roomConfigBundle);
        startActivity(intent);
    }
    public boolean isOnline()
    {
        try
        {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) { return false; }
    }
    public boolean isMobileDataPackOn()
    {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connectivitymanager.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo)
        {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
            {
                if (netInfo.isConnected())
                {
                    return false;
                }
            }
            else if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
            {
                if (netInfo.isConnected())
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isConnectedToWifi()
    {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        networkID = wifiManager.getConnectionInfo().getNetworkId();
        wifiName = wifiInfo.getSSID();
        wifiName = wifiName.replace("\"","");
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        allNetworkInfo = connectivityManager.getAllNetworkInfo();
        networkInfoWiFi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfoWiFi.isConnected())
        {
            wifiInfo = wifiManager.getConnectionInfo();
            networkID = wifiManager.getConnectionInfo().getNetworkId();
            wifiName = wifiInfo.getSSID();
            wifiName = wifiName.replace("\"","");
        }
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
            mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            if( !(mobileIpAddress.isEmpty()) )
            {
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
    }
    public void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}
