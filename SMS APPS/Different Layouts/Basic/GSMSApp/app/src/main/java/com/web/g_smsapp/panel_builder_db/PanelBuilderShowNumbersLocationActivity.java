package com.web.g_smsapp.panel_builder_db;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;

import java.util.ArrayList;
import java.util.List;

public class PanelBuilderShowNumbersLocationActivity extends AppCompatActivity
{
    Context context;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> LOCATION = new ArrayList<String>();

    List<String> theClient;
    Cursor clientCursor;
    PanelBuilderControllerDB panelBuilderControllerDB;
    SQLiteDatabase sqLiteDatabase;
    ArrayAdapter<String> clientDataAdapter;

    ListView lv;
    String clientChosen=null;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata_panel_builder_listview);
        lv = (ListView) findViewById(R.id.lstvw);
        panelBuilderControllerDB = new PanelBuilderControllerDB(this);
        sqLiteDatabase= panelBuilderControllerDB.getReadableDatabase();
    }//end of onCreate()
    @Override
    protected void onResume()
    {
        displayData();
        super.onResume();
    }
    private void displayData()
    {
        db = panelBuilderControllerDB.getReadableDatabase();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM PANEL_BUILDER_DEST_NUMBERS",null);
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
            PanelBuilderCustomAdapter ca = new PanelBuilderCustomAdapter(PanelBuilderShowNumbersLocationActivity.this,Id,NUMBER,LOCATION);
            lv.setAdapter(ca);
            //code to set adapter to populate list
            cursor.close();
        }catch (SQLException sqe){toast(sqe.toString());}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.panel_builder_delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                deleteClients();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()

    public void deleteClients()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CHOOSE CLIENT TO DELETE");
        ListView listClients     = new ListView(this);
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, LOCATION);
        listClients.setAdapter(modeAdapter);
        builder.setView(listClients);
        dialog = builder.create();
        listClients.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setBackgroundColor(Color.RED);
                TextView clientName = (TextView)view;
                clientName.setTextColor(Color.WHITE);
                clientChosen = ((TextView) view).getText().toString();
                confirmAndDelete(clientChosen);
            }
        });
        dialog.show();
    }//end of deleteClients()


    public void confirmAndDelete(final String clientChosen)
    {
        sqLiteDatabase.execSQL("DELETE FROM PANEL_BUILDER_DEST_NUMBERS WHERE LOCATION='"+clientChosen+"'");
        toast("DELETED "+clientChosen);
        toastLong("THE DISPLAY WILL BE UPDATED ON REATTEMPT OF CHOOSING \"SHOW CLIENTS\"");
    }

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void toastLong(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
