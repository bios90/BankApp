package com.dimfcompany.bankapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class BankFullActvt extends AppCompatActivity
{
    ModelBank bank;

    SpotsDialog sd;

    static List<ModelVklad> vklads;
    static List<ModelCard> cards;
    static List<ModelCredit> credits;
    int id_bank;

    Drawable rubYes;
    Drawable rubNo;
    Drawable dolYes;
    Drawable dolNo;
    Drawable euroYes;
    Drawable euroNo;

    ColorDrawable header2;
    ColorDrawable orange;

    Drawable arrowHeader;
    Drawable arrowOrange;

    ToggleButton btnVklad,btnCard,btnCredit;

    RecyclerView BFrecVVklads,recVCards,recVCredits;

    AdapterBFIvklads adapterVklad;
    AdapterBFIcards adapterCard;
    AdapterBFIcredit adapterCredit;

    ExpandableLayout expandVklad,expandCard,expandCredit;

    TextView bankName,bankAdress,bankSite,bankPhone,stavkaVklad,stavkaCard,stavkaCredit;
    ImageView vkladR,vkladD,vkladE,cardR,cardD,cardE,creditR,creditD,creditE,logo;

    double[] mmax = new double[9];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_full_actvt);



        //region Inizialization Colors && RecViews
        header2=new ColorDrawable(0xFF212121);
        orange=new ColorDrawable(0xFFf7811d);

        final int headerint=getResources().getColor(R.color.header2);
        final int orangeint=getResources().getColor(R.color.orange);

        arrowHeader=getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
        arrowOrange=getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_orange24dp);

        BFrecVVklads=(RecyclerView)findViewById(R.id.BFrecVVklads);
        recVCards=(RecyclerView)findViewById(R.id.BFrecVCards);
        recVCredits=(RecyclerView)findViewById(R.id.BFrecVCredit);

        BFrecVVklads.setLayoutManager(new LinearLayoutManager(this));
        recVCredits.setLayoutManager(new LinearLayoutManager(this));
        recVCards.setLayoutManager(new LinearLayoutManager(this));

        expandVklad=(ExpandableLayout)findViewById(R.id.expandLAVklad);
        expandCard=(ExpandableLayout)findViewById(R.id.expandLACard);
        expandCredit=(ExpandableLayout)findViewById(R.id.expandLACredit);

        rubYes=getResources().getDrawable(R.drawable.roubleyes);
        rubNo=getResources().getDrawable(R.drawable.roubleno);
        dolYes=getResources().getDrawable(R.drawable.dollaryes);
        dolNo=getResources().getDrawable(R.drawable.dollarno);
        euroYes=getResources().getDrawable(R.drawable.euroyes);
        euroNo=getResources().getDrawable(R.drawable.eurono);

        //endregion
        //region Buttons
        btnVklad=(ToggleButton)findViewById(R.id.btnVklad);
        btnCard=(ToggleButton)findViewById(R.id.btnCard);
        btnCredit=(ToggleButton)findViewById(R.id.btnCredit);

        btnCredit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(btnCredit.isChecked())
                {
                    btnCredit.setTextColor(headerint);
                    btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
                }
                else
                    {
                        btnCredit.setTextColor(orangeint);
                        btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
                    }

                boolean others=(expandVklad.isExpanded() || expandCard.isExpanded());

                if(expandVklad.isExpanded())btnVklad.performClick();
                if(expandCard.isExpanded())btnCard.performClick();
                expandVklad.collapse(true);
                expandCard.collapse(true);

                if(others==true)
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            expandCredit.toggle();

                        }
                    }, 460);
                }else
                    {
                        expandCredit.toggle();
                    }

            }
        });

        btnCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(btnCard.isChecked())
                {
                    btnCard.setTextColor(headerint);
                    btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
                }
                else
                {
                    btnCard.setTextColor(orangeint);
                    btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
                }

                boolean others=(expandVklad.isExpanded() || expandCredit.isExpanded());

                if(expandVklad.isExpanded())btnVklad.performClick();
                if(expandCredit.isExpanded())btnCredit.performClick();
                expandVklad.collapse(true);
                expandCredit.collapse(true);

                if(others==true)
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            expandCard.toggle();

                        }
                    }, 460);
                }else
                {
                    expandCard.toggle();
                }

            }
        });

        btnVklad.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(btnVklad.isChecked())
                {
                    btnVklad.setTextColor(headerint);
                    btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
                }
                else
                {
                    btnVklad.setTextColor(orangeint);
                    btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
                }
                //region OldColorsCheck
