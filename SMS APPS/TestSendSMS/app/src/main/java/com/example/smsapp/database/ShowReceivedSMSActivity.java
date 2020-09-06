package com.example.smsapp.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smsapp.R;

import java.util.ArrayList;

public class ShowReceivedSMSActivity extends AppCompatActivity
{
    /*SQLITE ELEMENTS*/
    SMSReceiverControllerDB controllerdb = new SMSReceiverControllerDB(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> DATE = new ArrayList<String>();
    private ArrayList<String> ADDRESS = new ArrayList<String>();
    private ArrayList<String> CONTENT = new ArrayList<String>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lv = (ListView) findViewById(R.id.lstvw);
    }
    @Override
    protected void onResume()
    {
        displayData();
        super.onResume();
    }
    private void displayData()
    {
        db = controllerdb.getReadableDatabase();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM G_SMS",null);
            Id.clear();
            DATE.clear();
            ADDRESS.clear();
            CONTENT.clear();
            if (cursor.moveToFirst())
            {
                do
                {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    DATE.add(cursor.getString(cursor.getColumnIndex("DATE")));
                    ADDRESS.add(cursor.getString(cursor.getColumnIndex("ADDRESS")));
                    CONTENT.add(cursor.getString(cursor.getColumnIndex("CONTENT")));
                }while (cursor.moveToNext());
            }
            SMSReceivedCustomAdapter ca = new SMSReceivedCustomAdapter(ShowReceivedSMSActivity.this,Id,DATE, ADDRESS,CONTENT);
            lv.setAdapter(ca);
            //code to set adapter to populate list
            cursor.close();
        }catch (SQLException sqe){toast(sqe.toString());}

    }
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

