package com.web.udni_sms.admin_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSMSReceiverControllerDB extends SQLiteOpenHelper
{
    SQLiteDatabase database;
    private static final String DATABASE_NAME="SqliteListviewDB";
    public AdminSMSReceiverControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of AdminSMSReceiverControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query, query2;
        query = "CREATE TABLE IF NOT EXISTS ADMIN_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}