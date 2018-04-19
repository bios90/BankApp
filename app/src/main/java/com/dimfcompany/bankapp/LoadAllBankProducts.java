package com.dimfcompany.bankapp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadAllBankProducts
{
    public static List<ModelVklad> vklads;
    public static List<ModelCard> cards;
    public static List<ModelCredit> credits;
    static RequestQueue.RequestFinishedListener finlis;

    public static void GetAallProducts(final Context ctx,final int id,final RequestQueue.RequestFinishedListener finListener)
    {
        Context context=ctx;
        int bankID=id;
        finlis=finListener;
        LoadVklads(context,bankID);
    }

    static void LoadVklads(final Context ctx,final int id)
    {
        vklads=new ArrayList<>();
        //region ResponseListener
        final Response.Listener<String> listener =new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                JSONArray jsonArray = null;
                try
                {
                    jsonArray = new JSONArray(response);

                } catch (JSONException e)
                {
                    PageViewActivity.sd.dismiss();
                    PageViewActivity.ShowToast(ctx,"Ошибка чтения данных,повторите запрос позже.");

                }
                int count = 0;
                while (count < jsonArray.length()) {
                    try
                    {
                        JSONObject object = jsonArray.getJSONObject(count);
                        ModelVklad newVklad = new ModelVklad();

                        newVklad.setId(object.getInt("id"));
                        newVklad.setName(object.getString("name"));
                        newVklad.setBank_id(object.getInt("id_bank"));
                        newVklad.setBankName(object.getString("bank_name"));
                        newVklad.setLogo(object.getString("logo"));

                        newVklad.setStavkarub(object.getDouble("rubstavka"));
                        newVklad.setStavkadol(object.getDouble("dolstavka"));
                        newVklad.setStavkaeuro(object.getDouble("eurostavka"));

                        newVklad.setRubsymma(object.getInt("rubsymma"));
                        newVklad.setDolsymma(object.getInt("dolsymma"));
                        newVklad.setEurosymma(object.getInt("eurosymma"));

                        newVklad.setRubsrok(object.getInt("rubsrok"));
                        newVklad.setDolsrok(object.getInt("dolsrok"));
                        newVklad.setEurosrok(object.getInt("eurosrok"));

                        newVklad.setPopoln(object.getInt("popol")==1?true:false);
                        newVklad.setSnyatie(object.getInt("snyatie")==1?true:false);
                        newVklad.setRastorj(object.getInt("rastorj")==1?true:false);

                        vklads.add(newVklad);
                        count++;

                    } catch (JSONException e)
                    {
                        PageViewActivity.sd.dismiss();
                        PageViewActivity.ShowToast(ctx,"Ошибка чтения данных,повторите запрос позже.");
                    }

                }

            }
        };
        //endregion

        //region ErrorListener
        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        PageViewActivity.sd.dismiss();
                        PageViewActivity.ShowToast(ctx,"Отсутсвует соединение с сетью,проверьте настройки устройства.");
                    }
                }, 450);



            }
        };
        //endregion

        //region FinListener
        RequestQueue.RequestFinishedListener finishedListener = new RequestQueue.RequestFinishedListener()
        {
            @Override
            public void onRequestFinished(Request request)
            {
                LoadCards(ctx, id);
            }
        };
        //endregion

        String query="SELECT * FROM `vklad` WHERE `id_bank`="+id;
        ReqMakerVklad myrequest = new ReqMakerVklad(query, listener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finishedListener);

    }

    static void LoadCards(final Context ctx,final  int id)
    {
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
                    PageViewActivity.ShowToast(ctx,"Ошибка чтения данных,повторите запрос позже.");
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
                        PageViewActivity.ShowToast(ctx,"Ошибка чтения данных,повторите запрос позже.");
                    }
                }



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
                        PageViewActivity.ShowToast(ctx,"Отсутсвует соединение с сетью,проверьте настройки устройства.");;
                    }
                }, 450);


            }
        };
        //endregion

        RequestQueue.RequestFinishedListener finishedListener = new RequestQueue.RequestFinishedListener()
        {
            @Override
            public void onRequestFinished(Request request)
            {
                LoadCredits(ctx, id);
            }
        };

        String query="SELECT * FROM `card` WHERE `id_bank`="+id;
        ReqMakerCard myRequest =new ReqMakerCard(query,listener,errorListener);
        queue.add(myRequest);
        queue.addRequestFinishedListener(finishedListener);

    }

    static void LoadCredits(final Context ctx,final  int id)
    {
        credits=new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(ctx);

        //region ResponseListener
        final Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                JSONArray jsonArray = null;
                try {
                    jsonArray=new JSONArray(response);
                } catch (JSONException e)
                {
                    Activity activity=(Activity)ctx;
                    PageViewActivity.ShowToast(ctx,"Ошибка чтения данных,повторите запрос позже.");
                }
                int count = 0;
                while (count<jsonArray.length())
                {
                    try
                    {
                        JSONObject object = jsonArray.getJSONObject(count);
                        ModelCredit newCredit = new ModelCredit();

                        newCredit.setId(object.getInt("id"));
                        newCredit.setName(object.getString("name"));
                        newCredit.setBank_id(object.getInt("id_bank"));
                        newCredit.setBankName(object.getString("bank_name"));
                        newCredit.setLogo(object.getString("logo"));

                        newCredit.setStavkarubcredit(object.getDouble("rubstavkacredit"));
                        newCredit.setStavkadolcredit(object.getDouble("dolstavkacredit"));
                        newCredit.setStavkaeurocredit(object.getDouble("eurostavkacredit"));

                        newCredit.setRubsymmacredit(object.getInt("rubsymmacredit"));
                        newCredit.setDolsymmacredit(object.getInt("dolsymmacredit"));
                        newCredit.setEurosymmacredit(object.getInt("eurosymmacredit"));

                        newCredit.setRubsrokcredit(object.getInt("rubsrokcredit"));
                        newCredit.setDolsrokcredit(object.getInt("dolsrokcredit"));
                        newCredit.setEurosrokcredit(object.getInt("eurosrokcredit"));

                        newCredit.setRefinans(object.getInt("refinans")==1?true:false);
                        newCredit.setObes(object.getInt("obes")==1?true:false);
                        newCredit.setTolkopass(object.getInt("tolkopass")==1?true:false);

                        newCredit.setStaj(object.getInt("staj"));
                        newCredit.setPodtverj(object.getInt("podtverj"));

                        credits.add(newCredit);
                        count++;
                    } catch (JSONException e)
                    {
                        Activity activity=(Activity)ctx;
                        PageViewActivity.ShowToast(ctx,"Ошибка чтения данных,повторите запрос позже.");
                    }

                }

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
                        PageViewActivity.ShowToast(ctx,"Отсутсвует соединение с сетью,проверьте настройки устройства.");
                    }
                }, 450);

            }
        };
        //endregion

        String query ="SELECT * FROM `credit` WHERE `id_bank`="+id;
        ReqMakerCredit myrequest = new ReqMakerCredit(query, listener, errorListener);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finlis);
    }

}

