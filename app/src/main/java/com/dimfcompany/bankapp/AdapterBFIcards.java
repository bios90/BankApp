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

public class AdapterBFIcards extends RecyclerView.Adapter<AdapterBFIcards.BankItemCardVH>
{
    List<ModelCard> cards;
    Context ctx;

    Drawable rubYes;
    Drawable rubNo;
    Drawable dolYes;
    Drawable dolNo;
    Drawable euroYes;
    Drawable euroNo;

    public AdapterBFIcards(Context ctx,List<ModelCard> cards)
    {
        this.cards = cards;
        this.ctx = ctx;

        rubYes=ctx.getResources().getDrawable(R.drawable.roubleyes);
        rubNo=ctx.getResources().getDrawable(R.drawable.roubleno);
        dolYes=ctx.getResources().getDrawable(R.drawable.dollaryes);
        dolNo=ctx.getResources().getDrawable(R.drawable.dollarno);
        euroYes=ctx.getResources().getDrawable(R.drawable.euroyes);
        euroNo=ctx.getResources().getDrawable(R.drawable.eurono);
    }

    @Override
    public BankItemCardVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.bank_product_item,parent,false);
        AdapterBFIcards.BankItemCardVH holder=new AdapterBFIcards.BankItemCardVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BankItemCardVH holder,final int position)
    {
        ModelCard card = cards.get(position);

        holder.name.setText(card.getName());
        double stavkaDblDva=0;

        if(card.getStavkaRub()!=0.0)
        {
            stavkaDblDva=card.getStavkaRub();
        }
        else if(card.getStavkaDol()!=0.0)
        {
            stavkaDblDva=card.getStavkaDol();
        }
        else if(card.getStavkaEuro()!=0.0)
        {
            stavkaDblDva=card.getStavkaEuro();
        }

        holder.stavka.setText("от "+stavkaDblDva+"%");

        holder.rub.setImageDrawable(card.getStavkaRub()!=0?rubYes:rubNo);
        holder.dol.setImageDrawable(card.getStavkaDol()!=0?dolYes:dolNo);
        holder.euro.setImageDrawable(card.getStavkaEuro()!=0?euroYes:euroNo);

        holder.itemLA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent loadinfo=new Intent(ctx,DetailsCard.class);
                loadinfo.putExtra("positionBF",position);
                ctx.startActivity(loadinfo);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cards.size();
    }

    class BankItemCardVH extends RecyclerView.ViewHolder
    {

        TextView name,stavka;
        ImageView rub,dol,euro;
        LinearLayout itemLA;

        public BankItemCardVH(View itemView)
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
