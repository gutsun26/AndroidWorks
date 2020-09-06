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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.web.udni_sms.ChangeNumbersActivity3;
import com.web.udni_sms.R;
import com.web.udni_sms.InfoSettingsActivity;

public class SettingsFragment3 extends Fragment {
    View root;
    ImageButton imageButton3_0;
    TextView home3_self;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings3, container, false);
        imageButton3_0 = root.findViewById(R.id.imageButton3_0);
        home3_self = root.findViewById(R.id.home3_self);

        home3_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumberFunction();
            }
        });

        imageButton3_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumberFunction();
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

    public void changeNumberFunction()
    {
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "3");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity3.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeNumberFunction()

    public void toast(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }//end of toast
}
