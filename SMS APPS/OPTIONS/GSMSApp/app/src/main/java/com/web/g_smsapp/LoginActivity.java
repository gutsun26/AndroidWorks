package com.web.g_smsapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.web.g_smsapp.admin.AdminActivityControl;
import com.web.g_smsapp.admin_db.AdminControllerDB;
import com.web.g_smsapp.customer.CustomerActivityControl;
import com.web.g_smsapp.panel_builder.PanelBuilderActivityControl;

public class LoginActivity extends AppCompatActivity
{
    /******************************MODEL****************************************/
    final String adminString                = "G";
    final String adminPassword              = "G";
    final String panelBuilderString1        = "P";
    final String panelBuilderPassword1      = "P";
    final String customerString             = "C";
    final String customerPassword           = "C";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;

    /******************************VIEW*****************************************/
    EditText editUserID, editPassword;
    Button loginBtn,userBtn;
    CheckBox checkBox;
    SharedPreferences permissionStatus;
    String stringUserID, stringPassword;
    ProgressDialog progressDialog;
    private boolean sentToSettings = false;
    private static final int PERMISSION_REQUEST_CODE = 1;

    /*******************************CONTROLLER**********************************/
    /*DATABASE API*/
    AdminControllerDB db =new AdminControllerDB(this);
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkBox            = (CheckBox)findViewById(R.id.checkBox);
        editPassword        = (EditText)findViewById(R.id.editPassword);
        editUserID          = (EditText)findViewById(R.id.editUserID);
        editUserID.setFocusable(true);
        editUserID.setText("");
        editPassword.setText("");
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
            toast("THERE IS SPACE IN ID OR PASSWORD! RETYPE WITHOUT SPACE");
        }
        else
        {
            if(stringUserID.equalsIgnoreCase(adminString) && stringPassword.equalsIgnoreCase(adminPassword))
            {
                Intent intent = new Intent(this, AdminActivityControl.class);
                startActivity(intent);
            }
            else if(stringUserID.equalsIgnoreCase(panelBuilderString1) && stringPassword.equalsIgnoreCase(panelBuilderPassword1))
            {
                Intent intent = new Intent(this, PanelBuilderActivityControl.class);
                startActivity(intent);
            }
            else if(stringUserID.equalsIgnoreCase(customerString) && stringPassword.equalsIgnoreCase(customerPassword))
            {
                Intent intent = new Intent(this, CustomerActivityControl.class);
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
    public void createUser()
    {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
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

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
