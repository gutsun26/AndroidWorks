package com.web.g_smsapp.admin_db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;

import java.util.ArrayList;

public class AdminShowNumbersLocationActivity extends AppCompatActivity
{
    /*SQLITE ELEMENTS*/
    private static final String DATABASE_NAME="SqliteListviewDB";
    private static final String TABLE_DEST="DEST_NUMBERS";
    private static final String COLUMN_NAME_1 = "NUMBER";
    private static final String COLUMN_NAME_2 = "LOCATION";

    AdminControllerDB controllerdb = new AdminControllerDB(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> LOCATION = new ArrayList<String>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata_admin_listview);
        lv = (ListView) findViewById(R.id.lstvw);
    }
    @Override
    protected void onResume()
    {
        displayData();
        super.onResume();
    }
    private void displayData()
    {
        db = controllerdb.getReadableDatabase();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM DEST_NUMBERS",null);
            Id.clear();
            NUMBER.clear();
            LOCATION.clear();
            if (cursor.moveToFirst())
            {
                do
                {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    NUMBER.add(cursor.getString(cursor.getColumnIndex("NUMBER")));
                    LOCATION.add(cursor.getString(cursor.getColumnIndex("LOCATION")));
                }while (cursor.moveToNext());
            }
            AdminCustomAdapter ca = new AdminCustomAdapter(AdminShowNumbersLocationActivity.this,Id,NUMBER,LOCATION);
            lv.setAdapter(ca);
            //code to set adapter to populate list
            cursor.close();
        }catch (SQLException sqe){toast(sqe.toString());}

    }
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

