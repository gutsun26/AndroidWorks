package com.web.udni_sms;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.udni_sms.admin_db.AdminControllerDB;
import com.web.udni_sms.admin_db.AdminCustomAdapter;
import com.web.udni_sms.admin_db.AdminCustomerToBeDeletedAdapter;

import java.util.ArrayList;

public class DeleteSiteActivity extends AppCompatActivity
{
    AdminControllerDB adminControllerDB = new AdminControllerDB(this);
    SQLiteDatabase sqLiteDatabase;
    int rowCount;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();

    ListView listOfSites;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_site);
        //listOfSites = (ListView)findViewById(R.id.listOfSites);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        displayData();
    }//end of onCreate()

    @Override
    protected void onResume()
    {
        displayData();
        super.onResume();
    }

    public void displayData()
    {
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        try
        {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN_CLIENT_DATA",null);
            rowCount = cursor.getCount();
            if(rowCount!=0)
            {
                Id.clear();
                NAME.clear();
                if (cursor.moveToFirst())
                {
                    do
                    {
                        Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                        NAME.add(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                    }while (cursor.moveToNext());
                }
                AdminCustomerToBeDeletedAdapter ca = new AdminCustomerToBeDeletedAdapter(DeleteSiteActivity.this,Id, NAME);
                listOfSites.setAdapter(ca);
                cursor.close();
            }
            else
            {
                toast("NO CLIENTS");
            }
        }catch(SQLException sqe){toast(sqe.toString());}
    }//end of displayData()

    public void toast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


}
