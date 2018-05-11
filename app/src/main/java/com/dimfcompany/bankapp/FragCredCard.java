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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.warkiz.widget.IndicatorSeekBar;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class FragCredCard extends Fragment
{

    ExpandableLayout credCartExpand;
    Button showMore,showBanks;

    IndicatorSeekBar credcartDistSeek,credCardSrok,credCardLimit;
    TextView distTV,limitTv,cardSrokTV;

    ScrollView cardScroll;

    CheckBox bezspravok,snyatielgot,cashback,besplatO,dostav,bistrReshenie,nizkPocent;

    String lim;
    int limit=10000;
    int srok=50;





    public static List<ModelCard> cardList;

    public FragCredCard()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cred_cart, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        PageViewActivity currentPVA=(PageViewActivity) getActivity();
        currentPVA.currentCard=this;



        //region ExpandLayout
        cardScroll=(ScrollView)view.findViewById(R.id.credCardScrollV);
        credCartExpand=(ExpandableLayout)view.findViewById(R.id.expandInfoCredCard);
        showMore=(Button)view.findViewById(R.id.moreInfoButtonCredCard);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                credCartExpand.toggle();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        cardScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 450);
            }
        });


        //endregion
        //region CredCardDistSeek
        //credcartDistSeek=(IndicatorSeekBar)view.findViewById(R.id.credCerdDistSeek);
        //distTV=(TextView)view.findViewById(R.id.credCardDistTV);
//        credcartDistSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener()
//        {
//            @Override
//            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
//                distTV.setText(String.valueOf(progressFloat));
//            }
//
//            @Override
//            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
//
//            }
//        });
        //endregion
        //region Seekers
        credCardSrok=(IndicatorSeekBar)view.findViewById(R.id.credCardSrokSeek);
        credCardLimit=(IndicatorSeekBar)view.findViewById(R.id.credCardLimitSeek);

        limitTv=(TextView)view.findViewById(R.id.credCardLimitTV);
        cardSrokTV=(TextView)view.findViewById(R.id.credCardSrokTV);



        //region Limit
        credCardLimit.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
            {

                 if(progress<=60 )
                 {
                     limit = (progress * 5000);

                 }else
                     {
                     limit = (progress * 10000) - 300000;
                     }

                 lim=String.valueOf(limit);
                 if(lim.length()==5)
                 {
                     lim = lim.substring(0, 2) + " " + lim.substring(2, 5);
                     limitTv.setText(lim);
                     return;
                 }
                if(lim.length()==6)
                {
                    lim=lim.substring(0,3)+" "+lim.substring(3,6);
                    limitTv.setText(lim);
                    return;
                }
                if(lim.length()==7)
                {
                    lim=lim.substring(0,1)+" "+lim.substring(1,4)+" "+lim.substring(4,7);
                    limitTv.setText(lim);
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
        //endregion

        credCardSrok.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
            {
                srok=progress*5;
                cardSrokTV.setText((progress*5)+" дней");
//                if (progress%10==1&&(progress!=11 && progress!=111 && progress!=211 && progress!=311  ))
//                {
//                    cardSrokTV.setText(progress+" день");
//                    return;
//                }
//                if (progress%10>=2 && progress%10 <=4 )
//                {
//                    cardSrokTV.setText(progress+" дня");
//                    return;
//                }
//                else cardSrokTV.setText(progress+" д");
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
        //region CheckBoxex
        bezspravok=(CheckBox)view.findViewById(R.id.ChBbezspravok);
        snyatielgot=(CheckBox)view.findViewById(R.id.CHBlgotNalichnie);
        cashback=(CheckBox)view.findViewById(R.id.ChbCashB);
        besplatO=(CheckBox)view.findViewById(R.id.CHBBesplatnoeO);
        dostav=(CheckBox)view.findViewById(R.id.CHBdostavka);
        bistrReshenie=(CheckBox)view.findViewById(R.id.CHBBistroeREsh);
        nizkPocent=(CheckBox)view.findViewById(R.id.CHBlowProcent);

        bezspravok.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        snyatielgot.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        cashback.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        besplatO.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        dostav.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        bistrReshenie.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        nizkPocent.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        //endregion


    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(credCardSrok.getProgress()>=credCardSrok.getMin() && credCardSrok.getProgress()<=credCardSrok.getMax())
        {
            credCardSrok.setProgress(credCardSrok.getProgress());
        }
        if(credCardLimit.getProgress()>=credCardLimit.getMin()&& credCardLimit.getProgress()<=credCardLimit.getMax())
        {
            credCardLimit.setProgress(credCardLimit.getProgress());
        }
    }

    //region ShowButtonVoid
    public void showButton()
    {
        final Button show=(Button)getActivity().findViewById(R.id.showBanksButt);
        show.setOnClickListener(null);
        show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                show.setEnabled(false);
                String query = FormingQuery();
                RequestQueue.RequestFinishedListener finishedListener=new RequestQueue.RequestFinishedListener()
                {
                    @Override
                    public void onRequestFinished(Request request)
                    {
                        show.setEnabled(true);
                        final SpotsDialog sd = PageViewActivity.sd;
                        sd.show();
                        cardList=new ArrayList<>();
                        cardList=LoadCard.cards;
                        if(cardList.size()>0)
                        {
                            final Intent loadShow = new Intent(getActivity(), BanksShow.class);
                            loadShow.putExtra("title", 3);
                            //loadShow.setFlags(loadShow.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);


                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {
                                    sd.dismiss();
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
                                        sd.dismiss();
                                        PageViewActivity.ShowToast(getContext(),"По вашему запросу карты не найдены,попробуйте изменить запрос.");
//                                        DialogCustom dialog= new DialogCustom(getActivity(),"По вашему запросу карты не найдены,попробуйте изменить запрос.");
//                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                                        dialog.show();
//                                        Window window=dialog.getWindow();
//                                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                                    }
                                }, 1600);

                            }
                    }
                };
                LoadCard.GetCards(query,getActivity(),finishedListener);
            }
        });
    }
    //endregion

    //region FormingQuery
    String FormingQuery()
    {
        String query = "Select * from `card` where ";
        if (limit==10000)
        {
            limit=5000;
        }
        String limstr = "(`rublimit` >="+limit+" or `dollimit`>="+((int)limit/60)+" or `eurolimit`>="+((int)limit/75)+")";
        query+=limstr;
        query+=" and `lgotsrok`>="+srok+"";
        if(bezspravok.isChecked())
        {
            query+=" and (`cardpodtverj` = 0 or `cardpodtverj` = 1230 or `cardpodtverj` = 10 or `cardpodtverj` = 20 or `cardpodtverj` = 30 or `cardpodtverj` = 120 or `cardpodtverj` = 130 or `cardpodtverj` = 230)";
        }
        if(snyatielgot.isChecked())
        {
            query+=" and(`lgottype`=2 or `lgottype`=12)";
        }
        if(cashback.isChecked())
        {
            query+=" and `cashback` = 1";
        }
        if (besplatO.isChecked())
        {
            query+=" and (`besplatnoeo` = 1 or `besplatnoeo` = 3)";
        }
        if(dostav.isChecked())
        {
            query+=" and `dostavka`=1";
        }
        if(bistrReshenie.isChecked())
        {
            query+=" and `reshenie`=1";
        }
        if(nizkPocent.isChecked())
        {
            query+=" and ((`stavkarubmin` <20.0 and `stavkarubmin` !=0.0) )";
        }
        Log.e("query",query);
        return query;
    }
    //endregion

}
