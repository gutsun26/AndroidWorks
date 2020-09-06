package com.web.wifilanscan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;


public class Controllerdb extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="SqliteListviewDB";
    private static final String ROOM_COLUMN_NAME="ROOM_NAME";
    private static final String SSID_COLUMN_NAME="SSID";
    private static final String PWD_COLUMN_NAME="PWD";

    public Controllerdb(Context applicationcontext)
    {
        super(applicationcontext, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS ROOM_NAMES(Id INTEGER PRIMARY KEY AUTOINCREMENT,SSID VARCHAR, ROOM_NAME VARCHAR, PWD VARCHAR);";
        db.execSQL(query);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query ;
        query = "DROP TABLE IF EXISTS ROOM_NAMES";
        db.execSQL(query);
        onCreate(db);
    }
    public String getROOMNAMEFromAlternateTable(String givenSSID)
    {
        ArrayList<String> array_list = new ArrayList<String>();
        String theRoomName=null;
        String selectQuery = "select ROOM_NAME from ROOM_NAMES where SSID=\'"+givenSSID+"\'";
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor res =  sqldb.rawQuery( selectQuery, null );
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(ROOM_COLUMN_NAME)));
            res.moveToNext();
        }
        String[] str = getArrayToString(array_list);
        theRoomName = Arrays.toString(str);
        theRoomName = theRoomName.replace("[","");
        theRoomName = theRoomName.replace("]","");
        return theRoomName;
    }
    public String[] getArrayToString(ArrayList<String> arr)
    {
        // declaration and initialise String Array
        String str[] = new String[arr.size()];
        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++)
        {
            // Assign each value to String array
            str[j] = arr.get(j);
        }
        return str;
    }
    public String getSSIDOfNamedRoom(String givenRoomName)
    {
        ArrayList<String> array_list = new ArrayList<String>();
        String theSSID=null;
        String selectQuery = "select SSID from ROOM_NAMES where ROOM_NAME=\'"+givenRoomName+"\'";
        SQLiteDatabase sqldb = this.getReadableDatabase();
        //Cursor res =  sqldb.rawQuery( "select * from authentication", null );
        Cursor res =  sqldb.rawQuery( selectQuery, null );
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(SSID_COLUMN_NAME)));
            res.moveToNext();
        }
        String[] str = getArrayToString(array_list);
        theSSID = Arrays.toString(str);
        theSSID = theSSID.replace("[","");
        theSSID = theSSID.replace("]","");
        return theSSID;
    }

    public String getPassword(String ssid)
    {
        ArrayList<String> array_list = new ArrayList<String>();
        String thePWD=null;
        String selectQuery = "select PWD from ROOM_NAMES where SSID=\'"+ssid+"\'";
        SQLiteDatabase sqldb = this.getReadableDatabase();
        //Cursor res =  sqldb.rawQuery( "select * from authentication", null );
        Cursor res =  sqldb.rawQuery( selectQuery, null );
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(PWD_COLUMN_NAME)));
            res.moveToNext();
        }
        String[] str = getArrayToString(array_list);
        thePWD = Arrays.toString(str);
        thePWD = thePWD.replace("[","");
        thePWD = thePWD.replace("]","");
        return thePWD;
    }
    public void dropTable()
    {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        String query = "DROP TABLE IF EXISTS ROOM_NAMES";
        db.execSQL(query);
    }
}