package com.indievoodoo.richard.weightliftingtracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Richard on 03/02/2017.
 */

public class SetUpWeights extends AppCompatActivity
{
    DBTools dbTools = new DBTools(this);

    EditText Bench;
    EditText Row;
    EditText Squat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupscreen);

        AddButtonListner();


        dbTools.getWritableDatabase();

        Bench = (EditText) findViewById(R.id.benchTextInputID);
        Row = (EditText) findViewById(R.id.rowTextInputID);
        Squat = (EditText) findViewById(R.id.squatTextInputID);

        ArrayList<Float> results = new ArrayList<>();

        results = dbTools.getweights();

        if (!results.isEmpty() )
        {
            Bench.setText(results.get(0).toString());
            Row.setText(results.get(1).toString());
            Squat.setText(results.get(2).toString());
        }

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

                dbTools.setweights(Bench.getText().toString() , Row.getText().toString() , Squat.getText().toString());
            }
        });
    }

    private void addWeights()
    {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }
}
