package com.dimfcompany.bankapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.warkiz.widget.IndicatorSeekBar;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class FragCredit extends Fragment
{




    IndicatorSeekBar creditSeek,creditTimeSeek;
    TextView creditSum,creditTimeTV;
    Button showMore,showBanks;
    ExpandableRelativeLayout expandLayout;

    int currentSum;
    int currentSrok=1;
    int stajPosition;
    int podrtverjPosition;

    CheckBox tolkopass,bezOpezpechenia,refinans;

    ScrollView creditScroll;

    Spinner creditSpinner;
    Spinner podtverjSpinner;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> podtverjadapter;

    public static List<ModelCredit> creditList;

    public FragCredit()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_credit, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        PageViewActivity pageV=(PageViewActivity)getActivity();
        pageV.currentCredit=this;

        //region CheckBox Inizialization
        tolkopass=(CheckBox)view.findViewById(R.id.CHBPassport);
        bezOpezpechenia=(CheckBox)view.findViewById(R.id.CBobsepech);
        refinans=(CheckBox)view.findViewById(R.id.CBRefinans);

        tolkopass.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        bezOpezpechenia.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        refinans.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        //endregion
        //region SeekBars
        creditSeek=(IndicatorSeekBar)view.findViewById(R.id.creditSumSeek);
        creditSum=(TextView)view.findViewById(R.id.creditSumTV);

        creditTimeSeek=(IndicatorSeekBar)view.findViewById(R.id.creditTimeSeek);
        creditTimeTV=(TextView)view.findViewById(R.id.creditTimeTV);

        creditSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
            {
                if(progress<=17)
                {
                    currentSum=progress;
                    int prog=(--progress*25000)+100000;
                    if(currentSum==1)
                    {
                        currentSum=0;
                    }
                    else {
                        currentSum = prog;
                    }
                    String textToset = String.valueOf(prog);
                    String first = textToset.substring(0,3);
                    String second = textToset.substring(3,6);
                    String myString = first+" "+second;
                    creditSum.setText(myString);
                return;
                }

                if((progress>17)&&(progress<49))
                {
                    int prog=(--progress*50000)-350000;
                    currentSum=prog;
                    String textToset=String.valueOf(prog);
                    if(textToset.length()==6)
                    {
                        String first = textToset.substring(0,3);
                        String second = textToset.substring(3,6);
                        String myString = first+" "+second;
                        creditSum.setText(myString);
                        return;
                    }
                    if (textToset.length()>6)
                    {
                        String first = textToset.substring(0,1);
                        String second = textToset.substring(1,4);
                        String third=textToset.substring(4,7);
                        String myString = first+" "+second+" "+third;
                        creditSum.setText(myString);
                        return;
                    }


                }
                if(progress>=50)
                {
                    int prog=(--progress*100000)-2900000;
                    currentSum=prog;
                    String textToset = String.valueOf(prog);
                    String first = textToset.substring(0,1);
                    String second = textToset.substring(1,4);
                    String third=textToset.substring(4,7);
                    String myString = first+" "+second+" "+third;
                    creditSum.setText(myString);
                    return;

                }

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
        creditTimeSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
            {
                currentSrok=progress;
                if(currentSrok==1)
                {
                    creditTimeTV.setText("12 месяцев");
                    return;
                }

                else if(currentSrok==2)
                {
                    creditTimeTV.setText("18 месяцев");
                    return;
                }
                else if(currentSrok>=3 && currentSrok<=5)
                {
                    creditTimeTV.setText(--currentSrok+" года");
                    return;
                }
                else if (currentSrok>5)
                {
                    creditTimeTV.setText(--currentSrok+" лет");
                    return;
                }
//                if (progress==21 ||progress==31 ||progress==41 ||progress==51)
//                {
//                    creditTimeTV.setText(String.valueOf(progress) + " месяц");
//                    return;
//                }
//                if(((progress>21)&&(progress<25))||((progress>31)&&(progress<35))||((progress>41)&&(progress<45))||((progress>41)&&(progress<45)))
//                {
//                    creditTimeTV.setText(String.valueOf(progress) + " месяца");
//                    return;
//                }
//                else creditTimeTV.setText(String.valueOf(progress) + " месяцев");

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
        //region Expanable
        showMore=(Button)view.findViewById(R.id.moreInfoButton);
        expandLayout=(ExpandableRelativeLayout)view.findViewById(R.id.expandInfo);

        creditScroll=(ScrollView)view.findViewById(R.id.creditScroll);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandLayout.toggle();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        creditScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 450);

            }
        });
        //endregion
        //region Spinner
        creditSpinner=(Spinner)view.findViewById(R.id.spinnerCredit);
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,getResources().getStringArray(R.array.srokRaboty));
        creditSpinner.setAdapter(adapter);

        creditSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                stajPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        podtverjSpinner=(Spinner)view.findViewById(R.id.spinnerPodt);
        podtverjadapter=new ArrayAdapter<>(getActivity(),R.layout.spinner_item,getResources().getStringArray(R.array.podtverjdenie));
        podtverjSpinner.setAdapter(podtverjadapter);

        podtverjSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                podrtverjPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("!!!!!!!!!!!!!","RESUMEDDDDDD");
        if((creditSeek.getProgress())>=(creditSeek.getMin()) && creditSeek.getProgress()<=creditSeek.getMax())
        {
            creditSeek.setProgress(creditSeek.getProgress());
        }
        if(creditTimeSeek.getProgress()>=creditTimeSeek.getMin() && creditTimeSeek.getProgress()<=creditTimeSeek.getMax())
        {
            creditTimeSeek.setProgress(creditTimeSeek.getProgress());
        }
    }

    //region ButtonLoadCredits
    public void ShowButton()
    {

        showBanks=(Button)getActivity().findViewById(R.id.showBanksButt);
        showBanks.setOnClickListener(null);
        showBanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showBanks.setEnabled(false);
                final SpotsDialog sdstatic;
                sdstatic=PageViewActivity.sd;
                sdstatic.show();
                String query = FormingQuery();
                Log.e("!!!!QUERY",query);
                RequestQueue.RequestFinishedListener finishListener = new RequestQueue.RequestFinishedListener()
                {
                    @Override
                    public void onRequestFinished(Request request)
                    {
                        showBanks.setEnabled(true);
                        creditList=new ArrayList<>();
                        creditList=LoadCredit.credits;
                        if(creditList.size()>0)
                        {
                            final Intent loadShow = new Intent(getActivity(), BanksShow.class);
                            loadShow.putExtra("title", 2);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {
                                    sdstatic.dismiss();
                                    startActivity(loadShow);
                                }
                            }, 1200);


                        }
                        else if(PageViewActivity.isInternetOn(getContext()))
                            {
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        sdstatic.dismiss();
                                        PageViewActivity.ShowToast(getContext(),"По вашему запросу кредиты не найдены,попробуйте изменить запрос.");
//                                        DialogCustom dialog= new DialogCustom(getActivity(),"По вашему запросу кредиты не найдены,попробуйте изменить запрос.");
//                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                                        dialog.show();
//                                        Window window=dialog.getWindow();
//                                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

                                    }
                                }, 600);

                            }
                    }
                };
                LoadCredit.GetCredits(query,getContext(),finishListener);
            }
        });
    }
    //endregion

    //region Formingquery
    String FormingQuery()
    {
        String query = "Select * from `credit` where";
        int sum =currentSum;

        int srok;
        String sumplus=" (`rubsymmacredit` >="+sum+" or `dolsymmacredit` >="+(int)sum/65+" or `eurosymmacredit` >="+(int)sum/75+")";
        query+=sumplus;

        if (currentSrok==1 || currentSrok==0)
        {
            srok=10;
        }
        else if(currentSrok==2)
        {
            srok=18;
        }
        else srok=(currentSrok)*12;
        String srokplus=" and (`rubsrokcredit` >="+srok+" or `dolsrokcredit` >="+srok+" or `eurosrokcredit` >="+srok+")";
        query+=srokplus;
        if(podrtverjPosition!=0)
        {
            switch (podrtverjPosition)
            {
                case 1:
                    String oneplus = " and `podtverjdenie` = 0";
                    query+=oneplus;
                    break;
                case 2:
                    String twoplus = " and (`podtverjdenie` = 1 or  `podtverjdenie` = 12 or  `podtverjdenie` = 123 or  `podtverjdenie` = 0)";
                    query+=twoplus;
                    break;
                case 3:
                    String threeplus = " and (`podtverjdenie` = 2 or  `podtverjdenie` = 12 or  `podtverjdenie` = 123 or  `podtverjdenie` = 0)";
                    query+=threeplus;
                    break;
                case 4:
                    String fourplus = " and (`podtverjdenie` = 3 or  `podtverjdenie` = 13 or  `podtverjdenie` = 123 or  `podtverjdenie` = 0)";
                    query+=fourplus;
                    break;
            }
        }

        if(stajPosition!=0)
        {
            switch (stajPosition)
            {
                case 1:
                    String oneplus=" and `staj` <= 1";
                    query+=oneplus;
                    break;
                case  2:
                    String twoplus =" and `staj` <= 2";
                    query+=twoplus;
                    break;
                case 3:
                    String threeplus =" and `staj` <= 3";
                    query+=threeplus;
                    break;
                case 4:
                    String fourplus =" and `staj` = 0";
                    query+=fourplus;
                    break;
            }
        }

        String zalog;
        if(bezOpezpechenia.isChecked())
        {
            zalog=" and `obespechenie` = 0";
            query+=zalog;
        }
       String refin;
        if(refinans.isChecked())
        {
            refin=" and `refinansirovanie` = 1";
            query+=refin;
        }
        String pass;
        if(tolkopass.isChecked())
        {
            pass=" and `tolkopass` = 1";
            query+=pass;
        }
        Log.e("!!!QUERY!!!",query);
        return query;
    }
    //endregion
}
