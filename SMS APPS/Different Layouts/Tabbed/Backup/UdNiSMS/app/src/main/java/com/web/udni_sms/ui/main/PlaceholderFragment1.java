package com.web.udni_sms.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.web.udni_sms.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment1 extends Fragment
{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment1 newInstance(int index)
    {
        PlaceholderFragment1 fragment = new PlaceholderFragment1();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View root1 = inflater.inflate(R.layout.fragment_main, container, false);
        //final TextView textView = root1.findViewById(R.id.section_label);
        final EditText editUserID = root1.findViewById(R.id.editUserID);
        final EditText editPassword = root1.findViewById(R.id.editPassword);
        final CheckBox checkBox = root1.findViewById(R.id.checkBox);
        final Button loginBtn = root1.findViewById(R.id.loginBtn);
        //toast("here");
        return root1;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        toast("here");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                return true;
            case R.id.item2:
                return true;
            case R.id.item3:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()
    public void toast(String s)
    {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}