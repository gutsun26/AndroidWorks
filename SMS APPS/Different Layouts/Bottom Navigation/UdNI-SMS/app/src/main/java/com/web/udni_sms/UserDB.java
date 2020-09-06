package com.web.udni_sms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper
{
    SQLiteDatabase database;
    private static final String DATABASE_NAME="SqliteListviewDB";

    public UserDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of AdminControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS USER_DATA(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "COMPANY TEXT," +
                "EMAIL TEXT," +
                "MOBILE1 TEXT," +
                "MOBILE2 TEXT," +
                "WHATSAPPNUM TEXT," +
                "ADDRESS TEXT" +
                ");";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}
