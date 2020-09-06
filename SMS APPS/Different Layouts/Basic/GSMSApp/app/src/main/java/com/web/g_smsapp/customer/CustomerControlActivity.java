package com.web.g_smsapp.customer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
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
import com.web.g_smsapp.customer_db.CustomerControllerDB;
import com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB;
import com.web.g_smsapp.customer_db.CustomerShowNumbersLocationActivity;
import com.web.g_smsapp.customer_db.CustomerShowReceivedSMSActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomerControlActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
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
    String  stringCMD16     = "#16%07AB5431";//LIFT ERROR STATUS
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
    int rowCount;

    /******************************VIEW*****************************************/
    ImageButton imageButton;
    Button CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20,CMD21;
    Button insertTheDestNumberBtn,phoneBookButton;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView dateTimeDisplay,timerText,adminText;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    int timerValue = 6000;
    String adminString="ADMIN";

    /*******************************CONTROLLER**********************************/
    CountDownTimer cTimer = null;
    Boolean flag=false;
    /*SMS API*/
    SmsManager sms;

    /*GENERAL API*/
    SharedPreferences permissionStatus;

    /*DATABASE API*/
    CustomerControllerDB customerControllerDB;
    SQLiteDatabase sqLiteDatabase;

    CustomerSMSReceiverControllerDB customerSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    String newQuery;

    /*SQLITE ELEMENTS*/
    private static final String DATABASE_NAME="SqliteListviewDB";
    private static final String TABLE_DEST="CUSTOMER_DEST_NUMBERS";
    private static final String COLUMN_NAME_1 = "NUMBER";
    private static final String COLUMN_NAME_2 = "LOCATION";
    String theQuery = "SELECT * FROM "+TABLE_DEST;
    boolean databaseExist=false;
    long count;
    String choice=null;
    String query;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_control);

        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase= customerControllerDB.getReadableDatabase();
        query = "CREATE TABLE IF NOT EXISTS CUSTOMER_DEST_NUMBERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER TEXT, LOCATION TEXT);";
        sqLiteDatabase.execSQL(query);

        customerSMSReceiverControllerDB = new CustomerSMSReceiverControllerDB(this);
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        theQuery = "CREATE TABLE IF NOT EXISTS G_CUSTOMER_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, NUMBER TEXT, CONTENT TEXT);";
        theSqLiteDatabase.execSQL(theQuery);

        /*MAPPING UI ELEMENTS*/
        adminText               =   (TextView)      findViewById(R.id.adminText);
        imageButton             =   (ImageButton)   findViewById(R.id.imageButton);
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
        phoneBookButton         =   (Button)        findViewById(R.id.phoneBookButton);
        adminText.setText(adminString);
        adminText.setBackgroundColor(Color.WHITE);
        adminText.setTextColor(Color.BLACK);
        /*END OF MAPPING*/
        sms = SmsManager.getDefault();
        List<String> DATA = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CUSTOMER_DEST_NUMBERS",null);
        rowCount = cursor.getCount();
        //toast(String.valueOf(rowCount));
        if(rowCount == 0)
        {
            toast("STORE A DESTINATION NUMBER FIRST");
            adminText.setText("STORE A DESTINATION NUMBER FIRST");
            adminText.setBackgroundColor(Color.RED);
            adminText.setTextColor(Color.WHITE);
            CMD7.setVisibility(View.INVISIBLE);
            CMD8.setVisibility(View.INVISIBLE);
            CMD9.setVisibility(View.INVISIBLE);
            CMD10.setVisibility(View.INVISIBLE);
            CMD11.setVisibility(View.INVISIBLE);
            CMD12.setVisibility(View.INVISIBLE);
            CMD13.setVisibility(View.INVISIBLE);
            CMD14.setVisibility(View.INVISIBLE);
            CMD15.setVisibility(View.INVISIBLE);
            CMD16.setVisibility(View.INVISIBLE);
            CMD17.setVisibility(View.INVISIBLE);
            CMD18.setVisibility(View.INVISIBLE);
            CMD19.setVisibility(View.INVISIBLE);
            CMD20.setVisibility(View.INVISIBLE);
        }
        else
        {
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
        }


        insertTheDestNumberBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                insertData();
            }
        });

        calendar = Calendar.getInstance();
        //dateFormat = new SimpleDateFormat("dd/MM/ HH:mm:ss a");
        dateFormat = new SimpleDateFormat("dd/MM");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);
        checkForSmsPermission();
        // Register a broadcast receiver for receiving SMS
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
        phoneBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 2);            }
        });
    }//end of onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (data != null)
        {
            Uri uri = data.getData();
            if (uri != null)
            {
                Cursor c = null;
                try
                {
                    c = getContentResolver().query(uri, new String[]
                                    {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                            null, null, null);

                    if (c != null && c.moveToFirst())
                    {
                        number = c.getString(0);
                        theReceivingNumber.setText(number);
                        name = c.getString(2);
                        theClient.setText(name);
                    }
                }
                finally
                {
                    if (c != null)
                    {
                        c.close();
                    }
                }
            }
        }
    }//end of onActivityResult()

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        choice = String.valueOf(destNumber.getSelectedItem());
        {
            if(choice.equalsIgnoreCase("-----------------"))
            {
                int z = (destNumber.getSelectedItemPosition()-2);
                String meti=String.valueOf(destNumber.getItemAtPosition(z));
                choice = meti;
            }
            else
            {
                length = choice.length();
                for(int i=0; i < length ; i++)
                {
                    flag = Character.isDigit(choice.charAt(i));
                }
                if(!flag)
                {
                    CustomerControllerDB customerControllerDB = new CustomerControllerDB(this);
                    sqLiteDatabase= customerControllerDB.getReadableDatabase();
                    Cursor c=sqLiteDatabase.rawQuery("SELECT NUMBER FROM "+TABLE_DEST+" WHERE "+COLUMN_NAME_2 +" ='"+choice+"'" ,null);
                    if(c!=null)
                    {
                        c.moveToFirst();
                        String data = c.getString(c.getColumnIndex("NUMBER"));
                        choice = data;
                    }
                }
            }
        }

    }//end of onItemSelected()

    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    public void insertData()
    {
        getReceivingNumber = theReceivingNumber.getText().toString();
        getClientString = theClient.getText().toString();
        sqLiteDatabase= customerControllerDB.getReadableDatabase();
        if(getClientString.isEmpty() || getReceivingNumber.isEmpty())
        {
            toast("FIELDS SHOULD NOT BE EMPTY");
        }
        else
        {
            sqLiteDatabase.execSQL("INSERT INTO CUSTOMER_DEST_NUMBERS(NUMBER, LOCATION) VALUES('" + getReceivingNumber + "','" + getClientString + "')" );
            toast("INSERTED DATA " + getReceivingNumber + " " + getClientString);
            adminText.setText("ADMIN");
            adminText.setTextColor(Color.BLACK);
        }
    }//end of insertData()

    public void changeNumberFunction(View view)
    {
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "1");
        Intent intent = new Intent(this, CustomerChangeNumbersActivity.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeNumberFunction()

    public void sendSMS(View view)
    {
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
                    CMD7.setTextColor(Color.GREEN);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD8:
                    sms.sendTextMessage(choice, null, stringCMD8, null, null);
                    CMD8.setTextColor(Color.GREEN);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;
                case R.id.CMD9:
                    sms.sendTextMessage(choice, null, stringCMD9, null, null);
                    CMD9.setTextColor(Color.GREEN);
                    //CMD9.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;
                case R.id.CMD10:
                    sms.sendTextMessage(choice, null, stringCMD10, null, null);
                    CMD10.setTextColor(Color.GREEN);
                    //CMD10.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD11:
                    sms.sendTextMessage(choice, null, stringCMD11, null, null);
                    CMD11.setTextColor(Color.GREEN);
                    //CMD11.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD12:
                    sms.sendTextMessage(choice, null, stringCMD12, null, null);
                    CMD12.setTextColor(Color.GREEN);
                    //CMD12.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD13:
                    sms.sendTextMessage(choice, null, stringCMD13, null, null);
                    CMD13.setTextColor(Color.GREEN);
                    //CMD13.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD14:
                    sms.sendTextMessage(choice, null, stringCMD14, null, null);
                    CMD14.setTextColor(Color.GREEN);
                    //CMD14.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD15:
                    sms.sendTextMessage(choice, null, stringCMD15, null, null);
                    CMD15.setTextColor(Color.GREEN);
                    //CMD15.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD16:
                    sms.sendTextMessage(choice, null, stringCMD16, null, null);
                    CMD16.setTextColor(Color.GREEN);
                    //CMD16.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;
                case R.id.CMD17:
                    sms.sendTextMessage(choice, null, stringCMD17, null, null);
                    CMD17.setTextColor(Color.GREEN);
                    //CMD17.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD18:
                    sms.sendTextMessage(choice, null, stringCMD18, null, null);
                    CMD18.setTextColor(Color.GREEN);
                    //CMD18.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;
                case R.id.CMD19:
                    sms.sendTextMessage(choice, null, stringCMD19, null, null);
                    CMD19.setTextColor(Color.GREEN);
                    //CMD19.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;

                case R.id.CMD20:
                    sms.sendTextMessage(choice, null, stringCMD20, null, null);
                    CMD20.setTextColor(Color.GREEN);
                    //CMD20.setBackgroundColor(Color.RED);
                    disableUI();
                    toast("SENT TO " +choice);
                    break;
            }//end of switch()
        }//end of outer else{}
    }//end of sendSMS()

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
                CustomerControlActivity.this.getReceivedSMS(theString);
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
        //toast("number: "+cellNumber+" message:"+extractedMessage);
        sqLiteDatabase= customerControllerDB.getReadableDatabase();
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CUSTOMER_DEST_NUMBERS",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("NUMBER")));
            }while (cursor.moveToNext());
        }

        if(!THENUMBER.isEmpty())
        {
            for(int i=0; i<THENUMBER.size(); i++)
            {
                if(THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                {
                    sqLiteDatabase.execSQL("INSERT INTO G_CUSTOMER_SMS(DATE, NUMBER, CONTENT) VALUES('"+date+"','"+cellNumber+"','"+extractedMessage+"')");
                    Toast.makeText(getApplicationContext(), "INSERTED DATA",Toast.LENGTH_LONG).show();
                }
            }
            for(int i=0; i<THENUMBER.size(); i++)
            {
                if(THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                {
                    Bundle choiceConfig = new Bundle();
                    choiceConfig.putString("KEY", extractedMessage);
                    Intent intent = new Intent(this, CustomerInformationImmediateActivity.class);
                    intent.putExtras(choiceConfig);
                    startActivity(intent);
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_menu, menu);
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
            case R.id.item4:
                deleteRecords();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()

    public void showData()
    {
        Intent intent=new Intent(this, CustomerShowNumbersLocationActivity.class);
        startActivity(intent);
    }//end of showData()

    public void showSMS()
    {
        Intent intent=new Intent(this, CustomerShowReceivedSMSActivity.class);
        startActivity(intent);
    }//end of showSMS()

    public void showMeaning()
    {
        Intent intent=new Intent(this, CustomerInformationActivity.class);
        startActivity(intent);
    }//end of showMeaning()
	
	public void deleteRecords()
    {
        Intent intent = new Intent(this, CustomerRecordsDeleteActivity.class);
        startActivity(intent);
    }//end of deleteRecords()
    
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast
}