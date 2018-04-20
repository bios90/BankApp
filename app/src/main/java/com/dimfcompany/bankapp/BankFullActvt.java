package com.dimfcompany.bankapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class BankFullActvt extends AppCompatActivity
{
    ModelBank bank;

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

    Button btnVklad,btnCard,btnCredit;

    RecyclerView BFrecVVklads,recVCards,recVCredits;

    AdapterBFIvklads adapterVklad,adapterVkladTwo;
    AdapterBFIcards adapterCard;

    ExpandableLayout expandVklad,expandCard,expandCredit;

    TextView bankName,bankAdress,bankSite,bankPhone,stavkaVklad,stavkaCard,stavkaCredit;
    ImageView vkladR,vkladD,vkladE,cardR,cardD,cardE,creditR,creditD,creditE,logo;

    double[] mmax = new double[9];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_full_actvt);

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

        //region Buttons
        btnVklad=(Button)findViewById(R.id.btnVklad);
        btnCard=(Button)findViewById(R.id.btnCard);
        btnCredit=(Button)findViewById(R.id.btnCredit);

        btnCredit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int color = Color.TRANSPARENT;
                Drawable background = btnCredit.getBackground();

                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable)background).getColor();


                switch (color)
                {
                    case -556771:
                        btnCredit.setBackground(header2);
                        btnCredit.setTextColor(orangeint);
                        btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
                        break;
                    case -14606047:
                        btnCredit.setBackground(orange);
                        btnCredit.setTextColor(headerint);
                        btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
                        break;
                }
            }
        });

        btnCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int color = Color.TRANSPARENT;
                Drawable background = btnCard.getBackground();

                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable)background).getColor();


                switch (color)
                {
                    case -556771:
                        btnCard.setBackground(header2);
                        btnCard.setTextColor(orangeint);
                        btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
                        break;
                    case -14606047:
                        btnCard.setBackground(orange);
                        btnCard.setTextColor(headerint);
                        btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
                        break;
                }
            }
        });

        btnVklad.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int color = Color.TRANSPARENT;
                Drawable background = btnVklad.getBackground();

                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable)background).getColor();

                Log.e("!!!!!COLOR!!!!!",color+"");

                switch (color)
                {
                    case -556771:
                        btnVklad.setBackground(header2);
                        btnVklad.setTextColor(orangeint);
                        btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
                        break;
                    case -14606047:
                        btnVklad.setBackground(orange);
                        btnVklad.setTextColor(headerint);
                        btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
                        break;
                }

                expandVklad.toggle();
            }
        });
        //endregion

        expandVklad=(ExpandableLayout)findViewById(R.id.expandLAVklad);

        rubYes=getResources().getDrawable(R.drawable.roubleyes);
        rubNo=getResources().getDrawable(R.drawable.roubleno);
        dolYes=getResources().getDrawable(R.drawable.dollaryes);
        dolNo=getResources().getDrawable(R.drawable.dollarno);
        euroYes=getResources().getDrawable(R.drawable.euroyes);
        euroNo=getResources().getDrawable(R.drawable.eurono);

        id_bank = getIntent().getIntExtra("id_bank",0);

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
                LoadRecViews();
                LoadCards();
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
        }
        else
            {
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
        }
        else
            {
                stavkaCard.setText("--");
            }
        if(credits.size()!=0)
        {
            creditR.setImageDrawable(mmax[6]==0?rubNo:rubYes);
            creditD.setImageDrawable(mmax[7]==0?dolNo:dolYes);
            creditE.setImageDrawable(mmax[8]==0?euroNo:euroYes);

            if(mmax[6]!=0.0)
            {
                stavkaCard.setText("от "+ mmax[6]+"%");
            }
            else if(mmax[7]!=0.0)
            {
                stavkaCard.setText("от "+ mmax[7]+"%");
            }
            else if(mmax[8]!=0.0)
            {
                stavkaCard.setText("от "+ mmax[8]+"%");
            }
        }
        else
        {
            stavkaCard.setText("--");
        }
    }
    //endregion

    private void LoadRecViews()
    {
        if(vklads.size()!=0)
        {
            adapterVklad=new AdapterBFIvklads(BankFullActvt.this,vklads,1);
            BFrecVVklads.setHasFixedSize(true);
            BFrecVVklads.setAdapter(adapterVklad);
        }
        else
            {
                btnVklad.setEnabled(false);
            }
    }

    void LoadCards()
    {
        if(cards.size()!=0)
        {
            recVCards.setLayoutManager(new LinearLayoutManager(this));
            adapterCard=new AdapterBFIcards(BankFullActvt.this,cards);
            recVCards.setHasFixedSize(true);
            recVCards.setAdapter(adapterCard);
        }
        else
        {
            btnCard.setEnabled(false);
        }
    }

    void LoadCredits()
    {
//        if(credits.size()!=0)
//        {
//            adapterCredit=new AdapterBFIvklads(BankFullActvt.this,credits,3);
//            recVCredits.setHasFixedSize(true);
//            recVCredits.setAdapter(adapterCredit);
//        }
    }
}
