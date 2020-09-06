package com.web.udni_sms;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.web.udni_sms.ui.main.SectionsPagerAdapter1;
import com.web.udni_sms.ui.main.SectionsPagerAdapter2;

public class MainActivity extends AppCompatActivity //implements TabLayout.OnTabSelectedListener
{
    String getUserID=null, getPWD=null;
    TabLayout tabs, tabss;
    SectionsPagerAdapter1 sectionsPagerAdapter1;
    SectionsPagerAdapter2 sectionsPagerAdapter2;
    ViewPager viewPager1;
    ViewPager viewPager2;
    TabLayout.Tab firstTab, secondTab, thirdTab;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Adding Page Adapter*/

        sectionsPagerAdapter1 = new SectionsPagerAdapter1(this, getSupportFragmentManager());
        viewPager1 = findViewById(R.id.view_pager1);
        viewPager1.setAdapter(sectionsPagerAdapter1);

        tabs = findViewById(R.id.tabs);
        //tabs.setupWithViewPager(viewPager1);
        firstTab = tabs.newTab();
        firstTab.setText(R.string.tab_text_1);
        tabs.addTab(firstTab);

        secondTab = tabs.newTab();
        secondTab.setText(R.string.tab_text_2);
        tabs.addTab(secondTab);
        /*
        thirdTab = tabs.newTab();
        thirdTab.setText(R.string.tab_text_3);
        tabs.addTab(thirdTab);
        */
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                        break;
                    case 1:
                        sectionsPagerAdapter2 = new SectionsPagerAdapter2(getApplicationContext(), getSupportFragmentManager());
                        viewPager2 = findViewById(R.id.view_pager2);
                        viewPager2.setAdapter(sectionsPagerAdapter2);
                        viewPager1.removeAllViews();
                        tabs.removeTab(firstTab);
                        break;
                    default:
                            break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send SMS", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}