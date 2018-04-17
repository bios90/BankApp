package com.dimfcompany.bankapp;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class SortCredit extends AppCompatActivity {

    CheckBox creditStavka, creditSrok, creditSymma,creditBanks,creditName;

    static int choosedChB;

    Button showCredit;

    static List<ModelCredit> creditsToSort;
    static List<ModelCredit> creditsSorted;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_credit);

        //region Inizialization
        creditStavka =(CheckBox)findViewById(R.id.chPoStavkeCredit);
        creditSrok =(CheckBox)findViewById(R.id.chPoSrokyCredit);
        creditSymma =(CheckBox)findViewById(R.id.chPoSymmeCredit);
        creditBanks=(CheckBox)findViewById(R.id.chPoBankamCredit);
        creditName=(CheckBox)findViewById(R.id.chPoNameCredit);

        creditStavka.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        creditSrok.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        creditSymma.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        creditBanks.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        creditName.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));

        showCredit=(Button)findViewById(R.id.buttShowCredit);
        //endregion

        //region CheckBoxClicks
        creditStavka.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creditSymma.setChecked(false);
                creditSrok.setChecked(false);
                creditName.setChecked(false);
                creditBanks.setChecked(false);

                creditStavka.setEnabled(false);

                creditSymma.setEnabled(true);
                creditSrok.setEnabled(true);
                creditName.setEnabled(true);
                creditBanks.setEnabled(true);

            }
        });
        creditSymma.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creditStavka.setChecked(false);
                creditSrok.setChecked(false);
                creditName.setChecked(false);
                creditBanks.setChecked(false);

                creditSymma.setEnabled(false);

                creditStavka.setEnabled(true);
                creditSrok.setEnabled(true);
                creditName.setEnabled(true);
                creditBanks.setEnabled(true);
            }
        });
        creditSrok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creditSymma.setChecked(false);
                creditStavka.setChecked(false);
                creditName.setChecked(false);
                creditBanks.setChecked(false);

                creditSrok.setEnabled(false);

                creditSymma.setEnabled(true);
                creditStavka.setEnabled(true);
                creditName.setEnabled(true);
                creditBanks.setEnabled(true);
            }
        });
        creditBanks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creditSymma.setChecked(false);
                creditSrok.setChecked(false);
                creditStavka.setChecked(false);
                creditName.setChecked(false);

                creditBanks.setEnabled(false);

                creditSymma.setEnabled(true);
                creditSrok.setEnabled(true);
                creditStavka.setEnabled(true);
                creditName.setEnabled(true);

            }
        });
        creditName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creditSymma.setChecked(false);
                creditSrok.setChecked(false);
                creditBanks.setChecked(false);
                creditStavka.setChecked(false);

                creditName.setEnabled(false);

                creditSymma.setEnabled(true);
                creditSrok.setEnabled(true);
                creditBanks.setEnabled(true);
                creditStavka.setEnabled(true);
            }
        });
        //endregion

        showCredit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creditsToSort=new ArrayList<>();
                creditsToSort=BanksShow.currentCredits;
                int firstid = creditsToSort.get(0).getId();
                String query = "Select * from credit where (`id`="+firstid;
                for(ModelCredit credit : creditsToSort)
                {
                    String addstr= " or `id`="+credit.getId();
                    query+=addstr;
                }
                query+=")";
                if(creditStavka.isChecked()){
                    query+=" ORDER BY `rubstavkacredit`";
                    choosedChB=1;
                }
                if(creditSymma.isChecked()){
                    query+=" ORDER BY `rubsymmacredit` DESC";
                    choosedChB=2;
                }
                if(creditSrok.isChecked()){
                    query+=" ORDER BY `rubsrokcredit` DESC";
                    choosedChB=3;
                }
                if(creditBanks.isChecked()){
                    query+=" ORDER BY `id_bank`";
                    choosedChB=4;
                }
                if(creditName.isChecked()){
                    query+=" Order by `name`";
                    choosedChB=5;
                }

                RequestQueue.RequestFinishedListener finishedListener=new RequestQueue.RequestFinishedListener() {
                    @Override
                    public void onRequestFinished(Request request)
                    {
                        creditsSorted=LoadCredit.credits;
                        Intent loadShow = new Intent(SortCredit.this,BanksShow.class);
                        loadShow.putExtra("title",22);
                        loadShow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(loadShow);

                    }
                };

                LoadCredit.GetCredits(query,SortCredit.this,finishedListener);


            }
        });

        if(choosedChB!=0)
        {
            switch (choosedChB)
            {
                case 1:
                    creditStavka.performClick();
                    break;
                case 2:
                    creditSymma.performClick();
                    break;
                case 3:
                    creditSrok.performClick();
                    break;
                case 4:
                    creditBanks.performClick();
                    break;
                case 5:
                    creditName.performClick();
                    break;


            }
        }

    }
}
