package com.indievoodoo.richard.weightliftingtracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity
{

    RelativeLayout layout;
    LineChart lChart;

    private float[] yData = { 5, 10, 15, 30, 40 };
    private String[] xData = { "Sony", "Huawei", "LG", "Apple", "Samsung" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        // setup layout and chart
        layout = (RelativeLayout) findViewById(R.id.Chart_layout);
        lChart = new LineChart(this);

        // add chart to layout
        layout.addView(lChart);
        layout.setBackgroundColor(Color.parseColor("#d3d3d3"));

        // config chart
        //lChart.setDescription();

        ViewGroup.LayoutParams params = lChart.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        addValues();

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

        lChart.setData(data);

        lChart.invalidate();
    }


}
