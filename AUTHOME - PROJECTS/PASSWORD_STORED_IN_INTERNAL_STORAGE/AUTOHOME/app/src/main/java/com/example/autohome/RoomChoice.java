package com.example.autohome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class RoomChoice extends AppCompatActivity
{
    RadioButton livingRoom, bedRoom;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomchoice);
        livingRoom = findViewById(R.id.livingRoom);
        bedRoom = findViewById(R.id.bedRoom);

        livingRoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bedRoom.setActivated(false);
                goToLivingRoom();
            }
        });

        bedRoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                livingRoom.setActivated(false);
                goToBedRoom();
            }
        });
    }

    public void goToLivingRoom()
    {
        Bundle extras = new Bundle();
        //Adding key value pairs to this bundle
        //there are quite a lot data types you can store in a bundle
        extras.putString("ROOM_CHOICE","1");
        Intent scanAutoHome = new Intent(this, ScanAutoHome.class);
        scanAutoHome.putExtras(extras);
        startActivity(scanAutoHome);
    }

    public void goToBedRoom()
    {
        Bundle extras = new Bundle();
        //Adding key value pairs to this bundle
        //there are quite a lot data types you can store in a bundle
        extras.putString("ROOM_CHOICE","2");
        Intent scanAutoHome = new Intent(this, ScanAutoHome.class);
        scanAutoHome.putExtras(extras);
        startActivity(scanAutoHome);
    }
}
