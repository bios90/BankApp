package com.dimfcompany.bankapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.BankItemViewHolder>
{
    Context ctx;
    List<ModelBank> listToShow;

    public BankListAdapter()
    {

    }

    public BankListAdapter(Context ctx, List<ModelBank> listToShow) {
        this.ctx = ctx;
        this.listToShow = listToShow;
    }

    @Override
    public BankItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view=inflater.inflate(R.layout.bank_item,parent,false);
        BankItemViewHolder holder = new BankItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BankItemViewHolder holder, int position)
    {
        ModelBank bank = listToShow.get(position);

        //String[] vklads = bank.getVkladVrubl();
        //String[] crdits = bank.getCredCardRubl();

        String adress = bank.getAdress();
        int firstSpace =adress.indexOf(',');
        if(firstSpace!=0)adress=adress.substring(firstSpace+2);

        holder.bankName.setText(bank.getName());
        holder.phone.setText(bank.getPhone());
        holder.site.setText(bank.getSite());

        if(!(bank.getMinmax()[0]==999.99))
        {
            holder.vkaldRub.setText("До " + String.valueOf(bank.getMinmax()[0])+"%");
        }
        else
        {
            holder.vkaldRub.setText("--");
        }

        if(!(bank.getMinmax()[1]==999.99))
        {
            holder.vkladDol.setText("До " + String.valueOf(bank.getMinmax()[1])+"%");
        }
        else
        {
            holder.vkladDol.setText("--");
        }

        if(!(bank.getMinmax()[2]==999.99))
        {
            holder.vkladEuro.setText("До " + String.valueOf(bank.getMinmax()[1])+"%");
        }
        else
        {
            holder.vkladEuro.setText("--");
        }
        //holder.adress.setText(adress);
        //holder.vklad.setText("от "+vklads[0]+"% до "+vklads[1]+"%");
        //holder.credit.setText("от "+crdits[0]+"% до "+crdits[1]+"%");
        //holder.vklad.setText("null Test null");
        //holder.credit.setText("null Test null");

        Picasso.get().load(bank.getLogo()).into(holder.bankImage);


    }

    @Override
    public int getItemCount() {
        return listToShow.size();
    }

    class BankItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView bankName,phone,site,vkaldRub,vkladDol,vkladEuro,adress,vklad,credit;
        ImageView bankImage;

        public BankItemViewHolder(View itemView) {
            super(itemView);

            bankName=(TextView)itemView.findViewById(R.id.bankName);
            phone=(TextView)itemView.findViewById(R.id.bankPhone);
            site=(TextView)itemView.findViewById(R.id.bankSite);
            vkaldRub=(TextView)itemView.findViewById(R.id.vkladRub);
            vkladDol=(TextView)itemView.findViewById(R.id.vkladDol);
            vkladEuro=(TextView)itemView.findViewById(R.id.vkladEuro);
//            adress=(TextView)itemView.findViewById(R.id.bankAdress);
//            vklad=(TextView)itemView.findViewById(R.id.bankVklad);
//            credit=(TextView)itemView.findViewById(R.id.bankCredit);

            bankImage=(ImageView)itemView.findViewById(R.id.bankImage);
        }
    }
}
