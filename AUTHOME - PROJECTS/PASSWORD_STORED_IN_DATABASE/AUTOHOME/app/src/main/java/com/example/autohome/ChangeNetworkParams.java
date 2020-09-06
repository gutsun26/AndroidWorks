package com.example.autohome;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

public class ChangeNetworkParams  extends AppCompatActivity
{
    EditText wifiSSID, wifiPWD;
    String wifiSSIDString = null;
    String wifiPWDString = null;
    Button setBtn, resetBtn;
    TextView ssidLengthWatch, pwdLengthWatch,errorMsg;

    /*PASSWORD RELATED VARIABLES*/
    String fileName = "PASSWORD.txt";
    String directory = "Download";
    String subDirectory = "JEANIE";
    Context context;
    /*END OF PASSWORD RELATED VARIABLES*/

    /*NETWORK RELATED VARIABLES*/
    Socket socket = new Socket();
    String wifiIpAddress        =  "192.168.4.1";
    int    wifiPortNumb        =   80;
    DataOutputStream dataOutputStream;
    WifiManager mainWifiObj;
    DhcpInfo dhcpInfo;
    String mobileIpAddress="";
    String message = null;
    int ssidStringLength = 12;
    int pwdStringLength = 8;
    int count = 0;
    int additionalCharactersForSSID=0;
    int additionalCharactersForPWD=0;
    StringBuffer sbSSID, sbPWD;
    String space=" ";
    WifiInfo wifiInfo;
    /*END OF NETWORK RELATED VARIABLES*/

