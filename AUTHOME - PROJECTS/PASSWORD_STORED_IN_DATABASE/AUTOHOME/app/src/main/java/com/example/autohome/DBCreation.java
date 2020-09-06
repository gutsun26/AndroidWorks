package com.example.autohome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;import android.widget.Toast;

import java.util.ArrayList;

public class DBCreation extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "AUTO_HOME.db";
    public static final String TABLE_NAME = "authentication";
    public static final String AUTHENTICATION_SSID_COLUMN_NAME = "ssid";
    public static final String AUTHENTICATION_PWD_COLUMN_NAME = "pwd";

    public DBCreation(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        db.execSQL("create table authentication" +"(id integer primary key, ssid text,pwd text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS authentication");
        onCreate(db);
    }

    public boolean insertData(String ssid, String pwd)
    {
        String bufferSSID=null,checkSSIDExists=null;
        bufferSSID = ssid;
        checkSSIDExists=getSSID(bufferSSID);
        if(checkSSIDExists.equalsIgnoreCase(bufferSSID))
        {
            return false;
        }
        else
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("ssid", ssid);
            contentValues.put("pwd", pwd);
            db.insert(TABLE_NAME, null, contentValues);
            return true;
        }
    }

    public Cursor getData(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from authentication where id="+id+"", null );
        return res;
    }

    public boolean updateData (Integer id, String ssid, String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ssid", ssid);
        contentValues.put("pwd", pwd);
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updatePWD(String ssid, String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pwd", pwd);
        db.update(TABLE_NAME, contentValues, "ssid = ? ", new String[] { String.valueOf(ssid)} );
        return true;
    }

    public Integer deleteData (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from authentication", null );
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(AUTHENTICATION_SSID_COLUMN_NAME )));
            res.moveToNext();
        }
        return array_list;
    }
    public String getPWD(String givenSSID)
    {
        ArrayList<String> array_list = new ArrayList<String>();
        String thePWD=null;
        //String selectQuery = "select pwd from authentication where ssid=\'"+givenSSID+"\'";
        String selectQuery = "select pwd from authentication";
        SQLiteDatabase sqldb = this.getReadableDatabase();
        //Cursor res =  sqldb.rawQuery( "select * from authentication", null );
        Cursor res =  sqldb.rawQuery( selectQuery, null );
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(AUTHENTICATION_PWD_COLUMN_NAME )));
            res.moveToNext();
        }
        String[] str = getArrayToString(array_list);
        thePWD = Arrays.toString(str);
        thePWD = thePWD.replace("[","");
        thePWD = thePWD.replace("]","");

        //thePWD = cu.getString(cu.getColumnIndex(AUTHENTICATION_PWD_COLUMN_NAME));
        return thePWD;
    }

    public String getSSID(String givenSSID)
    {
        ArrayList<String> array_list = new ArrayList<String>();
        String theSSID=null;
        String selectQuery = "select ssid from authentication where ssid=\'"+givenSSID+"\'";
        SQLiteDatabase sqldb = this.getReadableDatabase();
        //Cursor res =  sqldb.rawQuery( "select * from authentication", null );
        Cursor res =  sqldb.rawQuery( selectQuery, null );
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(AUTHENTICATION_SSID_COLUMN_NAME )));
            res.moveToNext();
        }
        String[] str = getArrayToString(array_list);
        theSSID = Arrays.toString(str);
        theSSID = theSSID.replace("[","");
        theSSID = theSSID.replace("]","");

        //thePWD = cu.getString(cu.getColumnIndex(AUTHENTICATION_PWD_COLUMN_NAME));
        return theSSID;
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

}
