package com.web.g_smsapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CreateUserActivity extends AppCompatActivity
{
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        name                = (EditText)findViewById(R.id.name);
        company             = (EditText)findViewById(R.id.company);
        email               = (EditText)findViewById(R.id.email);
        whatsAppNum         = (EditText)findViewById(R.id.whatsAppNum);
        mobile_1            = (EditText)findViewById(R.id.mobile_1);
        mobile_2            = (EditText)findViewById(R.id.mobile_2);
        adds_line_1         = (EditText)findViewById(R.id.adds_line_1);
        adds_line_2         = (EditText)findViewById(R.id.adds_line_2);
        adds_line_3         = (EditText)findViewById(R.id.adds_line_3);
        doneButton          = (Button)findViewById(R.id.doneButton);

        userDB = new UserDB(this);
        database = userDB.getReadableDatabase();
        database.execSQL(createTable);
        Cursor cursor = database.rawQuery("SELECT * FROM USER_DATA",null);
        rowCount = cursor.getCount();
        if(rowCount == 0)
        {
            toast("NO ROWS IN THE DATABASE");
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
        addressString       =   adds_line_1.getText().toString() + "," + adds_line_2.getText().toString() + "," + adds_line_3.getText().toString();
        if(nameString.isEmpty() || companyString.isEmpty() || emailString.isEmpty() || whatsAppNumString.isEmpty() || mobile_1String.isEmpty() || mobile_2String.isEmpty() || addressString.isEmpty())
        {
            toast("PLEASE FILL ALL THE FIELDS");
        }
        else
        {
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("IS YOUR DATA CORRECT?");

            List<String> list = new ArrayList<String>();
            list.clear();
            list.add(nameString);
            list.add(companyString);
            list.add(emailString);
            list.add(whatsAppNumString);
            list.add(mobile_1String);
            list.add(mobile_2String);
            list.add(addressString);

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
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    System.exit(0);
                }
            });

            final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
    public void toast(String s)
    {
        Toast.makeText(this, s,Toast.LENGTH_LONG).show();
    }
}