    /*USER FLAGS*/
    int theFlag=0;
    /*END OF USER FLAGS*/

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_change);
        wifiSSID = findViewById(R.id.wifiSSID);
        //wifiPWD = findViewById(R.id.wifiPWD);
        setBtn = findViewById(R.id.setBtn);
        //resetBtn = findViewById(R.id.resetBtn);
        errorMsg=findViewById(R.id.errorMsg);
        ssidLengthWatch = (TextView)findViewById(R.id.ssidLengthWatch);

        ssidLengthWatch.setText(count+"/"+ssidStringLength);
        //pwdLengthWatch = (TextView)findViewById(R.id.pwdLengthWatch);
        //pwdLengthWatch.setText(count+"/"+pwdStringLength);


        mainWifiObj = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        dhcpInfo = mainWifiObj.getDhcpInfo();
        int ip = dhcpInfo.gateway;
        ip = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ? Integer.reverseBytes(ip) : ip;
        byte[] ipAddressByte = BigInteger.valueOf(ip).toByteArray();
        try
        {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            wifiIpAddress = myAddr.getHostAddress();
            //Toast.makeText(this,"ROUTER:"+wifiIpAddress, Toast.LENGTH_SHORT).show();
            WifiInfo wifiInfo = mainWifiObj.getConnectionInfo();
            int phoneIp = wifiInfo.getIpAddress();
            mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            Toast.makeText(this,"YOUR IP ADDRESS:"+mobileIpAddress, Toast.LENGTH_SHORT).show();
        }
        catch(UnknownHostException uhe)
        {
            Toast.makeText(this,"APP IS NOT CONNECTED!", Toast.LENGTH_SHORT).show();
        }

        wifiSSID.setFocusable(true);
        //wifiPWD.setFocusable(true);

        wifiSSID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                int length = wifiSSID.length();
                //int displayFinalLength = ssidStringLength - length;
                String convert = String.valueOf((length));
                ssidLengthWatch.setText(convert+"/"+ssidStringLength);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        /*
        wifiPWD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = wifiPWD.length();
                //int displayFinalLength = ssidStringLength - length;
                String convert = String.valueOf((length));
                pwdLengthWatch.setText(convert+"/"+pwdStringLength);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

         */

        setBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setParams();
            }
        });
        /*
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCreatedFile();
            }
        });

         */
    }

    public void setParams()
    {
        int ssidStringCount=0,pwdStringCount=0,ssidCount,pwdCount;

        /*READING THE CHARACTERS GIVEN*/
        wifiSSIDString = wifiSSID.getText().toString();
        //wifiPWDString = wifiPWD.getText().toString();
        //Toast.makeText(this, wifiSSIDString+" "+wifiPWDString, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, wifiSSIDString, Toast.LENGTH_SHORT).show();

        /*GETTING THE LENGTH OF THE STRINGS TYPED*/
        ssidStringCount = wifiSSIDString.length();
        //pwdStringCount = wifiPWDString.length();
        //Toast.makeText(this, String.valueOf(ssidStringCount)+"-"+String.valueOf(pwdStringCount), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, String.valueOf(ssidStringCount), Toast.LENGTH_SHORT).show();

        /*ANALYZING THE STRING LENGTH*/
        /*******************************************************************************************/
        if(ssidStringCount>ssidStringLength)
        {
            Toast.makeText(this, "WIFI NAME SHOULD BE ONLY 12 CHARACTERS", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(ssidStringCount < ssidStringLength)
            {
                additionalCharactersForSSID = ssidStringLength - ssidStringCount;
                Toast.makeText(this, "ID:"+String.valueOf(additionalCharactersForSSID), Toast.LENGTH_SHORT).show();
            }
            sbSSID = new StringBuffer(wifiSSIDString);
            for(ssidCount=0; ssidCount<additionalCharactersForSSID; ssidCount++)
            {
                sbSSID=sbSSID.append(space);
            }
        }
        /*******************************************************************************************/
        /*
        if(pwdStringCount>pwdStringLength)
        {
            Toast.makeText(this, "PASSWORD SHOULD BE ONLY 8 CHARACTERS", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(pwdStringCount < pwdStringLength)
            {
                additionalCharactersForPWD = pwdStringLength - pwdStringCount;
                Toast.makeText(this, "P:"+String.valueOf(additionalCharactersForPWD), Toast.LENGTH_SHORT).show();
            }
            sbPWD = new StringBuffer(wifiPWDString);
            for(pwdCount=0; pwdCount<additionalCharactersForPWD; pwdCount++)
            {
                sbPWD=sbPWD.append(space);
            }
        }

         */
        /*******************************************************************************************/

        MyTask myTask = new MyTask();
        message="ID="+sbSSID+"*"+"PW=15081947";
        myTask.execute(message);
        /*
        String s = sbPWD.toString();
        theFlag = savePassword(s);

        if(theFlag == 1)
        {
            MyTask myTask = new MyTask();
            message="ID="+sbSSID+"*"+"PW="+sbPWD;
            myTask.execute(message);
        }
        else
        {
            Toast.makeText(this, "PASSWORD NOT SAVED, HENCE SSID/PWD NOT CHANGED", Toast.LENGTH_SHORT).show();
        }
        */

    }
    public int savePassword(String thePasswordToBeSaved)
    {
        //Toast.makeText(this, "TRYING TO SAVE PASSWORD", Toast.LENGTH_SHORT).show();
        /********************SAVING PASSWORD IN INTERNAL STORAGE***********************************/
        /*
        try
        {
            File myfolder=getFilesDir();
            File pwdfile=new File(myfolder+"/"+subDirectory+"/"+fileName);
            if(pwdfile.exists())
            {
                pwdfile.delete();
                pwdfile.createNewFile();
                Toast.makeText(this, "DIRECTORY TREE CREATED WITH THE FILE", Toast.LENGTH_SHORT).show();
                FileWriter writer = new FileWriter(pwdfile,false);
                writer.write(thePasswordToBeSaved);                         //SAVE THE PASSWORD IN THE TEXT FILE
                //writer.append("\r\n");
                writer.flush();
                writer.close();
                Toast.makeText(this, "FILE CREATED:" + fileName+" PASSWORD IS SAVED!", Toast.LENGTH_SHORT).show();
            }
            return 1;
        }
        catch(IOException e)
        {
            errorMsg.setText(e.toString());
            return 0;
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
                    writer.write(thePasswordToBeSaved);                         //SAVE THE PASSWORD IN THE TEXT FILE
                    //writer.append("\r\n");
                    writer.flush();
                    writer.close();
                    Toast.makeText(this, "FILE CREATED:" + fileName+" PASSWORD IS SAVED!", Toast.LENGTH_SHORT).show();
                }
                return 1;
            }
            else
            {
                File root = new File(Environment.getExternalStorageDirectory()+File.separator+directory, subDirectory);
                if (!root.exists())
                {
                    root.mkdirs();
                    File pwdfile = new File(root, fileName);
                    FileWriter writer = new FileWriter(pwdfile,true);
                    writer.write(thePasswordToBeSaved);                         //SAVE THE PASSWORD IN THE TEXT FILE
                    //writer.append("\r\n");
                    writer.flush();
                    writer.close();
                    Toast.makeText(this, "FILE CREATED:" + fileName+" PASSWORD IS SAVED!", Toast.LENGTH_SHORT).show();
                }
                return 1;
            }
        }
        catch(IOException e)
        {
            errorMsg.setText(e.toString());
            return 0;
        }
        /********************END OF BLOCK OF CODE STORING PASSWORD EXTERNALLY**********************/



        /****************************SAVING PASSWORD IN DATABASE***********************************/

        /********************END OF BLOCK OF CODE STORING PASSWORD IN DATABASE**********************/

    }

    public void removeCreatedFile()
    {
        File myfolder=getFilesDir();
        File f=new File(myfolder+"/"+subDirectory+"/"+fileName);
        f.delete();
        errorMsg.setText(f.getPath());
        Toast.makeText(this, f.getPath()+" DELETED", Toast.LENGTH_SHORT).show();

    }

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
                StringBuilder stringBuilder = new StringBuilder(message);
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
                final String all = message;
                //String all = message;
                socket.close();
                return all;
            }
            catch(InterruptedException ie){return ie.toString()+" Inside doInBackground()";}
            catch(SocketException se){return se.toString()+" Inside doInBackground()";}
            catch(IOException ioe){return ioe.toString()+" Inside doInBackground()";}
        }
        protected void onPostExecute(String responseStringReceived)
        {
            //Toast.makeText(getApplicationContext(), "SENT:"+message, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "WIFI NAME CHANGED TO "+wifiSSIDString, Toast.LENGTH_LONG).show();
            finish();/*EXITING FROM THIS ACTIVITY*/
        }
    }
}
