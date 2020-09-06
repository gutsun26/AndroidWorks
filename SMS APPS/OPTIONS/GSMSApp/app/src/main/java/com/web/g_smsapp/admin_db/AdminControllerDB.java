package com.web.g_smsapp.admin_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminControllerDB extends SQLiteOpenHelper
{
    SQLiteDatabase database;
    private static final String DATABASE_NAME="SqliteListviewDB";
    private static final String TABLE_DEST="DEST_NUMBERS";
    private static final String COLUMN_NAME_1 = "NUMBER";
    private static final String COLUMN_NAME_2 = "LOCATION";

    public AdminControllerDB(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }//end of AdminControllerDB

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query, query2;
        query = "CREATE TABLE IF NOT EXISTS DEST_NUMBERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER TEXT, LOCATION TEXT);";
        db.execSQL(query);


    }//end of onCreate()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        /*
        String query ;
        query = "DROP TABLE IF EXISTS USER_DATA";
        db.execSQL(query);
        onCreate(db);
        */
    }//end of onUpgrade()

    public void insertData(String columnData1, String columnData2)
    {
        database.execSQL("INSERT INTO "+TABLE_DEST+"("+COLUMN_NAME_1+","+COLUMN_NAME_2+") VALUES('" + columnData1 + "','" + columnData2 + "')" );
    }//end of insertData()
}