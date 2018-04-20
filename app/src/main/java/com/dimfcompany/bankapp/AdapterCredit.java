package com.dimfcompany.bankapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCredit extends RecyclerView.Adapter<AdapterCredit.CreditItemVH>
{
    Context ctx;
    List<ModelCredit> credits = new ArrayList<>();

    public AdapterCredit(Context ctx, List<ModelCredit> credits)
    {
        this.ctx = ctx;
        this.credits = credits;
    }

    @Override
    public CreditItemVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.item_credit,parent,false);
        CreditItemVH holder = new CreditItemVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CreditItemVH holder,final int position)
    {
        final ModelCredit credit = credits.get(position);

        holder.name.setText(credit.getName());
        holder.bankname.setText(credit.getBankName());
        double rubnum=credit.getStavkarubcredit();
        double dolnum=credit.getStavkadolcredit();
        double euronum=credit.getStavkaeurocredit();
        if (rubnum!=0.0 && rubnum!=999.99) {
            holder.stavka.setText("от " + String.valueOf(credit.getStavkarubcredit() + "%"));
        }else if(dolnum!=0.0)
        {
            holder.stavka.setText("от " + String.valueOf(credit.getStavkadolcredit() + "%"));
        }
        else holder.stavka.setText("от " + String.valueOf(credit.getStavkaeurocredit() + "%"));


        if(credit.isObes()==true)
        {
            holder.obes.setText("требуется");
            holder.obes.setTextColor(ctx.getResources().getColor(R.color.header2));
        }
        else
            {
                holder.obes.setText("не требуется");
                holder.obes.setTextColor(ctx.getResources().getColor(R.color.orange));
            }

        if(rubnum!=0.0 && rubnum!=999.99)
        {
            holder.rub.setImageDrawable(ctx.getResources().getDrawable(R.drawable.roubleyes));
        }
        else holder.rub.setImageDrawable(ctx.getResources().getDrawable(R.drawable.roubleno));

        if(dolnum!=0.0)
        {
            holder.dol.setImageDrawable(ctx.getResources().getDrawable(R.drawable.dollaryes));
        }
        else holder.dol.setImageDrawable(ctx.getResources().getDrawable(R.drawable.dollarno));

        if(euronum!=0.0)
        {
            holder.euro.setImageDrawable(ctx.getResources().getDrawable(R.drawable.euroyes));
        }
        else holder.euro.setImageDrawable(ctx.getResources().getDrawable(R.drawable.eurono));

        Picasso.get().load(credit.getLogo()).error(ctx.getResources().getDrawable(R.drawable.logodefault)).into(holder.bankImg);

        holder.bankImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ctx,BankFullActvt.class);
                intent.putExtra("id_bank",credit.getBank_id());
                ctx.startActivity(intent);
            }
        });

        holder.creditCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent loadinfo=new Intent(ctx,DetailsCredit.class);
                loadinfo.putExtra("position",position);
                //loadinfo.setFlags(loadinfo.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                ctx.startActivity(loadinfo);


            }
        });
    }

    @Override
    public int getItemCount()
    {
        return credits.size();
    }

    class CreditItemVH extends RecyclerView.ViewHolder
    {
        TextView name,bankname,stavka,obes;
        ImageView bankImg,rub,dol,euro;
        CardView creditCV;

        public CreditItemVH(View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.creditName);
            bankname=(TextView)itemView.findViewById(R.id.creditBankName);
            stavka=(TextView)itemView.findViewById(R.id.creditStavka);
            obes=(TextView)itemView.findViewById(R.id.creditObes);

            bankImg=(ImageView)itemView.findViewById(R.id.creditBankImage);
            rub=(ImageView)itemView.findViewById(R.id.creditImgRub);
            dol=(ImageView)itemView.findViewById(R.id.creditImgDol);
            euro=(ImageView)itemView.findViewById(R.id.creditImgEuro);
            
            creditCV=(CardView)itemView.findViewById(R.id.creditCView);

        }
    }
}
