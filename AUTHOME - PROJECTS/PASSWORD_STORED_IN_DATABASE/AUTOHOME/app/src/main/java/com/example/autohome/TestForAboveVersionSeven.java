package com.example.autohome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TestForAboveVersionSeven extends AppCompatActivity
{
    TestConnection testConnection;
    String severIp = "192.168.4.3";
    int portNum = 80;
    Socket connectionSock;
    long startTime = 0l;
    String lightOneOnString         =   "L1 ON";
    DataOutputStream dataOutputStream;
    StringBuffer stringBuffer;
    int conFlag=0, sentFlag=0;
    Button con, send;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        con = (Button)findViewById(R.id.con);
        send = (Button)findViewById(R.id.send);
        //NewClass newClass = new NewClass();
        testConnection = new TestConnection();

        Toast.makeText(this,"THREAD", Toast.LENGTH_SHORT).show();

        con.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getIt();
            }
        });
        /*
        if(conFlag == 1)
        {
            Toast.makeText(this,"CONNECTED", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"NOT CONNECTED", Toast.LENGTH_SHORT).show();
        }
        */

        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                sendMessage();
            }
        });

        /*
        if(sentFlag == 1)
        {
            Toast.makeText(this,"SENT", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"NOT SENT", Toast.LENGTH_SHORT).show();
        }
        */
    }

    class TestConnection implements Runnable
    {
        public void run()
        {
            try
            {
                InetAddress serverAddr = InetAddress.getByName(severIp);
                //startTime = System.currentTimeMillis();
                connectionSock = new Socket();
                connectionSock.connect(new InetSocketAddress(serverAddr, portNum), 5000);
                //long time = System.currentTimeMillis() - startTime;
            }
            catch(Exception e)
            {

            }
        }
    }
    public void getIt()
    {
        Toast.makeText(this,"CONNECT", Toast.LENGTH_SHORT).show();
        connect(severIp, portNum);
    }

    public void connect(String ip, int port)
    {
        this.severIp=ip;
        this.portNum=port;
        conFlag = 1;
        new Thread(new TestConnection()).start();

    }
    public void sendMessage()
    {
        try
        {
            dataOutputStream = new DataOutputStream(connectionSock.getOutputStream());
            stringBuffer =  new StringBuffer(lightOneOnString);
            String formattedMessage = stringBuffer.append("\r").toString();
            TimeUnit.SECONDS.sleep(1);
            dataOutputStream.writeBytes(formattedMessage);
            dataOutputStream.flush();
            dataOutputStream.close();
            sentFlag = 1;
            Toast.makeText(this,"INSIDE SENDING MESSAGE", Toast.LENGTH_SHORT).show();
        }
        catch(InterruptedException ie){}
        catch(Exception e){}
    }

}
