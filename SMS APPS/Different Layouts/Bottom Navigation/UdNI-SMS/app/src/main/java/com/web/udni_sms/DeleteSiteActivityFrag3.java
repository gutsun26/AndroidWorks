package com.web.udni_sms;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.web.udni_sms.customer_db.CustomerControllerDB;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DeleteSiteActivityFrag3 extends AppCompatActivity
{
    CustomerControllerDB customerControllerDB = new CustomerControllerDB(this);
    SQLiteDatabase sqLiteDatabase;
    int rowCount;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();
    String theKey=null;
    TextView theText;
    Dialog dialog;
    ListView listOfSites;
    String theString="DEL";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_site);
        //theText = (TextView)findViewById(R.id.theText);
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theKey                      = theChoice.getString("ID");
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("DO YOU WANT TO DELETE "+theKey +"?");
        alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                customerControllerDB = new CustomerControllerDB(getApplicationContext());
                sqLiteDatabase = customerControllerDB.getReadableDatabase();
                sqLiteDatabase.execSQL("DELETE FROM CUSTOMER_CLIENT_DATA WHERE CUSTOMER_CLIENT_NAME='"+theKey+"'");
                System.exit(0);
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
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


        /*
        try
        {
            TimeUnit.SECONDS.sleep(3);

        }catch(InterruptedException ie){}*/

    }//end of onCreate()

    public void toast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


}
