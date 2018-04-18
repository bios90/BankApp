package com.dimfcompany.bankapp;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class SortCard extends AppCompatActivity
{

    CheckBox poStavke,poLimity,poSroky,poStoimosti,poBankam,poName;
    Button showButton;

    static List<ModelCard> cardsToSort;
    static List<ModelCard> cardsSorted;

    static int choosedChB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_card);

        showButton=(Button)findViewById(R.id.buttShowCard);

        poStavke=(CheckBox)findViewById(R.id.chPoStavkeCard);
        poLimity=(CheckBox)findViewById(R.id.chPoLimitCard);
        poSroky=(CheckBox)findViewById(R.id.chPoSrokyCard);
        poStoimosti=(CheckBox)findViewById(R.id.chPoStoimosCard);
        poBankam=(CheckBox)findViewById(R.id.chPoBankamCard);
        poName=(CheckBox)findViewById(R.id.chPoNameCard);

        poStavke.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        poLimity.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        poSroky.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        poStoimosti.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        poBankam.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        poName.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));

        //region CheckBoxClicks
        poName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                poStavke.setChecked(false);
                poLimity.setChecked(false);
                poSroky.setChecked(false);
                poBankam.setChecked(false);
                poStoimosti.setChecked(false);

                poName.setEnabled(false);

                poStavke.setEnabled(true);
                poLimity.setEnabled(true);
                poSroky.setEnabled(true);
                poBankam.setEnabled(true);
                poStoimosti.setEnabled(true);
            }
        });

        poStavke.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                poName.setChecked(false);
                poLimity.setChecked(false);
                poSroky.setChecked(false);
                poBankam.setChecked(false);
                poStoimosti.setChecked(false);

                poStavke.setEnabled(false);

                poName.setEnabled(true);
                poLimity.setEnabled(true);
                poSroky.setEnabled(true);
                poBankam.setEnabled(true);
                poStoimosti.setEnabled(true);

            }
        });
        poLimity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                poName.setChecked(false);
                poStavke.setChecked(false);
                poSroky.setChecked(false);
                poBankam.setChecked(false);
                poStoimosti.setChecked(false);

                poLimity.setEnabled(false);

                poName.setEnabled(true);
                poStavke.setEnabled(true);
                poSroky.setEnabled(true);
                poBankam.setEnabled(true);
                poStoimosti.setEnabled(true);
            }
        });
        poSroky.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                poName.setChecked(false);
                poLimity.setChecked(false);
                poStavke.setChecked(false);
                poBankam.setChecked(false);
                poStoimosti.setChecked(false);

                poSroky.setEnabled(false);

                poName.setEnabled(true);
                poLimity.setEnabled(true);
                poStavke.setEnabled(true);
                poBankam.setEnabled(true);
                poStoimosti.setEnabled(true);
            }
        });
        poStoimosti.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                poName.setChecked(false);
                poLimity.setChecked(false);
                poSroky.setChecked(false);
                poStavke.setChecked(false);
                poBankam.setChecked(false);

                poStoimosti.setEnabled(false);

                poName.setEnabled(true);
                poLimity.setEnabled(true);
                poSroky.setEnabled(true);
                poStavke.setEnabled(true);
                poBankam.setEnabled(true);

            }
        });
        poBankam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                poName.setChecked(false);
                poLimity.setChecked(false);
                poSroky.setChecked(false);
                poStoimosti.setChecked(false);
                poStavke.setChecked(false);

                poBankam.setEnabled(false);

                poName.setEnabled(true);
                poLimity.setEnabled(true);
                poSroky.setEnabled(true);
                poStoimosti.setEnabled(true);
                poStavke.setEnabled(true);
            }
        });
        //endregion

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cardsToSort=new ArrayList<>();
                cardsToSort=BanksShow.currentCards;

                int firstid = cardsToSort.get(0).getId();
                String query = "Select * from `card` where (`id`="+firstid;
                for(ModelCard card : cardsToSort)
                {
                    String addstr= " or `id`="+card.getId();
                    query+=addstr;
                }
                query+=")";

                if(poStavke.isChecked()){
                    query +=" ORDER BY `stavkarubmin`";
                    choosedChB=1;
                }
                if(poLimity.isChecked()){
                    query +=" ORDER BY `rublimit` DESC";
                    choosedChB=2;
                }
                if(poSroky.isChecked()){
                    query +=" ORDER BY `lgotsrok` DESC";
                    choosedChB=3;
                }
                if(poStoimosti.isChecked()){
                    query +=" ORDER BY `stoimostobslj`";
                    choosedChB=4;
                }
                if(poBankam.isChecked()){
                    query +=" ORDER BY `id_bank`";
                    choosedChB=5;
                }
                if(poName.isChecked()){
                    query +=" ORDER BY `cardname`";
                    choosedChB=6;
                }



                RequestQueue.RequestFinishedListener finishedListener=new RequestQueue.RequestFinishedListener() {
                    @Override
                    public void onRequestFinished(Request request)
                    {
                        cardsSorted=LoadCard.cards;
                        if(cardsSorted.size()==0)
                        {
                            finish();
//                            Intent loadStart= new Intent(SortCard.this,PageViewActivity.class);
//                            loadStart.putExtra("error",1);
//                            loadStart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(loadStart);
                        }
                        else
                            {
                            Intent loadShow = new Intent(SortCard.this, BanksShow.class);
                            loadShow.putExtra("title", 33);
                            loadShow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(loadShow);
                            }
                    }
                };
                LoadCard.GetCards(query,SortCard.this,finishedListener);
            }
        });

        if (choosedChB!=0)
        {
            switch (choosedChB)
            {
                case 1:
                    poStavke.performClick();
                    break;
                case 2:
                    poLimity.performClick();
                    break;
                case 3:
                    poSroky.performClick();
                    break;
                case 4:
                    poStoimosti.performClick();
                    break;
                case 5:
                    poBankam.performClick();
                    break;
                case 6:
                    poName.performClick();
                    break;
            }
        }
    }
}
