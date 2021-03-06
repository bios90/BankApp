package com.dimfcompany.bankapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
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

    View orRight, orLeft;

    ColorDrawable header2;
    ColorDrawable orange;

    Drawable arrowHeader;
    Drawable arrowOrange;

    ToggleButton btnVklad, btnCard, btnCredit;

    static final int REQUEST_CODE_LOCATION = 7000;
    Button locate;
    boolean mLocationPermissionGranted = false;
    FusedLocationProviderClient mFusedLocationProvierClient;
    private static final String TAG = "BankFullActvt";


    RecyclerView BFrecVVklads, recVCards, recVCredits;

    AdapterBFIvklads adapterVklad;
    AdapterBFIcards adapterCard;
    AdapterBFIcredit adapterCredit;

    ExpandableLayout expandVklad, expandCard, expandCredit;

    TextView bankName, bankAdress, bankSite, bankPhone, stavkaVklad, stavkaCard, stavkaCredit;
    ImageView vkladR, vkladD, vkladE, cardR, cardD, cardE, creditR, creditD, creditE, logo;

    double[] mmax = new double[9];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_full_actvt);


        //region Inizialization Colors && RecViews
        header2 = new ColorDrawable(0xFF212121);
        orange = new ColorDrawable(0xFFf7811d);

        orLeft = (View) findViewById(R.id.orangeViewLeft);
        orRight = (View) findViewById(R.id.orangeViewRight);

        final int headerint = getResources().getColor(R.color.header2);
        final int orangeint = getResources().getColor(R.color.orange);

        arrowHeader = getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
        arrowOrange = getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_orange24dp);

        BFrecVVklads = (RecyclerView) findViewById(R.id.BFrecVVklads);
        recVCards = (RecyclerView) findViewById(R.id.BFrecVCards);
        recVCredits = (RecyclerView) findViewById(R.id.BFrecVCredit);

        BFrecVVklads.setLayoutManager(new LinearLayoutManager(this));
        recVCredits.setLayoutManager(new LinearLayoutManager(this));
        recVCards.setLayoutManager(new LinearLayoutManager(this));

        expandVklad = (ExpandableLayout) findViewById(R.id.expandLAVklad);
        expandCard = (ExpandableLayout) findViewById(R.id.expandLACard);
        expandCredit = (ExpandableLayout) findViewById(R.id.expandLACredit);

        rubYes = getResources().getDrawable(R.drawable.roubleyes);
        rubNo = getResources().getDrawable(R.drawable.roubleno);
        dolYes = getResources().getDrawable(R.drawable.dollaryes);
        dolNo = getResources().getDrawable(R.drawable.dollarno);
        euroYes = getResources().getDrawable(R.drawable.euroyes);
        euroNo = getResources().getDrawable(R.drawable.eurono);

        //endregion
        //region Buttons
        btnVklad = (ToggleButton) findViewById(R.id.btnVklad);
        btnCard = (ToggleButton) findViewById(R.id.btnCard);
        btnCredit = (ToggleButton) findViewById(R.id.btnCredit);
        locate = (Button) findViewById(R.id.BFlocate);

        btnVklad.setTypeface(ResourcesCompat.getFont(BankFullActvt.this, R.font.museosanscyrl_500));
        btnCard.setTypeface(ResourcesCompat.getFont(BankFullActvt.this, R.font.museosanscyrl_500));
        btnCredit.setTypeface(ResourcesCompat.getFont(BankFullActvt.this, R.font.museosanscyrl_500));
        locate.setTypeface(ResourcesCompat.getFont(BankFullActvt.this, R.font.museosanscyrl_500));

        //region ProductButtons
        btnCredit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                if(btnCredit.isChecked())
//                {
//                    btnCredit.setTextColor(headerint);
//                    btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
//                }
//                else
//                    {
//                        btnCredit.setTextColor(orangeint);
//                        btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
//                    }

                boolean others = (expandVklad.isExpanded() || expandCard.isExpanded());

                if (expandVklad.isExpanded()) btnVklad.performClick();
                if (expandCard.isExpanded()) btnCard.performClick();
                expandVklad.collapse(true);
                expandCard.collapse(true);

                if (others == true)
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
                } else
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
//                if(btnCard.isChecked())
//                {
//                    btnCard.setTextColor(headerint);
//                    btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
//                }
//                else
//                {
//                    btnCard.setTextColor(orangeint);
//                    btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
//                }

                boolean others = (expandVklad.isExpanded() || expandCredit.isExpanded());

                if (expandVklad.isExpanded()) btnVklad.performClick();
                if (expandCredit.isExpanded()) btnCredit.performClick();
                expandVklad.collapse(true);
                expandCredit.collapse(true);

                if (others == true)
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
                } else
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

