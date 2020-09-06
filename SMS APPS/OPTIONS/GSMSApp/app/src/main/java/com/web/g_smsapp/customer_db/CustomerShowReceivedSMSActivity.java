package com.web.g_smsapp.customer_db;

import android.app.Dialog;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.web.g_smsapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerShowReceivedSMSActivity extends AppCompatActivity
{
    /*SQLITE ELEMENTS*/
    private static final String DATABASE_NAME="SqliteListviewDB";
    CustomerSMSReceiverControllerDB controllerdb = new CustomerSMSReceiverControllerDB(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    //private ArrayList<String> DATE = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> CONTENT = new ArrayList<String>();
    ArrayList<String> DATA = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;

    ListView lv;
    String choice;
    ListView textDate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_show);
        lv = (ListView) findViewById(R.id.lstvw);
        toast("RECEIEVED SMS");
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
            Cursor cursor = db.rawQuery("SELECT * FROM G_CUSTOMER_SMS",null);
            Id.clear();
            //DATE.clear();
            NUMBER.clear();
            CONTENT.clear();
            if (cursor.moveToFirst())
            {
                do
                {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    NUMBER.add(cursor.getString(cursor.getColumnIndex("NUMBER")));
                    CONTENT.add(cursor.getString(cursor.getColumnIndex("CONTENT")));
                }while (cursor.moveToNext());
            }
            //AdminSMSReceivedCustomAdapter ca = new AdminSMSReceivedCustomAdapter(AdminShowReceivedSMSActivity.this,Id,DATE, ADDRESS,CONTENT);
            CustomerSMSReceivedCustomAdapter ca = new CustomerSMSReceivedCustomAdapter(CustomerShowReceivedSMSActivity.this,Id,NUMBER,CONTENT);
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
