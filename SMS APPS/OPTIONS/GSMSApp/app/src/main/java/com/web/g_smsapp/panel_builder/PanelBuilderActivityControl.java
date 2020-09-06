package com.web.g_smsapp.panel_builder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;
import com.web.g_smsapp.admin_db.AdminControllerDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PanelBuilderActivityControl extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    /******************************MODEL****************************************/
    /*SMS SENDING RELATED*/
    String  stringCMD7      = "#07%+91";
    String  stringCMD8      = "#08%+91";
    String  stringCMD9      = "#09%+91";
    String  stringCMD10     = "#10%+91";
    String  stringCMD11     = "#11%+91";
    String  stringCMD12     = "#12%+91";
    String  stringCMD13     = "#13%+91";
    String  stringCMD14     = "#14%+91";
    String  stringCMD15     = "#15%+91";
    String  stringCMD16     = "#16%+91";
    String  stringCMD17     = "#17%+91";
    String  stringCMD18     = "#18%+91";
    String  stringCMD19     = "#19%+91";
    String  stringCMD20     = "#20%+91";
    String getReceivingNumber=null;
    String getClientString=null;
    int length = 0, numberLength = 10;
    /*SMS RECEIVING RELATED*/
    public static final String SMS_BUNDLE = "pdus";
    String receivedMessage=null;
    String cellNumber=null;
    String extractedMessage=null;

    /******************************VIEW*****************************************/
    ImageButton imageButton1, imageButton2;
    Button CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20;
    Button insertTheDestNumberBtn;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView dateTimeDisplay;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;

    /*******************************CONTROLLER**********************************/
    Boolean flag=false;
    /*SMS API*/
    SmsManager sms;

    /*GENERAL API*/
    SharedPreferences permissionStatus;

    /*DATABASE API*/
    AdminControllerDB adminControllerDB;
    SQLiteDatabase sqLiteDatabase;


    /*SQLITE ELEMENTS*/
    private static final String DATABASE_NAME="SqliteListviewDB";
    private static final String TABLE_DEST="DEST_NUMBERS";
    private static final String COLUMN_NAME_1 = "NUMBER";
    private static final String COLUMN_NAME_2 = "LOCATION";
    String theQuery = "SELECT * FROM "+TABLE_DEST;
    boolean databaseExist=false;
    long count;
    String choice;
    String query;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_builder);

        /*MAPPING UI ELEMENTS*/
        imageButton1            =   (ImageButton)   findViewById(R.id.imageButton1);
        imageButton2            =   (ImageButton)   findViewById(R.id.imageButton2);
        theReceivingNumber      =   (EditText)      findViewById(R.id.theReceivingNumber);
        theClient               =   (EditText)      findViewById(R.id.theClient);
        insertTheDestNumberBtn  =   (Button)        findViewById(R.id.insertTheDestNumberBtn);
        destNumber              =   (Spinner)       findViewById(R.id.destNumber);
        dateTimeDisplay         =   (TextView)      findViewById(R.id.dateTimeDisplay);


        CMD7                    =   (Button)        findViewById(R.id.CMD7);
        CMD8                    =   (Button)        findViewById(R.id.CMD8);
        CMD9                    =   (Button)        findViewById(R.id.CMD9);
        CMD10                   =   (Button)        findViewById(R.id.CMD10);
        CMD11                   =   (Button)        findViewById(R.id.CMD11);
        CMD12                   =   (Button)        findViewById(R.id.CMD12);
        CMD13                   =   (Button)        findViewById(R.id.CMD13);
        CMD14                   =   (Button)        findViewById(R.id.CMD14);
        CMD15                   =   (Button)        findViewById(R.id.CMD15);
        CMD16                   =   (Button)        findViewById(R.id.CMD16);
        CMD17                   =   (Button)        findViewById(R.id.CMD17);
        CMD18                   =   (Button)        findViewById(R.id.CMD18);
        CMD19                   =   (Button)        findViewById(R.id.CMD19);
        CMD20                   =   (Button)        findViewById(R.id.CMD20);
        /*END OF MAPPING*/

        /*
        sms = SmsManager.getDefault();
        List<String> DATA = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DEST_NUMBERS",null);
        DATA.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                DATA.add(cursor.getString(cursor.getColumnIndex("NUMBER")));
                DATA.add(cursor.getString(cursor.getColumnIndex("LOCATION")));
                DATA.add("-----------------");
            }while (cursor.moveToNext());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DATA);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        destNumber.setAdapter(dataAdapter);
        destNumber.setOnItemSelectedListener(this);
        */
        insertTheDestNumberBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                insertData();
            }
        });

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        // Register a broadcast receiver for receiving SMS
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
    }//end of onCreate()

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        choice = String.valueOf(destNumber.getSelectedItem());
        if(choice.equalsIgnoreCase("-----------------"))
        {
            int z = (destNumber.getSelectedItemPosition()-2);
            String meti=String.valueOf(destNumber.getItemAtPosition(z));
            //toast("THE NUMBER IS:"+meti);
            choice = meti;
        }
        else
        {

            //toast("YOU CHOSE:"+choice);
            length = choice.length();
            for(int i=0; i < length ; i++)
            {
                flag = Character.isDigit(choice.charAt(i));
            }
            if(!flag)
            {
                //toast("It is not a number");
                AdminControllerDB adminControllerDB = new AdminControllerDB(this);
                sqLiteDatabase= adminControllerDB.getReadableDatabase();
                Cursor c=sqLiteDatabase.rawQuery("SELECT NUMBER FROM "+TABLE_DEST+" WHERE "+COLUMN_NAME_2 +" ='"+choice+"'" ,null);
                if(c!=null)
                {
                    c.moveToFirst();
                    String data = c.getString(c.getColumnIndex("NUMBER"));
                    toast(data);
                    choice = data;
                }
            }
        }


    }//end of onItemSelected()
    public void onNothingSelected(AdapterView<?> parent)
    {

    }//end of onNothingSelected()

    public void insertData()
    {
        getReceivingNumber = theReceivingNumber.getText().toString();
        getClientString = theClient.getText().toString();
        toast(getClientString+":"+getReceivingNumber);
        /*
        sqLiteDatabase=adminControllerDB.getReadableDatabase();
        if(getClientString.isEmpty() || getReceivingNumber.isEmpty())
        {
            toast("FIELDS SHOULD NOT BE EMPTY");
        }
        else
        {
            sqLiteDatabase.execSQL("INSERT INTO DEST_NUMBERS(NUMBER, LOCATION) VALUES('" + getReceivingNumber + "','" + getClientString + "')" );
            toast("INSERTED DATA " + getReceivingNumber + " " + getClientString);
        }*/
    }//end of insertData()

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

    public void sendSMS(View view)
    {
        //getReceivingNumber = theReceivingNumber.getText().toString();
        if(choice.isEmpty())
        {
            toast("PLEASE ENTER THE DESTINATION NUMBER");
        }
        else
        {
            switch(view.getId())
            {
                case R.id.CMD7:
                    sms.sendTextMessage(choice, null, stringCMD7, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD7);
                    break;

                case R.id.CMD8:
                    sms.sendTextMessage(choice, null, stringCMD8, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD8);
                    break;

                case R.id.CMD9:
                    sms.sendTextMessage(choice, null, stringCMD9, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD9);
                    break;

                case R.id.CMD10:
                    sms.sendTextMessage(choice, null, stringCMD10, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD10);
                    break;

                case R.id.CMD11:
                    sms.sendTextMessage(choice, null, stringCMD11, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD11);
                    break;

                case R.id.CMD12:
                    sms.sendTextMessage(choice, null, stringCMD12, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD12);
                    break;

                case R.id.CMD13:
                    sms.sendTextMessage(choice, null, stringCMD13, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD13);
                    break;

                case R.id.CMD14:
                    sms.sendTextMessage(choice, null, stringCMD14, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD14);
                    break;

                case R.id.CMD15:
                    sms.sendTextMessage(choice, null, stringCMD15, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD15);
                    break;

                case R.id.CMD16:
                    sms.sendTextMessage(choice, null, stringCMD16, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD16);
                    break;

                case R.id.CMD17:
                    sms.sendTextMessage(choice, null, stringCMD17, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD17);
                    break;

                case R.id.CMD18:
                    sms.sendTextMessage(choice, null, stringCMD18, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD18);
                    break;

                case R.id.CMD19:
                    sms.sendTextMessage(choice, null, stringCMD19, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD19);
                    break;

                case R.id.CMD20:
                    sms.sendTextMessage(choice, null, stringCMD20, null, null);
                    toast("SENT TO " +choice + " COMMAND:" +stringCMD20);
                    break;
            }//end of switch()
        }//end of outer else{}
    }//end of sendSMS()

    public void changeNumberFunction(View view)
    {
        switch(view.getId())
        {
            case R.id.imageButton1:
                //changeElevatorCompanyNumbers();
                break;

            case R.id.imageButton2:
                //changePanelBuilderCompanyNumbers();
                break;
        }
    }//end of changeNumberFunction()

    BroadcastReceiver smsReceiver = new BroadcastReceiver()
    {
        SmsMessage message;
        String from;
        String content;
        String myNumber="9892012016";
        public void onReceive(Context context, Intent intent)
        {
            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null)
            {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String smsMessageStr = "";
                String theString = "";
                for (int i = 0; i < sms.length; ++i)
                {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    String smsBody = smsMessage.getMessageBody();
                    String address = smsMessage.getOriginatingAddress();

                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";
                }

                for (int i = 0; i < sms.length; ++i)
                {
                    message  = SmsMessage.createFromPdu((byte[]) sms[i]);

                    content = message.getMessageBody();
                    from = message.getOriginatingAddress();

                    theString += from + ":";
                    theString += content + "\n";
                }
                toast(theString);
                //txtview.setText(theString);
                PanelBuilderActivityControl.this.getReceivedSMS(theString);
            }
        }//end of onReceiver()
    };//end of BroadcastReceiver class

    public void getReceivedSMS(final String smsMessage)
    {
        receivedMessage = smsMessage;
        String[] array = receivedMessage.split(":",0);
        String theNumber=array[0];
        cellNumber          = theNumber.replace("+91","");
        extractedMessage    = array[1];
        sqLiteDatabase= adminControllerDB.getReadableDatabase();
        //sqLiteDatabase.execSQL("INSERT INTO G_SMS(DATE, ADDRESS, CONTENT) VALUES('09-02-2020','9820841315','BINGO')");
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DEST_NUMBERS",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("NUMBER")));
            }while (cursor.moveToNext());
        }
        for(int i=0; i<THENUMBER.size(); i++)
        {
            if(THENUMBER.get(i).equalsIgnoreCase(cellNumber))
            {
                sqLiteDatabase.execSQL("INSERT INTO G_SMS(DATE, ADDRESS, CONTENT) VALUES('"+date+"','"+cellNumber+"','"+extractedMessage+"')");
                Toast.makeText(getApplicationContext(), "INSERTED DATA",Toast.LENGTH_LONG).show();
            }
        }
    }//end of getReceivedSMS()
}
