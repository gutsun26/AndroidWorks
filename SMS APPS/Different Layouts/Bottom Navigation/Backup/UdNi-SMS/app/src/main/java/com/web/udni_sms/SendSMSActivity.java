package com.web.udni_sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.web.udni_sms.admin_db.AdminControllerDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SendSMSActivity extends FragmentActivity
{
    /******************************MODEL****************************************/
    /*SMS SENDING RELATED*/
    String  stringCMD7      = "#07%0000001A";//MAIN CARD INPUT
    String  stringCMD8      = "#08%0000CA1F";//MAIN CARD TERMINAL INPUT
    String  stringCMD9      = "#09%0000BCDE";//MAIN CARD OUTPUT
    String  stringCMD10     = "#10%0000AAEF";//CBC INPUTS
    String  stringCMD11     = "#11%0000EBCD";//CBC OUTPUTS
    String  stringCMD12     = "#12%CE550423";//FLOOR UP KEYS
    String  stringCMD13     = "#13%AACB6813";//FLOOR DOWN KEYS
    String  stringCMD14     = "#14%AACB6813";//CAR CALLS
    String  stringCMD15     = "#15%00000004";//LIFT STATUS
    String  stringCMD16     = "#16%000ABCDE";
    String  stringCMD17     = "#17%";
    String  stringCMD18     = "#18%";
    String  stringCMD19     = "#19%";
    String  stringCMD20     = "#20%00000000";
    String number=null,name=null;
    String getReceivingNumber=null;
    String getClientString=null;
    int length = 0, numberLength = 10;
    /*SMS RECEIVING RELATED*/
    public static final String SMS_BUNDLE = "pdus";
    String receivedMessage=null;
    String cellNumber=null;
    String extractedMessage=null;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
    int rowCount, rowCount2;
    String choice = "9892052058";
    /******************************VIEW*****************************************/
    ImageButton imageButton1, imageButton2, imageButton3;
    ImageButton CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20,CMD21;
    Button insertTheDestNumberBtn,phoneBookButton;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView dateTimeDisplay,timerText,adminText,numberText;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    int timerValue = 6000;
    String adminString="ADMIN";
    String theKey=null;
    /*******************************CONTROLLER**********************************/
    CountDownTimer cTimer = null;
    Boolean flag=false;
    /*SMS API*/
    SmsManager sms;
    /******************************************************************************/
    AdminControllerDB adminControllerDB;
    SQLiteDatabase sqLiteDatabase;
    String query = null, contactNumberQuery = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);
        /*MAPPING UI ELEMENTS*/
        adminText               =   (TextView)        findViewById(R.id.adminText);
        //numberText              =   (TextView)        findViewById(R.id.numberText);
        CMD7                    =   (ImageButton)        findViewById(R.id.CMD7);
        CMD8                    =   (ImageButton)        findViewById(R.id.CMD8);
        CMD9                    =   (ImageButton)        findViewById(R.id.CMD9);
        CMD10                   =   (ImageButton)        findViewById(R.id.CMD10);
        CMD11                   =   (ImageButton)        findViewById(R.id.CMD11);
        CMD12                   =   (ImageButton)        findViewById(R.id.CMD12);
        CMD13                   =   (ImageButton)        findViewById(R.id.CMD13);
        CMD14                   =   (ImageButton)        findViewById(R.id.CMD14);
        CMD15                   =   (ImageButton)        findViewById(R.id.CMD15);
        CMD16                   =   (ImageButton)        findViewById(R.id.CMD16);
        CMD17                   =   (ImageButton)        findViewById(R.id.CMD17);
        CMD18                   =   (ImageButton)        findViewById(R.id.CMD18);
        CMD19                   =   (ImageButton)        findViewById(R.id.CMD19);
        CMD20                   =   (ImageButton)        findViewById(R.id.CMD20);

        CMD7.setBackgroundColor(Color.GRAY);
        CMD8.setBackgroundColor(Color.GRAY);
        CMD9.setBackgroundColor(Color.GRAY);
        CMD10.setBackgroundColor(Color.GRAY);
        CMD11.setBackgroundColor(Color.GRAY);
        CMD12.setBackgroundColor(Color.GRAY);
        CMD13.setBackgroundColor(Color.GRAY);
        CMD14.setBackgroundColor(Color.GRAY);
        CMD15.setBackgroundColor(Color.GRAY);
        CMD16.setBackgroundColor(Color.GRAY);
        CMD17.setBackgroundColor(Color.GRAY);
        CMD18.setBackgroundColor(Color.GRAY);
        CMD19.setBackgroundColor(Color.GRAY);
        CMD20.setBackgroundColor(Color.GRAY);

        sms = SmsManager.getDefault();
        checkForSmsPermission();
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theKey                      = theChoice.getString("ID");

        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        query = "SELECT ADMIN_CLIENT_NAME FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+theKey+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    adminText.setText(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                    adminText.setBackgroundColor(Color.WHITE);
                    adminText.setTextColor(Color.BLACK);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        contactNumberQuery = "SELECT ADMIN_CLIENT_NUMBER FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+theKey+"'";
        Cursor cursor2 = sqLiteDatabase.rawQuery(contactNumberQuery,null);
        rowCount2 = cursor2.getCount();
        if(rowCount2!=0)
        {
            if (cursor2.moveToFirst())
            {
                do
                {
                    choice = cursor2.getString(cursor2.getColumnIndex("ADMIN_CLIENT_NUMBER"));/*
                    numberText.setText(choice);
                    numberText.setBackgroundColor(Color.WHITE);
                    numberText.setTextColor(Color.BLACK);*/
                }while (cursor2.moveToNext());
            }
        }
        cursor2.close();
        /*
        CMD7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCMD7();
            }
        });*/
    }

    public void sendCMD7()
    {
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        contactNumberQuery = "SELECT ADMIN_CLIENT_NUMBER FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+theKey+"'";
        Cursor cursor2 = sqLiteDatabase.rawQuery(contactNumberQuery,null);
        rowCount2 = cursor2.getCount();
        if(rowCount2!=0)
        {
            if (cursor2.moveToFirst())
            {
                do
                {
                    choice = cursor2.getString(cursor2.getColumnIndex("ADMIN_CLIENT_NUMBER"));
                    numberText.setText(choice);
                    numberText.setBackgroundColor(Color.WHITE);
                    numberText.setTextColor(Color.BLACK);
                }while (cursor2.moveToNext());
            }
        }
        cursor2.close();
    }

    public void sendSMS(View view)
    {
        //toast("Clicked " + String.valueOf(view.getId()));

        switch(view.getId())
        {
            case R.id.CMD7:
                sms.sendTextMessage(choice, null, stringCMD7, null, null);
                CMD7.setBackgroundColor(Color.GREEN);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD8:
                sms.sendTextMessage(choice, null, stringCMD8, null, null);
                CMD8.setBackgroundColor(Color.GREEN);
                disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD9:
                sms.sendTextMessage(choice, null, stringCMD9, null, null);
                CMD9.setBackgroundColor(Color.GREEN);
                //CMD9.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD10:
                sms.sendTextMessage(choice, null, stringCMD10, null, null);
                CMD10.setBackgroundColor(Color.GREEN);
                //CMD10.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD11:
                sms.sendTextMessage(choice, null, stringCMD11, null, null);
                CMD11.setBackgroundColor(Color.GREEN);
                //CMD11.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD12:
                sms.sendTextMessage(choice, null, stringCMD12, null, null);
                CMD12.setBackgroundColor(Color.GREEN);
                //CMD12.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD13:
                sms.sendTextMessage(choice, null, stringCMD13, null, null);
                CMD13.setBackgroundColor(Color.GREEN);
                //CMD13.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD14:
                sms.sendTextMessage(choice, null, stringCMD14, null, null);
                CMD14.setBackgroundColor(Color.GREEN);
                //CMD14.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD15:
                sms.sendTextMessage(choice, null, stringCMD15, null, null);
                CMD15.setBackgroundColor(Color.GREEN);
                //CMD15.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD16:
                sms.sendTextMessage(choice, null, stringCMD16, null, null);
                CMD16.setBackgroundColor(Color.GREEN);
                //CMD16.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD17:
                sms.sendTextMessage(choice, null, stringCMD17, null, null);
                CMD17.setBackgroundColor(Color.GREEN);
                //CMD17.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD18:
                sms.sendTextMessage(choice, null, stringCMD18, null, null);
                CMD18.setBackgroundColor(Color.GREEN);
                //CMD18.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD19:
                sms.sendTextMessage(choice, null, stringCMD19, null, null);
                CMD19.setBackgroundColor(Color.GREEN);
                //CMD19.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;

            case R.id.CMD20:
                sms.sendTextMessage(choice, null, stringCMD20, null, null);
                CMD20.setBackgroundColor(Color.GREEN);
                //CMD20.setBackgroundColor(Color.RED);
                disableUI();
                toast("SENT TO " +choice);
                break;
        }//end of switch()
    }//end of sendSMS()

    public void disableUI()
    {
        CMD7.setEnabled(false);
        CMD8.setEnabled(false);
        CMD9.setEnabled(false);
        CMD10.setEnabled(false);
        CMD11.setEnabled(false);
        CMD12.setEnabled(false);
        CMD13.setEnabled(false);
        CMD14.setEnabled(false);
        CMD15.setEnabled(false);
        CMD16.setEnabled(false);
        CMD17.setEnabled(false);
        CMD18.setEnabled(false);
        CMD19.setEnabled(false);
        CMD20.setEnabled(false);
        startTimer(timerValue);
    }//end of disableUI()

    public void enableUI()
    {
        CMD7.setEnabled(true);
        CMD8.setEnabled(true);
        CMD9.setEnabled(true);
        CMD10.setEnabled(true);
        CMD11.setEnabled(true);
        CMD12.setEnabled(true);
        CMD13.setEnabled(true);
        CMD14.setEnabled(true);
        CMD15.setEnabled(true);
        CMD16.setEnabled(true);
        CMD17.setEnabled(true);
        CMD18.setEnabled(true);
        CMD19.setEnabled(true);
        CMD20.setEnabled(true);

        CMD7.setBackgroundColor(Color.GRAY);
        CMD8.setBackgroundColor(Color.GRAY);
        CMD9.setBackgroundColor(Color.GRAY);
        CMD10.setBackgroundColor(Color.GRAY);
        CMD11.setBackgroundColor(Color.GRAY);
        CMD12.setBackgroundColor(Color.GRAY);
        CMD13.setBackgroundColor(Color.GRAY);
        CMD14.setBackgroundColor(Color.GRAY);
        CMD15.setBackgroundColor(Color.GRAY);
        CMD16.setBackgroundColor(Color.GRAY);
        CMD17.setBackgroundColor(Color.GRAY);
        CMD18.setBackgroundColor(Color.GRAY);
        CMD19.setBackgroundColor(Color.GRAY);
        CMD20.setBackgroundColor(Color.GRAY);

    }//end of enableUI()

    public void startTimer(int timeInMilliseconds)
    {
        cTimer = new CountDownTimer(timeInMilliseconds, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                toast("Please wait: " + millisUntilFinished / 1000);
                if(millisUntilFinished/1000 == 1)
                {
                    cTimer.cancel();
                    enableUI();
                }
            }
            public void onFinish()
            {
            }
        };
        cTimer.start();
    }//end of startTimer()

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

    private void checkForSmsPermission()
    {
        /*CHECKING AND REQUESTING FOR SENDIN SMS*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            //toast("REQUESTING PERMISSION TO SEND SMS");
        }
        else
        {
            //toast("SMS SENDING PERMISSION GRANTED");
        }

        /*CHECKING AND REQUESTING FOR RECEIVING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            //toast("REQUESTING PERMISSION TO RECEIVE SMS");
        }
        else
        {
            //toast("SMS RECEIVING PERMISSION GRANTED");
        }

        /*CHECKING AND REQUESTING FOR READING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},MY_PERMISSIONS_REQUEST_READ_SMS);
            //toast("REQUESTING PERMISSION TO READ SMS");
        }
        else
        {
            //toast("SMS READING PERMISSION GRANTED");
        }

    }//end of checkForSmsPermission()
}