//                int color = Color.TRANSPARENT;
//                Drawable background = btnVklad.getBackground();
//
//                if (background instanceof ColorDrawable)
//                    color = ((ColorDrawable)background).getColor();
//
//
//                switch (color)
//                {
//                    case -556771:
//                        btnVklad.setBackground(header2);
//                        btnVklad.setTextColor(orangeint);
//                        btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
//                        break;
//                    case -14606047:
//                        btnVklad.setBackground(orange);
//                        btnVklad.setTextColor(headerint);
//                        btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
//                        break;
//                }
                //endregion

                boolean others=(expandCard.isExpanded() || expandCredit.isExpanded());

                if(expandCard.isExpanded())btnCard.performClick();
                if(expandCredit.isExpanded())btnCredit.performClick();
                expandCard.collapse(true);
                expandCredit.collapse(true);

                if(others==true)
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            expandVklad.toggle();

                        }
                    }, 460);
                }else
                {
                    expandVklad.toggle();
                }
            }
        });
        //endregion


        id_bank = getIntent().getIntExtra("id_bank",0);


        sd=new SpotsDialog(BankFullActvt.this, R.style.Custom);
        sd.show();

        //region Inizialization
        vklads=new ArrayList<>();
        cards=new ArrayList<>();
        credits=new ArrayList<>();

        bankName=(TextView)findViewById(R.id.BFbank_name);
        bankAdress=(TextView)findViewById(R.id.BFbankAdress);
        bankSite=(TextView)findViewById(R.id.BFbankSite);
        bankPhone=(TextView)findViewById(R.id.BFbankPhone);
        stavkaVklad=(TextView)findViewById(R.id.BFvkladStavka);
        stavkaCard=(TextView)findViewById(R.id.BFcardStavka);
        stavkaCredit=(TextView)findViewById(R.id.BFcreditStavka);

        vkladR=(ImageView)findViewById(R.id.BFvkladRub);
        vkladD=(ImageView)findViewById(R.id.BFvkladDol);
        vkladE=(ImageView)findViewById(R.id.BFvkladEuro);
        cardR=(ImageView)findViewById(R.id.BFcardRub);
        cardD=(ImageView)findViewById(R.id.BFcardDol);
        cardE=(ImageView)findViewById(R.id.BFcardEuro);
        creditR=(ImageView)findViewById(R.id.BFcreditRub);
        creditD=(ImageView)findViewById(R.id.BFcreditDol);
        creditE=(ImageView)findViewById(R.id.BFcreditEuro);

        logo=(ImageView)findViewById(R.id.BFbankLogo);
        //endregion

        LoadBankInfo(id_bank);
        LoadAllInfo(id_bank);

    }


    //region LoadInfo
    private void LoadBankInfo(int id_bank)
    {
        RequestQueue.RequestFinishedListener finishedListener=new RequestQueue.RequestFinishedListener()
        {
            @Override
            public void onRequestFinished(Request request)
            {
                bank = new ModelBank();
                bank=LoadBanks.bank;
            }
        };

        LoadBanks.LoadBank(BankFullActvt.this,id_bank,finishedListener);
    }


    void LoadAllInfo(int id)
    {

        RequestQueue.RequestFinishedListener finishedListener = new RequestQueue.RequestFinishedListener()
        {
            @Override
            public void onRequestFinished(Request request)
            {
                vklads.clear();
                cards.clear();
                credits.clear();

                vklads=LoadAllBankProducts.vklads;
                cards=LoadAllBankProducts.cards;
                credits=LoadAllBankProducts.credits;

                MakeView();
                LoadCredist();
                LoadVklads();
                LoadCards();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        sd.dismiss();
                    }
                }, 1200);

            }
        };

        LoadAllBankProducts.GetAallProducts(BankFullActvt.this,id_bank,finishedListener);
    }




    //endregion

    //region BankInfoShow
    private void MakeView()
    {
        bankName.setText(bank.getName());
        bankSite.setText(bank.getSite());
        bankPhone.setText(bank.getPhone());

        String adress =bank.getAdress();
        int i=adress.indexOf(',');
        adress= TextUtils.substring(adress,i+1,adress.length());
        bankAdress.setText(adress);

        Picasso.get().load(bank.getLogo()).error(getResources().getDrawable(R.drawable.logodefault)).into(logo);

        mmax=bank.getMinmax();

        for (double d : mmax)
        {
            Log.e("mmmxxxxxx",d+"");
        }
        if(vklads.size()!=0)
        {
            vkladR.setImageDrawable(mmax[0]==0.0?rubNo:rubYes);
            vkladD.setImageDrawable(mmax[1]==0.0?dolNo:dolYes);
            vkladD.setImageDrawable(mmax[2]==0.0?euroNo:euroYes);

            if(mmax[0]!=0.0)
            {
                stavkaVklad.setText("до "+ mmax[0]+"%");
            }
            else if(mmax[1]!=0.0)
            {
                stavkaVklad.setText("до "+ mmax[1]+"%");
            }
            else if(mmax[2]!=0.0)
            {
                stavkaVklad.setText("до "+ mmax[2]+"%");
            }
            else
            {
                vkladR.setImageDrawable(rubNo);
                vkladD.setImageDrawable(dolNo);
                vkladD.setImageDrawable(euroNo);
                stavkaVklad.setText("--");
            }
        }
        else
            {
                vkladR.setImageDrawable(rubNo);
                vkladD.setImageDrawable(dolNo);
                vkladD.setImageDrawable(euroNo);
                stavkaVklad.setText("--");
            }

        if(cards.size()!=0)
        {
            cardR.setImageDrawable(mmax[3]==0?rubNo:rubYes);
            cardD.setImageDrawable(mmax[4]==0?dolNo:dolYes);
            cardE.setImageDrawable(mmax[5]==0?euroNo:euroYes);

            if(mmax[3]!=0.0)
            {
                stavkaCard.setText("от "+ mmax[3]+"%");
            }
            else if(mmax[4]!=0.0)
            {
                stavkaCard.setText("от "+ mmax[4]+"%");
            }
            else if(mmax[5]!=0.0)
            {
                stavkaCard.setText("от "+ mmax[5]+"%");
            }
            else
                {
                    cardR.setImageDrawable(rubNo);
                    cardD.setImageDrawable(dolNo);
                    cardE.setImageDrawable(euroNo);
                    stavkaCard.setText("--");
                }
        }
        else
            {
                cardR.setImageDrawable(rubNo);
                cardD.setImageDrawable(dolNo);
                cardE.setImageDrawable(euroNo);
                stavkaCard.setText("--");
            }
        if(credits.size()!=0)
        {
            creditR.setImageDrawable(mmax[6]==0?rubNo:rubYes);
            creditD.setImageDrawable(mmax[7]==0?dolNo:dolYes);
            creditE.setImageDrawable(mmax[8]==0?euroNo:euroYes);

            if(mmax[6]!=0.0)
            {
                stavkaCredit.setText("от "+ mmax[6]+"%");
            }
            else if(mmax[7]!=0.0)
            {
                stavkaCredit.setText("от "+ mmax[7]+"%");
            }
            else if(mmax[8]!=0.0)
            {
                stavkaCredit.setText("от "+ mmax[8]+"%");
            }
            else
            {
                creditR.setImageDrawable(rubNo);
                creditD.setImageDrawable(dolNo);
                creditE.setImageDrawable(euroNo);
                stavkaCard.setText("--");
            }
        }
        else
        {
            creditR.setImageDrawable(rubNo);
            creditD.setImageDrawable(dolNo);
            creditE.setImageDrawable(euroNo);
            stavkaCard.setText("--");
        }
    }
    //endregion

    //region LoadProductsByPArt
    private void LoadCredist()
    {
        if(credits.size()!=0)
        {
            adapterCredit=new AdapterBFIcredit(BankFullActvt.this,credits);
            recVCredits.setHasFixedSize(true);
            recVCredits.setAdapter(adapterCredit);
        }else
        {
            btnCredit.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
            btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
            btnCredit.setEnabled(false);
            expandCredit.setEnabled(false);
            btnCredit.setTextColor(getResources().getColor(R.color.header2));

            btnCredit.setVisibility(View.GONE);
        }
    }

    private void LoadVklads()
    {
        if(vklads.size()!=0)
        {
            adapterVklad=new AdapterBFIvklads(BankFullActvt.this,vklads,1);
            BFrecVVklads.setHasFixedSize(true);
            BFrecVVklads.setAdapter(adapterVklad);
        }
        else
            {
                btnVklad.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
                btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                btnVklad.setEnabled(false);
                expandVklad.setEnabled(false);
                btnVklad.setTextColor(getResources().getColor(R.color.header2));

                btnVklad.setVisibility(View.GONE);
            }
    }

    void LoadCards()
    {
        if(cards.size()!=0)
        {
            adapterCard=new AdapterBFIcards(BankFullActvt.this,cards);
            recVCards.setHasFixedSize(true);
            recVCards.setAdapter(adapterCard);
        }
        else
        {
            btnCard.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
            btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
            btnCard.setEnabled(false);
            expandCard.setEnabled(false);
            btnCard.setTextColor(getResources().getColor(R.color.header2));

            btnCard.setVisibility(View.GONE);
        }
    }
    //endregion

}
