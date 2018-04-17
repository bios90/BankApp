package com.dimfcompany.bankapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadCard
{
    static List<ModelCard> cards;
    static RequestQueue.RequestFinishedListener finishedListener;

    public static void GetCards(final String query, final Context ctx, final RequestQueue.RequestFinishedListener finListener)
    {
        finishedListener=finListener;
        cards=new ArrayList<>();

        RequestQueue queue= Volley.newRequestQueue(ctx);
        //region ResponseListener
        final Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                JSONArray jsonArray = null;
                try
                {
                    jsonArray=new JSONArray(response);
                } catch (JSONException e)
                {
                    PageViewActivity.sd.dismiss();
                    Activity activity=(Activity)ctx;
                    DialogCustom dialog= new DialogCustom(activity,"Ошибка чтения данных,повторите запрос позже.");
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    Window window=dialog.getWindow();
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

                }
                int count = 0;
                while (count<jsonArray.length())
                {
                    try
                    {
                        JSONObject object = jsonArray.getJSONObject(count);
                        ModelCard newCard=new ModelCard();

                        newCard.setId(object.getInt("id"));
                        newCard.setName(object.getString("name"));
                        newCard.setId_bank(object.getInt("id_bank"));
                        newCard.setBankName(object.getString("bank_name"));
                        newCard.setLogo(object.getString("logo"));

                        newCard.setStavkaRub(object.getDouble("stavkarubmin"));
                        newCard.setStavkaDol(object.getDouble("stavkadolmin"));
                        newCard.setStavkaEuro(object.getDouble("stavkaeuromin"));

                        newCard.setRubLimit(object.getInt("rublimit"));
                        newCard.setDolLimit(object.getInt("dollimit"));
                        newCard.setEuroLimit(object.getInt("eurolimit"));

                        newCard.setBesplatnoeO(object.getInt("besplatnoeo"));
                        newCard.setObsluj(object.getInt("stoimostobslj"));
                        newCard.setCashBack(object.getInt("cashback"));
                        newCard.setNalsnyatie(object.getDouble("nalsnyatie"));
                        newCard.setLgotSrok(object.getInt("lgotsrok"));
                        newCard.setLgotType(object.getInt("lgottype"));
                        newCard.setAge(object.getInt("age"));
                        newCard.setCardPodtverj(object.getInt("cardpodtverj"));
                        newCard.setRegister(object.getInt("register"));
                        newCard.setCardStaj(object.getInt("cardstaj"));
                        newCard.setDostavka(object.getInt("dostavka"));
                        newCard.setReshenie(object.getInt("reshenie"));

                        cards.add(newCard);
                        count++;
                    }
                    catch (JSONException e)
                    {
                        PageViewActivity.sd.dismiss();
                        Activity activity=(Activity)ctx;
                        DialogCustom dialog= new DialogCustom(activity,"Ошибка чтения данных,повторите зпарос позже.");
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        Window window=dialog.getWindow();
                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

                    }
                }


                // Toast.makeText(ctx,cards.size()+"",Toast.LENGTH_SHORT).show();
            }
        };
        //endregion
        //region ErrorListener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        PageViewActivity.sd.dismiss();
                        Activity activity=(Activity)ctx;
                        DialogCustom dialog= new DialogCustom(activity);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        Window window=dialog.getWindow();
                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    }
                }, 450);


            }
        };
        //endregion

        ReqMakerCard myRequest =new ReqMakerCard(query,listener,errorListener);
        queue.add(myRequest);
        queue.addRequestFinishedListener(finishedListener);

    }
}
