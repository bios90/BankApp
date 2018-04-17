package com.dimfcompany.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsCard extends AppCompatActivity
{

    TextView name,bankName,stavkarub,stavkadol,stavkaeuro,limitrub,limitdol,limiteuro,lgotSrok,lgottype,besplatO,stoimostO,cashBack,nalSnyatie,bistrR,dostavka,age,staj,podtverj,register;
    LinearLayout rubLA,dolLA,euroLA,lgotYesLA,lgotNoLA,besplatLA,yearPAyLA;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_card);

        Intent intent = getIntent();
        int num=intent.getIntExtra("position",0);

        //region Inizialization
        name=(TextView)findViewById(R.id.cardName);
        bankName=(TextView)findViewById(R.id.cardBankName);
        stavkarub=(TextView)findViewById(R.id.cardRubStavka);
        stavkadol=(TextView)findViewById(R.id.cardDolStavka);
        stavkaeuro=(TextView)findViewById(R.id.cardEuroStavka);
        limitrub=(TextView)findViewById(R.id.cardRubLimit);
        limitdol=(TextView)findViewById(R.id.cardDolLimit);
        limiteuro=(TextView)findViewById(R.id.cardEuroLimit);
        lgotSrok=(TextView)findViewById(R.id.cardLgotSrok);
        lgottype=(TextView)findViewById(R.id.cardLgotType);
        besplatO=(TextView)findViewById(R.id.cardBesplatoneO);
        stoimostO=(TextView)findViewById(R.id.cardObslujStoimost);
        cashBack=(TextView)findViewById(R.id.cardCashB);
        nalSnyatie=(TextView)findViewById(R.id.cardNalSnyat);
        bistrR=(TextView)findViewById(R.id.cardBistroeR);
        dostavka=(TextView)findViewById(R.id.cardDostavka);
        age=(TextView)findViewById(R.id.cardAge);
        staj=(TextView)findViewById(R.id.cardStaj);
        podtverj=(TextView)findViewById(R.id.cardPodtverj);
        register=(TextView)findViewById(R.id.cardRegister);

        rubLA=(LinearLayout)findViewById(R.id.cardRubLayout);
        dolLA=(LinearLayout)findViewById(R.id.cardDolLayout);
        euroLA=(LinearLayout)findViewById(R.id.cardEuroLayout);
        lgotYesLA=(LinearLayout)findViewById(R.id.cardLlgotLayoutYes);
        lgotNoLA=(LinearLayout)findViewById(R.id.cardLlgotLayoutNo);
        besplatLA=(LinearLayout)findViewById(R.id.cardBesplatOLayout);
        yearPAyLA=(LinearLayout)findViewById(R.id.cardGodovoeLayout);

        logo=(ImageView)findViewById(R.id.cardBankLogo);
        //endregion

        ModelCard card= BanksShow.currentCards.get(num);

        Log.e("test!!!!",String.valueOf(card.getBesplatnoeO()));

        name.setText(card.getName());
        bankName.setText(card.getBankName());

        if(card.getStavkaRub()!=0.0 || card.getRubLimit()!=0)
        {
            stavkarub.setText(card.getStavkaRub()+"%");
            if(card.getRubLimit()!=9999999)
            {
                limitrub.setText(AdapterCards.StringRight(card.getRubLimit()) + "\u20BD");
            }
            else
                {
                    limitrub.setText("индивидуально");
                }
        }
        else
            {
                rubLA.setVisibility(View.GONE);
            }

        if(card.getStavkaDol()!=0.0 || card.getDolLimit()!=0)
        {
            stavkadol.setText(card.getStavkaDol()+"%");
            if(card.getDolLimit()!=9999999)
            {
                limitdol.setText(AdapterCards.StringRight(card.getDolLimit()) + "$");
            }
            else
                {
                    limitdol.setText("индивидуально");
                }
        }
        else
            {
                dolLA.setVisibility(View.GONE);
            }

        if(card.getStavkaEuro()!=0.0 || card.getEuroLimit()!=0)
        {
            stavkaeuro.setText(card.getStavkaEuro()+"%");
            if(card.getEuroLimit()!=9999999)
            {
                limiteuro.setText(AdapterCards.StringRight(card.getEuroLimit()) + "€");
            }
            else
                {
                    limiteuro.setText("индивидуально");
                }
        }
        else
        {
            euroLA.setVisibility(View.GONE);
        }

        if(card.getLgotSrok()!=0)
        {
            lgotSrok.setText(FormingDays(card.getLgotSrok()));
            switch (card.getLgotType())
            {
                case 1:
                    lgottype.setText("покупки      ");
                    break;
                case 2:
                    lgottype.setText("снятие наличных");
                    break;
                case 12:
                    lgottype.setText("покупки/снятие наличных");
                    break;
            }
            lgotNoLA.setVisibility(View.GONE);
        }
        else
            {
                lgotYesLA.setVisibility(View.GONE);
            }

        if(card.besplatnoeO==1)
        {
            besplatO.setText("да");
            yearPAyLA.setVisibility(View.GONE);
        }
        else if(card.besplatnoeO==3)
        {
            besplatO.setText("возможно");
            yearPAyLA.setVisibility(View.GONE);
        }
        else
            {
                stoimostO.setText(card.getObsluj()+"\u20BD");
                besplatLA.setVisibility(View.GONE);
            }

            if(card.getCashBack()==1)
            {
                cashBack.setText("да");
            }
            else
                {
                    cashBack.setText("нет");
                    cashBack.setTextColor(this.getResources().getColor(R.color.header2));
                }

        nalSnyatie.setText("от "+card.getNalsnyatie()+"%");
        if(card.getReshenie()==1)
        {
            bistrR.setText("да");
        }
        else
            {
                bistrR.setText("нет");
                bistrR.setTextColor(this.getResources().getColor(R.color.header2));
            }

        if(card.getDostavka()==1)
        {
            dostavka.setText("возможна");
        }
        else
        {
            dostavka.setText("нет");
            dostavka.setTextColor(this.getResources().getColor(R.color.header2));
        }

        age.setText("от "+card.getAge());

        switch (card.getCardStaj())
        {
            case 0:
                staj.setText("не важен");
                break;
            case 1:
                staj.setText("от 3 месяцев");
                break;
            case 2:
                staj.setText("от 4 месяцев");
                break;
            case 3:
                staj.setText("от 6 месяцев");
                break;
            case 4:
                staj.setText("от года");
                break;
        }

        switch (card.getCardPodtverj())
        {
            case 1:
            case 2:
            case 3:
            case 12:
            case 13:
            case 23:
            case 123:
                podtverj.setText("требуется");
                podtverj.setTextColor(this.getResources().getColor(R.color.header2));
                break;
            case 0:
            case 10:
            case 20:
            case 30:
            case 120:
            case 130:
            case 230:
            case 1230:
                podtverj.setText("не требуется");
                podtverj.setTextColor(this.getResources().getColor(R.color.orange));
                break;
        }

       if(card.getRegister()==0)
       {
           register.setText("не требуется");
       }
       else
           {
               register.setText("требуется");
               register.setTextColor(this.getResources().getColor(R.color.header2));
           }

        Picasso.get().load(card.getLogo()).error(getResources().getDrawable(R.drawable.logodefault)).into(logo);
    }

    String FormingDays(int days)
    {
        String daysstr;
         if (days%10==1&&(days!=11 && days!=111 && days!=211 && days!=311 && days!=411 && days!=511 && days!=611 && days!=711 && days!=811  ))
        {
            daysstr=days+" день";
            return daysstr;
        }
        if (days%10>=2 && days%10 <=4 )
        {
            daysstr=days+" дня";
            return daysstr;
        }
        else
            {
                daysstr=days+" дней";
                return daysstr;
            }
    }

}
