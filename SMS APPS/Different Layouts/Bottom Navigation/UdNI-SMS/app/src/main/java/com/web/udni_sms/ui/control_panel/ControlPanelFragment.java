package com.web.udni_sms.ui.control_panel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.web.udni_sms.R;
import com.web.udni_sms.SendSMSActivity1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlPanelFragment extends Fragment
{


    private ControlPanelViewModel controlPanelViewModel;
    /******************************VIEW*****************************************/
    ImageButton imageButton1, imageButton2, imageButton3,smsBtn;
    Button CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20,CMD21;
    Button insertTheDestNumberBtn,phoneBookButton;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView dateTimeDisplay,timerText,adminText;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    int timerValue = 6000;
    String adminString="ADMIN";
    List<String> DATA = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        controlPanelViewModel = ViewModelProviders.of(this).get(ControlPanelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_controlpanel, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        controlPanelViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        //setHasOptionsMenu(true);
        /*MAPPING UI ELEMENTS*/
        imageButton1            =   root.findViewById(R.id.imageButton1);
        imageButton2            =   root.findViewById(R.id.imageButton2);
        imageButton3            =   root.findViewById(R.id.imageButton3);
        destNumber              =   root.findViewById(R.id.destNumber);
        DATA.clear();
        DATA.add("9892012016");
        DATA.add("9820841315");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, DATA);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        destNumber.setAdapter(dataAdapter);

        FloatingActionButton fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToActivity();
            }
        });
        return root;
    }
    public void goToActivity()
    {
        Intent intent;
        intent = new Intent(getActivity(), SendSMSActivity1.class);
        startActivity(intent);
    }

    public void sendSMS()
    {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("TO SEND SMS CLICK THE BUTTON");
        alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                goToActivity();
            }
        });
        final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void sendCMD7()
    {
        Toast.makeText(getContext(), "SENDING SMS", Toast.LENGTH_SHORT).show();
    }
    public void changeNumberFunction(View view)
    {
        Toast.makeText(getContext(), "CHANGING NUMBER", Toast.LENGTH_SHORT).show();
    }
}