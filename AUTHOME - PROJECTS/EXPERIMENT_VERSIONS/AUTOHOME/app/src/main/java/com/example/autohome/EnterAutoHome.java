package com.example.autohome;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;

public class EnterAutoHome extends ListActivity
{
    String gopranWifi = "AUTO_HOME";
    String ssid="";
    Button gopranButton;
    WifiManager mainWifiObj;
    WifiInfo wifiInfo;
    WifiScanReceiver wifiReciever;
    ListView listView;
    String wifis[];
    Intent intent = new Intent();
    TextView textView, msg;
    EditText pass;

    String wifiIpAddress        =   "";
    int     wifiPortNumb        =   80;
    DhcpInfo dhcpInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);
        gopranButton = (Button)findViewById(R.id.gopran);
        listView = getListView();

        textView        = (TextView)findViewById(R.id.wifi);
        gopranButton    = (Button)findViewById(R.id.gopran);
        msg             = (TextView)findViewById(R.id.msg);
        gopranButton.setEnabled(false);

        mainWifiObj = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.setWifiEnabled(true);
        wifiInfo = mainWifiObj.getConnectionInfo();
        ssid=wifiInfo.getSSID();
        textView.setText(ssid);

        Toast.makeText(this, "ENABLED WIFI", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "CONNECTED TO:"+ssid, Toast.LENGTH_SHORT).show();

        dhcpInfo = mainWifiObj.getDhcpInfo();
        int ip = dhcpInfo.gateway;
        ip = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ? Integer.reverseBytes(ip) : ip;

        byte[] ipAddressByte = BigInteger.valueOf(ip).toByteArray();
        try
        {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            wifiIpAddress = myAddr.getHostAddress();
        }
        catch(UnknownHostException uhe)
        {
            Toast.makeText(this,"UNKNOWN HOST",Toast.LENGTH_SHORT).show();
        }
        if(ssid.equals(gopranWifi))
        {
            gopranButton.setEnabled(true);
        }
        else if(ssid.startsWith("0x") || ssid.matches(""))
        {
            textView.setText("NOTHING. PLEASE CONNECT TO AUTO_HOME");
            gopranButton.setEnabled(false);
        }
        wifiReciever = new WifiScanReceiver();
        mainWifiObj.startScan();

        // listening to single list item on click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                ssid = ((TextView) view).getText().toString();
                connectToWifi(ssid);
            }
        });
        gopranButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goNinja(gopranButton);
            }
        });
    }
    public void goNinja(View view)
    {
        try
        {
            Intent intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void onPause()
    {
        unregisterReceiver(wifiReciever);
        super.onPause();
    }//end of onPause()

    protected void onResume()
    {
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }//end of onResume()

    //Inner class definition WifiScanReceiver
    class WifiScanReceiver extends BroadcastReceiver
    {
        @SuppressLint("UseValueOf")
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
            wifis = new String[wifiScanList.size()];
            for(int i = 0; i < wifiScanList.size(); i++){
                wifis[i] = ((wifiScanList.get(i)).toString());
            }
            String filtered[] = new String[wifiScanList.size()];
            int counter = 0;
            for (String eachWifi : wifis) {
                String[] temp = eachWifi.split(",");

                filtered[counter] = temp[0].substring(5).trim();

                counter++;

            }
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item,R.id.label, filtered));
        }
    }//end of inner class WifiScanReceiver

    private void finallyConnect(String networkPass, String networkSSID)
    {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", networkSSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", networkPass);

        // remember id
        int netId = mainWifiObj.addNetwork(wifiConfig);
        mainWifiObj.disconnect();
        mainWifiObj.enableNetwork(netId, true);
        mainWifiObj.reconnect();

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"\"" + networkSSID + "\"\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        mainWifiObj.addNetwork(conf);
        textView.setText(ssid);
        if(ssid.equals("AUTO_HOME"))
        {
            gopranButton.setEnabled(true);
            msg.setText("THE APP IS CONNECTED TO AUTO_HOME");
        }
        else
        {
            gopranButton.setEnabled(false);
        }

    }//end of finallyConnect()

    private void connectToWifi(final String wifiSSID)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.connect);
        dialog.setTitle("Connect to Network");
        TextView textSSID = (TextView) dialog.findViewById(R.id.textSSID1);

        Button dialogButton = (Button) dialog.findViewById(R.id.okButton);
        pass = (EditText) dialog.findViewById(R.id.textPassword);
        textSSID.setText(wifiSSID);

        // if button is clicked, connect to the network;
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkPassword = pass.getText().toString();
                finallyConnect(checkPassword, wifiSSID);
                dialog.dismiss();
            }
        });
        dialog.show();
    }//end of connectToWifi()
}
