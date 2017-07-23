package com.indievoodoo.richard.weightliftingtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
{
    DBTools db = new DBTools(this);
    int i = 0;
    private AdView mAdView;

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

        Context context = getApplicationContext();
        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        i = settings.getInt("Workout No." , 0);
        MobileAds.initialize(this , "ca-app-pub-3940256099942544/6300978111");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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

    public void addStartButtonListner()
    {
        final Context context = this;
        Button button = (Button) findViewById(R.id.startWorkout); // TODO switch workouts
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                if(i == 1)
                {
                    Intent intent = new Intent(context, WorkoutTwo.class);
                    startActivity(intent);
                }
                else if(i == 2 || i == 0)
                {
                    Intent intent = new Intent(context, WorkoutOne.class);
                    startActivity(intent);
                }

                /*Intent intent = new Intent(context, WorkoutTwo.class);
                startActivity(intent);*/
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
