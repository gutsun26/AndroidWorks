package com.web.udni_sms.admin_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminControllerDB extends SQLiteOpenHelper
{
    private SQLiteDatabase database;
    private static final String DATABASE_NAME="UdNi_SMS_DB";
    private String query;
    public AdminControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of AdminControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        query = "CREATE TABLE IF NOT EXISTS ADMIN_CLIENT_DATA(Id INTEGER PRIMARY KEY AUTOINCREMENT, ADMIN_CLIENT_NAME TEXT, ADMIN_CLIENT_NUMBER TEXT, ADMIN_CLIENT_ADDRESS);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()

    public void insertData(String columnData1, String columnData2)
    {

    }//end of insertData()
}