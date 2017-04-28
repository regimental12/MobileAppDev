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


// TODO cycle through workouts to send user to the right one.

public class MainActivity extends AppCompatActivity
{
    DBTools db = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStartButtonListner();
        //addSetUPButtonListner();
        addChartButtonListner();

        db.getWritableDatabase();
        db.close();
    }

    private void addChartButtonListner()
    {
        final Context context = this;
        Button button = (Button) findViewById(R.id.Chart_Button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                Intent intent = new Intent(context, ChartActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void addSetUPButtonListner()
    {
        final Context context = this;
        Button button = (Button) findViewById(R.id.setWeightsScreenButtonID);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                Intent intent = new Intent(context, SetUpWeights.class);
                startActivity(intent);
            }
        });
    }*/

    public void addStartButtonListner()
    {

        final Context context = this;
        Button button = (Button) findViewById(R.id.startWorkout);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                Intent intent = new Intent(context, WorkoutOne.class);
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
