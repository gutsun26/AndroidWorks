package com.web.wifilanscan;

import androidx.appcompat.app.AppCompatActivity;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    byte[] mb = null;
    public String   s_dns1 ;
    public String   s_dns2;
    public String   s_gateway;
    public String   s_ipAddress;
    public String   s_leaseDuration;
    public String   s_netmask;
    public String   s_serverAddress;
    DhcpInfo d;
    WifiManager wifii;

    TextView allDNSData;
    EditText allIPs;
    Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode. ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        allDNSData=(TextView)findViewById(R.id.allDNSData);
        allIPs=(EditText)findViewById(R.id.allIPs);

        btn = (Button)findViewById(R.id.btn);
        btn2 = (Button)findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAhead();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFar();
            }
        });

        wifii = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifii.setWifiEnabled(true);
        d = wifii.getDhcpInfo();
        int ipd = d.gateway;
        ipd = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ? Integer.reverseBytes(ipd) : ipd;
        byte[] ipAddressByte = BigInteger.valueOf(ipd).toByteArray();
        try
        {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            String wifiIpAddress = myAddr.getHostAddress();
            //Toast.makeText(this,"ROUTER:"+wifiIpAddress, Toast.LENGTH_SHORT).show();
            WifiInfo wifiInfo = wifii.getConnectionInfo();
            int phoneIp = wifiInfo.getIpAddress();
            String mobileIpAddress = Formatter.formatIpAddress(phoneIp);
            if(mobileIpAddress == null)
            {
                Toast.makeText(this,"APP IS DISCONNECTED FROM THE AUTOMATION DEVICE!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"YOUR IP:"+mobileIpAddress, Toast.LENGTH_SHORT).show();
            }
        }
        catch(UnknownHostException uhe)
        {
            Toast.makeText(this,"UNKNOWN HOST EXCEPTION", Toast.LENGTH_SHORT).show();
        }
        String connections = "";
        InetAddress host;
        /*
        try
        {
            host = InetAddress.getByName(intToIp(d.dns1));

            s_dns1 = "DNS 1: " + host.toString();
            s_dns2 = "DNS 2: " + d.dns2;
            s_gateway = "Default Gateway: " + intToIp(d.gateway);
            s_ipAddress = "Your IP Address: " + intToIp(d.ipAddress);
            s_leaseDuration = "Lease Time: " + intToIp(d.leaseDuration);
            s_netmask = "Subnet Mask: " + intToIp(d.netmask);
            s_serverAddress = "Server IP: " + intToIp(d.serverAddress);

            allDNSData.setText(s_dns1+"\n"+s_dns2+"\n"+s_gateway+"\n"+s_ipAddress+"\n"+s_leaseDuration+"\n"+s_netmask+"\n"+s_serverAddress);
            byte[] ipp = host.getAddress();
            for(int i = 0; i <= 2; i++)
            {
                ipp[3] = (byte) i;
                //allIPs.setText("JAWAN"+"-"+ipp[0]+"-"+ipp[1]+"-"+ipp[2]);
                InetAddress address = InetAddress.getByAddress(ipp);
                connections+= address+"@";
                //allIPs.setText(connections);
                String replaceString = connections.replace("/","");
                //toastMessage(connections);
                //toastMessage(replaceString);
                String[] arrOfString = replaceString.split("@",0);
                //toastMessage(arrOfString[i].toString());
                if(i==2)
                {
                    //runSystemCommand("ping "+arrOfString[3]);
                    InetAddress adds = InetAddress.getByName("192.168.4.3");
                    //toastMessage(adds.toString());
                    if(adds.isReachable(2000))
                    {
                        toastMessage("192.168.4.3 IP IS REACHABLE!");
                        //runSystemCommand("ping 192.168.4.3");
                    }
                    else
                    {
                        toastMessage("192.168.4.3 IP IS UNREACHABLE!");
                    }

                }

                //executeCmd("ping -c 1 -w 1 "+replaceString, false);
            }
        }
        catch(UnknownHostException e1)
        {
            e1.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }*/

    }//end of onCreate()

    public void goAhead()
    {
        //Intent intent = new Intent(this, ScanAutoHome.class);
        //startActivity(intent);
        String text = allIPs.getText().toString();
        toastMessage(text);
        isTheIPReachable(text);
    }
    public void goFar()
    {
        Intent intent = new Intent(this, HomeWifiToDevice.class);
        startActivity(intent);
    }
    public void isTheIPReachable(String ipaddress)
    {
        try
        {
            InetAddress adds = InetAddress.getByName(ipaddress);
            //toastMessage(adds.toString());
            if(adds.isReachable(2000))
            {
                toastMessage(ipaddress+" IS REACHABLE!");
                //runSystemCommand("ping 192.168.4.3");
            }
            else
            {
                toastMessage(ipaddress+" IS NOT REACHABLE!");
            }

        }catch(Exception e){toastMessage(e.toString());}
    }
    public String intToIp(int i)
    {
        return (i & 0xFF) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                ((i >> 24) & 0xFF);
    }

    public void toastMessage(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    public void runSystemCommand(String command) {

        try
        {
            toastMessage(command);
            Process p = Runtime.getRuntime().exec(command);
            TimeUnit.SECONDS.sleep(2);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            TimeUnit.SECONDS.sleep(2);
            String s = "";
            s = inputStream.readLine();
            if(s!=null)
            {
                allIPs.setText(s);
            }

            // reading output stream of the command
            /*
            while ((s = inputStream.readLine()) != null)
            {
                //System.out.println(s);
                allIPs.setText(s);
            }
            /*
            Runtime runtime = Runtime.getRuntime();
            try
            {
                Process  mIpAddrProcess = runtime.exec("/system/bin/"+command);
                //Process  mIpAddrProcess = runtime.exec(command);
                int mExitValue = mIpAddrProcess.waitFor();
                if(mExitValue==0)
                {
                    toastMessage("OK");
                }
                else
                {
                    toastMessage("NG");
                }
            }
            catch (InterruptedException ignore)
            {
                toastMessage(ignore.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println(" Exception:"+e);
            }*/

        }catch (Exception e) {toastMessage(e.toString());}
    }

    public static String executeCmd(String cmd, boolean sudo){
        try {

            Process p;
            if(!sudo)
                p= Runtime.getRuntime().exec(cmd);
            else{
                p= Runtime.getRuntime().exec(new String[]{"su", "-c", cmd});
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s;
            String res = "";
            while ((s = stdInput.readLine()) != null) {
                res += s + "\n";
            }
            p.destroy();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
