package com.indievoodoo.richard.weightliftingtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import static android.graphics.Color.WHITE;

/**
 * Created by Richard on 03/02/2017.
 */

// TODO dont auto add weight on non completion of sets.
public class WorkoutOne extends AppCompatActivity implements View.OnClickListener
{
    DBTools dbTools = new DBTools(this);

    HashMap<String , Integer> setsDone = new HashMap<String, Integer>();
    HashMap<String , String> Amrap = new HashMap<String, String>();
    HashMap<String, Float> Weights = new HashMap<>();
    TextView benchWeight, rowWeight, squatWeight;

    public void firstTime()
    {
        final String PREFS_NAME = "MyPrefsFile";
        Context context = getApplicationContext();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        if (settings.getBoolean("my_first_time", true))
        {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();

        }
    }


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

        final String PREFS_NAME = "MyPrefsFile";
        Context context = getApplicationContext();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        if (settings.getBoolean("my_first_time", true))
        {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // first time task
            if (!textVeiwRes.isEmpty())
            {
                benchWeight.setText(textVeiwRes.get(0).toString());
                rowWeight.setText(textVeiwRes.get(1).toString());
                squatWeight.setText(textVeiwRes.get(2).toString());
            }

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
        else
        {
            float a = (float) (2.5 + textVeiwRes.get(0));
            float b = (float) (2.5 + textVeiwRes.get(1));
            float c = (float) (2.5 + textVeiwRes.get(2));

            benchWeight.setText(String.valueOf(a));
            rowWeight.setText(String.valueOf(b));
            squatWeight.setText(String.valueOf(c));
        }


    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bench1:
                setDone("Bench1");
                if (setsDone.get("Bench1") == 1)
                {
                    countDown();
                }
                break;

            case R.id.bench2:
                setDone("Bench2");
                if (setsDone.get("Bench2") == 1)
                {
                    countDown();
                }
                break;

            case R.id.bench3:
                setDone("Bench3");
                if (setsDone.get("Bench3") == 1)
                {
                    countDown();
                    AMRAP("Bench3");
                }
                break;

            case R.id.row1:
                setDone("Row1");
                if (setsDone.get("Row1") == 1)
                {
                    countDown();
                }
                break;

            case R.id.row2:
                setDone("Row2");
                if (setsDone.get("Row2") == 1)
                {
                    countDown();
                }
                break;

            case R.id.row3:
                setDone("Row3");
                if (setsDone.get("Row3") == 1)
                {
                    countDown();
                    AMRAP("Row3");
                }
                break;

            case R.id.squat1:
                setDone("Squat1");
                if (setsDone.get("Squat1") == 1)
                {
                    countDown();
                }
                break;

            case R.id.squat2:
                setDone("Squat2");
                if (setsDone.get("Squat2") == 1)
                {
                    countDown();
                }
                break;

            case R.id.squat3:
                setDone("Squat3");
                if (setsDone.get("Squat3") == 1)
                {
                    countDown();
                    AMRAP("Squat3");
                }
                break;

            case R.id.finishButtonID:
                FinishButton();
                break;
        }
    }

    public void countDown()
    {
        final Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout) , "" , 93000 ).setAction("CANCEL" ,new View.OnClickListener() // TODO Get rest time from setup screen?
                {
                    @Override
                    public void onClick(View v) { }
                });

        snackbar.setActionTextColor(WHITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        final TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        snackbar.show();

        new CountDownTimer(90000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                textView.setText("REST: " + millisUntilFinished / 1000);

            }

            public void onFinish()
            {
                //Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout) , "DO YA NEXT SET, YA LAZY FUCK!", Snackbar.LENGTH_SHORT );
                //snackbar.show();
                textView.setText("\"DO YA NEXT SET, YA LAZY FUCK!");


            }
        }.start();
    }

    private void FinishButton()
    {
        // send info to database.
        Weights.put("Bench" , Float.parseFloat(benchWeight.getText().toString()));
        Weights.put("Row" , Float.parseFloat(rowWeight.getText().toString()));
        Weights.put("Squat" , Float.parseFloat(squatWeight.getText().toString()));
        dbTools.addWorkOut(setsDone, Amrap , Weights);

        Context context = getApplicationContext();
        CharSequence text = "Good Job!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text , duration);
        toast.show();

        firstTime();
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    private void setDone(final String Key)
    {
        if(setsDone.get(Key) == 0)
        {
            setsDone.put(Key, 1);
        }
        else
        {
            setsDone.put(Key, 0);
        }

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
