package com.dimfcompany.bankapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
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

public class LoadCredit
{
    static List<ModelCredit> credits;
    static RequestQueue.RequestFinishedListener finishListener;

    public static void GetCredits(final String query, final Context ctx,final  RequestQueue.RequestFinishedListener finListener)
    {
        finishListener=finListener;
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
//                    DialogCustom dialog= new DialogCustom(activity,"Ошибка чтения данных,повторите запрос позже.");
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    dialog.show();
//                    Window window=dialog.getWindow();
//                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

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
//                        DialogCustom dialog= new DialogCustom(activity,"Ошибка чтения данных,повторите запрос позже.");
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog.show();
//                        Window window=dialog.getWindow();
//                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

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
//                        Activity activity=(Activity)ctx;
//                        DialogCustom dialog= new DialogCustom(activity);
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog.show();
//                        Window window=dialog.getWindow();
//                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    }
                }, 450);

            }
        };
        //endregion

        ReqMakerCredit myrequest = new ReqMakerCredit(query, listener, errorListener);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finishListener);
    }
}
