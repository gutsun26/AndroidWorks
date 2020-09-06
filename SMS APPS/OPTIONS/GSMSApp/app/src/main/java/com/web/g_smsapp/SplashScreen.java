package com.web.g_smsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreen extends AppCompatActivity
{
    /*CUSTOMER DETAILS*/
    //Button startBtn, stopBtn,permBtn;
    //EditText custID,custNum;

    /*SMS RELATED*//*
    SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    private static final int SMS_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private static final int REQUEST_SEND_SMS = 123;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String custIDString = null;
    String custNumString=null;
    String stopMessageString="STOP";
    String startMessageSttring="START";
    TextView textView;

    /*PHONE RELATED*/
    //String versionRelease = Build.VERSION.RELEASE;


    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                SplashScreen.this.finish();
            }
        }, 3000);

        /*

        startBtn    = (Button)findViewById(R.id.startBtn);
        stopBtn     = (Button)findViewById(R.id.stopBtn);
        permBtn     = (Button)findViewById(R.id.permBtn);

        custID      = (EditText)findViewById(R.id.custID);
        custNum     = (EditText)findViewById(R.id.custNum);

        textView    = (TextView)findViewById(R.id.textView);

        //Very important, otherwise, App gets installed but screen keeps blinking
        permissionStatus = getSharedPreferences("permissionStatus", Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String[] permissions = {Manifest.permission.SEND_SMS};
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.SEND_SMS}, 1);
        }

        permBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getBaseContext(), "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
                    startBtn.setEnabled(true);
                    stopBtn.setEnabled(true);
                }
            }

        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

         */
    }//end of onCreate()

    /*
    public void sendSMS()
    {
        try
        {
            SmsManager sms = SmsManager.getDefault(); // using android SmsManager
            custIDString    = custID.getText().toString();
            custNumString   = custNum.getText().toString();
            stopMessageString += custIDString;
            sms.sendTextMessage(custNumString, null, stopMessageString, null, null);
            if (permissionStatus.getBoolean(Manifest.permission.SEND_SMS, true))
            {
                Toast.makeText(this, "SMS SENT", Toast.LENGTH_SHORT).show();
                textView.setText("MESSAGE SENT TO "+custNumString+" WAS "+stopMessageString);
            }
            else
            {
                Toast.makeText(this, "NOT SENT", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            textView.setText(e.toString());
        }
    }*/
}
