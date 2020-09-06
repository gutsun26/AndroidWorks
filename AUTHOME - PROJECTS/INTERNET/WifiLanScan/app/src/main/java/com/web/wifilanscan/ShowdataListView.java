package com.web.wifilanscan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowdataListView extends AppCompatActivity
{
    Controllerdb controllerdb = new Controllerdb(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> SSID = new ArrayList<String>();
    private ArrayList<String> ROOM_NAME = new ArrayList<String>();
    private ArrayList<String> PASSWORD = new ArrayList<String>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata_listview);
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
        Cursor cursor = db.rawQuery("SELECT * FROM  ROOM_NAMES",null);
        Id.clear();
        SSID.clear();
        ROOM_NAME.clear();
        PASSWORD.clear();
        if (cursor.moveToFirst())
        {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                SSID.add(cursor.getString(cursor.getColumnIndex("SSID")));
                ROOM_NAME.add(cursor.getString(cursor.getColumnIndex("ROOM_NAME")));
                PASSWORD.add(cursor.getString(cursor.getColumnIndex("PWD")));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(ShowdataListView.this,Id,SSID,ROOM_NAME,PASSWORD);
        lv.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }
}

