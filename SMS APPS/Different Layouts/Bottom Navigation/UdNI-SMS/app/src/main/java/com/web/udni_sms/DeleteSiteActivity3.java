package com.web.udni_sms;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.web.udni_sms.admin_db.AdminControllerDB;
import com.web.udni_sms.builder_db.BuilderControllerDB;
import com.web.udni_sms.customer_db.CustomerControllerDB;

import java.util.ArrayList;

public class DeleteSiteActivity3 extends AppCompatActivity
{
    ListView siteList;
    Button exit;
    CustomerControllerDB customerControllerDB = new CustomerControllerDB(this);
    SQLiteDatabase sqLiteDatabase;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_site1);
        siteList = (ListView)findViewById(R.id.siteList);
        exit = (Button)findViewById(R.id.exit);
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }//end of onCreate()
    @Override
    protected void onResume()
    {
        displayData();
        super.onResume();
    }
    public void displayData()
    {
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        try
        {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CUSTOMER_CLIENT_DATA",null);
            Id.clear();
            NAME.clear();
            if (cursor.moveToFirst())
            {
                do
                {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    NAME.add(cursor.getString(cursor.getColumnIndex("CUSTOMER_CLIENT_NAME")));
                }while (cursor.moveToNext());
            }
            DeleteSiteAdapter3 da = new DeleteSiteAdapter3(DeleteSiteActivity3.this,Id,NAME);
            siteList.setAdapter(da);
            //code to set adapter to populate list
            cursor.close();
        }catch (SQLException sqe){toastLong(sqe.toString());}
    }//end of displayData()

    @Override
    public void onBackPressed()
    {

    }

    @Override
    public boolean onKeyDown(int key_code, KeyEvent keyEvent)
    {
        if(key_code == KeyEvent.KEYCODE_BACK)
        {
            super.onKeyDown(key_code,keyEvent);
            return true;
        }
        return false;
    }
    public void toastLong(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
