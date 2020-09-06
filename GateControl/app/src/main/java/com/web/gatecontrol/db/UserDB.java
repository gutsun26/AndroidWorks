package com.web.gatecontrol.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper
{
    SQLiteDatabase database;
    private static final String DATABASE_NAME="GCDB";

    public UserDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of UserDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS GATES(Id INTEGER PRIMARY KEY AUTOINCREMENT, APP_ID TEXT, GATE_NAME TEXT, " +
                "SERVER TEXT,USERNAME TEXT, PASSWORD TEXT);";
        db.execSQL(query);
    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}
