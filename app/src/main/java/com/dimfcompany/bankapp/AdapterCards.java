package com.dimfcompany.bankapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCards extends RecyclerView.Adapter<AdapterCards.CardItemVH> {

    Context ctx;
    List<ModelCard> cards = new ArrayList<>();

    public AdapterCards(Context ctx, List<ModelCard> cards)
    {
        this.ctx = ctx;
        this.cards = cards;
    }

    @Override
    public CardItemVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view=inflater.inflate(R.layout.item_card,parent,false);
        CardItemVH holder = new CardItemVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardItemVH holder,final int position)
    {
        final ModelCard card = cards.get(position);

        holder.name.setText(card.getName());
        holder.bankname.setText(card.getBankName());

        double rubnum=card.getStavkaRub();
        double dolnum=card.getStavkaDol();
        double euronum=card.getStavkaEuro();

        if(rubnum!=0.0)
        {
            holder.stavka.setText("от "+rubnum+"%");
        }
        else if(dolnum!=0.0)
        {
            holder.stavka.setText("от "+dolnum+"%");
        }
        else if(euronum!=0.0)
        {
            holder.stavka.setText("от "+euronum+"%");
        }

        if(card.getRubLimit()!=0)
        {
            if(card.getRubLimit()!=9999999)
            {
                holder.limit.setText(StringRight(card.getRubLimit()) + " ₽");
            }
            else
                {
                    holder.limit.setText("индивидуально");
                }
        }
        else if(card.getDolLimit()!=0)
        {
            if (card.getDolLimit()!=9999999)
            {
                holder.limit.setText(StringRight(card.getDolLimit()) + " $");
            }
            else
                {
                    holder.limit.setText("индивидуально");
                }
        }
        else if(card.getEuroLimit()!=0)
        {
            if(card.getEuroLimit()!=9999999)
            {
                holder.limit.setText(StringRight(card.getEuroLimit()) + " €");
            }
            else
            {
                holder.limit.setText("индивидуально");
            }

        }

        if(card.getLgotSrok()!=0)
        {
            holder.srok.setText(card.getLgotSrok() +" дней");
            holder.srok.setTextColor(ctx.getResources().getColor(R.color.orange));
        }
        else
            {
                holder.srok.setText("нет");
                holder.srok.setTextColor(ctx.getResources().getColor(R.color.header2));
            }

        if(card.getCashBack()==1)
        {
            holder.cashB.setText("есть");
            holder.cashB.setTextColor(ctx.getResources().getColor(R.color.orange));
        }
        else
            {
                holder.cashB.setText("нет");
                holder.cashB.setTextColor(ctx.getResources().getColor(R.color.header2));
            }

        if(card.getDostavka()==1)
        {
            holder.dostavka.setText("возможна");
            holder.dostavka.setTextColor(ctx.getResources().getColor(R.color.orange));
        }
        else
            {
                holder.dostavka.setText("нет");
                holder.dostavka.setTextColor(ctx.getResources().getColor(R.color.header2));
            }

        if(card.getRubLimit()!=0)
        {
            holder.rub.setImageDrawable(ctx.getResources().getDrawable(R.drawable.roubleyes));
        }
        else holder.rub.setImageDrawable(ctx.getResources().getDrawable(R.drawable.roubleno));

        if(card.getDolLimit()!=0)
        {
            holder.dol.setImageDrawable(ctx.getResources().getDrawable(R.drawable.dollaryes));
        }
        else holder.dol.setImageDrawable(ctx.getResources().getDrawable(R.drawable.dollarno));

        if(card.getEuroLimit()!=0)
        {
            holder.euro.setImageDrawable(ctx.getResources().getDrawable(R.drawable.euroyes));
        }
        else holder.euro.setImageDrawable(ctx.getResources().getDrawable(R.drawable.eurono));

        Picasso.get().load(card.getLogo()).error(ctx.getResources().getDrawable(R.drawable.logodefault)).into(holder.bankImg);

        holder.bankImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ctx,BankFullActvt.class);
                intent.putExtra("id_bank",card.getId_bank());
                ctx.startActivity(intent);
            }
        });

        holder.cardCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent loadinfo=new Intent(ctx,DetailsCard.class);
                loadinfo.putExtra("position",position);
                //loadinfo.setFlags(loadinfo.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                ctx.startActivity(loadinfo);
            }
        });
    }

    public static String StringRight(int limit)
    {
        String rightsum=String.valueOf(limit);

        if (rightsum.length()==6 )
        {
            String first = rightsum.substring(0,3);
            String second = rightsum.substring(3,6);
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
            rightsum = first+" "+second;
            return rightsum;
        }
        if (rightsum.length()==4 )
        {
            String first = rightsum.substring(0,1);
            String second = rightsum.substring(1,4);
            rightsum = first+" "+second;
            return rightsum;
        }

        return rightsum;
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class CardItemVH extends RecyclerView.ViewHolder
    {
        TextView name,bankname,limit,srok,stavka,cashB,dostavka;
        ImageView bankImg,rub,dol,euro;
        CardView cardCV;

        public CardItemVH(View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.cardName);
            bankname=(TextView)itemView.findViewById(R.id.cardBankName);
            limit=(TextView)itemView.findViewById(R.id.cardLimit);
            srok=(TextView)itemView.findViewById(R.id.cardSrok);
            stavka=(TextView)itemView.findViewById(R.id.cardStavka);
            cashB=(TextView)itemView.findViewById(R.id.cardCashBack);
            dostavka=(TextView)itemView.findViewById(R.id.cardDostavka);

            bankImg=(ImageView) itemView.findViewById(R.id.cardBankImage);
            rub=(ImageView)itemView.findViewById(R.id.cardImgRub);
            dol=(ImageView)itemView.findViewById(R.id.cardImgDol);
            euro=(ImageView)itemView.findViewById(R.id.cardImgEuro);

            cardCV=(CardView)itemView.findViewById(R.id.cardCView);
        }
    }
}
