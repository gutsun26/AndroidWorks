package com.example.autohome;

import android.widget.Toast;

import java.util.ArrayList;

public class WifiSSID
{
    public String option;
    public WifiSSID(String theOption)
    {
        this.option=theOption;
    }
    public String getSSID()
    {
        return option;
    }
}
