package com.dimfcompany.bankapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.warkiz.widget.IndicatorSeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class VkladFrag extends Fragment implements AdapterView.OnItemSelectedListener{

    Button moreInfo;
    ExpandableRelativeLayout expandMore;
    Spinner mySpinner;
    ArrayAdapter<String> adapter;

    IndicatorSeekBar procentSeek,distSeek;
    TextView procentTV,distTV;


    public VkladFrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                return inflater.inflate(R.layout.fragment_vklad, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    //region Spinner
        mySpinner=(Spinner)view.findViewById(R.id.mySpinner);
        adapter=new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,getResources().getStringArray(R.array.valueSpinner));
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ((TextView)parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.header2));
                TextView testTest=(TextView) view;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    //endregion
    //region ExpandLayout
        moreInfo=(Button)view.findViewById(R.id.moreInfoButton);
        expandMore=(ExpandableRelativeLayout)view.findViewById(R.id.expandInfo);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandMore.toggle();
            }
        });
    //endregion
    //region SeekValuListeners
        distSeek=(IndicatorSeekBar)view.findViewById(R.id.distSeek);
        distTV=(TextView)view.findViewById(R.id.distTV);
        distSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                distTV.setText(String.valueOf(progressFloat));
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        procentSeek=(IndicatorSeekBar)view.findViewById(R.id.procentSeek);
        procentTV=(TextView)view.findViewById(R.id.procentTB) ;
        procentSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
           {
               procentTV.setText(String.valueOf(progressFloat));
           }

           @Override
           public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {

           }

           @Override
           public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

           }

           @Override
           public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

           }
        });
        //endregion

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
