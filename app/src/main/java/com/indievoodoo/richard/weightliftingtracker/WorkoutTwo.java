package com.indievoodoo.richard.weightliftingtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.graphics.Color.WHITE;

/**
 * Created by Richard on 28/04/2017.
 */

public class WorkoutTwo extends AppCompatActivity implements View.OnClickListener
{
    DBTools dbTools = new DBTools(this);

    HashMap<String , Integer> setsDone = new HashMap<String, Integer>();
    HashMap<String , String> Amrap = new HashMap<String, String>();
    HashMap<String, Float> Weights = new HashMap<>();
    TextView OHPWeight, ChinUpWeight, DeadLiftWeight;

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
        setContentView(R.layout.workout_two);

        setsDone.put("OHP1" , 0);
        setsDone.put("OHP2" , 0);
        setsDone.put("OHP3" , 0);

        setsDone.put("Chin1" , 0);
        setsDone.put("Chin2" , 0);
        setsDone.put("Chin3" , 0);

        setsDone.put("DeadLift1" , 0);

        Amrap.put("OHPAMRAP" , "0");
        Amrap.put("ChinAMRAP" , "0");
        Amrap.put("DeadLiftAMRAP" , "0");

        ((Button)findViewById(R.id.OHP1)).setOnClickListener(this);
        ((Button)findViewById(R.id.OHP2)).setOnClickListener(this);
        ((Button)findViewById(R.id.OHP3)).setOnClickListener(this);
        ((Button)findViewById(R.id.Chin1)).setOnClickListener(this);
        ((Button)findViewById(R.id.Chin2)).setOnClickListener(this);
        ((Button)findViewById(R.id.Chin3)).setOnClickListener(this);
        ((Button)findViewById(R.id.DeadLift1)).setOnClickListener(this);

        ((Button)findViewById(R.id.finishButtonID)).setOnClickListener(this);


        OHPWeight = (TextView) findViewById(R.id.OHPWeightID);
        ChinUpWeight = (TextView) findViewById(R.id.ChinUPWeightID);
        DeadLiftWeight = (TextView) findViewById(R.id.DeadLiftWeightID);

        ArrayList<Float> textVeiwRes = new ArrayList<>();
        textVeiwRes = dbTools.getweights2();

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
                OHPWeight.setText(textVeiwRes.get(0).toString());
                ChinUpWeight.setText(textVeiwRes.get(1).toString());
                DeadLiftWeight.setText(textVeiwRes.get(2).toString());
            }

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
        else
        {
            float a = (float) (2.5 + textVeiwRes.get(0));
            float b = (float) (2.5 + textVeiwRes.get(1));
            float c = (float) (2.5 + textVeiwRes.get(2));

            OHPWeight.setText(String.valueOf(a));
            ChinUpWeight.setText(String.valueOf(b));
            DeadLiftWeight.setText(String.valueOf(c));
        }
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.OHP1:
                setDone("OHP1");
                if (setsDone.get("OHP1") == 1)
                {
                    countDown();
                }
                break;

            case R.id.OHP2:
                setDone("OHP2");
                if (setsDone.get("OHP2") == 1)
                {
                    countDown();
                }
                break;

            case R.id.OHP3:
                setDone("OHP3");
                if (setsDone.get("OHP3") == 1)
                {
                    countDown();
                    AMRAP("OHP3");
                }
                break;

            case R.id.Chin1:
                setDone("Chin1");
                if (setsDone.get("Chin1") == 1)
                {
                    countDown();
                }
                break;

            case R.id.Chin2:
                setDone("Chin2");
                if (setsDone.get("Chin2") == 1)
                {
                    countDown();
                }
                break;

            case R.id.Chin3:
                setDone("Chin3");
                if (setsDone.get("Chin3") == 1)
                {
                    countDown();
                    AMRAP("Chin3");
                }
                break;

            case R.id.DeadLift1:
                setDone("DeadLift1");
                if (setsDone.get("DeadLift1") == 1)
                {
                    countDown();
                    AMRAP("DeadLift1");
                }
                break;

            case R.id.finishButtonID:
                FinishButton();
                break;
        }
    }
    public void countDown()
    {
        final Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout) , "" , 93000 ).setAction("CANCEL" ,new View.OnClickListener()
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
        Weights.put("OHP" , Float.parseFloat(OHPWeight.getText().toString()));
        Weights.put("Chin" , Float.parseFloat(ChinUpWeight.getText().toString()));
        Weights.put("DeadLift" , Float.parseFloat(DeadLiftWeight.getText().toString()));
        dbTools.addWorkOut2(setsDone, Amrap , Weights);

        Context context = getApplicationContext();

        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        int i = settings.getInt("Workout No." , 0);

        if(i == 0 || i == 1)
        {
            editor.putInt("Workout No." , 2).commit();
        }

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
