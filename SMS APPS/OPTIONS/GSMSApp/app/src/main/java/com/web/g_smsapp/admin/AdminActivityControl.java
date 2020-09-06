package com.web.g_smsapp.admin;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.web.g_smsapp.R;
import com.web.g_smsapp.admin_db.AdminControllerDB;
import com.web.g_smsapp.admin_db.AdminSMSReceiverControllerDB;
import com.web.g_smsapp.admin_db.AdminShowNumbersLocationActivity;
import com.web.g_smsapp.admin_db.AdminShowReceivedSMSActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminActivityControl extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    /******************************MODEL****************************************/
    /*SMS SENDING RELATED*/
    String  stringCMD7      = "#07%";
    String  stringCMD8      = "#08%";
    String  stringCMD9      = "#09%";
    String  stringCMD10     = "#10%";
    String  stringCMD11     = "#11%";
    String  stringCMD12     = "#12%";
    String  stringCMD13     = "#13%";
    String  stringCMD14     = "#14%";
    String  stringCMD15     = "#15%";
    String  stringCMD16     = "#16%";
    String  stringCMD17     = "#17%";
    String  stringCMD18     = "#18%";
    String  stringCMD19     = "#19%";
    String  stringCMD20     = "#20%";
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

    /******************************VIEW*****************************************/
    ImageButton imageButton1, imageButton2, imageButton3;
    Button CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20;
    Button insertTheDestNumberBtn;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView dateTimeDisplay,timerText;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    int timerValue = 2000;

    /*******************************CONTROLLER**********************************/
    CountDownTimer cTimer = null;
    Boolean flag=false;
    /*SMS API*/
    SmsManager sms;

    /*GENERAL API*/
    SharedPreferences permissionStatus;

    /*DATABASE API*/
    AdminControllerDB adminControllerDB;//= new AdminControllerDB(this);
    SQLiteDatabase sqLiteDatabase;

    AdminSMSReceiverControllerDB AdminSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    String newQuery;

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
        setContentView(R.layout.activity_admin_control);

        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase= adminControllerDB.getReadableDatabase();
        query = "CREATE TABLE IF NOT EXISTS DEST_NUMBERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER TEXT, LOCATION TEXT);";
        sqLiteDatabase.execSQL(query);


        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        theQuery = "CREATE TABLE IF NOT EXISTS G_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
        theSqLiteDatabase.execSQL(theQuery);
        //Toast.makeText(this, "TABLE CREATED G_SMS",Toast.LENGTH_LONG).show();

        /*MAPPING UI ELEMENTS*/
        imageButton1            =   (ImageButton)   findViewById(R.id.imageButton1);
        imageButton2            =   (ImageButton)   findViewById(R.id.imageButton2);
        imageButton3            =   (ImageButton)   findViewById(R.id.imageButton3);
        theReceivingNumber      =   (EditText)      findViewById(R.id.theReceivingNumber);
        theClient               =   (EditText)      findViewById(R.id.theClient);
        insertTheDestNumberBtn  =   (Button)        findViewById(R.id.insertTheDestNumberBtn);
        destNumber              =   (Spinner)       findViewById(R.id.destNumber);
        dateTimeDisplay         =   (TextView)      findViewById(R.id.dateTimeDisplay);
        //timerText               =   (TextView)      findViewById(R.id.timerText);
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
        checkForSmsPermission();
        // Register a broadcast receiver for receiving SMS
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
    }//end of onCreate()

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        choice = String.valueOf(destNumber.getSelectedItem());
        //toast(choice);
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

    }
    public boolean checkIfDataExists()
    {
        String someData=null;
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        try
        {
            Cursor cursor = sqLiteDatabase.rawQuery(theQuery,null);
            if (cursor.moveToFirst())
            {
                do
                {
                    someData = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_1));
                    if(someData.isEmpty())
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }while (cursor.moveToNext());
            }
            return true;
        }
        catch (SQLException sqe)
        {
            return false;
        }
    }//end of checkIfDataExists()

    private static boolean doesDatabaseExist(Context context, String DATABASE_NAME)
    {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        String path = dbFile.getPath();
        return dbFile.exists();
    }//end of doesDatabaseExist()

    private boolean doesTableExist()
    {
        AdminControllerDB dbCreation = new AdminControllerDB(this);
        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = dbCreation.getReadableDatabase();
        Cursor c = null;
        boolean tableExists = false;
        /* get cursor on it */
        try
        {
            c = mDatabase.query(TABLE_DEST, null,null, null, null, null, null);
            tableExists = true;
        }catch (SQLiteException e) {}
        return tableExists;
    }//end of doestTableExist()

    public void insertData()
    {
        getReceivingNumber = theReceivingNumber.getText().toString();
        getClientString = theClient.getText().toString();
        //adminControllerDB.insertData(getReceivingNumber, getClientString);
        sqLiteDatabase= adminControllerDB.getReadableDatabase();
        if(getClientString.isEmpty() || getReceivingNumber.isEmpty())
        {
            toast("FIELDS SHOULD NOT BE EMPTY");
        }
        else
        {
            sqLiteDatabase.execSQL("INSERT INTO DEST_NUMBERS(NUMBER, LOCATION) VALUES('" + getReceivingNumber + "','" + getClientString + "')" );
            toast("INSERTED DATA " + getReceivingNumber + " " + getClientString);
        }
    }//end of insertData()

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
                    CMD7.setTextColor(Color.WHITE);
                    CMD7.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD8:
                    sms.sendTextMessage(choice, null, stringCMD8, null, null);
                    CMD8.setTextColor(Color.WHITE);
                    CMD8.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD9:
                    sms.sendTextMessage(choice, null, stringCMD9, null, null);
                    CMD9.setTextColor(Color.WHITE);
                    CMD9.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD10:
                    sms.sendTextMessage(choice, null, stringCMD10, null, null);
                    CMD10.setTextColor(Color.WHITE);
                    CMD10.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD11:
                    sms.sendTextMessage(choice, null, stringCMD11, null, null);
                    CMD11.setTextColor(Color.WHITE);
                    CMD11.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD12:
                    sms.sendTextMessage(choice, null, stringCMD12, null, null);
                    CMD12.setTextColor(Color.WHITE);
                    CMD12.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD13:
                    sms.sendTextMessage(choice, null, stringCMD13, null, null);
                    CMD13.setTextColor(Color.WHITE);
                    CMD13.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD14:
                    sms.sendTextMessage(choice, null, stringCMD14, null, null);
                    CMD14.setTextColor(Color.WHITE);
                    CMD14.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD15:
                    sms.sendTextMessage(choice, null, stringCMD15, null, null);
                    CMD15.setTextColor(Color.WHITE);
                    CMD15.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD16:
                    sms.sendTextMessage(choice, null, stringCMD16, null, null);
                    CMD16.setTextColor(Color.WHITE);
                    CMD16.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD17:
                    sms.sendTextMessage(choice, null, stringCMD17, null, null);
                    CMD17.setTextColor(Color.WHITE);
                    CMD17.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD18:
                    sms.sendTextMessage(choice, null, stringCMD18, null, null);
                    CMD18.setTextColor(Color.WHITE);
                    CMD18.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD19:
                    sms.sendTextMessage(choice, null, stringCMD19, null, null);
                    CMD19.setTextColor(Color.WHITE);
                    CMD19.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD20:
                    sms.sendTextMessage(choice, null, stringCMD20, null, null);
                    CMD20.setTextColor(Color.WHITE);
                    CMD20.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;
            }//end of switch()
        }//end of outer else{}
    }//end of sendSMS()

    public void changeNumberFunction(View view)
    {
        switch(view.getId())
        {
            case R.id.imageButton1:
                changeElevatorCompanyNumbers();
                break;

            case R.id.imageButton2:
                changePanelBuilderCompanyNumbers();
                break;

            case R.id.imageButton3:
                changeSelfNumbers();
                break;
        }
    }//end of changeNumberFunction()

    public void changeElevatorCompanyNumbers()
    {
        //toast("CHANGE ELEVATOR COMPANY NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "1");
        Intent intent = new Intent(this, AdminChangeNumbersActivity.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeElevatorCompanyNumbers()

    public void changePanelBuilderCompanyNumbers()
    {
        //toast("CHANGE PANEL BUILDER COMPANY NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "2");
        Intent intent = new Intent(this, AdminChangeNumbersActivity.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changePanelBuilderCompanyNumbers()

    public void changeSelfNumbers()
    {
        //toast("CHANGE SELF NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "3");
        Intent intent = new Intent(this, AdminChangeNumbersActivity.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeSelfNumbers()

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                showData();
                return true;
            case R.id.item2:
                showSMS();
                return true;
            case R.id.item3:
                showMeaning();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()

    public void showData()
    {
        Intent intent=new Intent(this, AdminShowNumbersLocationActivity.class);
        startActivity(intent);
    }//end of showData()


    public void showSMS()
    {
        Intent intent=new Intent(this, AdminShowReceivedSMSActivity.class);
        startActivity(intent);
    }//end of showSMS()

    public void showMeaning()
    {
        Intent intent=new Intent(this, AdminInformationActivity.class);
        startActivity(intent);
    }

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

    public void doSomething()
    {
        AdminControllerDB adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase= adminControllerDB.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("SELECT NUMBER FROM "+TABLE_DEST+" WHERE "+COLUMN_NAME_2 +" ='"+choice+"'" ,null);
        if(c!=null)
        {
            c.moveToFirst();
            String data = c.getString(c.getColumnIndex("NUMBER"));
            toast(data);
        }
    }//end of doSomething()


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
                Toast.makeText(context, theString, Toast.LENGTH_SHORT).show();
                //txtview.setText(theString);
                AdminActivityControl.this.getReceivedSMS(theString);
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
        CMD7.setBackgroundColor(Color.BLUE);
        CMD8.setBackgroundColor(Color.BLUE);
        CMD9.setBackgroundColor(Color.BLUE);
        CMD10.setBackgroundColor(Color.BLUE);
        CMD11.setBackgroundColor(Color.BLUE);
        CMD12.setBackgroundColor(Color.BLUE);
        CMD13.setBackgroundColor(Color.BLUE);
        CMD14.setBackgroundColor(Color.BLUE);
        CMD15.setBackgroundColor(Color.BLUE);
        CMD16.setBackgroundColor(Color.BLUE);
        CMD17.setBackgroundColor(Color.BLUE);
        CMD18.setBackgroundColor(Color.BLUE);
        CMD19.setBackgroundColor(Color.BLUE);
        CMD20.setBackgroundColor(Color.BLUE);

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
    }

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
            toast("REQUESTING PERMISSION TO SEND SMS");
        }
        else
        {
            toast("SMS SENDING PERMISSION GRANTED");
        }

        /*CHECKING AND REQUESTING FOR RECEIVING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            toast("REQUESTING PERMISSION TO RECEIVE SMS");
        }
        else
        {
            toast("SMS RECEIVING PERMISSION GRANTED");
        }

        /*CHECKING AND REQUESTING FOR READING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},MY_PERMISSIONS_REQUEST_READ_SMS);
            toast("REQUESTING PERMISSION TO READ SMS");
        }
        else
        {
            toast("SMS READING PERMISSION GRANTED");
        }

    }//end of checkForSmsPermission()
}
