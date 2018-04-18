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

public class SortDialogActvt extends AppCompatActivity
{

    static int choosedChB;
    CheckBox rub,dol,euro,banks,vklads;
    Button show;
    List<ModelVklad> vkladsTosSort;
    static List<ModelVklad> vkladsSorted;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_dialog_actvt);

        //region Inizialization
        rub=(CheckBox)findViewById(R.id.chRub);
        dol=(CheckBox)findViewById(R.id.chDol);
        euro=(CheckBox)findViewById(R.id.chEuro);
        banks=(CheckBox)findViewById(R.id.chBanks);
        vklads=(CheckBox)findViewById(R.id.chVklad);

        rub.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        dol.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        euro.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        banks.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));
        vklads.setTypeface(ResourcesCompat.getFont(this, R.font.museosanscyrl_500));

        show=(Button)findViewById(R.id.buttShow);
        //endregion


        //region CheckBoxClicks
        rub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dol.setChecked(false);
                euro.setChecked(false);
                vklads.setChecked(false);
                banks.setChecked(false);

                rub.setEnabled(false);

                dol.setEnabled(true);
                euro.setEnabled(true);
                vklads.setEnabled(true);
                banks.setEnabled(true);

            }
        });
        dol.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rub.setChecked(false);
                euro.setChecked(false);
                vklads.setChecked(false);
                banks.setChecked(false);

                dol.setEnabled(false);

                rub.setEnabled(true);
                euro.setEnabled(true);
                vklads.setEnabled(true);
                banks.setEnabled(true);
            }
        });
        euro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dol.setChecked(false);
                rub.setChecked(false);
                vklads.setChecked(false);
                banks.setChecked(false);

                euro.setEnabled(false);

                dol.setEnabled(true);
                rub.setEnabled(true);
                vklads.setEnabled(true);
                banks.setEnabled(true);
            }
        });
        banks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dol.setChecked(false);
                euro.setChecked(false);
                rub.setChecked(false);
                vklads.setChecked(false);

                banks.setEnabled(false);

                dol.setEnabled(true);
                euro.setEnabled(true);
                rub.setEnabled(true);
                vklads.setEnabled(true);

            }
        });
        vklads.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dol.setChecked(false);
                euro.setChecked(false);
                banks.setChecked(false);
                rub.setChecked(false);

                vklads.setEnabled(false);

                dol.setEnabled(true);
                euro.setEnabled(true);
                banks.setEnabled(true);
                rub.setEnabled(true);
            }
        });
        //endregion

        show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                vkladsTosSort=new ArrayList<>();
                vkladsTosSort=BanksShow.currentVklads;
                int firstid = vkladsTosSort.get(0).getBank_id();
                String query = "Select * from vklad where (`id`="+firstid;
                for(ModelVklad vklad : vkladsTosSort)
                {
                    String addstr= " or `id`="+vklad.getId();
                    query+=addstr;
                }
                query+=")";
                if(rub.isChecked())
                {
                    query+=" ORDER BY `rubstavka` DESC";
                    choosedChB=1;
                }
                if(dol.isChecked())
                {
                    query+=" ORDER BY `dolstavka` DESC";
                    choosedChB=2;
                }
                if(euro.isChecked())
                {
                    query+=" ORDER BY `eurostavka` DESC";
                    choosedChB=3;
                }
                if(banks.isChecked())
                {
                    query+=" ORDER BY `id_bank`";
                    choosedChB=4;
                }
                if(vklads.isChecked())
                {
                    query+=" Order by `name`";
                    choosedChB=5;
                }



                RequestQueue.RequestFinishedListener finishedListener=new RequestQueue.RequestFinishedListener() {
                    @Override
                    public void onRequestFinished(Request request)
                    {
                        vkladsSorted=LoadVklads.vkladList;
                        if(vkladsSorted.size()==0)
                        {
                            finish();
//                            Intent loadStart= new Intent(SortDialogActvt.this,PageViewActivity.class);
//                            loadStart.putExtra("error",1);
//                            loadStart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(loadStart);
                        }
                        else{
                            Intent loadShow = new Intent(SortDialogActvt.this, BanksShow.class);
                            loadShow.putExtra("title", 11);
                            loadShow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(loadShow);
                            }

                    }
                };

                LoadVklads.GetVklads(query,SortDialogActvt.this,finishedListener);


            }
        });

        if(choosedChB!=0)
        {
            switch (choosedChB)
            {
                case 1:
                    rub.performClick();
                    break;
                case 2:
                    dol.performClick();
                    break;
                case 3:
                    euro.performClick();
                    break;
                case 4:
                    banks.performClick();
                    break;
                case 5:
                    vklads.performClick();
                    break;
            }
        }



    }
}
