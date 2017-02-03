package com.indievoodoo.richard.weightliftingtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.util.HashMap;

/**
 * Created by Richard on 03/02/2017.
 */


public class WorkoutOne extends Activity implements View.OnClickListener
{
    DBTools dbTools = new DBTools(this);

    HashMap<Integer , Integer> setsDone = new HashMap<Integer, Integer>();
    HashMap<Integer , String> Amrap = new HashMap<Integer, String>();
    TextView benchWeight, rowWeight, squatWeight;

    //private String m_Text = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_one);

        setsDone.put(R.id.bench1 , 0);
        setsDone.put(R.id.bench2 , 0);
        setsDone.put(R.id.bench3 , 0);

        setsDone.put(R.id.row1 , 0);
        setsDone.put(R.id.row2 , 0);
        setsDone.put(R.id.row3 , 0);

        setsDone.put(R.id.squat1 , 0);
        setsDone.put(R.id.squat2 , 0);
        setsDone.put(R.id.squat3 , 0);

        Amrap.put(R.id.bench3 , "0");
        Amrap.put(R.id.row3 , "0");
        Amrap.put(R.id.squat3 , "0");

        ((Button)findViewById(R.id.bench1)).setOnClickListener(this);
        ((Button)findViewById(R.id.bench2)).setOnClickListener(this);
        ((Button)findViewById(R.id.bench3)).setOnClickListener(this);
        ((Button)findViewById(R.id.row1)).setOnClickListener(this);
        ((Button)findViewById(R.id.row2)).setOnClickListener(this);
        ((Button)findViewById(R.id.row3)).setOnClickListener(this);
        ((Button)findViewById(R.id.squat1)).setOnClickListener(this);
        ((Button)findViewById(R.id.squat2)).setOnClickListener(this);
        ((Button)findViewById(R.id.squat3)).setOnClickListener(this);
        ((Button)findViewById(R.id.finishButtonID)).setOnClickListener(this);


        benchWeight  = (TextView) findViewById(R.id.benchWeight);
        rowWeight  = (TextView) findViewById(R.id.rowWeight);
        squatWeight  = (TextView) findViewById(R.id.squatWeight);

    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bench1:
                setDone(view.getId());
                break;

            case R.id.bench2:
                setDone(view.getId());
                break;

            case R.id.bench3:
                setDone(view.getId());
                AMRAP(view.getId());
                break;

            case R.id.row1:
                setDone(view.getId());
                break;

            case R.id.row2:
                setDone(view.getId());
                break;

            case R.id.row3:
                setDone(view.getId());
                AMRAP(view.getId());
                break;

            case R.id.squat1:
                setDone(view.getId());
                break;

            case R.id.squat2:
                setDone(view.getId());
                break;

            case R.id.squat3:
                setDone(view.getId());
                AMRAP(view.getId());
                break;


        }
    }

    private void setDone(final int Key)
    {
        setsDone.put(Key , 1);
        //Log.d("Button clicked" , Key + " " +  setsDone.get(Key).toString());
    }

    private void AMRAP(final int Key)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Number Of Reps");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        input.setText(Amrap.get(Key));
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Amrap.put(Key , input.getText().toString() );
                //m_Text = ;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
