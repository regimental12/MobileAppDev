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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Richard on 03/02/2017.
 */


public class WorkoutOne extends Activity implements View.OnClickListener
{
    DBTools dbTools = new DBTools(this);

    HashMap<String , Integer> setsDone = new HashMap<String, Integer>();
    HashMap<String , String> Amrap = new HashMap<String, String>();
    HashMap<String, Float> Weights = new HashMap<>();
    TextView benchWeight, rowWeight, squatWeight;

    //private String m_Text = "";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_one);

        setsDone.put("Bench1" , 0);
        setsDone.put("Bench2" , 0);
        setsDone.put("Bench3" , 0);

        setsDone.put("Row1" , 0);
        setsDone.put("Row2" , 0);
        setsDone.put("Row3" , 0);

        setsDone.put("Squat1" , 0);
        setsDone.put("Squat2" , 0);
        setsDone.put("Squat3" , 0);

        Amrap.put("BenchAMRAP" , "0");
        Amrap.put("RowAMRAP" , "0");
        Amrap.put("SquatAMRAP" , "0");

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

        ArrayList<Float> textVeiwRes = new ArrayList<>();
        textVeiwRes = dbTools.getweights();

        if (!textVeiwRes.isEmpty())
        {
            benchWeight.setText(textVeiwRes.get(0).toString());
            rowWeight.setText(textVeiwRes.get(1).toString());
            squatWeight.setText(textVeiwRes.get(2).toString());
        }

    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bench1:
                setDone("Bench1");
                break;

            case R.id.bench2:
                setDone("Bench2");
                break;

            case R.id.bench3:
                setDone("bench3");
                AMRAP("Bench3");
                break;

            case R.id.row1:
                setDone("Row1");
                break;

            case R.id.row2:
                setDone("Row2");
                break;

            case R.id.row3:
                setDone("Row3");
                AMRAP("Row3");
                break;

            case R.id.squat1:
                setDone("Squat1");
                break;

            case R.id.squat2:
                setDone("Squat2");
                break;

            case R.id.squat3:
                setDone("Squat3");
                AMRAP("Squat3");
                break;

            case R.id.finishButtonID:
                FinishButton();
                break;
        }
    }

    private void FinishButton()
    {
        // send info to database.
        Weights.put("Bench" , Float.parseFloat(benchWeight.getText().toString()));
        Weights.put("Row" , Float.parseFloat(rowWeight.getText().toString()));
        Weights.put("Squat" , Float.parseFloat(squatWeight.getText().toString()));
        dbTools.addWorkOut(setsDone, Amrap , Weights);

        // TODO signal success and send user back to main screen.
    }

    private void setDone(final String Key)
    {
        setsDone.put(Key , 1);
        //Log.d("Button clicked" , Key + " " +  setsDone.get(Key).toString());
    }

    private void AMRAP(final String Key)
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
