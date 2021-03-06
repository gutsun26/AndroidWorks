package com.web.udni_sms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity
{
    String theChosen=null;
    static final String adminChoice   = "1";
    static final String builderChoice   = "2";
    static final String companyChoice      = "3";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theChosen                   = theChoice.getString("KEY");
        if(theChosen.equalsIgnoreCase(adminChoice))
        {
            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    //R.id.navigation_home,  R.id.navigation_controlpanel, R.id.navigation_clients)
                    R.id.navigation_home,  R.id.navigation_clients)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);
        }
    }//end of onCreate()
    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
