package com.indievoodoo.richard.weightliftingtracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Richard on 22/02/2017.
 */
public class PagerAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm , int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                BenchFrag bf = new BenchFrag();
                return bf;
            case 1:
                RowFrag rf = new RowFrag();
                return rf;
            case 2:
                SquatFrag sf = new SquatFrag();
                return sf;
            default:
                return null;

        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}
