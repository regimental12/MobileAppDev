package com.indievoodoo.richard.weightliftingtracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Richard on 03/02/2017.
 */

// TODO add the second day weights to setup.
// TODO add info on how to use the setup screen. only use to setup first time. or to make adjustments to weights.

public class SetUpWeights extends AppCompatActivity
{
    DBTools dbTools = new DBTools(this);

    EditText Bench;
    EditText Row;
    EditText Squat;

    EditText OHP;
    EditText Chin;
    EditText Dead;

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

        OHP = (EditText) findViewById(R.id.OHPTextInputID);
        Chin = (EditText) findViewById(R.id.ChinUPTextInputID);
        Dead = (EditText) findViewById(R.id.DeadLiftTextInputID);

        ArrayList<Float> results = new ArrayList<>();
        ArrayList<Float> results2 = new ArrayList<>();

        results = dbTools.getweights1();
        results2 = dbTools.getweights2();

        if (!results.isEmpty() )
        {
            Bench.setText(results.get(0).toString());
            Row.setText(results.get(1).toString());
            Squat.setText(results.get(2).toString());

            OHP.setText(results2.get(0).toString());
            Chin.setText(results2.get(1).toString());
            Dead.setText(results2.get(2).toString());
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
                dbTools.setweights(Bench.getText().toString() , Row.getText().toString() , Squat.getText().toString());
                dbTools.setweights2(OHP.getText().toString() , Chin.getText().toString() , Dead.getText().toString());

                CharSequence text = "Weights Updated";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return MenuHandler.menuHandler(this , item);
    }
}
