package com.example.autohome;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ScanAutoHome extends AppCompatActivity
{

    WifiManager wifiManager;
    ListView listView;
    Button buttonScan, disconForget, autoHome, resetBtn;
    //Switch autoHome;
    private int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;
    String ssid = "";
    //String theSSID = "AUTO_HOME";
    String theSSID = "ABCD_HOME";
    String testSSID = "1234_HOME   ";
    String originalSSID = "AUTO_HOME";
    String wifiPassword = "15081947";
    EditText pass;
    TextView textView;
    DhcpInfo dhcpInfo;
    String message              =   "";
    String mobileIpAddress;
    String fileName = "PASSWORD.txt";
    String directory = "Download";
    String subDirectory = "JEANIE";
    String livingRoom="";
    String bedRoom="";
    String roomChoice="";
    String checkPassword="", getPassword="";
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanwifi);
        buttonScan = findViewById(R.id.scanBtn);
        autoHome = findViewById(R.id.autoHome);
        disconForget = findViewById(R.id.disconForget);
        resetBtn=findViewById(R.id.resetBtn);
        textView = findViewById(R.id.wifi);
        autoHome.setVisibility(View.INVISIBLE);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCreatedFile();
            }
        });
        buttonScan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                scanWifi();
            }
        });



        listView = findViewById(R.id.wifiList);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

        /*ENABLING WIFI*/
        if (!wifiManager.isWifiEnabled())
        {
            //Toast.makeText(this, "ENABLING WIFI...", Toast.LENGTH_SHORT).show();
            wifiManager.setWifiEnabled(true);
        }
        else
        {
            //Toast.makeText(this, "WIFI IS ENABLED ALREADY!", Toast.LENGTH_SHORT).show();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanWifi();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        roomChoice = extras.getString("ROOM_CHOICE");
        //textView.setText("RECEIVED:"+roomChoice);
        /*
        if(roomChoice.equalsIgnoreCase("1"))
        {
            textView.setText("CHOOSE ABCD_HOME");
        }
        else if(roomChoice.equalsIgnoreCase("2"))
        {
            textView.setText("CHOOSE 1234_HOME");
        }
        else
        {
            textView.setText("NOTHING!");
        }

         */


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ssid = ((TextView) view).getText().toString();
                connectToWifi(ssid);
                /*BELOW CODE IS OBSOLETE*/
                /*
                if(ssid.equalsIgnoreCase(theSSID) && (roomChoice.equalsIgnoreCase("1")))
                {
                    connectToWifi(ssid);
                }
                if(ssid.equalsIgnoreCase(testSSID) && (roomChoice.equalsIgnoreCase("2")))
                {
                    connectToWifi(ssid);
                }

                 */
            }
        });

        disconForget.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                diconnWifi();
            }
        });
    }

    public void diconnWifi()
    {
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list )
        {
            wifiManager.removeNetwork(i.networkId);
        }
        wifiManager.disconnect();
        wifiManager.setWifiEnabled(false);
        Toast.makeText(this, "DISCONNECTED FROM ALL WIFI!", Toast.LENGTH_SHORT).show();
    }

    private void scanWifi()
    {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.setWifiEnabled(true);
        //Toast.makeText(this, "ENABLED WIFI", Toast.LENGTH_SHORT).show();
        wifiManager.startScan();
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
    }
    public void removeCreatedFile()
    {
        autoHome.setVisibility(View.INVISIBLE);
        File myfolder=getFilesDir();
        File f=new File(myfolder+"/"+subDirectory+"/"+fileName);
        f.delete();
        Toast.makeText(this, f.getPath()+" DELETED", Toast.LENGTH_SHORT).show();
        Intent basicIntent = new Intent(this, RoomChoice.class);
        startActivity(basicIntent);

    }
    BroadcastReceiver wifiReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);

            for (ScanResult scanResult : results)
            {
                arrayList.add(scanResult.SSID);
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void connectToWifi(final String wifiSSID)
    {
        checkPassword = passwordFileOperation();
        Toast.makeText(this, "THE PASSWORD:"+checkPassword,Toast.LENGTH_SHORT).show();
        if(checkPassword.equalsIgnoreCase("OK"))
        {
            /*IF THE RETURNED STRING IS "OK", THEN OPEN A PASSWORD DIALOG BOX TO TYPE THE PASSWORD & THUS SAVE IT IN THE INTERNAL MEMORY*/
            getPassWordDialogBox(wifiSSID);
        }
        else/*IF PASSWORD SAVED IS RETRIEVED, ENTER THIS BLOCK OF THE CODE*/
        {
            /*THIS BLOCK MEANS THE FILE EXISTS AND THUS PROCEED FURTHER*/
            finallyConnect(checkPassword, wifiSSID);
        }


    }//end of connectToWifi()

    public void getPassWordDialogBox(final String wifiSSID)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.connect);
        dialog.setTitle("Connect to Network");
        TextView textSSID = (TextView) dialog.findViewById(R.id.textSSID1);

        Button dialogButton = (Button) dialog.findViewById(R.id.okButton);
        pass = (EditText) dialog.findViewById(R.id.textPassword);
        textSSID.setText(wifiSSID);

        // if button is clicked, connect to the network;
        dialogButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getPassword = pass.getText().toString();
                //encryptTheString(getPassword);              /*ENCRYPTING THE GIVEN STRING*/
                savePassword(getPassword);                  /*SAVING THE PASSWORD IN THE TEXT FILE*/
                finallyConnect(getPassword, wifiSSID);
                dialog.dismiss();
                /*BELOW BLOCK OF CODE IS REDUNDANT*/
                /*
                if(getPassword.equalsIgnoreCase(wifiPassword))
                {
                    //encryptTheString(getPassword);              /*ENCRYPTING THE GIVEN STRING*
                    savePassword(getPassword);                  /*SAVING THE PASSWORD IN THE TEXT FILE*
                    finallyConnect(getPassword, wifiSSID);
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "WRONG PASSWORD! CONNECT AGAIN!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

                 */
            }
        });
        dialog.show();
    }

    private void finallyConnect(String networkPass, String networkSSID)
    {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", networkSSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", networkPass);

        // remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"\"" + networkSSID + "\"\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        wifiManager.addNetwork(conf);
        textView.setText("CONNECTED TO "+ssid);
        autoHome.setVisibility(View.VISIBLE);
        autoHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getRoomConfiguration();
            }
        });
    }//end of finallyConnect()
    public void savePassword(String thePasswordToBeSaved)
    {
        Toast.makeText(this, "TRYING TO SAVE PASSWORD", Toast.LENGTH_SHORT).show();
        try
        {
            File myfolder=getFilesDir();
            Toast.makeText(this, myfolder.toString(), Toast.LENGTH_SHORT).show();
            //errorMsg.setText(myfolder.toString());
            File f=new File(myfolder, subDirectory);
            f.mkdir();
            File pwdfile = new File(f, fileName);
            FileWriter writer = new FileWriter(pwdfile,true);
            writer.write(thePasswordToBeSaved);                         /*SAVE THE PASSWORD IN THE TEXT FILE*/
            //writer.append("\r\n");
            writer.flush();
            writer.close();
            Toast.makeText(this, "FILE CREATED:" + fileName+" PASSWORD IS SAVED!", Toast.LENGTH_SHORT).show();
            //return 1;
        }
        catch(IOException e)
        {
            //errorMsg.setText(e.toString());
            //return 0;
        }

    }
    public String passwordFileOperation()
    {
        String returnString="";
        try
        {
            //File root = new File(Environment.getExternalStorageDirectory()+File.separator+directory, subDirectory);
            File root = getFilesDir();
            if(root.exists())
            {
                Toast.makeText(this, "DIRECTORY TREE EXISTS", Toast.LENGTH_SHORT).show();
                File thePWDFile = new File(root+File.separator+subDirectory, fileName);
                if(thePWDFile.exists()) /*IF THE FILE EXISTS, RETURN THE SAVED PASSWORD*/
                {
                    Toast.makeText(this, thePWDFile.getPath(), Toast.LENGTH_SHORT).show();
                    BufferedReader br = new BufferedReader(new FileReader(thePWDFile));
                    String string;
                    while((string = br.readLine()) != null)
                    {
                        returnString =  string;
                        Toast.makeText(this, "PASSWORD:"+returnString, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(this, "GOT THE PASSWORD!", Toast.LENGTH_SHORT).show();
                    }
                    return returnString;/*RETURNING THE SAVED PASSWORD*/
                }
                else
                {
                    Toast.makeText(this, "DIDN'T GET PASSWORD", Toast.LENGTH_SHORT).show();
                    returnString = "OK";
                    return returnString;
                }
            }
            else
            {
                returnString = "OK";
                return returnString;        /*IF THE FILE/FOLDER DO NOT EXIST, RETURN THIS STRING*/
            }

            /*BELOW BLOCK OF CODE IS REDUNDANT*/
            /*
            if (root.exists())
            {
                /*CHECKING WHETHER THE FOLDER & FILE EXISTS OR NOT*
                returnString = "OK";
                return returnString;        /*IF THE FILE/FOLDER DO NOT EXIST, RETURN THIS STRING*
            }
            else /*IF THE FOLDER AND FILE EXIST, THEN EXTRACT THE PASSWORD*
            {
                File thePWDFile = new File(root, fileName);
                BufferedReader br = new BufferedReader(new FileReader(thePWDFile));
                String string;
                while((string = br.readLine()) != null)
                {
                    returnString =  string;
                    //Toast.makeText(this, "PASSWORD:"+returnString, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "GOT THE PASSWORD!", Toast.LENGTH_SHORT).show();
                }
                return returnString;
            }
            */
        }
        catch(IOException e)
        {
           return e.toString();
        }
    }
    public void getRoomConfiguration()
    {
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException ie){ie.printStackTrace();}
        Intent basicIntent = new Intent(this, GetRoomConfiguration.class);
        startActivity(basicIntent);
    }
}