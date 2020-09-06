package com.example.autohome;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HigherVersionAutoHome extends AppCompatActivity
{
    private static String ip = "";
    private static String portString="";
    //private static int portNum = 80;
    private static int portNum = 0;
    TextView textView, msg;
    EditText ipadd,port;
    Button autoHome,setServer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.higherversion);
        autoHome    = (Button)findViewById(R.id.auto);
        setServer   = (Button)findViewById(R.id.serverBtn);
        ipadd       = (EditText) findViewById(R.id.ip);
        port        = (EditText) findViewById(R.id.port);
        textView    = (TextView)findViewById(R.id.txt);

        autoHome.setEnabled(false);

        setServer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ip = ipadd.getText().toString();
                portString = port.getText().toString();
                textView.setText(ip+":"+portString);

                portNum = Integer.parseInt(portString);

                if( (ip.equalsIgnoreCase("192.168.4.1")) && (portNum == 80) )
                {
                    autoHome.setEnabled(true);
                }
                else
                {
                    msg.setText("INCORRECT IP ADDRESS/PORT NUMBER");
                    msg.setTextColor(Color.RED);
                }
            }
        });
    }

    public void enterAutoHome(View view)
    {
        Intent intent = new Intent(this, HigherVersionSubActivity.class);
        startActivity(intent);
    }
}
