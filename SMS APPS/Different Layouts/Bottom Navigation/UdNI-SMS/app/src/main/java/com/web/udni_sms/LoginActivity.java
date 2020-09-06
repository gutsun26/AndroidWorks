package com.web.udni_sms;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    /******************************MODEL****************************************/
    final String adminString                = "G";
    final String adminPassword              = "G";
    final String panelBuilderString        = "P";
    final String panelBuilderPassword      = "P";
    final String customerString             = "C";
    final String customerPassword           = "C";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
    String theKey = "KEY";
    String adminKey = "1";
    String builderKey = "2";
    String customerKey = "3";

    /******************************VIEW*****************************************/
    EditText editUserID, editPassword;
    Button loginBtn,userBtn;
    CheckBox checkBox;
    SharedPreferences permissionStatus;
    String stringUserID, stringPassword;
    ProgressDialog progressDialog;
    private boolean sentToSettings = false;
    private static final int PERMISSION_REQUEST_CODE = 1;
    TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        signIn              = (TextView) findViewById(R.id.signIn);
        checkBox            = (CheckBox)findViewById(R.id.checkBox);
        editPassword        = (EditText)findViewById(R.id.editPassword);
        editUserID          = (EditText)findViewById(R.id.editUserID);
        signIn.setEnabled(false);
        editUserID.setFocusable(true);

        editUserID.setText(null);
        editPassword.setText(null);
        checkBox.setChecked(false);
        loginBtn            = (Button)findViewById(R.id.loginBtn);
        //userBtn             = (Button)findViewById(R.id.userBtn);
        //Very important, otherwise, App gets installed but screen keeps blinking
        permissionStatus = getSharedPreferences("permissionStatus", Context.MODE_PRIVATE);
        checkForSmsPermission();
        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getLoginDetails();
            }
        });
    }

    public void getLoginDetails()
    {
        stringUserID        = editUserID.getText().toString();
        stringPassword      = editPassword.getText().toString();
        if(stringUserID.contains(" ") || stringPassword.contains(" "))
        {
            toast("There is space in ID or Password, please retype without spaces");
        }
        else
        {
            if(stringUserID.equalsIgnoreCase(adminString) && stringPassword.equalsIgnoreCase(adminPassword))
            {
                Bundle choiceConfig = new Bundle();
                choiceConfig.putString(theKey,adminKey);
                Intent intent = new Intent(this, MainActivity1.class);
                editUserID.setText(" ");
                editPassword.setText(" ");
                intent.putExtras(choiceConfig);
                startActivity(intent);
            }
            else if(stringUserID.equalsIgnoreCase(panelBuilderString) && stringPassword.equalsIgnoreCase(panelBuilderPassword))
            {
                //Bundle choiceConfig = new Bundle();
                //choiceConfig.putString(theKey,builderKey);
                Intent intent = new Intent(this, MainActivity2.class);
                //intent.putExtras(choiceConfig);
                editUserID.setText(" ");
                editPassword.setText(" ");
                startActivity(intent);
            }
            else if(stringUserID.equalsIgnoreCase(customerString) && stringPassword.equalsIgnoreCase(customerPassword))
            {
                //Bundle choiceConfig = new Bundle();
                //choiceConfig.putString(theKey,customerKey);
                Intent intent = new Intent(this, MainActivity3.class);
                //intent.putExtras(choiceConfig);
                editUserID.setText(" ");
                editPassword.setText(" ");
                startActivity(intent);
            }
            else if(stringUserID.isEmpty() || stringPassword.isEmpty())
            {
                toast("ID/PASSWORD CANNOT BE EMPTY!");
            }
            else
            {
                toast("NOT THE RIGHT ID/PASSWORD!");
            }
        }
    }

    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId())
        {
            case R.id.checkBox:
                if (!checked)
                {
                    editPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
                else
                {
                    editPassword.setTransformationMethod(null);
                }
                break;
        }
    }

    private void checkForSmsPermission()
    {
        /*CHECKING AND REQUESTING FOR SENDIN SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

        /*CHECKING AND REQUESTING FOR RECEIVING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
        }

        /*CHECKING AND REQUESTING FOR READING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},MY_PERMISSIONS_REQUEST_READ_SMS);
        }

    }//end of checkForSmsPermission()
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                deleteRecords();
                return true;
            case R.id.item2:
                showRecords();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()
    */
    public void deleteRecords()
    {
        UserDB userDB = new UserDB(this);
        SQLiteDatabase database = userDB.getReadableDatabase();
        Cursor cursor = database.rawQuery("DROP TABLE USER_DATA ",null);
        toast("DATA OF USER DELETED");
    }//end of deleteRecords()

    public void showRecords()
    {
        int rowCount;
        UserDB userDB = new UserDB(this);
        SQLiteDatabase database = userDB.getReadableDatabase();
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("USER RECORD");

        List<String> list = new ArrayList<String>();
        try
        {
            Cursor cursor = database.rawQuery("SELECT * FROM USER_DATA ",null);
            rowCount = cursor.getCount();
            if(rowCount == 0)
            {
                toast("NO USER RECORDS");
            }
            else
            {
                list.clear();
                if(cursor.moveToFirst())
                {
                    do
                    {
                        list.add("NAME:"+cursor.getString(cursor.getColumnIndex("NAME"))+"\n"+
                                "COMPANY:"+cursor.getString(cursor.getColumnIndex("COMPANY"))+"\n"+
                                "EMAIL:"+cursor.getString(cursor.getColumnIndex("EMAIL"))+"\n"+
                                "MOBILE1:"+cursor.getString(cursor.getColumnIndex("MOBILE1"))+"\n"+
                                "MOBILE2:"+cursor.getString(cursor.getColumnIndex("MOBILE2"))+"\n"+
                                "WHATSAPP NUMBER:"+cursor.getString(cursor.getColumnIndex("WHATSAPPNUM"))+"\n"+
                                "ADDRESS:"+cursor.getString(cursor.getColumnIndex("ADDRESS"))
                        );
                    }while(cursor.moveToNext());
                }

            }
        }catch(SQLException sqle){toast(sqle.toString());}
        ListView listOfUserData     = new ListView(this);
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listOfUserData.setAdapter(modeAdapter);
        alertDialogBuilder.setView(listOfUserData);
        final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialogBuilder.setPositiveButton("EXIT",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                alertDialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
