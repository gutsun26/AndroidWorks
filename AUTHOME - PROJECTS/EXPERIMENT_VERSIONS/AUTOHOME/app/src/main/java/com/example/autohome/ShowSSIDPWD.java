package com.example.autohome;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowSSIDPWD extends AppCompatActivity
{
    /*DATABASE RELATED VARIABLES*/
    ListView obj;
    DBCreation mydb;
    /*END OF DATABASE RELATED VARIABLES*/
    String ssid="";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        Toast.makeText(this, "WELCOME",Toast.LENGTH_SHORT).show();
        mydb = new DBCreation(this);

        ArrayList array_list = mydb.getAllCotacts();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        obj = (ListView)findViewById(R.id.listView);
        obj.setAdapter(arrayAdapter);
    }
}