//                if(btnVklad.isChecked())
//                {
//                    btnVklad.setTextColor(headerint);
//                    btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowHeader,null);
//                }
//                else
//                {
//                    btnVklad.setTextColor(orangeint);
//                    btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowOrange,null);
//                }
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

                boolean others = (expandCard.isExpanded() || expandCredit.isExpanded());

                if (expandCard.isExpanded()) btnCard.performClick();
                if (expandCredit.isExpanded()) btnCredit.performClick();
                expandCard.collapse(true);
                expandCredit.collapse(true);

                if (others == true)
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
                } else
                {
                    expandVklad.toggle();
                }
            }
        });
        //endregion

        //region LocateButton
        locate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e(TAG, "onClick: " );
                if (mLocationPermissionGranted)
                {
                    ShowLcoation();
                }
                else
                {
                    RequestPermission();
                }
            }
        });
        //endregion

        id_bank = getIntent().getIntExtra("id_bank", 0);


        sd = new SpotsDialog(BankFullActvt.this, R.style.Custom);
        sd.show();

        //region Inizialization
        vklads = new ArrayList<>();
        cards = new ArrayList<>();
        credits = new ArrayList<>();

        bankName = (TextView) findViewById(R.id.BFbank_name);
        bankAdress = (TextView) findViewById(R.id.BFbankAdress);
        bankSite = (TextView) findViewById(R.id.BFbankSite);
        bankPhone = (TextView) findViewById(R.id.BFbankPhone);
        stavkaVklad = (TextView) findViewById(R.id.BFvkladStavka);
        stavkaCard = (TextView) findViewById(R.id.BFcardStavka);
        stavkaCredit = (TextView) findViewById(R.id.BFcreditStavka);

        vkladR = (ImageView) findViewById(R.id.BFvkladRub);
        vkladD = (ImageView) findViewById(R.id.BFvkladDol);
        vkladE = (ImageView) findViewById(R.id.BFvkladEuro);
        cardR = (ImageView) findViewById(R.id.BFcardRub);
        cardD = (ImageView) findViewById(R.id.BFcardDol);
        cardE = (ImageView) findViewById(R.id.BFcardEuro);
        creditR = (ImageView) findViewById(R.id.BFcreditRub);
        creditD = (ImageView) findViewById(R.id.BFcreditDol);
        creditE = (ImageView) findViewById(R.id.BFcreditEuro);

        logo = (ImageView) findViewById(R.id.BFbankLogo);
        //endregion

        LoadBankInfo(id_bank);
        LoadAllInfo(id_bank);

    }

    private void RequestPermission()
    {

        Log.e(TAG, "RequestPermission: " );
        String[] permissions = {android.Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_FINE_LOCATION};

        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mLocationPermissionGranted = true;
            ShowLcoation();

        } else
        {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_LOCATION);
        }
    }

    private void ShowLcoation()
    {
        Log.e(TAG, "ShowLcoation: " );
        mFusedLocationProvierClient = LocationServices.getFusedLocationProviderClient(this);
        try
        {
            if (mLocationPermissionGranted)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    PageViewActivity.ShowToast(this,"Приложению запрещен доступ к геоданным. Если вы хотите найти ближайший банк,измените настройки");
                    return;
                }
                final Task location = mFusedLocationProvierClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener()
                {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if(task.isSuccessful())
                        {
                            Location curLocation = (Location)task.getResult();
                            if(curLocation!=null)
                            {
                                double lat = curLocation.getLatitude();
                                double lon= curLocation.getLongitude();

                                String bankName = bank.getName();
                                String noSpaces=bankName.replace(' ','+');
                                String mapurl = "https://www.google.com/maps/search/"+noSpaces+"/@"+lat+""+","+""+lon+""+",16.0z"+"";
                                Uri myUri= Uri.parse(mapurl);
                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                        myUri);
                                startActivity(intent);

                            }
                        }
                        else
                            {
                                PageViewActivity.ShowToast(BankFullActvt.this,"Не удалось найти данные о геолокации.");
                            }
                    }
                });

            }
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        mLocationPermissionGranted=false;
        switch(requestCode)
        {
            case REQUEST_CODE_LOCATION:
                {
                    if(grantResults.length>0)
                    {
                        for(int a= 0;a<grantResults.length;a++)
                        {
                            if(grantResults[a]!=PackageManager.PERMISSION_GRANTED)
                            {
                                mLocationPermissionGranted=false;
                                PageViewActivity.ShowToast(BankFullActvt.this,"Нет доступа к данным геолокации,проверьте насторойи устройства.");
                                return;
                            }
                        }
                        ShowLcoation();
                    }
                    else
                        {
                            PageViewActivity.ShowToast(BankFullActvt.this,"Опять 25 нет доступа к данным геолокации,проверьте насторойи устройства.");
                        }
                }
        }
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
            vkladE.setImageDrawable(mmax[2]==0.0?euroNo:euroYes);

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
                vkladE.setImageDrawable(euroNo);
                stavkaVklad.setText("--");
            }
        }
        else
            {
                vkladR.setImageDrawable(rubNo);
                vkladD.setImageDrawable(dolNo);
                vkladE.setImageDrawable(euroNo);
                stavkaVklad.setText("--");
            }

        if(cards.size()!=0)
        {
            cardR.setImageDrawable(mmax[3]==0?rubNo:rubYes);
            cardD.setImageDrawable(mmax[4]==0?dolNo:dolYes);
            cardE.setImageDrawable(mmax[5]==0?euroNo:euroYes);

            if(mmax[3]!=0.0)
            {
                if(mmax[3]==888.88)
                {
                    stavkaCard.setText("от 0%");
                }
                else
                {
                    stavkaCard.setText("от " + mmax[3] + "%");
                }
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
            //recVCredits.setNestedScrollingEnabled(false);
        }else
        {
//            btnCredit.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
//            btnCredit.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
//            btnCredit.setEnabled(false);
//            expandCredit.setEnabled(false);
//            btnCredit.setTextColor(getResources().getColor(R.color.header2));

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
            //BFrecVVklads.setNestedScrollingEnabled(false);
        }
        else
            {
//                btnVklad.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
//                btnVklad.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
//                btnVklad.setEnabled(false);
//                expandVklad.setEnabled(false);
//                btnVklad.setTextColor(getResources().getColor(R.color.header2));

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
            //recVCards.setNestedScrollingEnabled(false);
        }
        else
        {
//            btnCard.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
//            btnCard.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
//            btnCard.setEnabled(false);
//            expandCard.setEnabled(false);
//            btnCard.setTextColor(getResources().getColor(R.color.header2));

            btnCard.setVisibility(View.GONE);
        }

        LoadSpaces();
    }

    private void LoadSpaces()
    {
        if((btnCredit.getVisibility()==View.VISIBLE) && (btnCard.getVisibility()==View.VISIBLE) && (btnVklad.getVisibility()==View.VISIBLE))
        {
            return;
        }else
            {
                if(btnVklad.getVisibility()==View.VISIBLE && btnCard.getVisibility()==View.VISIBLE && btnCredit.getVisibility()==View.GONE)
                {
                    orRight.setVisibility(View.GONE);
                    return;
                }
                if(btnVklad.getVisibility()==View.VISIBLE && btnCard.getVisibility()==View.GONE && btnCredit.getVisibility()==View.VISIBLE)
                {
                    orRight.setVisibility(View.GONE);
                    return;
                }
                if(btnVklad.getVisibility()==View.GONE && btnCard.getVisibility()==View.VISIBLE && btnCredit.getVisibility()==View.VISIBLE)
                {
                    orLeft.setVisibility(View.GONE);
                    return;
                }
                else
                    {
                        orRight.setVisibility(View.GONE);
                        orLeft.setVisibility(View.GONE);
                        return;
                    }
            }

    }
    //endregion

}
