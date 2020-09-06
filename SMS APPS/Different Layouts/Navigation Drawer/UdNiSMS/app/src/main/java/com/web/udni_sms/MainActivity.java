package com.web.udni_sms;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        checkBox            = (CheckBox)findViewById(R.id.checkBox);
        editPassword        = (EditText)findViewById(R.id.editPassword);
        editUserID          = (EditText)findViewById(R.id.editUserID);
        editUserID.setFocusable(true);
        editUserID.setText("");
        editPassword.setText("");
        checkBox.setChecked(false);
        loginBtn            = (Button)findViewById(R.id.loginBtn);
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
        */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Add Site", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }//end of onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }//end of onCreateOptionsMenu

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }//end of onSupportNavigateUp()

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
                //Intent intent = new Intent(this, AdminControlActivity.class);
                //startActivity(intent);
            }
            else if(stringUserID.equalsIgnoreCase(panelBuilderString1) && stringPassword.equalsIgnoreCase(panelBuilderPassword1))
            {
                //Intent intent = new Intent(this, PanelBuilderControlActivity.class);
                //startActivity(intent);
            }
            else if(stringUserID.equalsIgnoreCase(customerString) && stringPassword.equalsIgnoreCase(customerPassword))
            {
                //Intent intent = new Intent(this, CustomerControlActivity.class);
                //startActivity(intent);
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
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
}//end of MainActivity
