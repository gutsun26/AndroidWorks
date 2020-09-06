package com.web.g_smsapp.panel_builder_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PanelBuilderControllerDB extends SQLiteOpenHelper
{
    SQLiteDatabase database;
    private static final String DATABASE_NAME="SqliteListviewDB";
    public PanelBuilderControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of PanelBuilderControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS PANEL_BUILDER_DEST_NUMBERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER TEXT, LOCATION TEXT);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }//end of onUpgrade()
}
