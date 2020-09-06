package com.web.udni_sms.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.web.udni_sms.ChangeNumbersActivity2;
import com.web.udni_sms.R;
import com.web.udni_sms.InfoSettingsActivity;

public class SettingsFragment2 extends Fragment //implements IntOnBackPressed
{
    private HomeViewModel homeViewModel;
    ImageButton imageButton2_1, imageButton2_2;
    TextView home2_sites, home1_pbldr, home2_self;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings2, container, false);
        imageButton2_1 = root.findViewById(R.id.imageButton2_1);
        imageButton2_2 = root.findViewById(R.id.imageButton2_2);
        home2_sites     =root.findViewById(R.id.home2_sites);
        home2_self      =root.findViewById(R.id.home2_self);

        home2_sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            changeElevatorCompanyNumbers();
            }
        });

        home2_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelfNumbers();
            }
        });
        imageButton2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeElevatorCompanyNumbers();
            }
        });

        imageButton2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelfNumbers();
            }
        });
        setHasOptionsMenu(true);
        return root;
    }
    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.settings_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                displayScreenInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()
    */
    public void displayScreenInfo()
    {
        Intent intent = new Intent(getContext(), InfoSettingsActivity.class);
        startActivity(intent);
    }

    public void changeElevatorCompanyNumbers()
    {
        //toast("CHANGE ELEVATOR COMPANY NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "1");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity2.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeElevatorCompanyNumbers()

    public void changeSelfNumbers()
    {
        //toast("CHANGE SELF NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "3");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity2.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeSelfNumbers()

    public void toast(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }//end of toast
}