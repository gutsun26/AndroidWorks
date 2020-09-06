package com.web.g_smsapp.customer_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerControllerDB extends SQLiteOpenHelper
{
    SQLiteDatabase database;
    private static final String DATABASE_NAME="SqliteListviewDB";
    public CustomerControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of CustomerControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query, query2;
        query = "CREATE TABLE IF NOT EXISTS CUSTOMER_DEST_NUMBERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER TEXT, LOCATION TEXT);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}
