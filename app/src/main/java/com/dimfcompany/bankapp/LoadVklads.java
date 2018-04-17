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

public class LoadVklads
{
    static List<ModelVklad> vkladList;
    static RequestQueue.RequestFinishedListener finishListener;

    public static void GetVklads(final String query, final Context ctx, final RequestQueue.RequestFinishedListener finlisener)
    {
        finishListener=finlisener;
        vkladList=new ArrayList<>();

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
                    Activity activity=(Activity)ctx;
                    DialogCustom dialog= new DialogCustom(activity,"Ошибка чтения данных,повторите запрос позже.");
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    Window window=dialog.getWindow();
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

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

                        double test =object.getDouble("rubstavka");
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

                        vkladList.add(newVklad);
                        count++;

                    } catch (JSONException e)
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
                        Activity activity=(Activity)ctx;
                        DialogCustom dialog= new DialogCustom(activity);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        Window window=dialog.getWindow();
                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    }
                }, 450);


                //Toast.makeText(ctx, "Cant connect error", Toast.LENGTH_SHORT).show();
            }
        };
    //endregion

        ReqMakerVklad myrequest = new ReqMakerVklad(query, listener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finishListener);

    }
}
