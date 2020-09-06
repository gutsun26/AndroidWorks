package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smsapp.database.SMSReceiverControllerDB;
import com.example.smsapp.database.ShowReceivedSMSActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    SmsManager sms ;
    String phoneNumber = "9892012016";
    TextView dateTimeDisplay,txtview;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    SMSReceiverControllerDB SMSReceiverControllerDB;
    SQLiteDatabase sqLiteDatabase;
    String query;
    String receivedMessage=null;
    String cellNumber=null;
    String extractedMessage=null;
    Button smsBtn, del, showBtn;
    SharedPreferences permissionStatus;
    EditText msgTxt, numTxt;
    public static final String SMS_BUNDLE = "pdus";
    String messageString = null;
    String phoneNumberString=null;
    TextView textView;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sms = SmsManager.getDefault(); // using android SmsManager
        smsBtn          = (Button)findViewById(R.id.send);
        showBtn         = (Button)findViewById(R.id.show);
        del             = (Button)findViewById(R.id.del);

        dateTimeDisplay = (TextView)findViewById(R.id.dateTimeDisplay);
        msgTxt      = (EditText)findViewById(R.id.msgTxt);
        numTxt      = (EditText)findViewById(R.id.numTxt);
        txtview     = (TextView)findViewById(R.id.txtview);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        SMSReceiverControllerDB = new SMSReceiverControllerDB(this);
        sqLiteDatabase= SMSReceiverControllerDB.getReadableDatabase();
        query = "CREATE TABLE IF NOT EXISTS G_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
        sqLiteDatabase.execSQL(query);

        permissionStatus = getSharedPreferences("permissionStatus", Context.MODE_PRIVATE);//Very important, otherwise, App gets installed but screen keeps blinking
        textView = (TextView)findViewById(R.id.msg);
        smsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                sendSMS();
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showSMS();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSMS();
            }
        });
        checkForSmsPermission();
        // Register a broadcast receiver
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
    }

    private void checkForSmsPermission()
    {
        /*CHECKING AND REQUESTING FOR SENDIN SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            Toast.makeText(this, "REQUESTING PERMISSION",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "SMS SENDING PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
        }

        /*CHECKING AND REQUESTING FOR RECEIVING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            Toast.makeText(this, "REQUESTING PERMISSION TO RECEIVE SMS",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "SMS RECEIVING PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
        }

        /*CHECKING AND REQUESTING FOR READING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},MY_PERMISSIONS_REQUEST_READ_SMS);
            Toast.makeText(this, "REQUESTING PERMISSION TO READ SMS",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "SMS READING PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
        }

    }//end of checkForSmsPermission()

    BroadcastReceiver smsReceiver = new BroadcastReceiver()
    {
        SmsMessage message;
        String from;
        String content;
        public void onReceive(Context context, Intent intent)
        {
            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null)
            {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String theString = "";
                for (int i = 0; i < sms.length; ++i)
                {
                    message  = SmsMessage.createFromPdu((byte[]) sms[i]);

                    content = message.getMessageBody();
                    from = message.getOriginatingAddress();

                    theString += from + ":";
                    theString += content + "\n";
                }
                Toast.makeText(context, theString, Toast.LENGTH_SHORT).show();
                txtview.setText(theString);
                MainActivity.this.updateList(theString);
            }
        }//end of onReceiver()
    };

    public void sendSMS()
    {
        try
        {
            messageString = msgTxt.getText().toString();
            phoneNumberString = numTxt.getText().toString();

            sms.sendTextMessage(phoneNumberString, null, messageString, null, null);
            if (permissionStatus.getBoolean(Manifest.permission.SEND_SMS, true))
            {
                Toast.makeText(this, "SMS SENT", Toast.LENGTH_SHORT).show();
                textView.setText("MESSAGE SENT TO "+phoneNumberString+" WAS "+messageString);
                msgTxt.setText(" ");
                numTxt.setText(" ");
            }
        }
        catch(Exception e)
        {
            textView.setText(e.toString());
        }
    }

    public void updateList(final String smsMessage)
    {
        receivedMessage = smsMessage;
        String[] array = receivedMessage.split(":",0);
        String theNumber=array[0];
        cellNumber          = theNumber.replace("+91","");
        extractedMessage    = array[1];
        sqLiteDatabase= SMSReceiverControllerDB.getReadableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO G_SMS(DATE, ADDRESS, CONTENT) VALUES('"+date+"','"+cellNumber+"','"+extractedMessage+"')");
        Toast.makeText(getApplicationContext(), "INSERTED DATA",Toast.LENGTH_LONG).show();
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        try {
            String[] smsMessages = smsMessagesList.get(pos).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            for (int i = 1; i < smsMessages.length; ++i) {
                smsMessage += smsMessages[i];
            }

            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSMS()
    {
        Intent intent=new Intent(this, ShowReceivedSMSActivity.class);
        startActivity(intent);
    }

    public void deleteSMS()
    {
        String numberFilter = "address='"+ phoneNumber + "'";
        String messageid = null;
        Cursor cursor = getApplicationContext().getContentResolver().query(Uri.parse("content://sms/"),
                null, numberFilter, null, null);

        if (cursor.moveToFirst()) {
            messageid = cursor.getString(0);
        }
        getContentResolver().delete(Uri.parse("content://sms/" + messageid), null, null);
        toast("Deleted");
    }

    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}