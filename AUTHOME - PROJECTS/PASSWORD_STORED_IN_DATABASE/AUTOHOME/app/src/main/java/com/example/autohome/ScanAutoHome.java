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
import android.graphics.Color;
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
import android.view.Menu;
import android.view.MenuItem;
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
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ScanAutoHome extends AppCompatActivity
{
    String wifiName=null;
    String messageDisplay="NOTHING";
    String unknownSSID = "<unknown ssid>";
    String nullSSID = "0x";
    WifiInfo wifiInfo;
    WifiManager wifiManager;
    Socket socket = new Socket();
    String wifiIpAddress        =   "";
    int    wifiPortNumb        =   80;
    DataOutputStream dataOutputStream;
    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    String mobileIpAddress="";
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
    String connectionStatusString=null;
    EditText pass;
    TextView textView;
    String message              =   "";
    String fileName = "PASSWORD.txt";
    String directory = "Download";
    String subDirectory = "JEANIE";
    String livingRoom="";
    String bedRoom="";
    String roomChoice="";
    String checkPassword="", getPassword="";
    boolean recordStat=false;

    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanwifi);
        autoHome = findViewById(R.id.autoHome);
        textView = findViewById(R.id.wifi);
        autoHome.setVisibility(View.INVISIBLE);
        listView = findViewById(R.id.wifiList);

        Intent connectionStatus = getIntent();
        Bundle connectionStatusBundle = connectionStatus.getExtras();
        connectionStatusString = connectionStatusBundle.getString("CON_STAT");

        textView.setText("CONNECTED TO:"+connectionStatusString);


        /*ENABLING WIFI*/
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanWifi();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ssid = ((TextView) view).getText().toString();
                connectToWifi(ssid);
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
        //Intent basicIntent = new Intent(this, RoomChoice.class);
        //startActivity(basicIntent);

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
                adapter.notifyDataSetChanged();/*
                if(arrayList.contains(connectionStatusString))
                {
                    Toast.makeText(getApplicationContext(),"WOW!",Toast.LENGTH_SHORT).show();
                    getConnectedIPAddress();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"( o )( o )",Toast.LENGTH_SHORT).show();
                }
                */
            }
        }
    };

    private void connectToWifi(final String wifiSSID)
    {
        getPassWordDialogBox(wifiSSID);
        //checkPassword = passwordFileOperation();
        //Toast.makeText(this, "THE PASSWORD:"+checkPassword,Toast.LENGTH_SHORT).show();
        /*
        checkPassword = "OK";
        if(checkPassword.equalsIgnoreCase("OK"))
        {
            /*IF THE RETURNED STRING IS "OK", THEN OPEN A PASSWORD DIALOG BOX TO TYPE THE PASSWORD & THUS SAVE IT IN THE INTERNAL MEMORY*
            getPassWordDialogBox(wifiSSID);
        }

        else/*IF PASSWORD SAVED IS RETRIEVED, ENTER THIS BLOCK OF THE CODE*
        {
            /*THIS BLOCK MEANS THE FILE EXISTS AND THUS PROCEED FURTHER*
            finallyConnect(checkPassword, wifiSSID);
        }
         */
    }//end of connectToWifi()

    public void getPassWordDialogBox(final String wifiSSID)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.connect);
        dialog.setTitle("Connect to " + wifiSSID);
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
                if(getPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"PASSWORD CANNOT BE EMPTY!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    savePassword(getPassword);                  /*SAVING THE PASSWORD IN THE TEXT FILE & DATABASE*/
                    finallyConnect(getPassword, wifiSSID);
                    dialog.dismiss();
                }
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
        /*EXTRACTING THE PASSWORD STORED IN THE DATABASE*/
        DBCreation dbCreation = new DBCreation(this);
        String storedPssword = dbCreation.getPWD(networkSSID);
        //Toast.makeText(this, "STORED PASSWORD:"+storedPssword, Toast.LENGTH_SHORT).show();

        /*END OF EXTRACTING THE PASSWORD*/
        if(storedPssword.equalsIgnoreCase(networkPass))
        {
            textView.setText("CONNECTION SUCCESSFUL!");
            autoHome.setVisibility(View.VISIBLE);
            autoHome.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    getRoomConfiguration();
                }
            });
        }
        else
        {
            Toast.makeText(this, "WRONG PASSWORD! TRY AGAIN!", Toast.LENGTH_SHORT).show();
        }
        /*
        textView.setText("CONNECTION SUCCESSFUL!");
        autoHome.setVisibility(View.VISIBLE);
        autoHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getRoomConfiguration();
            }
        });

         */


    }//end of finallyConnect()
    public void savePassword(String thePasswordToBeSaved)
    {
        String theEncryptedString=null;
        theEncryptedString = encryptTheString(thePasswordToBeSaved);              /*ENCRYPTING THE GIVEN STRING*/
        //Toast.makeText(this, theEncryptedString, Toast.LENGTH_SHORT).show();
        /********************SAVING PASSWORD IN INTERNAL STORAGE***********************************/
        /*
        try
        {
            File myfolder=getFilesDir();
            Toast.makeText(this, myfolder.toString(), Toast.LENGTH_SHORT).show();
            //errorMsg.setText(myfolder.toString());
            File f=new File(myfolder, subDirectory);
            f.mkdir();
            File pwdfile = new File(f, fileName);
            FileWriter writer = new FileWriter(pwdfile,true);
            writer.write(thePasswordToBeSaved);                         //SAVE THE PASSWORD IN THE TEXT FILE
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
        */
        /********************END OF BLOCK OF CODE STORING PASSWORD INTERNALLY**********************/

        /********************SAVING PASSWORD IN EXTERNAL STORAGE***********************************/

        try
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                File root = new File(getExternalFilesDir(null)+File.separator+directory, subDirectory);
                if (!root.exists())
                {
                    root.mkdirs();
                    File pwdfile = new File(root, fileName);
                    FileWriter writer = new FileWriter(pwdfile,true);
                    writer.write(theEncryptedString);                         //SAVE THE PASSWORD IN THE TEXT FILE
                    //writer.append("\r\n");
                    writer.flush();
                    writer.close();
                    //Toast.makeText(this, "FILE CREATED:" + root.getPath()+ " "+fileName+" PASSWORD IS SAVED!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                File root = new File(Environment.getExternalStorageDirectory()+File.separator+directory, subDirectory);
                if (!root.exists())
                {
                    root.mkdirs();
                    File pwdfile = new File(root, fileName);
                    FileWriter writer = new FileWriter(pwdfile,true);
                    writer.write(theEncryptedString);                         //SAVE THE PASSWORD IN THE TEXT FILE
                    //writer.append("\r\n");
                    writer.flush();
                    writer.close();
                    //Toast.makeText(this, "FILE CREATED:" + root.getPath()+ " "+fileName+" PASSWORD IS SAVED!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        /********************END OF BLOCK OF CODE STORING PASSWORD EXTERNALLY**********************/


        /****************************SAVING PASSWORD IN DATABASE***********************************/



        /********************END OF BLOCK OF CODE STORING PASSWORD IN DATABASE**********************/
    }
    public String passwordFileOperation()
    {
        String returnString="";
        /*
        try
        {
            /********************RETRIEVING PASSWORD FROM INTERNAL STORAGE***********************************/
            /*
            File root = getFilesDir();
            if(root.exists())
            {
                Toast.makeText(this, "DIRECTORY TREE EXISTS", Toast.LENGTH_SHORT).show();
                File thePWDFile = new File(root+File.separator+subDirectory, fileName);
                if(thePWDFile.exists()) //IF THE FILE EXISTS, RETURN THE SAVED PASSWORD
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
                    return returnString;//RETURNING THE SAVED PASSWORD
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
                return returnString;        //IF THE FILE/FOLDER DO NOT EXIST, RETURN THIS STRING
            }
        }
        catch(IOException e)
        {
           return e.toString();
        }
        */
        /********************END OF RETRIEVING PASSWORD FROM INTERNAL STORAGE****************************/



        /********************RETRIEVING PASSWORD FROM EXTERNAL STORAGE***********************************/
        try
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                File root = new File(getExternalFilesDir(null)+File.separator+directory, subDirectory);
                if (!root.exists())
                {
                    /*CHECKING WHETHER THE FOLDER AND FILE EXISTS OR NOT*/
                    returnString = "OK";
                    return returnString;        /*IF THE FILE/FOLDER DO NOT EXIST, RETURN THIS STRING*/
                }
                else
                {
                    /*IF THE FOLDER AND FILE EXIST, THEN EXTRACT THE PASSWORD*/
                    File thePWDFile = new File(root, fileName);
                    BufferedReader br = new BufferedReader(new FileReader(thePWDFile));
                    String string;
                    while((string = br.readLine()) != null)
                    {
                        returnString =  string;
                        Toast.makeText(this, "PASSWORD:"+returnString, Toast.LENGTH_SHORT).show();
                    }
                    return returnString;
                }
            }
            else
            {
                File root = new File(Environment.getExternalStorageDirectory()+File.separator+directory, subDirectory);
                if (!root.exists())
                {
                    /*CHECKING WHETHER THE FOLDER AND FILE EXISTS OR NOT*/
                    returnString = "OK";
                    return returnString;        /*IF THE FILE/FOLDER DO NOT EXIST, RETURN THIS STRING*/
                }
                else
                {
                    /*IF THE FOLDER AND FILE EXIST, THEN EXTRACT THE PASSWORD*/
                    File thePWDFile = new File(root, fileName);
                    BufferedReader br = new BufferedReader(new FileReader(thePWDFile));
                    String string;
                    while((string = br.readLine()) != null)
                    {
                        returnString =  string;
                        Toast.makeText(this, "PASSWORD:"+returnString, Toast.LENGTH_SHORT).show();
                    }
                    return returnString;
                }
            }
        }
        catch(IOException e)
        {
            return e.toString();
        }
        /********************END OF RETRIEVING PASSWORD FROM EXTERNAL STORAGE****************************/


        /********************RETRIEVING PASSWORD FROM DATABASE***********************************/

        /********************END OF RETRIEVING PASSWORD FROM DATABASE****************************/
    }
    public String encryptTheString(String thePasswordToBeEncrypted)
    {
        String theBufferString=null, theEncryptedPassword=null;
        theBufferString = thePasswordToBeEncrypted;
        StringBuffer en = new StringBuffer();
        String[] data = new String[100];
        double[] num = new double[100];
        double[] bum = new double[100];
        int[] dum = new int[100];
        char[] c=theBufferString.toCharArray();
        for (int i = 0; i < theBufferString.length(); i++)
        {
            num[i] = theBufferString.charAt(i) - '0';
            num[i] = (num[i] * (Math.pow((i + 1), 2)));
            en = en.append(num[i] + ",");
        }
        theEncryptedPassword = en.toString();
        return theEncryptedPassword;
    }
    public void getRoomConfiguration()
    {
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException ie){ie.printStackTrace();}
        Bundle connection = new Bundle();
        connection.putString("SSID",ssid);
        Intent basicIntent = new Intent(this, GetRoomConfiguration.class);
        basicIntent.putExtras(connection);
        startActivity(basicIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wifi_setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                scanWifi();
                return true;
            default:
                return false;
        }
    }

    public void getConnectedIPAddress()
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
           Toast.makeText(this,"ROUTER:"+wifiIpAddress, Toast.LENGTH_SHORT).show();
           TimeUnit.SECONDS.sleep(2);
           wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
           WifiInfo wifiInfo = wifiManager.getConnectionInfo();
           int phoneIp = wifiInfo.getIpAddress();
           mobileIpAddress = Formatter.formatIpAddress(phoneIp);
           if(mobileIpAddress != null) {
               textView.setText("ALREADY CONNECTED TO AUTOMATION DEVICE!");
               autoHome.setVisibility(View.VISIBLE);
               autoHome.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       getRoomConfiguration();
                   }
               });
               Toast.makeText(this, "YOUR IP ADDRESS:" + mobileIpAddress, Toast.LENGTH_SHORT).show();
           }
       }catch (InterruptedException ie){ie.printStackTrace();}
       catch(UnknownHostException ue){Toast.makeText(this, ue.toString(),Toast.LENGTH_SHORT).show();}
    }
}