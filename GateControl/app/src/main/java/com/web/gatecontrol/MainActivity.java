package com.web.gatecontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.web.gatecontrol.db.UserDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity
{
    UserDB userDB;
    SQLiteDatabase database;
    //String appTable = "CREATE TABLE IF NOT EXISTS APP(Id INTEGER PRIMARY KEY AUTOINCREMENT, APP_ID TEXT);";
    String gateTable = "CREATE TABLE IF NOT EXISTS GATES(Id INTEGER PRIMARY KEY AUTOINCREMENT, APP_ID TEXT, GATE_NAME TEXT, " +
            "SERVER TEXT,USERNAME TEXT, PASSWORD TEXT);";
    String decodeData="";
    int rowCount;
    String theAppID, gateName, serverURI, userName, passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDB = new UserDB(this);
        database = userDB.getReadableDatabase();
        Intent data = getIntent();
        Bundle allData = data.getExtras();
        decodeData = allData.getString("allData");
        if(decodeData.isEmpty())
        {
            try
            {
                toast("At least ONE Gate exists");
            }
            catch(NullPointerException npe)
            {
                toast(npe.toString());
            }
        }
        else
        {
            String[] arrOfStrz = decodeData.split("-", 0);
            theAppID    = arrOfStrz[0];
            gateName    = arrOfStrz[1];
            serverURI   = arrOfStrz[2];
            userName    = arrOfStrz[3];
            passWord    = arrOfStrz[4];
            database.execSQL("INSERT INTO GATES(APP_ID, GATE_NAME, SERVER, USERNAME, PASSWORD) " +
                    "VALUES('"+theAppID+"','"+ gateName+"','"+serverURI+"','"+userName+"','"+passWord+"')");
            //database.execSQL("INSERT INTO GATES(GATE_NAME, APP_ID) " +"VALUES('"+gateName+"','"+theAppID+"')");
            Cursor cursor = database.rawQuery("SELECT * FROM GATES",null);
            rowCount = cursor.getCount();
            toast(String.valueOf(rowCount));
        }


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}
