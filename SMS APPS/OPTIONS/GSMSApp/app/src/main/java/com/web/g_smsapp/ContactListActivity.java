package com.web.g_smsapp;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ContactListActivity extends AppCompatActivity
{
    ListView listView ;
    ArrayList<String> StoreContacts ;
    ArrayAdapter<String> arrayAdapter ;
    Cursor cursor ;
    String name, phonenumber ;
    public  static final int RequestPermissionCode  = 1 ;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        listView = (ListView)findViewById(R.id.listview1);
        button = (Button)findViewById(R.id.button1);
        StoreContacts = new ArrayList<String>();
        EnableRuntimePermission();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GetContactsIntoArrayList();
                arrayAdapter = new ArrayAdapter<String>(ContactListActivity.this,R.layout.activity_contact_items_listview, R.id.textView, StoreContacts);
                listView.setAdapter(arrayAdapter);
            }
        });
    }//end of onCreate()

    public void GetContactsIntoArrayList()
    {
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
        while (cursor.moveToNext())
        {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            StoreContacts.add(name + " "  + ":" + " " + phonenumber);
        }
        cursor.close();
    }//end of GetContactsIntoArrayList()

    public void EnableRuntimePermission()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ContactListActivity.this,Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(ContactListActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        }
        else
        {
            ActivityCompat.requestPermissions(ContactListActivity.this,new String[]{Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }
    }//end of EnableRuntimePermission()

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult)
    {

        switch (RC)
        {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED)
                {

                    Toast.makeText(ContactListActivity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(ContactListActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }//end of onRequestPermission()
}
