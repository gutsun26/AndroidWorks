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

import com.web.udni_sms.ChangeNumbersActivity1;
import com.web.udni_sms.R;
import com.web.udni_sms.InfoSettingsActivity;

public class SettingsFragment1 extends Fragment //implements IntOnBackPressed
{
    private HomeViewModel homeViewModel;
    ImageButton imageButton1_1, imageButton1_2, imageButton1_3;
    TextView home1_sites, home1_pbldr, home1_self;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings1, container, false);
        setHasOptionsMenu(true);
        home1_sites     =root.findViewById(R.id.home1_sites);
        home1_pbldr     =root.findViewById(R.id.home1_pbldr);
        home1_self      =root.findViewById(R.id.home1_self);
        imageButton1_1 = root.findViewById(R.id.imageButton1_1);
        imageButton1_2 = root.findViewById(R.id.imageButton1_2);
        imageButton1_3 = root.findViewById(R.id.imageButton1_3);

        home1_sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeElevatorCompanyNumbers();
            }
        });
        home1_pbldr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePanelBuilderCompanyNumbers();
            }
        });
        home1_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelfNumbers();
            }
        });
        imageButton1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeElevatorCompanyNumbers();
            }
        });

        imageButton1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePanelBuilderCompanyNumbers();
            }
        });

        imageButton1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelfNumbers();
            }
        });
        return root;
    }

    public void changeElevatorCompanyNumbers()
    {
        //toast("CHANGE ELEVATOR COMPANY NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "1");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity1.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeElevatorCompanyNumbers()

    public void changePanelBuilderCompanyNumbers()
    {
        //toast("CHANGE PANEL BUILDER COMPANY NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "2");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity1.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changePanelBuilderCompanyNumbers()

    public void changeSelfNumbers()
    {
        //toast("CHANGE SELF NUMBERS");
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "3");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity1.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeSelfNumbers()
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

    public void toast(String s)
    {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
}