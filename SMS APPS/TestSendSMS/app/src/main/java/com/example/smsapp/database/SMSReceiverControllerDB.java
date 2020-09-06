package com.example.smsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SMSReceiverControllerDB extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="SqliteListviewDB";
    public SMSReceiverControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of SMSReceiverControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query, query2;
        query = "CREATE TABLE IF NOT EXISTS G_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}