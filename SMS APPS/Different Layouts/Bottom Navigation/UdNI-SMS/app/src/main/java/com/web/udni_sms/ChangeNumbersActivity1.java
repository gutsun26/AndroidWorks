package com.web.udni_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.web.udni_sms.admin_db.AdminControllerDB;
import com.web.udni_sms.admin_db.AdminSMSReceiverControllerDB;

import java.util.ArrayList;
import java.util.List;

import static com.web.udni_sms.SendSMSActivity1.SMS_BUNDLE;

public class ChangeNumbersActivity1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    /*UI*/
    Button changeButton, exitButton;
    String getReceivingNumber, getnumber, getChoice;
    String item;
    EditText textNumber, textNumber2;
    Spinner theReceivingNumber, spinner1;
    TextView receivingNumberLengthWatch, changedNumberLengthWatch,choiceOfNumber,title,responseText;
    List<String> numbers;
    List<String> theClient, theDate,theContent, theNumber;
    Cursor dateCursor, clientCursor;
    ArrayAdapter<String> clientDataAdapter;
    String receivedMessage, extractedMessage=null;
    /*MODEL*/
    public static final String SMS_BUNDLE = "pdus";
    int length1 = 0, length2 = 0, numberLength = 10;
    String number1Command = "#01%+91";
    String number2Command = "#02%+91";
    String number3Command = "#03%+91";
    String number4Command = "#04%+91";
    String number5Command = "#05%+91";
    String number6Command = "#06%+91";
    String number1 = "NUMBER # 1";
    String number2 = "NUMBER # 2";
    String number3 = "NUMBER # 3";
    String number4 = "NUMBER # 4";
    String number5 = "NUMBER # 5";
    String number6 = "NUMBER # 6";
    AdminControllerDB adminControllerDB;
    SQLiteDatabase sqLiteDatabase;
    static final String companyChoice   = "1";
    static final String builderChoice   = "2";
    static final String selfChoice      = "3";
    final String five="5", four="4", three="3", two="2", one="1", zero="0";
    String theChosen=null;
    int rowCount;

    CountDownTimer cTimer = null;
    int timerValue = 16000;
    int flag;
    /*SMS API*/
    SmsManager sms;
    protected void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_change_ele_number);
        flag=0;
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theChosen                   = theChoice.getString("KEY");
        //toast(theChosen);
        title                       =   (TextView) findViewById(R.id.title);
        theReceivingNumber          =   (Spinner) findViewById(R.id.theReceivingNumber);
        textNumber                  =   (EditText) findViewById(R.id.textNumber);
        changeButton                =   (Button)   findViewById(R.id.changeButton);
        //exitButton                  =   (Button)   findViewById(R.id.exitButton);
        spinner1                    =   (Spinner)  findViewById(R.id.spinner1);
        responseText                =   (TextView) findViewById(R.id.responseText);
        changeButton.setEnabled(true);
        changeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getNumberDetails();
            }
        });
        //receivingNumberLengthWatch  =   (TextView)findViewById(R.id.receivingNumberLengthWatch);
        changedNumberLengthWatch    =   (TextView)findViewById(R.id.changedNumberLengthWatch);
        //choiceOfNumber              =   (TextView)findViewById(R.id.choiceOfNumber);
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
        // Spinner Drop down elements
        numbers = new ArrayList<String>();
        if(theChosen.equalsIgnoreCase(companyChoice))
        {
            numbers.add(number1);
            numbers.add(number2);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter);
            title.setText("Change Client Numbers");
            adminControllerDB = new AdminControllerDB(this);
            sqLiteDatabase= adminControllerDB.getReadableDatabase();
            /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
            theClient = new ArrayList<String>();
            clientCursor = sqLiteDatabase.rawQuery("SELECT ADMIN_CLIENT_NAME FROM ADMIN_CLIENT_DATA",null);
            rowCount = clientCursor.getCount();
            if(rowCount != 0)
            {
                theClient.clear();
                if (clientCursor.moveToFirst())
                {
                    do
                    {
                        theClient.add(clientCursor.getString(clientCursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                    }while (clientCursor.moveToNext());
                }
                clientCursor.close();
                clientDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theClient);
                // Drop down layout style - list view with radio button
                clientDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                theReceivingNumber.setAdapter(clientDataAdapter);
                theReceivingNumber.setOnItemSelectedListener(this);
            }
            else
            {
                toast("There are no Sites Added!");
                responseText.setText("There are no Sites Added! Please Add Site from Sites Tab");
                textNumber.setEnabled(false);
                //changeButton.setEnabled(false);
            }

        }
        if(theChosen.equalsIgnoreCase(builderChoice))
        {
            numbers.add(number3);
            numbers.add(number4);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter);
            title.setText("Change Pan. Bldr. Numbers");
            adminControllerDB = new AdminControllerDB(this);
            sqLiteDatabase= adminControllerDB.getReadableDatabase();
            /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
            theClient = new ArrayList<String>();
            clientCursor = sqLiteDatabase.rawQuery("SELECT ADMIN_CLIENT_NAME FROM ADMIN_CLIENT_DATA",null);
            rowCount = clientCursor.getCount();
            if(rowCount != 0)
            {
                theClient.clear();
                if (clientCursor.moveToFirst())
                {
                    do
                    {
                        theClient.add(clientCursor.getString(clientCursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                    }while (clientCursor.moveToNext());
                }
                clientCursor.close();
                clientDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theClient);
                // Drop down layout style - list view with radio button
                clientDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                theReceivingNumber.setAdapter(clientDataAdapter);
                theReceivingNumber.setOnItemSelectedListener(this);
            }
            else
            {
                toast("There are no Sites Added!");
                responseText.setText("There are no Sites Added! Please Add Site from Sites Tab");
                textNumber.setEnabled(false);
                //changeButton.setEnabled(false);
            }
        }
        if(theChosen.equalsIgnoreCase(selfChoice))
        {
            numbers.add(number5);
            numbers.add(number6);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter);
            title.setText("Change Gopran Numbers");
            adminControllerDB = new AdminControllerDB(this);
            sqLiteDatabase= adminControllerDB.getReadableDatabase();
            /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
            theClient = new ArrayList<String>();
            clientCursor = sqLiteDatabase.rawQuery("SELECT ADMIN_CLIENT_NAME FROM ADMIN_CLIENT_DATA",null);
            rowCount = clientCursor.getCount();
            if(rowCount != 0)
            {
                theClient.clear();
                if (clientCursor.moveToFirst())
                {
                    do
                    {
                        theClient.add(clientCursor.getString(clientCursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                    }while (clientCursor.moveToNext());
                }
                clientCursor.close();
                clientDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theClient);
                // Drop down layout style - list view with radio button
                clientDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                theReceivingNumber.setAdapter(clientDataAdapter);
                theReceivingNumber.setOnItemSelectedListener(this);
            }
            else
            {
                toast("There are no Sites Added!");
                responseText.setText("There are no Sites Added! Please Add Site from Sites Tab");
                textNumber.setEnabled(false);
                //changeButton.setEnabled(false);
            }
        }

        textNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length2 = textNumber.length();
                String getMobile_1;
                getMobile_1 = textNumber.getText().toString();
                if  (
                                getMobile_1.startsWith(five)    ||
                                getMobile_1.startsWith(four)    ||
                                getMobile_1.startsWith(three)   ||
                                getMobile_1.startsWith(two)     ||
                                getMobile_1.startsWith(one)     ||
                                getMobile_1.startsWith(zero)
                )
                {
                    toast("Incorrect number");
                    textNumber.setFocusable(true);
                    changeButton.setEnabled(false);
                }
                else
                {
                    changeButton.setEnabled(true);
                }
                if(textNumber.getText().toString().isEmpty())
                {
                    flag = 1;
                    toast("Please enter a number");
                }
                else
                {
                    if(length2 <= 10 )
                    {
                        String convert = String.valueOf((length2));
                        changedNumberLengthWatch.setText(convert+"/"+numberLength);
                        if(length2 == 10)
                        {
                            textNumber.setEnabled(false);
                            flag = 1;
                        }
                    }
                    if(length2 > 10)
                    {
                        textNumber.setEnabled(false);
                        ////changeButton.setEnabled(false);
                        toast("ONLY 10 DIGITS");
                        textNumber.setFocusable(true);
                        textNumber.setEnabled(true);
                        //changeButton.setEnabled(false);
                    }
                    else
                    {
                        changeButton.setEnabled(true);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sms = SmsManager.getDefault();
    }//end of onCreate()

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        getChoice  = String.valueOf(theReceivingNumber.getSelectedItem());
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase= adminControllerDB.getReadableDatabase();
        clientCursor = sqLiteDatabase.rawQuery("SELECT ADMIN_CLIENT_NUMBER FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+getChoice+"'",null);
        theNumber = new ArrayList<String>();
        theNumber.clear();
        if (clientCursor.moveToFirst())
        {
            do
            {
                theNumber.add(clientCursor.getString(clientCursor.getColumnIndex("ADMIN_CLIENT_NUMBER")));
                //toast(theNumber.get(0));
            }while (clientCursor.moveToNext());
        }
        clientCursor.close();
        getReceivingNumber = String.valueOf(theNumber.get(0));

    }//end of onItemSelected()
    public void onNothingSelected(AdapterView<?> parent)
    {

    }//end of onNothingSelected()

    public void getNumberDetails()
    {
        if(length2 != numberLength)
        {
            //showDialogBox();
            toast("Cell Phone Number has to be "+numberLength+" digits only!");
            flag = 1;
        }
        else
        {
            //getReceivingNumber  = theReceivingNumber.getText().toString();
            getnumber          = textNumber.getText().toString();
            item                = String.valueOf(spinner1.getSelectedItem());
            //choiceOfNumber.setText("CHOSEN:"+item);
            if(getnumber.isEmpty())
            {
                flag = 1;
                toast("Please enter a number");
            }
            if  (
                    getnumber.startsWith(five)    ||
                            getnumber.startsWith(four)    ||
                            getnumber.startsWith(three)   ||
                            getnumber.startsWith(two)     ||
                            getnumber.startsWith(one)     ||
                            getnumber.startsWith(zero)
            )
            {
                toast("Incorrect number");
                System.exit(0);
            }
            if(item.isEmpty())
            {
                flag = 1;
                toast("No client selected");
            }
            else
            {
                switch(theChosen)
                {
                    case companyChoice:
                    {
                        if(item.equalsIgnoreCase(number1))
                        {
                            sms.sendTextMessage(getReceivingNumber, null, number1Command+getnumber, null, null);
                            toast("SENT TO " +getReceivingNumber + " COMMAND:" +number1Command+getnumber);
                            startTimer(timerValue);
                        }
                        if(item.equalsIgnoreCase(number2))
                        {
                            sms.sendTextMessage(getReceivingNumber, null, number2Command+getnumber, null, null);
                            toast("SENT TO " +getReceivingNumber + " COMMAND:" +number2Command+getnumber);
                            startTimer(timerValue);
                        }
                    }
                    break;
                    case builderChoice:
                    {
                        if(item.equalsIgnoreCase(number3))
                        {
                            sms.sendTextMessage(getReceivingNumber, null, number3Command+getnumber, null, null);
                            toast("SENT TO " +getReceivingNumber + " COMMAND:" +number3Command+getnumber);
                            startTimer(timerValue);
                        }
                        if(item.equalsIgnoreCase(number4))
                        {
                            sms.sendTextMessage(getReceivingNumber, null, number4Command+getnumber, null, null);
                            toast("SENT TO " +getReceivingNumber + " COMMAND:" +number4Command+getnumber);
                            startTimer(timerValue);
                        }
                    }
                    break;
                    case selfChoice:
                    {
                        if(item.equalsIgnoreCase(number5))
                        {
                            sms.sendTextMessage(getReceivingNumber, null, number5Command+getnumber, null, null);
                            toast("SENT TO " +getReceivingNumber + " COMMAND:" +number5Command+getnumber);
                            startTimer(timerValue);
                        }
                        if(item.equalsIgnoreCase(number6))
                        {
                            sms.sendTextMessage(getReceivingNumber, null, number6Command+getnumber, null, null);
                            toast("SENT TO " +getReceivingNumber + " COMMAND:" +number6Command+getnumber);
                            startTimer(timerValue);
                        }
                    }
                    break;
                }//end of switch()
            }

        }//end of else

    }//end of getNumberDetails()

    public void exitFromHere()
    {
        System.exit(0);
    }//end of exitFromHere()

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
                ChangeNumbersActivity1.this.getReceivedSMS(theString);
            }
        }//end of onReceiver()
    };//end of BroadcastReceiver class

    public void getReceivedSMS(final String smsMessage)
    {
        receivedMessage = smsMessage;
        String[] array = receivedMessage.split(":",0);
        extractedMessage    = array[1];
        responseText.setText(extractedMessage);
        flag = 1;

    }//end of getReceivedSMS()

    public void startTimer(int timeInMilliseconds)
    {
        cTimer = new CountDownTimer(timeInMilliseconds, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                toast("Please wait for response!" + millisUntilFinished / 1000);
                if(millisUntilFinished/1000 == 1)
                {
                    cTimer.cancel();
                    flag = 1;
                    toast("Now press back");
                }
            }
            public void onFinish()
            {
            }
        };
        cTimer.start();
    }//end of startTimer()

    @Override
    public void onBackPressed()
    {
        if(length2 != numberLength)
        {
            flag = 1;
        }
        if(flag == 1)
        {
            flag = 0;
            System.exit(0);
        }
        else
        {
            toast("Please wait for response");
        }
    }

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }//end of toast()
}