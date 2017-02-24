package com.indievoodoo.richard.weightliftingtracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RowFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RowFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RowFrag extends Fragment
{
    LineChart lChart1;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RowFrag()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RowFrag.
     */
    //
    public static RowFrag newInstance(String param1, String param2)
    {
        RowFrag fragment = new RowFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.fragment_row, container, false);

        LinearLayout layout1 = (LinearLayout) v.findViewById(R.id.BenchRowLin);
        lChart1 = new LineChart(getActivity().getApplicationContext());


        // add chart to layout1
        layout1.addView(lChart1);

        ViewGroup.LayoutParams params = lChart1.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        lChart1.setGridBackgroundColor(WHITE);
        lChart1.setDrawGridBackground(false);
        addWeights();

        return v;
    }

    private void addWeights()
    {
        DBTools dbtools = new DBTools(getActivity().getApplicationContext());
        ArrayList<Graphresult> graphresults = dbtools.getWeightsForGraph("Row");

        ArrayList<Entry> xVals = new ArrayList<>();

        for (int i = 0 ; i < graphresults.size() ; i++)
        {
            xVals.add(new Entry(i ,graphresults.get(i).getWeight()));
        }

        LineDataSet xDataSet = new LineDataSet(xVals , "Row Weight");
        xDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        xDataSet.setColor(RED);
        xDataSet.setDrawCircles(false);
        xDataSet.setLineWidth(2.0f);

        LineData data = new LineData(xDataSet);

        lChart1.setData(data);

        lChart1.invalidate();
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }
}
