package com.dimfcompany.bankapp;

import android.content.Context;

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

public class GetInfoStatic {

    static List<ModelBank> bankList;
    //static List<ModelBank> bankswithminmaks;
    static RequestQueue.RequestFinishedListener finlis;

    public static void GetInfo(final String query, final Context ctx, final RequestQueue.RequestFinishedListener finlisener) {
        finlis = finlisener;
        bankList = new ArrayList<>();

        //region ResponseListener
        final Response.Listener<String> jsonListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    bankList.clear();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int count = 0;
                while (count < jsonArray.length()) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(count);
                        ModelBank newbank = new ModelBank();
                        newbank.setId(object.getInt("Id"));
                        newbank.setName(object.getString("Name"));
                        newbank.setAdress(object.getString("Adress"));
                        newbank.setLogo(object.getString("Logo"));
                        newbank.setPhone(object.getString("Phone"));
                        newbank.setSite(object.getString("Site"));

                        double[] minmax = new double[9];
                        minmax[0]=object.getDouble("1");
                        minmax[1]=object.getDouble("2");
                        minmax[2]=object.getDouble("3");
                        minmax[3]=object.getDouble("4");
                        minmax[4]=object.getDouble("5");
                        minmax[5]=object.getDouble("6");
                        minmax[6]=object.getDouble("7");
                        minmax[7]=object.getDouble("8");
                        minmax[8]=object.getDouble("9");

                        newbank.setMinmax(minmax);
                        bankList.add(newbank);
                        count++;

                    } catch (JSONException e) {

                    }
                }
            }
        };
        //endregion
        //region Errorlistener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        //endregion
        //region FinishListenerOld
        RequestQueue.RequestFinishedListener finishedListener = new RequestQueue.RequestFinishedListener() {
            @Override
            public void onRequestFinished(Request request) {


                //UploadMinMax(bankList, ctx);

            }
        };
        //endregion

        RequestMaker myrequest = new RequestMaker(query, jsonListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finlis);
    }


    //region UploadMinmaxOld
     /*   public static void UploadMinMax(final List<ModelBank> banks, final Context ctx) {
        List<Integer> neededid = new ArrayList<Integer>(banks.size());

        //region forming query string
        for (ModelBank bank : banks) {
            int i = bank.getId();
            neededid.add(i);
        }

        String query = "Select * from minmax where id=" + String.valueOf(neededid.get(0));

        for (int a = 1; a < neededid.size(); a++)
        {
            String add = " or id=" + String.valueOf(neededid.get(a));
            query = query + add;
        }

        //endregion

        //region MinMaxREsponseLisener
        final Response.Listener<String> jsonListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {

                    e.printStackTrace();
                }

                int count = 0;
                while (count < jsonArray.length())
                    try
                    {

                        double[] numstoset = new double[9];

                        JSONObject object = jsonArray.getJSONObject(count);


                        numstoset[0] = object.getDouble("1");
                        numstoset[1] = object.getDouble("2");
                        numstoset[2] = object.getDouble("3");
                        numstoset[3] = object.getDouble("4");
                        numstoset[4] = object.getDouble("5");
                        numstoset[5] = object.getDouble("6");
                        numstoset[6] = object.getDouble("7");
                        numstoset[7] = object.getDouble("8");
                        numstoset[8] = object.getDouble("9");

                        bankList.get(count).setMinmax(numstoset);
                        count++;

                    } catch (JSONException e)
                    {

                    }

            }
        };
        //endregion

        //region errorlisener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        //endregion

        MinMaxRequestMaker myrequest = new MinMaxRequestMaker(query, jsonListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(myrequest);
        queue.addRequestFinishedListener(finlis);
    }
    */
    //endregion
}
