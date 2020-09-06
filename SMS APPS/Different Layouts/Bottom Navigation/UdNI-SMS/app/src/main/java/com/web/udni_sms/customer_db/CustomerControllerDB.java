package com.web.udni_sms.customer_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerControllerDB extends SQLiteOpenHelper
{
    private SQLiteDatabase database;
    private static final String DATABASE_NAME="UdNi_SMS_DB";
    private String query;
    public CustomerControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of AdminControllerDB
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        query = "CREATE TABLE IF NOT EXISTS CUSTOMER_CLIENT_DATA(Id INTEGER PRIMARY KEY AUTOINCREMENT, CUSTOMER_CLIENT_NAME TEXT, CUSTOMER_CLIENT_NUMBER TEXT, CUSTOMER_CLIENT_ADDRESS);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}
