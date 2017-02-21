package com.indievoodoo.richard.weightliftingtracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


// TODO get data from database.
// TODO format graph better.
// TODO Create tabed layout1 for each graph.

public class ChartActivity extends AppCompatActivity
{

    LinearLayout layout1;
    LinearLayout layout3;
    LinearLayout layout2;
    LineChart lChart1 , lChart2 , lChart3;

    private float[] yData = { 5, 10, 15, 30, 40 };
    private String[] xData = { "Sony", "Huawei", "LG", "Apple", "Samsung" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        // setup layout1 and chart
        layout1 = (LinearLayout) findViewById(R.id.tab1);
        layout2 = (LinearLayout) findViewById(R.id.tab2);
        layout3 = (LinearLayout) findViewById(R.id.tab3);
        lChart1 = new LineChart(this);
        lChart2 = new LineChart(this);
        lChart3 = new LineChart(this);

        // add chart to layout1
        layout1.addView(lChart1);
        layout1.setBackgroundColor(Color.parseColor("#d3d3d3"));

        /*layout2.addView(lChart2);
        layout2.setBackgroundColor(Color.parseColor("#d3d3d3"));

        layout3.addView(lChart3);
        layout3.setBackgroundColor(Color.parseColor("#d3d3d3"));*/

        // config chart
        //lChart1.setDescription();

        ViewGroup.LayoutParams params2 = lChart1.getLayoutParams();
        params2.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params2.width = ViewGroup.LayoutParams.MATCH_PARENT;

        /*ViewGroup.LayoutParams params1 = lChart2.getLayoutParams();
        params1.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params1.width = ViewGroup.LayoutParams.MATCH_PARENT;

        ViewGroup.LayoutParams params3 = lChart3.getLayoutParams();
        params3.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params3.width = ViewGroup.LayoutParams.MATCH_PARENT;*/


        addWeights();

    }

    private void addWeights()
    {
        DBTools dbtools = new DBTools(this);
        ArrayList<Graphresult> graphresults = new ArrayList<>();
        graphresults = dbtools.getWeightsForGraph();

        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<Entry> xVals = new ArrayList<>();

        for (int i = 0 ; i < graphresults.size() ; i++)
        {
            yVals.add(new Entry(graphresults.get(i).getWorkoutNo() , i));
            xVals.add(new Entry(graphresults.get(i).getWeight() , i));
        }

        LineDataSet xDataSet = new LineDataSet(xVals , "WorkOuT No.");
        //xDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet yDataSet = new LineDataSet(yVals , "Weight.");

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(xDataSet);
        dataSets.add(yDataSet);

        LineData data = new LineData(dataSets);

        lChart1.setData(data);

        lChart1.invalidate();
    }

    private void addValues()
    {
        ArrayList<Entry> yVals = new ArrayList<>();

        for (int i = 0; i < yData.length; i++)
            yVals.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);



        LineDataSet dataSet = new LineDataSet(yVals , "Market Share");

        LineData data = new LineData(dataSet);

        lChart1.setData(data);
        lChart2.setData(data);
        lChart3.setData(data);

        lChart1.invalidate();
        lChart2.invalidate();
        lChart3.invalidate();

    }


}
