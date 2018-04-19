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
import java.util.zip.Inflater;

public class AdapterBankProducts extends RecyclerView.Adapter<AdapterBankProducts.BankProductVH>
{
    Context ctx;
    List<ModelVklad> vklads;
    List<ModelCard> cards;
    List<ModelCredit> credits;
    int listType;

    Drawable rubYes;
    Drawable rubNo;
    Drawable dolYes;
    Drawable dolNo;
    Drawable euroYes;
    Drawable euroNo;

    public AdapterBankProducts(Context ctx,List<?> list,int i)
    {

        rubYes=ctx.getResources().getDrawable(R.drawable.roubleyes);
        rubNo=ctx.getResources().getDrawable(R.drawable.roubleno);
        dolYes=ctx.getResources().getDrawable(R.drawable.dollaryes);
        dolNo=ctx.getResources().getDrawable(R.drawable.dollarno);
        euroYes=ctx.getResources().getDrawable(R.drawable.euroyes);
        euroNo=ctx.getResources().getDrawable(R.drawable.eurono);

        listType=i;
        this.ctx=ctx;

        if(listType==1)
        {
            this.vklads=(List<ModelVklad>)list;
        }
        if(listType==2)
        {
            this.cards=(List<ModelCard>)list;
        }
        if(listType==3)
        {
            this.credits=(List<ModelCredit>)list;
        }
    }

    @Override
    public BankProductVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.bank_product_item,parent,false);
        BankProductVH holder=new BankProductVH(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(BankProductVH holder,final int position)
    {
        if(listType==1)
        {
            ModelVklad vklad=vklads.get(position);

            holder.name.setText(vklad.getName());
            double stavkaDbl=0;
            if(vklad.getStavkarub()!=0.0)
            {
                stavkaDbl=vklad.getStavkarub();
            }
            else if(vklad.getStavkadol()!=0.0)
            {
                stavkaDbl=vklad.getStavkadol();
            }
            else if(vklad.getStavkaeuro()!=0.0)
            {
                stavkaDbl=vklad.getStavkaeuro();
            }

            holder.stavka.setText("до "+stavkaDbl+"%");

            holder.rub.setImageDrawable(vklad.getStavkarub()!=0?rubYes:rubNo);
            holder.dol.setImageDrawable(vklad.getStavkadol()!=0?dolYes:dolNo);
            holder.euro.setImageDrawable(vklad.getStavkaeuro()!=0?euroYes:euroNo);

            holder.itemLA.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent loadinfo=new Intent(ctx,DetailsVklad.class);
                    loadinfo.putExtra("positionBF",position);
                    ctx.startActivity(loadinfo);
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if(listType==1)
        {
            return vklads.size();
        }
        return 0;
    }

    class BankProductVH extends RecyclerView.ViewHolder
    {

        TextView name,stavka;
        ImageView rub,dol,euro;
        LinearLayout itemLA;

        public BankProductVH(View itemView)
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

//    public AdapterBankProducts(Context ctx,List<ModelCard> cards)
//    {
//        this.ctx=ctx;
//        this.cards=cards;
//    }

//    public AdapterBankProducts(Context ctx,List<ModelCredit> credits)
//    {
//        this.ctx=ctx;
//        this.credits=credits;
//    }
}
