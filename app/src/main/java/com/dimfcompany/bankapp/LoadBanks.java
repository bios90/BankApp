package com.dimfcompany.bankapp;

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

public class LoadBanks
{
    static ModelBank bank;
    static RequestQueue.RequestFinishedListener finListener;

    public static void LoadBank(final Context ctx, final int id, final RequestQueue.RequestFinishedListener finListener)
    {
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
                        ModelBank newBank = new ModelBank();

                        newBank.setId(object.getInt("Id"));
                        newBank.setName(object.getString("Name"));
                        newBank.setAdress(object.getString("Adress"));
                        newBank.setLogo(object.getString("Logo"));
                        newBank.setPhone(object.getString("Phone"));
                        newBank.setSite(object.getString("Site"));

                        double[] minmaxs = new double[9];

                        minmaxs[0]=object.getDouble("vkladR");
                        minmaxs[1]=object.getDouble("vkladD");
                        minmaxs[2]=object.getDouble("vkladE");
                        minmaxs[3]=object.getDouble("cardR");
                        minmaxs[4]=object.getDouble("cardD");
                        minmaxs[5]=object.getDouble("cardE");
                        minmaxs[6]=object.getDouble("creditR");
                        minmaxs[7]=object.getDouble("creditD");
                        minmaxs[8]=object.getDouble("creditE");

                        newBank.setMinmax(minmaxs);
                        bank=newBank;
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
        //endregion

        String query="SELECT * FROM `banks` WHERE `id`="+id;
        ReqMakerBank myrequest = new ReqMakerBank(query, listener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finListener);

    }

}
