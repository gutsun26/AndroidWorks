package com.web.udni_sms.ui.sites;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.web.udni_sms.AddSiteActivity2;
import com.web.udni_sms.DeleteSiteActivity2;
import com.web.udni_sms.InfoGopranActivity;
import com.web.udni_sms.R;
import com.web.udni_sms.InfoSitesActivity;
import com.web.udni_sms.builder_db.BuilderControllerDB;
import com.web.udni_sms.builder_db.BuilderCustomAdapter;

import java.util.ArrayList;

public class SitesFragment2 extends Fragment
{
    //private ImageButton addSite, deleteSite, editSite;
    private Button addSite;
    private TableLayout theTable,tableForm;
    private TableRow tableRow;
    private TextView title,addSiteText,deleteSiteText,deleteSiteName;
    private ImageView imageSite;
    private ClientsViewModel clientsViewModel;
    private ScrollView scrollViewLayout;
    private View root;
    private EditText siteName, siteNumber, siteAddress_1, siteAddress_2;
    private Button regBtn,refreshButton;
    private String getClientName, getClientNumber, getClientAddress;
    private LinearLayout cell;
    private ListView lv;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    String clientChosen=null;;
    Dialog dialog;

    BuilderControllerDB builderControllerDB;
    SQLiteDatabase sqLiteDatabase;
    String query;
    int rowCount;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        clientsViewModel = ViewModelProviders.of(this).get(ClientsViewModel.class);
        root = inflater.inflate(R.layout.fragment_sites2, container, false);
        //setHasOptionsMenu(true);
        title               = root.findViewById(R.id.title);
        tableRow            = root.findViewById(R.id.tableRow);
        addSite             = root.findViewById(R.id.addSite);
        //deleteSite          = root.findViewById(R.id.deleteSite);
        theTable            = root.findViewById(R.id.theTable);
        imageSite           = root.findViewById(R.id.imageSite);
        scrollViewLayout    = root.findViewById(R.id.scrollViewLayout);
        lv                  = root.findViewById(R.id.lstvw);
        addSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndDisplayNewSite();
            }
        });

        /*
        deleteSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSite();
            }
        });*/
        builderControllerDB = new BuilderControllerDB(getContext());
        sqLiteDatabase = builderControllerDB.getReadableDatabase();
        query = "CREATE TABLE IF NOT EXISTS BUILDER_CLIENT_DATA(Id INTEGER PRIMARY KEY AUTOINCREMENT, BUILDER_CLIENT_NAME TEXT, BUILDER_CLIENT_NUMBER TEXT, BUILDER_CLIENT_ADDRESS);";
        sqLiteDatabase.execSQL(query);
        displayData();
        return root;
    }//enf of onCreateView()

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.sites_menu, menu);
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                displayScreenInfo();
                return true;
            case R.id.item2:
                contactGopran();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()
    */
    public void addAndDisplayNewSite()
    {
        /*
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_add_site, (ViewGroup)root.findViewById(R.id.scrollViewLayout));
        siteName        = (EditText)layout.findViewById(R.id.siteName);
        siteNumber      = (EditText)layout.findViewById(R.id.siteNumber);
        siteAddress_1   = (EditText)layout.findViewById(R.id.siteAddress_1);
        //siteAddress_2   = (EditText)layout.findViewById(R.id.siteAddress_2);
        //regBtn          = (Button)layout.findViewById(R.id.regBtn);
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("SITE INFORMATION");
        alertDialogBuilder.setView(layout);
        alertDialogBuilder.setPositiveButton("REGISTER",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                getClientName = siteName.getText().toString();
                getClientNumber = siteNumber.getText().toString();
                getClientAddress = siteAddress_1.getText().toString();// + " " + siteAddress_2.getText().toString();
                if( getClientName.isEmpty() || getClientNumber.isEmpty() || getClientAddress.isEmpty())
                {
                    toast("PLEASE FILL ALL THE FIELDS!");
                }
                else
                {
                    sqLiteDatabase.execSQL("INSERT INTO BUILDER_CLIENT_DATA(BUILDER_CLIENT_NAME,BUILDER_CLIENT_NUMBER,BUILDER_CLIENT_ADDRESS) VALUES('" + getClientName + "','" + getClientNumber + "','" + getClientAddress +"')" );
                    toast("UPDATED THE GIVEN DATA INTO DATABASE");
                    displayData();
                }
            }
        });
        final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
        Intent intent = new Intent(getContext(), AddSiteActivity2.class);
        startActivity(intent);
    }//end of addAndDisplay()

    public void deleteSite()
    {
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("CHOOSE CLIENT TO DELETE");
        ListView listClients     = new ListView(getContext());
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, NAME);
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
                dialog.cancel();
                confirmAndDelete(clientChosen);
            }
        });
        dialog.show();
         */
        Intent intent = new Intent(getContext(), DeleteSiteActivity2.class);
        startActivity(intent);
    }//end of deleteSite()


    public void confirmAndDelete(final String clientChosen)
    {
        sqLiteDatabase.execSQL("DELETE FROM BUILDER_CLIENT_DATA WHERE BUILDER_CLIENT_NAME='"+clientChosen+"'");
        toast("DELETED "+clientChosen);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM BUILDER_CLIENT_DATA",null);
        rowCount = cursor.getCount();
        if(rowCount == 0)
        {
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("TO REFRESH THE LIST THIS WILL EXIT THE APP. DO YOU WANT TO?");
            alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    System.exit(0);
                }
            });
            alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {

                }
            });
            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        this.displayData();
    }
    
    public void displayData()
    {
        try
        {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM BUILDER_CLIENT_DATA",null);
            rowCount = cursor.getCount();
            if(rowCount!=0)
            {
                Id.clear();
                NAME.clear();
                NUMBER.clear();
                if (cursor.moveToFirst())
                {
                    do
                    {
                        Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                        //toast(cursor.getString(cursor.getColumnIndex("Id")));
                        NAME.add(cursor.getString(cursor.getColumnIndex("BUILDER_CLIENT_NAME")));
                    }while (cursor.moveToNext());
                }
                BuilderCustomAdapter ca = new BuilderCustomAdapter(getContext(),Id, NAME);
                lv.setAdapter(ca);
            }
            else
            {
                toast("NO CLIENTS");
            }
        }catch(SQLException sqe){toast(sqe.toString());}
    }

    public void displayScreenInfo()
    {
        Intent intent = new Intent(getContext(), InfoSitesActivity.class);
        startActivity(intent);
    }

    public void contactGopran()
    {
        /*
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                //alertDialog.closeOptionsMenu();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

         */
        Intent intent = new Intent(getContext(), InfoGopranActivity.class);
        startActivity(intent);
    }

    public void toast(String s)
    {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
