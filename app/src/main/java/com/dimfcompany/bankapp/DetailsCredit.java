package com.dimfcompany.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsCredit extends AppCompatActivity
{
    TextView creditName,creditBankName,rubStavka,rubSumma,rubSrok,dolStavka,dolSumma,dolSrok,refinans,obes,staj,tolkopass,
    podtverjName,ndfl,bankForm,other,noPodtverj;

    LinearLayout rubLA,dolLA,bankLA;

    ImageView logo;

    ModelCredit credit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_credit);

        Intent intent = getIntent();
        int num=intent.getIntExtra("position",9999);
        int numTwo=intent.getIntExtra("positionBF",9999);

        //region Inizialization
        creditName=(TextView)findViewById(R.id.creditInfoName);
        creditBankName=(TextView)findViewById(R.id.creditInfoBank);
        rubStavka=(TextView)findViewById(R.id.creditInfoRubStavka);
        rubSumma=(TextView)findViewById(R.id.creditInfoRubSumma);
        rubSrok=(TextView)findViewById(R.id.creditInfoRubSrok);
        dolStavka=(TextView)findViewById(R.id.creditInfoDolStavka);
        dolSumma=(TextView)findViewById(R.id.creditInfoDolSumma);
        dolSrok=(TextView)findViewById(R.id.creditInfoDolSrok);
        refinans=(TextView)findViewById(R.id.creditInfoRefinans);
        obes=(TextView)findViewById(R.id.creditInfoObes);
        staj=(TextView)findViewById(R.id.creditInfoStaj);
        tolkopass=(TextView)findViewById(R.id.creditInfoTolkoPass);
        ndfl=(TextView)findViewById(R.id.creditInfoNDFL);
        podtverjName=(TextView)findViewById(R.id.creditInfoPodtverjName);
        bankForm=(TextView)findViewById(R.id.creditInfoBankForm);
        other=(TextView)findViewById(R.id.creditInfoOther);
        noPodtverj=(TextView)findViewById(R.id.creditInfoNoPodtverj);

        logo=(ImageView)findViewById(R.id.creditInfoLogo);

        bankLA=(LinearLayout)findViewById(R.id.creditBankLA);

        rubLA=(LinearLayout)findViewById(R.id.creditRubLayout);
        dolLA=(LinearLayout)findViewById(R.id.creditDolLayout);
        //endregion

        credit=new ModelCredit();// BanksShow.currentCredits.get(num);

        //region MakeView
        if(num!=9999)
        {
            credit = BanksShow.currentCredits.get(num);
        }
        else if(numTwo!=9999)
        {
            credit= BankFullActvt.credits.get(numTwo);
        }

        creditName.setText(credit.getName());
        creditBankName.setText(credit.getBankName());

        if(credit.getStavkarubcredit()!=0.0)
        {
            rubStavka.setText(String.valueOf(credit.getStavkarubcredit())+"%");
            rubSumma.setText(summString(credit.getRubsymmacredit())+"₽");
            rubSrok.setText(srokString(credit.getRubsrokcredit()));
        }
        else
            {
                rubLA.setVisibility(View.GONE);
            }

        if(credit.getStavkadolcredit()!=0.0)
        {

            dolLA.setVisibility(View.VISIBLE);
            dolStavka.setText(String.valueOf(credit.getStavkadolcredit()+"%"));
            dolSumma.setText(summString(credit.getDolsymmacredit())+"$");
            dolSrok.setText(srokString(credit.getDolsrokcredit()));
        }
        else
            {
                dolLA.setVisibility(View.GONE);
            }

        if(credit.isRefinans()==true)
        {
            refinans.setText("да");
        }
        else
            {
                refinans.setText("нет");
                refinans.setTextColor(this.getResources().getColor(R.color.header2));
            }

        if(credit.isObes())
        {
            obes.setText("требуется");
            obes.setTextColor(this.getResources().getColor(R.color.header2));
        }
        else
            {
                obes.setText("не требуется");
            }

        int stajInt = credit.getStaj();

        switch (stajInt)
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

        if (credit.isTolkopass())
        {
            tolkopass.setText("да");
        }
        else
            {
                tolkopass.setText("нет");
                tolkopass.setTextColor(this.getResources().getColor(R.color.header2));
            }

        int podtverjInt = credit.getPodtverj();

        if(podtverjInt!=0)
        {
            switch (podtverjInt)
            {
                case 1:
                    podtverjName.setText("Подтверждение дохода :");
                    ndfl.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    podtverjName.setText("Подтверждение дохода :");
                    bankForm.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    podtverjName.setText("Подтверждение дохода :");
                    other.setVisibility(View.VISIBLE);
                    break;
                case 12:
                    ndfl.setVisibility(View.VISIBLE);
                    bankForm.setVisibility(View.VISIBLE);
                    break;
                case 13:
                    ndfl.setVisibility(View.VISIBLE);
                    other.setVisibility(View.VISIBLE);
                    break;
                case 123:
                    ndfl.setVisibility(View.VISIBLE);
                    bankForm.setVisibility(View.VISIBLE);
                    other.setVisibility(View.VISIBLE);
                    break;
            }
        }
        else
            {
                podtverjName.setText("Подтверждение дохода :");
                noPodtverj.setVisibility(View.VISIBLE);
            }


        Picasso.get().load(credit.getLogo()).error(getResources().getDrawable(R.drawable.logodefault)).into(logo);
        //endregion

        bankLA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DetailsCredit.this,BankFullActvt.class);
                intent.putExtra("id_bank",credit.getBank_id());
                startActivity(intent);
            }
        });
    }

    String srokString(int srok)
    {
        String rightSrok;
        if (srok==21 ||srok==31 ||srok==41 ||srok==51||srok==61||srok==71||srok==81||srok==91||srok==101||srok==121||srok==131||srok==141||srok==151||srok==161||srok==171||srok==181
                ||srok==191||srok==201|srok==221)
        {
            rightSrok=String.valueOf(srok) + " месяц";

        }
        if(((srok>21)&&(srok<25))||((srok>31)&&(srok<35))||((srok>41)&&(srok<45))||((srok>41)&&(srok<45))||((srok>61)&&(srok<65))||((srok>71)&&(srok<75))||((srok>81)&&(srok<85))
                ||((srok>91)&&(srok<95))||((srok>101)&&(srok<105))||((srok>111)&&(srok<115))||((srok>121)&&(srok<125))||((srok>131)&&(srok<135))||((srok>141)&&(srok<145))||((srok>151)&&(srok<155)))
        {
            rightSrok=String.valueOf(srok) + " месяца";

        }
        else rightSrok=String.valueOf(srok) + " месяцев";

        return rightSrok;
    }

    String summString(int sum)
    {
        String rightsum=String.valueOf(sum);

        if (rightsum.length()==6 )
        {
            String first = rightsum.substring(0,3);
            String second = rightsum.substring(3,6);
            //String third=rightsum.substring(4,7);
            rightsum = first+" "+second;
            return rightsum;
        }
        if (rightsum.length()==7 )
        {
            String first = rightsum.substring(0,1);
            String second = rightsum.substring(1,4);
            String third=rightsum.substring(4,7);
            rightsum = first+" "+second+" "+third;
            return rightsum;
        }
        if (rightsum.length()==8 )
        {
            String first = rightsum.substring(0,2);
            String second = rightsum.substring(2,5);
            String third=rightsum.substring(5,8);
            rightsum = first+" "+second+" "+third;
            return rightsum;
        }
        if (rightsum.length()==5 )
        {
            String first = rightsum.substring(0,2);
            String second = rightsum.substring(2,5);
            //String third=rightsum.substring(4,7);
            rightsum = first+" "+second;
            return rightsum;
        }

        return rightsum;
    }
}
