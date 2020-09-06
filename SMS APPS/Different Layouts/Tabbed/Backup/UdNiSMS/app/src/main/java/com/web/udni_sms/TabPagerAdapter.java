package com.web.udni_sms;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter
{
    public int tabCount;
    public TabPagerAdapter(FragmentManager fm, int numberOfTabs)
    {
        super(fm);
        this.tabCount = numberOfTabs;
    }
    @Override
    public Fragment getItem(int position)
    {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment1 (defined as a static inner class below).
        switch (position)
        {
            case 0:
                Tab1Fragment tab1 = new Tab1Fragment();
                return tab1;
            case 1:
                Tab2Fragment tab2 = new Tab2Fragment();
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return tabCount;
    }
}
