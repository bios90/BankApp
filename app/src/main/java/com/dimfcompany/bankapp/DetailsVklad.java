package com.dimfcompany.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsVklad extends AppCompatActivity {

    TextView name,bankname,rubstavka,rubsymma,rubsrok,dolstavka,dolsymma,dolsrok,eurostavka,eurosymma,eurosrok,popol,snyatie,rastorj;
    ImageView logo;
    LinearLayout rubLA,dolLA,euroLA,bankLA;

    ModelVklad vklad;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vklad_details);

        Intent intent = getIntent();
        int num=intent.getIntExtra("position",9999);
        int numTwo=intent.getIntExtra("positionBF",9999);
        //region Inizialition
        name=(TextView)findViewById(R.id.vkladInfoName);
        bankname=(TextView)findViewById(R.id.vkladInfoBank);
        rubstavka=(TextView)findViewById(R.id.vkladInfoRubStavka);
        rubsymma=(TextView)findViewById(R.id.vkladInfoRubSumma);
        rubsrok=(TextView)findViewById(R.id.vkladInfoRumSrok);
        dolstavka=(TextView)findViewById(R.id.vkladInfoDolStavka);
        dolsymma=(TextView)findViewById(R.id.vkladInfoDolSymma);
        dolsrok=(TextView)findViewById(R.id.vkladInfoDolSrok);
        eurostavka=(TextView)findViewById(R.id.vkladInfoEuroStavka);
        eurosymma=(TextView)findViewById(R.id.vkladInfoEuroSymma);
        eurosrok=(TextView)findViewById(R.id.vkladInfoEuroSrok);
        popol=(TextView)findViewById(R.id.vkladInfoPopolnenie);
        snyatie=(TextView)findViewById(R.id.vkladInfoSnyatie);
        rastorj=(TextView)findViewById(R.id.vkladInfoRastojenie);

        logo=(ImageView)findViewById(R.id.vkladInfoLogo);

        rubLA=(LinearLayout)findViewById(R.id.vkladRubLA);
        dolLA=(LinearLayout)findViewById(R.id.vkladDolLA);
        euroLA=(LinearLayout)findViewById(R.id.vkladEuroLA);

        bankLA=(LinearLayout)findViewById(R.id.vkladBankLA);
        //endregion

        vklad=new ModelVklad();
        if(num!=9999)
        {
            vklad = BanksShow.currentVklads.get(num);
        }
        else if(numTwo!=9999)
        {
            vklad= BankFullActvt.vklads.get(numTwo);
        }


        name.setText(vklad.getName());
        bankname.setText(vklad.getBankName());

        //region TextBoxes
        if(vklad.getStavkarub()==0)
        {
            rubLA.setVisibility(View.GONE);
            rubstavka.setText("--");
            rubstavka.setTextColor(getResources().getColor(R.color.header2));
        }else
            {
                rubstavka.setText(String.valueOf(vklad.getStavkarub())+"%");
            }

        if(vklad.getRubsymma()==0)
        {
            rubsymma.setText("--");
            rubsymma.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            rubsymma.setText(String.valueOf(vklad.getRubsymma()+"₽"));
        }


        if(vklad.getRubsrok()==0)
        {
            rubsrok.setText("--");
            rubsrok.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            rubsrok.setText(String.valueOf(vklad.getRubsrok())+" дней");
        }

        if(vklad.getStavkadol()==0)
        {
            dolLA.setVisibility(View.GONE);
            dolstavka.setText("--");
            dolstavka.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            dolstavka.setText(String.valueOf(vklad.getStavkadol()+"%"));
        }


            if(vklad.getDolsymma()==0)
        {
            dolsymma.setText("--");
            dolsymma.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            dolsymma.setText(String.valueOf(vklad.getDolsymma())+"$");
        }

        if(vklad.getDolsrok()==0)
        {
            dolsrok.setText("--");
            dolsrok.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            dolsrok.setText(String.valueOf(vklad.getDolsrok())+" дней");
        }

            if(vklad.getStavkaeuro()==0)
        {
            euroLA.setVisibility(View.GONE);
            eurostavka.setText("--");
            eurostavka.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            eurostavka.setText(String.valueOf(vklad.getStavkaeuro()+"%"));
        }

        if(vklad.getEurosymma()==0)
        {
            eurosymma.setText("--");
            eurosymma.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            eurosymma.setText(String.valueOf(vklad.getEurosymma())+"€");
        }

        if(vklad.getEurosrok()==0)
        {
            eurosrok.setText("--");
            eurosrok.setTextColor(getResources().getColor(R.color.header2));
        }else
        {
            eurosrok.setText(String.valueOf(vklad.getEurosrok())+" дней");
        }

        if(vklad.isPopoln())
        {
            popol.setText("Возможно");
        }else
            {
                popol.setText("Нет");
                popol.setTextColor(getResources().getColor(R.color.header2));
            }

        if(vklad.isSnyatie())
        {
            snyatie.setText("Возможно");
        }else
        {
            snyatie.setText("Нет");
            snyatie.setTextColor(getResources().getColor(R.color.header2));
        }
        if(vklad.isRastorj())
        {
            rastorj.setText("Возможно");
        }else
        {
            rastorj.setText("Нет");
            rastorj.setTextColor(getResources().getColor(R.color.header2));
        }
        //endregion


        Picasso.get().load(vklad.getLogo()).error(getResources().getDrawable(R.drawable.logodefault)).into(logo);

        bankLA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DetailsVklad.this,BankFullActvt.class);
                intent.putExtra("id_bank",vklad.getBank_id());
                startActivity(intent);
            }
        });
    }
}
