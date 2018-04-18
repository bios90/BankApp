package com.dimfcompany.bankapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class BanksShow extends AppCompatActivity {

    RecyclerView recView;
    List<BankModel> banksToShow;
    AdapterVklad adapter;
    AdapterCredit creditAdapter;
    AdapterCards cardsAdapter;

    Button needHelp;

    static List<ModelVklad> currentVklads;
    static List<ModelCredit> currentCredits;
    static List<ModelCard> currentCards;

    ImageView sort;

    static int newRequestsaved;

    TextView title;
    String titleStr;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_show);


        //region Inizialization
        Intent intent = getIntent();
        title=(TextView)findViewById(R.id.banksShowTitle);
        recView=(RecyclerView)findViewById(R.id.recView);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        sort = (ImageView)findViewById(R.id.sortImage);

        currentVklads=new ArrayList<>();
        currentCredits=new ArrayList<>();
        currentCards=new ArrayList<>();

        needHelp=(Button)findViewById(R.id.needHelp);

        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                newRequestsaved=0;
                Intent help=new Intent(BanksShow.this,HelpActivity.class);
                help.setFlags(help.getFlags()| Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(help);
            }
        });
        //endregion




        switch (intent.getIntExtra("title",0))
        {
            case 1:
            {
                SortDialogActvt.choosedChB=0;
                currentVklads= FragVklad.vkladList;
                titleStr="Вклады "+"("+currentVklads.size()+")";
                title.setText(titleStr);
                showVklads();
                SetSortVklad();
                break;
            }
            case 11:
            {
                currentVklads=SortDialogActvt.vkladsSorted;
                titleStr="Вклады "+"("+currentVklads.size()+")";
                title.setText(titleStr);
                showVklads();
                SetSortVklad();
                break;
            }
            case 2:
            {
                SortCredit.choosedChB=0;
                currentCredits= FragCredit.creditList;
                titleStr="Кредиты "+"("+currentCredits.size()+")";
                title.setText(titleStr);
                showCredits();
                SetSortCredit();
                break;
            }
            case 22:
            {
                currentCredits=SortCredit.creditsSorted;
                titleStr="Кредиты "+"("+currentCredits.size()+")";
                title.setText(titleStr);
                showCredits();
                SetSortCredit();
                break;
            }
            case 3:
            {
                SortCard.choosedChB=0;
                currentCards= FragCredCard.cardList;
                titleStr="Кредитные Карты "+"("+currentCards.size()+")";
                title.setText(titleStr);
                showCards();
                setSortCard();
                break;
            }
            case 33:
            {
                currentCards= SortCard.cardsSorted;
                titleStr="Кредитные Карты "+"("+currentCards.size()+")";
                title.setText(titleStr);
                showCards();
                setSortCard();
                break;
            }

        }


    }

    @Override
    protected void onResume() {

        super.onResume();
        if(newRequestsaved==99) {
            PageViewActivity.ShowToast(BanksShow.this,"Ваш вопрос принят,скоро мы с вами свяжемся.");
//            DialogCustom dialog = new DialogCustom(BanksShow.this, "Ваш вопрос принят,скоро мы с вами свяжемся.", "Успешно");
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.show();
//            Window window = dialog.getWindow();
//            window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            newRequestsaved=0;
        }
    }

    void showCards()
    {
        cardsAdapter=new AdapterCards(this,currentCards);
        recView.setAdapter(cardsAdapter);
    }

    void showVklads()
    {
        adapter=new AdapterVklad(currentVklads,this);
        recView.setAdapter(adapter);
    }

    void showCredits()
    {
        creditAdapter=new AdapterCredit(this,currentCredits);
        recView.setAdapter(creditAdapter);
    }

    //region sortButton
    void setSortCard()
    {
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent sort=new Intent(BanksShow.this,SortCard.class);
                sort.setFlags(sort.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(sort);

            }
        });
    }

    void SetSortVklad()
    {
        sort.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sort=new Intent(BanksShow.this,SortDialogActvt.class);
                sort.setFlags(sort.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivityForResult(sort,1);

            }
        });
    }

    void SetSortCredit()
    {
        sort.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sort=new Intent(BanksShow.this,SortCredit.class);
                sort.setFlags(sort.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(sort);

            }
        });
    }
    //endregion


    public void dialogShow()
    {

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode==1)
//        {
//            if(resultCode==Activity.RESULT_OK)
//            {
//                int res=data.getIntExtra("result",0);
//                if(res==1)
//                {
//
//                    DialogCustom dialog= new DialogCustom(BanksShow.this,"Ваш вопрос принят,скоро мы с вами свяжемся","Успещно");
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    dialog.show();
//                    Window window=dialog.getWindow();
//                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//                }
//            }
//        }
//    }
}
