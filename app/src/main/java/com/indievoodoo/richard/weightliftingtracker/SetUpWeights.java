package com.indievoodoo.richard.weightliftingtracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Richard on 03/02/2017.
 */

public class SetUpWeights extends AppCompatActivity
{
    DBTools dbTools;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupscreen);

        AddButtonListner();

        dbTools = new DBTools(this);
        dbTools.getWritableDatabase();
    }

    private void AddButtonListner()
    {
        final Context context = this;
        Button button = (Button) findViewById(R.id.setWeightButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                // TODO set weight in database. display toast on success?
                //dbTools.testDB();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }
}
