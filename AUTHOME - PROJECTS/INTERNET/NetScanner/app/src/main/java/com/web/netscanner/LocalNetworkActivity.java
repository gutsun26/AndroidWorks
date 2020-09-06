package com.web.netscanner;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class LocalNetworkActivity extends AppCompatActivity
{
    public String gatewayIP = "";
    private boolean isRefreshing = false;
    public String wifiIp = "";
    public boolean isSearchingForDevicesInSubnet = false;
    ArrayList<SubnetInfo> subnetAddresses = new ArrayList<>();
    ArrayAdapter adapter;
    ListView listView;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subnet_layout);
        listView = (ListView)findViewById(R.id.list);
        message = (TextView)findViewById(R.id.message);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subnetAddresses);
        listView.setAdapter(adapter);
        //startFindingThread();
        findDevicesInSubnet();
    }

    public void startFindingThread()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalNetworkActivity.this.findDevicesInSubnet();
            }
        }).start();
    }
    public void checkHosts(String subnet)
    {
        int timeout=1000;
        for (int i=1;i<255;i++) {
            String host = subnet + "." + i;
            message.setText(host);
        }
    }
    public void findDevicesInSubnet()
    {
        if (!this.isSearchingForDevicesInSubnet)
        {
            this.wifiIp = getWifiIP();
            this.gatewayIP = getWifiGatewayIP();
            if (this.wifiIp == null || this.wifiIp.equals(""))
            {
                toastMessage("Only supported on wifi, please turn wifi on");
                return;
            }
            else
            {
                toastMessage("Scanning Router...");
                toastMessage("IP Address of this Device:"+wifiIp);
                toastMessage("IP Address of Router:"+gatewayIP);
            }
            //this.isSearchingForDevicesInSubnet = true;
            checkHosts(gatewayIP);
            /*

            try
            {
                final SubnetDevices fromLocalAddress = SubnetDevices.fromLocalAddress();
                fromLocalAddress.setTimeOutMillis(3500);
                fromLocalAddress.setNoThreads(95);
                fromLocalAddress.findDevices(new SubnetDevices.OnSubnetDeviceFound()
                {
                    public void onDeviceFound(Device device)
                    {
                        try
                        {
                            InetAddress byName = InetAddress.getByName(device.f44ip);
                            //device.mac = ARPInfo.getMACFromIPAddress(byName.getHostAddress());
                            message.setText("Z:"+byName.toString());
                            PingResult doPing = new PingResult(byName);
                            if (doPing.isReachable)
                            {
                                device.time = doPing.timeTaken;
                            }
                        }
                        catch (UnknownHostException e)
                        {
                            message.setText("X:"+e.toString());
                        }
                        LocalNetworkActivity.this.addSubnetDevice(device);
                    }
                    public void onFinished(ArrayList<Device> arrayList)
                    {
                        LocalNetworkActivity.this.isSearchingForDevicesInSubnet = false;
                    }
                });
            }catch(Exception e){message.setText("Y:"+e.toString());}*/
        }
    }
    public String getWifiIP()
    {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager == null || wifiManager.getConnectionInfo() == null)
        {
            return null;
        }
        return intToInetAddress(wifiManager.getConnectionInfo().getIpAddress()).getHostAddress();
    }

    public String getWifiGatewayIP()
    {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager == null || wifiManager.getDhcpInfo() == null)
        {
            return null;
        }
        return intToInetAddress(wifiManager.getDhcpInfo().gateway).getHostAddress();
    }

    public static InetAddress intToInetAddress(int i)
    {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    public void toastMessage(String message)
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    public void addSubnetDevice(final Device device)
    {
        runOnUiThread(new Runnable()
        {
            public void run()
            {
                SubnetInfo subnetInfo = new SubnetInfo(device);
                StringBuilder sb = new StringBuilder();
                sb.append("Add subnet device: ");
                sb.append(subnetInfo.f51ip);
                sb.append(" ");
                sb.append(subnetInfo.mac);
                sb.append(" is null");
                sb.append(subnetInfo.macAddressInfo == null);
                if (subnetInfo.macAddressInfo != null)
                {
                }

                if (subnetInfo.f51ip.equals(LocalNetworkActivity.this.wifiIp) && Settings.getHideDevice()) {
                    return;
                }
                if (!subnetInfo.f51ip.equals(LocalNetworkActivity.this.gatewayIP) || !Settings.getHideGateway()) {
                    if (subnetInfo.f51ip.equals(LocalNetworkActivity.this.wifiIp))
                    {

                    } else if (subnetInfo.f51ip.equals(LocalNetworkActivity.this.gatewayIP))
                    {

                    }
                    Iterator it = LocalNetworkActivity.this.subnetAddresses.iterator();
                    while (it.hasNext()) {
                        if (((SubnetInfo) it.next()).f51ip.equals(subnetInfo.f51ip)) {
                            return;
                        }
                    }
                    LocalNetworkActivity.this.addIp(subnetInfo.f51ip);
                    LocalNetworkActivity.this.subnetAddresses.add(subnetInfo);
                    LocalNetworkActivity.this.adapter.notifyDataSetChanged();
                    //LocalNetworkActivity.this.subnetAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    public boolean addIp(String str)
    {
        if (IPTools.isIPv4Address(str))
        {
            return App.get().getCacheHelper().getIpAddressCache().add(str);
        }
        return false;
    }
}

