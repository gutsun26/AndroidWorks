package com.web.g_smsapp.panel_builder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;
import com.web.g_smsapp.customer_db.CustomerControllerDB;
import com.web.g_smsapp.panel_builder_db.PanelBuilderControllerDB;
import com.web.g_smsapp.panel_builder_db.PanelBuilderSMSReceiverControllerDB;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PanelBuilderRecordsDeleteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    /******************************MODEL*****************************************/
    Context context;
    String numberChosenToDelete;
    int rows;
    /******************************VIEW*****************************************/
    String emptyString="CLICK HERE";
    Spinner dateChoice,clientChoice,choiceSpinner;
    String dateChosen, clientChosen, choiceMade;
    TextView record,choice,numberToBeDeleted, address;
    List<String> theClient, theDate, theChoices;
    ArrayAdapter<String> clientDataAdapter, dateAdapter,choiceAdapter;
    TextView clientString, dateString;
    String dateLiteral="DATE";
    String clientLiteral="CLIENT";
    String clickHere = "CLICK HERE";


    /*******************************CONTROLLER**********************************/
    /*DATABASE API*/
    com.web.g_smsapp.panel_builder_db.PanelBuilderSMSReceiverControllerDB panelBuilderSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    Cursor dateCursor, clientCursor;
    PanelBuilderControllerDB panelBuilderControllerDB;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panelbuilder_record_delete);
        context = this;
        record                  =       (TextView)      findViewById(R.id.record);
        dateString              =       (TextView)      findViewById(R.id.dateString);
        clientString            =       (TextView)      findViewById(R.id.clientString);
        choiceSpinner           =       (Spinner)      findViewById(R.id.choiceSpinner);
        dateChoice              =       (Spinner)       findViewById(R.id.dateChoice);
        clientChoice            =       (Spinner)       findViewById(R.id.clientChoice);
        dateString.setVisibility(View.INVISIBLE);
        clientString.setVisibility(View.INVISIBLE);
        dateChoice.setVisibility(View.INVISIBLE);
        clientChoice.setVisibility(View.INVISIBLE);
        theChoices = new ArrayList<String>();
        theChoices.add(emptyString);
        theChoices.add(dateLiteral);
        theChoices.add(clientLiteral);
        choiceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theChoices);
        choiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceSpinner.setAdapter(choiceAdapter);
        choiceSpinner.setOnItemSelectedListener(this);

        record.setVisibility(View.INVISIBLE);

        panelBuilderSMSReceiverControllerDB = new PanelBuilderSMSReceiverControllerDB(this);
        theSqLiteDatabase= panelBuilderSMSReceiverControllerDB.getReadableDatabase();

        panelBuilderControllerDB = new PanelBuilderControllerDB(this);
        sqLiteDatabase= panelBuilderControllerDB.getReadableDatabase();

        /*EXTRACTING DATE AND SHOWING IT IN DROPDOWN*/
        theDate = new ArrayList<String>();
        //dateCursor = theSqLiteDatabase.rawQuery("SELECT DISTINCT DATE FROM G_PANEL_BUILDER_SMS",null);
        theDate.clear();
        theDate.add(clickHere);
        /*
        if (dateCursor.moveToFirst())
        {
            do
            {
                theDate.add(dateCursor.getString(dateCursor.getColumnIndex("DATE")));
            }while (dateCursor.moveToNext());
        }*/
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theDate);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateChoice.setAdapter(dateAdapter);
        dateChoice.setOnItemSelectedListener(this);


        /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
        theClient = new ArrayList<String>();
        //clientCursor = sqLiteDatabase.rawQuery("SELECT LOCATION FROM PANEL_BUILDER_DEST_NUMBERS",null);
        theClient.clear();
        theClient.add(clickHere);
        /*
        if (clientCursor.moveToFirst())
        {
            do
            {
                theClient.add(clientCursor.getString(clientCursor.getColumnIndex("LOCATION")));
            }while (clientCursor.moveToNext());
        }*/
        clientDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theClient);
        // Drop down layout style - list view with radio button
        clientDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        clientChoice.setAdapter(clientDataAdapter);
        clientChoice.setOnItemSelectedListener(this);

    }//enf of onCreate()

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        choiceMade      = String.valueOf(choiceSpinner.getSelectedItem());
        if(choiceMade.equalsIgnoreCase(dateLiteral))
        {
            record.setVisibility(View.VISIBLE);
            clientString.setVisibility(View.INVISIBLE);
            clientChoice.setVisibility(View.INVISIBLE);
            dateString.setVisibility(View.VISIBLE);
            dateChoice.setVisibility(View.VISIBLE);
            dateChosen      = String.valueOf(dateChoice.getSelectedItem());
            toast(dateChosen);
            panelBuilderSMSReceiverControllerDB = new PanelBuilderSMSReceiverControllerDB(this);
            theSqLiteDatabase= panelBuilderSMSReceiverControllerDB.getReadableDatabase();
            theSqLiteDatabase.execSQL("DELETE FROM G_PANEL_BUILDER_SMS WHERE DATE='"+dateChosen+"'");
            if(dateChosen.equalsIgnoreCase("CLICK TO CHOOSE"))
            {
                record.setVisibility(View.INVISIBLE);
            }
            else
            {
                record.setVisibility(View.VISIBLE);
                record.setText("DELETED RECORDS OF :"+dateChosen);
            }
        }
        if(choiceMade.equalsIgnoreCase(clientLiteral))
        {
            record.setVisibility(View.VISIBLE);
            dateString.setVisibility(View.INVISIBLE);
            dateChoice.setVisibility(View.INVISIBLE);
            clientString.setVisibility(View.VISIBLE);
            clientChoice.setVisibility(View.VISIBLE);
            clientChosen    = String.valueOf(clientChoice.getSelectedItem());
            /*DELETING FROM THE SMS FROM THE PARTICULAR CLIENT*/
            panelBuilderControllerDB = new PanelBuilderControllerDB(this);
            sqLiteDatabase= panelBuilderControllerDB.getReadableDatabase();
            //clientCursor = sqLiteDatabase.rawQuery("SELECT NUMBER FROM PANEL_BUILDER_DEST_NUMBERS WHERE LOCATION='"+clientChosen+"'",null);
            theClient = new ArrayList<String>();
            theClient.clear();
            /*
            if (clientCursor.moveToFirst())
            {
                do
                {
                    theClient.add(clientCursor.getString(clientCursor.getColumnIndex("NUMBER")));
                    numberChosenToDelete = theClient.get(0);
                    numberChosenToDelete = numberChosenToDelete.trim();
                    //toast(numberChosenToDelete);
                }while (clientCursor.moveToNext());
            }*/
            panelBuilderControllerDB = new PanelBuilderControllerDB(this);
            sqLiteDatabase= panelBuilderControllerDB.getReadableDatabase();
            //sqLiteDatabase.execSQL("DELETE FROM G_PANEL_BUILDER_SMS WHERE NUMBER='"+numberChosenToDelete+"'");
            if(clientChosen.equalsIgnoreCase(clickHere))
            {
                record.setVisibility(View.INVISIBLE);
            }
            else
            {
                record.setVisibility(View.VISIBLE);
                record.setText("DELETED ALL SMS FROM :"+clientChosen);
            }
        }
    }//end of onItemSelected()
    public void onNothingSelected(AdapterView<?> parent)
    {

    }//end of onNothingSelected()


    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

}
