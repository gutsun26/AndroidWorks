package com.web.gatecontrol.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.web.gatecontrol.R;
import com.web.gatecontrol.ScanQR;
import com.web.gatecontrol.db.UserDB;
import com.web.gatecontrol.display.DisplayAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;

public class HomeFragment extends Fragment
{
    UserDB userDB;
    SQLiteDatabase database;
    int rowCount;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> GATE_NAME = new ArrayList<String>();
    private static final int RESULT_OK = -1;
    private HomeViewModel homeViewModel;
    FloatingActionButton btnScanBarcode;
    String theAppID, gateName, serverURI, userName, passWord;
    TextView theAppIDText, gateNameText, serverURIText, userNameText, passWordText;
    String query = "SELECT * FROM GATES";
    ListView lv;
    Button click;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        userDB = new UserDB(getContext());
        database = userDB.getReadableDatabase();
        lv = root.findViewById(R.id.lstvw);
        //theAppIDText = root.findViewById(R.id.theAppIDText);
        //gateNameText = root.findViewById(R.id.gateNameText);
        /*serverURIText = root.findViewById(R.id.serverURIText);
        userNameText = root.findViewById(R.id.userNameText);
        passWordText = root.findViewById(R.id.passWordText);*/
        //click = root.findViewById(R.id.click);
        getData();
        /*
        userDB = new UserDB(getContext());
        database = userDB.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            ID.clear();
            GATE_NAME.clear();
            if (cursor.moveToFirst())
            {
                do
                {
                    /*theAppIDText.setText(cursor.getString(cursor.getColumnIndex("APP_ID")));
                    gateNameText.setText(cursor.getString(cursor.getColumnIndex("GATE_NAME")));
                    serverURIText.setText(cursor.getString(cursor.getColumnIndex("SERVER")));
                    userNameText.setText(cursor.getString(cursor.getColumnIndex("USERNAME")));
                    passWordText.setText(cursor.getString(cursor.getColumnIndex("PASSWORD")));*
                    ID.add(cursor.getString(cursor.getColumnIndex("Id")));
                    GATE_NAME.add(cursor.getString(cursor.getColumnIndex("GATE_NAME")));
                } while (cursor.moveToNext());
            }
            DisplayAdapter da = new DisplayAdapter(getContext(),ID,GATE_NAME);
            lv.setAdapter(da);
        }
        /*
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                //deleteTable();
            }
        });
         */
        return root;
    }
    public void getData()
    {
        Cursor cursor = database.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    //theAppIDText.setText(cursor.getString(cursor.getColumnIndex("APP_ID")));
                    gateNameText.setText(cursor.getString(cursor.getColumnIndex("GATE_NAME")));/*
                    serverURIText.setText(cursor.getString(cursor.getColumnIndex("SERVER")));
                    userNameText.setText(cursor.getString(cursor.getColumnIndex("USERNAME")));
                    passWordText.setText(cursor.getString(cursor.getColumnIndex("PASSWORD")));*/
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                deleteTable();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteTable()
    {
        database.execSQL("DROP TABLE GATES");
        toast("Deleted GATES table");
    }
    public void toast(String s)
    {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }
}