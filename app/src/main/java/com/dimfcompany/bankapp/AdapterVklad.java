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

public class AdapterVklad extends RecyclerView.Adapter<AdapterVklad.VkladItemVH>
{
    List<ModelVklad> vklads = new ArrayList<>();
    Context ctx;

    //region Constructors
    public AdapterVklad()
    {

    }

    public AdapterVklad(List<ModelVklad> vklads, Context ctx)
    {
        this.vklads = vklads;
        this.ctx = ctx;
    }
    //endregion

    @Override
    public VkladItemVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.item_vklad,parent,false);
        VkladItemVH holder= new VkladItemVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final VkladItemVH holder, final int position)
    {
        final ModelVklad vklad = vklads.get(position);

        holder.name.setText(vklad.getName());
        holder.bankname.setText(vklad.getBankName());

        double rubnum=vklad.getStavkarub();
        double dolnum=vklad.getStavkadol();
        double euronum=vklad.getStavkaeuro();

        holder.rub.setText(rubnum==0.0?"--":("до "+String.valueOf(rubnum)+"%"));
        holder.dol.setText(dolnum==0.0?"--":("до "+String.valueOf(dolnum)+"%"));
        holder.euro.setText(euronum==0.0?"--":("до "+String.valueOf(euronum)+"%"));

        Picasso.get().load(vklad.getLogo()).error(ctx.getResources().getDrawable(R.drawable.logodefault)).into(holder.logo);

        holder.logo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ctx,BankFullActvt.class);
                intent.putExtra("id_bank",vklad.getBank_id());
                ctx.startActivity(intent);
            }
        });

        holder.vkladCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent loadinfo=new Intent(ctx,DetailsVklad.class);
                loadinfo.putExtra("position",position);
                //loadinfo.setFlags(loadinfo.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                ctx.startActivity(loadinfo);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return vklads.size();
    }

    class VkladItemVH extends RecyclerView.ViewHolder
    {
        TextView name,bankname,rub,dol,euro;
        ImageView logo;
        CardView vkladCardView;

        public VkladItemVH(View itemView)
        {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.vkladName);
            bankname=(TextView)itemView.findViewById(R.id.bankName);
            rub=(TextView)itemView.findViewById(R.id.vkladRub);
            dol=(TextView)itemView.findViewById(R.id.vkladDol);
            euro=(TextView)itemView.findViewById(R.id.vkladEuro);

            logo=(ImageView)itemView.findViewById(R.id.bankImage);

            vkladCardView=(CardView)itemView.findViewById(R.id.vkladCardView);
        }
    }
}
