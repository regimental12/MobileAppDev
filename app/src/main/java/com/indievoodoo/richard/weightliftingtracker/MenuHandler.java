/**
 * Created by Richard on 03/03/2017.
 */
package com.indievoodoo.richard.weightliftingtracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MenuHandler extends AppCompatActivity
{
    private static final String TAG = "MenuHandler";

    public static final boolean menuHandler(Activity act , MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.setupMenuID:
                act.startActivity(new Intent(act , SetUpWeights.class));
                return true;
            case R.id.aboutMenuID:
                act.startActivity(new Intent(act , About.class));
                return true;
            case R.id.helpMenuId:
                act.startActivity(new Intent(act , HelpMenu.class));
                return true;
            default:
                return false;
        }
    }
}
