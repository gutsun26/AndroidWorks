package com.web.g_smsapp.customer;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;
import com.web.g_smsapp.customer_db.CustomerControllerDB;
import com.web.g_smsapp.customer_db.CustomerSMSReceivedCustomAdapter;
import com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB;
import com.web.g_smsapp.customer_db.CustomerShowReceivedSMSActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomerSMSDeleteActivity extends AppCompatActivity
{
    /*SQLITE ELEMENTS*/
    private static final String DATABASE_NAME="SqliteListviewDB";
    com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB customerSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    CustomerControllerDB customerControllerDB;
    SQLiteDatabase sqLiteDatabase;
    Cursor dateCursor, clientCursor;
    CustomerSMSReceiverControllerDB controllerdb = new CustomerSMSReceiverControllerDB(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    //private ArrayList<String> DATE = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> CONTENT = new ArrayList<String>();
    ArrayList<String> DATA = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();
    List<String> theClient;
    ArrayAdapter<String> clientDataAdapter, dateAdapter,choiceAdapter;
    ListView lv;
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_show);
        mContext = this;
        lv = (ListView) findViewById(R.id.lstvw);
        /*
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setBackgroundColor(Color.BLUE);
                TextView sms = (TextView)view;
                sms.setTextColor(Color.WHITE);
                String smsElement = ((TextView) view).getText().toString();
                toast(smsElement);
                //deleteSMS(smsElement);
            }
        });

        theClient = new ArrayList<String>();
        clientCursor = sqLiteDatabase.rawQuery("SELECT LOCATION FROM CUSTOMER_DEST_NUMBERS",null);
        theClient.clear();
        theClient.add(emptyString);
        if (clientCursor.moveToFirst())
        {
            do
            {
                theClient.add(clientCursor.getString(clientCursor.getColumnIndex("LOCATION")));
            }while (clientCursor.moveToNext());
        }
        clientDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theClient);
        lv.setAdapter(clientDataAdapter);

         */
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
            Cursor cursor = db.rawQuery("SELECT DISTINCT Id, NUMBER FROM G_CUSTOMER_SMS",null);
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
                    //CONTENT.add(cursor.getString(cursor.getColumnIndex("CONTENT")));
                }while (cursor.moveToNext());
            }
            CustomerSMSToBeDeletedAdapter ca = new CustomerSMSToBeDeletedAdapter(CustomerSMSDeleteActivity.this,Id,NUMBER);
            lv.setAdapter(ca);
            //code to set adapter to populate list
            cursor.close();
        }catch (SQLException sqe){toast(sqe.toString());}
    }//end of displayData()
    public void deleteSMS(final String sms)
    {
        try
        {
            Uri uriSms = Uri.parse("content://sms/inbox");
            int res = mContext.getContentResolver().delete(uriSms, sms, null);
            toast("DELETED MESSAGE FROM "+sms);
        }
        catch (Exception ex)
        {
            toast(ex.toString());
        }
    }
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast
}
