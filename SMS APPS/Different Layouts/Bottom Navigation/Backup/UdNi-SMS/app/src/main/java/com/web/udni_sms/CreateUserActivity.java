package com.web.udni_sms;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class CreateUserActivity extends AppCompatActivity
{
    String phoneNumber = "9892012016";
    SmsManager sms;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
    String vijigeeshuEmail = "vijigeeshu@gmail.com";
    EditText name, company, email, whatsAppNum, mobile_1, mobile_2, adds_line_1, adds_line_2, adds_line_3;
    String nameString, companyString, emailString, whatsAppNumString, mobile_1String, mobile_2String, addressString;
    int rowCount;
    UserDB userDB;
    SQLiteDatabase database;
    String createTable = "CREATE TABLE IF NOT EXISTS " +
            "                   USER_DATA(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "NAME TEXT, " +
                                "COMPANY TEXT," +
                                "EMAIL TEXT," +
                                "MOBILE1 TEXT," +
                                "MOBILE2 TEXT," +
                                "WHATSAPPNUM TEXT," +
                                "ADDRESS TEXT" +
                                ");";
    Button doneButton;
    int nameLength=2, cellNumberLength=10;
    int numberCount1, numberCount2, numberCount3;
    final String five="5", four="4", three="3", two="2", one="1", zero="0";
    View root1, root2, root3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        sms = SmsManager.getDefault();
        checkForSmsPermission();
        getSupportActionBar().hide();
        name                = (EditText)findViewById(R.id.name);
        company             = (EditText)findViewById(R.id.company);
        email               = (EditText)findViewById(R.id.email);
        whatsAppNum         = (EditText)findViewById(R.id.whatsAppNum);
        mobile_1            = (EditText)findViewById(R.id.mobile1);
        mobile_2            = (EditText)findViewById(R.id.mobile2);
        adds_line_1         = (EditText)findViewById(R.id.adds_line_1);
        adds_line_2         = (EditText)findViewById(R.id.adds_line_2);
        //adds_line_3         = (EditText)findViewById(R.id.adds_line_3);
        doneButton          = (Button)findViewById(R.id.doneButton);
        root1 = mobile_1.getRootView();
        root2 = mobile_2.getRootView();
        root3 = whatsAppNum.getRootView();


        mobile_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //mobile_1.setBackgroundColor(getResources().getColor(R.color.startGrad));
                String getMobile_1;
                int count;
                count = mobile_1.length();
                getMobile_1 = mobile_1.getText().toString();
                if  (
                        getMobile_1.startsWith(five)    ||
                        getMobile_1.startsWith(four)    ||
                        getMobile_1.startsWith(three)   ||
                        getMobile_1.startsWith(two)     ||
                        getMobile_1.startsWith(one)     ||
                        getMobile_1.startsWith(zero)
                    )
                {
                    mobile_2.setEnabled(false);
                    whatsAppNum.setEnabled(false);
                    adds_line_1.setEnabled(false);
                    adds_line_2.setEnabled(false);
                    doneButton.setEnabled(false);

                    toast("Incorrect number");
                    mobile_1.setFocusable(true);
                }
                else
                {
                    mobile_1.setEnabled(true);
                    mobile_2.setEnabled(true);
                    whatsAppNum.setEnabled(true);
                    adds_line_1.setEnabled(true);
                    adds_line_2.setEnabled(true);
                    doneButton.setEnabled(true);
                }

                if(count > 10)
                {
                    mobile_2.setEnabled(false);
                    whatsAppNum.setEnabled(false);
                    adds_line_1.setEnabled(false);
                    adds_line_2.setEnabled(false);
                    doneButton.setEnabled(false);

                    toast("Mobile 1 digits are more than 10. Please correct it!");
                    mobile_1.setFocusable(true);
                }
                else
                {
                    mobile_1.setEnabled(true);
                    mobile_2.setEnabled(true);
                    whatsAppNum.setEnabled(true);
                    adds_line_1.setEnabled(true);
                    adds_line_2.setEnabled(true);
                    doneButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mobile_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String getMobile_2;
                int count;
                count = mobile_2.length();
                getMobile_2 = mobile_2.getText().toString();
                if  (
                        getMobile_2.startsWith(five)    ||
                                getMobile_2.startsWith(four)    ||
                                getMobile_2.startsWith(three)   ||
                                getMobile_2.startsWith(two)     ||
                                getMobile_2.startsWith(one)     ||
                                getMobile_2.startsWith(zero)
                )
                {
                    mobile_1.setEnabled(false);
                    whatsAppNum.setEnabled(false);
                    adds_line_1.setEnabled(false);
                    adds_line_2.setEnabled(false);
                    doneButton.setEnabled(false);

                    toast("Incorrect number");
                    mobile_2.setFocusable(true);
                }
                else
                {
                    mobile_1.setEnabled(true);
                    mobile_2.setEnabled(true);
                    whatsAppNum.setEnabled(true);
                    adds_line_1.setEnabled(true);
                    adds_line_2.setEnabled(true);
                    doneButton.setEnabled(true);
                }
                if(count > 10)
                {
                    mobile_1.setEnabled(false);
                    whatsAppNum.setEnabled(false);
                    adds_line_1.setEnabled(false);
                    adds_line_2.setEnabled(false);
                    doneButton.setEnabled(false);

                    toast("Mobile 2 digits are more than 10. Please correct it!");
                    mobile_2.setFocusable(true);
                }
                else
                {
                    mobile_1.setEnabled(true);
                    mobile_2.setEnabled(true);
                    whatsAppNum.setEnabled(true);
                    adds_line_1.setEnabled(true);
                    adds_line_2.setEnabled(true);
                    doneButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        whatsAppNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String getMobile;
                int count;
                count = whatsAppNum.length();
                getMobile = whatsAppNum.getText().toString();
                if  (
                        getMobile.startsWith(five)			  ||
                                getMobile.startsWith(four)    ||
                                getMobile.startsWith(three)   ||
                                getMobile.startsWith(two)     ||
                                getMobile.startsWith(one)     ||
                                getMobile.startsWith(zero)
                )
                {
                    mobile_1.setEnabled(false);
                    mobile_2.setEnabled(false);
                    adds_line_1.setEnabled(false);
                    adds_line_2.setEnabled(false);
                    doneButton.setEnabled(false);

                    toast("Incorrect number");
                    whatsAppNum.setEnabled(true);
                }
                else
                {
                    mobile_1.setEnabled(true);
                    mobile_2.setEnabled(true);
                    whatsAppNum.setEnabled(true);
                    adds_line_1.setEnabled(true);
                    adds_line_2.setEnabled(true);
                    doneButton.setEnabled(true);
                }
                if(count > 10)
                {
                    mobile_1.setEnabled(false);
                    mobile_2.setEnabled(false);
                    adds_line_1.setEnabled(false);
                    adds_line_2.setEnabled(false);
                    doneButton.setEnabled(false);

                    toast("WhatsApp Number digits are more than 10. Please correct it!");
                    whatsAppNum.setFocusable(true);
                }
                else
                {
                    mobile_1.setEnabled(true);
                    mobile_2.setEnabled(true);
                    whatsAppNum.setEnabled(true);
                    adds_line_1.setEnabled(true);
                    adds_line_2.setEnabled(true);
                    doneButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        userDB = new UserDB(this);
        database = userDB.getReadableDatabase();
        database.execSQL(createTable);
        Cursor cursor = database.rawQuery("SELECT * FROM USER_DATA",null);
        rowCount = cursor.getCount();
        if(rowCount == 0)
        {
            toast("EMPTY DATABASE");
        }
        else
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUserData();
            }
        });
    }//end of onCreate()

    public void insertUserData()
    {
        nameString          =   name.getText().toString();
        companyString       =   company.getText().toString();
        emailString         =   email.getText().toString();
        whatsAppNumString   =   whatsAppNum.getText().toString();
        mobile_1String      =   mobile_1.getText().toString();
        mobile_2String      =   mobile_2.getText().toString();
        addressString       =   adds_line_1.getText().toString() + "," + adds_line_2.getText().toString();// + "," + adds_line_3.getText().toString();
        numberCount1        =   mobile_1String.length();
        //numberCount2        =   mobile_2String.length();
        //numberCount3        =   whatsAppNumString.length();
        //if(nameString.isEmpty() || companyString.isEmpty() || emailString.isEmpty() || whatsAppNumString.isEmpty() || mobile_1String.isEmpty() || mobile_2String.isEmpty() || addressString.isEmpty())
        if(nameString.isEmpty() || companyString.isEmpty() || emailString.isEmpty() || mobile_1String.isEmpty() || addressString.isEmpty())
        {
            toast("Please fill all the fields");
        }
        if(whatsAppNumString.isEmpty())
        {
            whatsAppNumString = "NULL";
        }
        if(mobile_2String.isEmpty())
        {
            mobile_2String = "NULL";
        }
        if( !(emailString.contains("@")) || !(emailString.contains(".")) )
        {
            toast("Email Address is invalid! Please enter a valid address");
            email.setFocusable(true);
            email.setSelectAllOnFocus(true);
            name.setEnabled(false);
            company.setEnabled(false);
            mobile_2.setEnabled(false);
            whatsAppNum.setEnabled(false);
            adds_line_1.setEnabled(false);
            adds_line_2.setEnabled(false);
        }
        else if(numberCount1 != cellNumberLength)
        {
            toast("Mobile 1's digits are less than 10");
            mobile_1.setFocusable(true);
            mobile_1.setSelectAllOnFocus(true);
            name.setEnabled(false);
            company.setEnabled(false);
            email.setEnabled(false);
            mobile_2.setEnabled(false);
            whatsAppNum.setEnabled(false);
            adds_line_1.setEnabled(false);
            adds_line_2.setEnabled(false);
        }
        /*
        else if(numberCount2 != cellNumberLength)
        {
            toast("Mobile 2's digits are less than 10");
            mobile_2.setFocusable(true);
            mobile_2.setSelectAllOnFocus(true);
            name.setEnabled(false);
            company.setEnabled(false);
            email.setEnabled(false);
            mobile_1.setEnabled(false);
            whatsAppNum.setEnabled(false);
            adds_line_1.setEnabled(false);
            adds_line_2.setEnabled(false);
        }
        else if(numberCount3 != cellNumberLength)
        {
            toast("WhatsApp Number's digits are less than 10");
            whatsAppNum.setFocusable(true);
            whatsAppNum.setSelectAllOnFocus(true);
            name.setEnabled(false);
            company.setEnabled(false);
            email.setEnabled(false);
            mobile_1.setEnabled(false);
            mobile_2.setEnabled(false);
            adds_line_1.setEnabled(false);
            adds_line_2.setEnabled(false);
        }*/
        else
        {
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("IS YOUR DATA CORRECT?");

            List<String> list = new ArrayList<String>();
            list.clear();
            list.add("Name:"+nameString);
            list.add("Company:"+companyString);
            list.add("Email:"+emailString);
            list.add("Mobile 1:"+mobile_1String);
            list.add("Mobile 2:"+mobile_2String);
            list.add("WhatsApp Number:"+whatsAppNumString);
            list.add("Address:"+addressString);

            ListView listOfUserData     = new ListView(this);
            ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
            listOfUserData.setAdapter(modeAdapter);
            alertDialogBuilder.setView(listOfUserData);
            alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    database.execSQL("INSERT INTO USER_DATA(NAME,COMPANY, EMAIL,MOBILE1,MOBILE2,WHATSAPPNUM,ADDRESS) " +
                            "VALUES('" +
                                        nameString + "','" +
                                        companyString + "','" +
                                        emailString + "','" +
                                        mobile_1String + "','" +
                                        mobile_2String + "','" +
                                        whatsAppNumString + "','" +
                                        addressString +
                                "')" );
                    toast("INSERTED THE DATA INTO USER_DATA");
                    /*
                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                    intent.putExtra(Intent.EXTRA_TEXT, nameString + "','" +
                                                             companyString + "','" +
                                                             emailString + "','" +
                                                             mobile_1String + "','" +
                                                             mobile_2String + "','" +
                                                             whatsAppNumString + "','" +
                                                             addressString
                                                             );
                    intent.setData(Uri.parse("mailto:"+vijigeeshuEmail)); // or just "mailto:" for blank
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(intent);

                     */
                    /*
                    sms.sendTextMessage(phoneNumber, null, nameString + "','" +
                            companyString + "','" +
                            emailString + "','" +
                            mobile_1String + "','" +
                            mobile_2String + "','" +
                            whatsAppNumString + "','" +
                            addressString, null, null);

                     */
                    /*
                    String numberFilter = "address='"+ phoneNumber + "'";
                    String messageid = null;
                    Cursor cursor = getApplicationContext().getContentResolver().query(Uri.parse("content://sms/"),
                            null, numberFilter, null, null);

                    if (cursor.moveToFirst()) {
                        messageid = cursor.getString(0);
                    }
                    getContentResolver().delete(Uri.parse("content://sms/" + messageid), null, null);
                    toast("Deleted");

                     */
                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginIntent);
                }
            });
            alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    name.setText(nameString);
                    company.setText(companyString);
                    email.setText(emailString);
                    whatsAppNum.setText(whatsAppNumString);
                    mobile_1.setText(mobile_1String);
                    mobile_2.setText(mobile_2String);
                    adds_line_1.setText(adds_line_1.getText().toString());
                    adds_line_2.setText(adds_line_2.getText().toString());
                    addressString       =   adds_line_1.getText().toString() + "," + adds_line_2.getText().toString();/*
                    root1 = mobile_1.getRootView();
                    root1.setBackgroundColor(getResources().getColor(R.color.primary));
                    root2 = mobile_2.getRootView();
                    root2.setBackgroundColor(getResources().getColor(R.color.primary));
                    root3 = whatsAppNum.getRootView();
                    root3.setBackgroundColor(getResources().getColor(R.color.primary));*/
                }
            });

            final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

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
    public void toast(String s)
    {
        Toast.makeText(this, s,Toast.LENGTH_LONG).show();
    }
}
