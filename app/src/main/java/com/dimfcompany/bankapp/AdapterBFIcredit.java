package com.dimfcompany.bankapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterBFIcredit extends RecyclerView.Adapter<AdapterBFIcredit.BankItemCreditVH>
{
    List<ModelCredit> credits;
    Context ctx;

    Drawable rubYes;
    Drawable rubNo;
    Drawable dolYes;
    Drawable dolNo;
    Drawable euroYes;
    Drawable euroNo;

    public AdapterBFIcredit(Context ctx,List<ModelCredit> credits)
    {
        this.credits = credits;
        this.ctx = ctx;

        rubYes=ctx.getResources().getDrawable(R.drawable.roubleyes);
        rubNo=ctx.getResources().getDrawable(R.drawable.roubleno);
        dolYes=ctx.getResources().getDrawable(R.drawable.dollaryes);
        dolNo=ctx.getResources().getDrawable(R.drawable.dollarno);
        euroYes=ctx.getResources().getDrawable(R.drawable.euroyes);
        euroNo=ctx.getResources().getDrawable(R.drawable.eurono);
    }

    @Override
    public BankItemCreditVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.bank_product_item,parent,false);
        AdapterBFIcredit.BankItemCreditVH holder=new AdapterBFIcredit.BankItemCreditVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BankItemCreditVH holder,final int position)
    {
        ModelCredit credit = credits.get(position);

        holder.name.setText(credit.getName());
        double stavkaDblDva=0;

        if(credit.getStavkarubcredit()!=0.0)
        {
            stavkaDblDva=credit.getStavkarubcredit();
        }
        else if(credit.getStavkadolcredit()!=0.0)
        {
            stavkaDblDva=credit.getStavkadolcredit();
        }
        else if(credit.getStavkaeurocredit()!=0.0)
        {
            stavkaDblDva=credit.getStavkaeurocredit();
        }

        holder.stavka.setText("от "+stavkaDblDva+"%");

        holder.rub.setImageDrawable(credit.getStavkarubcredit()!=0?rubYes:rubNo);
        holder.dol.setImageDrawable(credit.getStavkadolcredit()!=0?dolYes:dolNo);
        holder.euro.setImageDrawable(credit.getStavkaeurocredit()!=0?euroYes:euroNo);

        holder.itemLA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent loadinfo=new Intent(ctx,DetailsCredit.class);
                loadinfo.putExtra("positionBF",position);
                ctx.startActivity(loadinfo);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return credits.size();
    }

    class BankItemCreditVH extends RecyclerView.ViewHolder
    {
        TextView name,stavka;
        ImageView rub,dol,euro;
        LinearLayout itemLA;

        public BankItemCreditVH(View itemView)
        {
            super(itemView);

            name=itemView.findViewById(R.id.productItemName);
            stavka=itemView.findViewById(R.id.productItemStavka);

            rub=itemView.findViewById(R.id.productItemRubImg);
            dol=itemView.findViewById(R.id.productItemDolImg);
            euro=itemView.findViewById(R.id.productItemEuroImg);

            itemLA=itemView.findViewById(R.id.productItemLayout);
        }
    }
}
