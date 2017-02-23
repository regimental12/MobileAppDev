package com.indievoodoo.richard.weightliftingtracker;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;


// TODO get data from database.
// TODO format graph better.
// TODO Create tabed layout1 for each graph.

public class ChartActivity extends AppCompatActivity
{
    LinearLayout layout1;
    LineChart lChart1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        android.app.ActionBar toolbar = getActionBar(); //(Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Bench"));
        tabLayout.addTab(tabLayout.newTab().setText("Row"));
        tabLayout.addTab(tabLayout.newTab().setText("Squat"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // setup layout1 and chart
        /*layout1 = (LinearLayout) findViewById(R.id.BenchFragLin);
        lChart1 = new LineChart(this);


        // add chart to layout1
        layout1.addView(lChart1);

        ViewGroup.LayoutParams params = lChart1.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        lChart1.setGridBackgroundColor(WHITE);
        lChart1.setDrawGridBackground(false);
        addWeights();*/

    }

   /* private void addWeights()
    {
        DBTools dbtools = new DBTools(this);
        ArrayList<Graphresult> graphresults = dbtools.getWeightsForGraph();

        ArrayList<Entry> xVals = new ArrayList<>();

        for (int i = 0 ; i < graphresults.size() ; i++)
        {
            xVals.add(new Entry(i ,graphresults.get(i).getWeight()));
        }

        LineDataSet xDataSet = new LineDataSet(xVals , "Weight");
        xDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        xDataSet.setColor(RED);
        xDataSet.setDrawCircles(false);
        xDataSet.setLineWidth(2.0f);

        LineData data = new LineData(xDataSet);

        lChart1.setData(data);

        lChart1.invalidate();
    }*/





}
