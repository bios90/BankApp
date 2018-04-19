package com.dimfcompany.bankapp;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.warkiz.widget.IndicatorSeekBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragVklad extends Fragment implements AdapterView.OnItemSelectedListener
{

    Button moreInfo,getBanks;
    ExpandableRelativeLayout expandMore;
    Spinner mySpinner;
    ArrayAdapter<String> adapter;

    CheckBox snyatie,popolnenie,rastorjenie;
    int spinerselected;

    ScrollView vkladScroll;

    IndicatorSeekBar procentSeek,distSeek;
    TextView procentTV,distTV;

    List<ModelBank> bankList;
    public static List<ModelVklad> vkladList;

    public static List<ModelBank> staticBanks;

    FirebaseDatabase db;
    DatabaseReference banksRef;

    public FragVklad() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_vklad_vp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        PageViewActivity pageV = (PageViewActivity)getActivity();
        pageV.currentVklad=this;



        Typeface sfReg=Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"myfonts/sanfran_regular.ttf");
        Typeface sfHeavy=Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"myfonts/sanfran_heavy.ttf");

        snyatie=(CheckBox)view.findViewById(R.id.ChBSnyatie);
        rastorjenie=(CheckBox)view.findViewById(R.id.ChBRastorj);
        popolnenie=(CheckBox)view.findViewById(R.id.ChBPopol);

        snyatie.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        rastorjenie.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));
        popolnenie.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.museosanscyrl_300));

        //region FirebaseTrash
        db=FirebaseDatabase.getInstance();
        banksRef=db.getReference("Banks");
        bankList=new ArrayList<>();
        //endregion
        //region Spinner
        mySpinner=(Spinner)view.findViewById(R.id.mySpinner);
        adapter=new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,getResources().getStringArray(R.array.valueSpinner));
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinerselected=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion
        //region ExpandLayout
        moreInfo=(Button)view.findViewById(R.id.moreInfoButton);
        expandMore=(ExpandableRelativeLayout)view.findViewById(R.id.expandInfo);
        vkladScroll = (ScrollView)view.findViewById(R.id.vkladScroll);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandMore.toggle();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        vkladScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 450);
            }
        });
        //endregion
        //region SeekValuListeners
//        distSeek=(IndicatorSeekBar)view.findViewById(R.id.distSeek);
//        distTV=(TextView)view.findViewById(R.id.distTV);
//        distSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
//                distTV.setText(String.valueOf(progressFloat));
//            }
//
//            @Override
//            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
//
//            }
//        });

        procentSeek=(IndicatorSeekBar)view.findViewById(R.id.procentSeek);
        procentSeek.getBuilder().setTextTypeface(sfHeavy);
        procentTV=(TextView)view.findViewById(R.id.procentTB) ;
        procentSeek.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
            {
                final float a = 10.0f;
                String str = String.valueOf(progressFloat/a);
                if(str.length()==3)str+="0";
                procentTV.setText(str);

            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        //endregion
        pageV.currentVklad=this;

    }
        //region VoidDataFromDataBaseOld
    /*private void ShowBanks()
    {
        banksRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot bank : dataSnapshot.getChildren())
                {
                    ModelBank newBank = new ModelBank();

                    newBank.setName(bank.child("name").getValue(String.class));
                    newBank.setAdress(bank.child("adress").getValue(String.class));
                    newBank.setLogo(bank.child("logo").getValue(String.class));
                    newBank.setPhone(bank.child("phone").getValue(String.class));
                    newBank.setSite(bank.child("site").getValue(String.class));
//                    newBank.setCredCardRubl(new String[]
//                            {
//                                    String.valueOf(bank.child("cred_card").child("min").getValue(Double.class)),
//                                    String.valueOf(bank.child("cred_card").child("max").getValue(Double.class))
//                            });
//                    newBank.setVkladVrubl(new String[]
//                            {
//                                    String.valueOf(bank.child("vklad_rubles").child("min").getValue(Double.class)),
//                                    String.valueOf(bank.child("vklad_rubles").child("max").getValue(Double.class))
//                            });

                    bankList.add(newBank);
                }

                staticBanks=bankList;
                Intent loadShow = new Intent(getActivity(),BanksShow.class);
                loadShow.putExtra("title",1);
                startActivity(loadShow);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    */
    //endregion
        //region NonStaticInfoGet
    private void GetInfo()
    {
        //region ResponseListener
        final Response.Listener<String> jsonListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    bankList.clear();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int count = 0;
                while (count<jsonArray.length())
                {
                    try
                    {
                        JSONObject object=jsonArray.getJSONObject(count);
                        ModelBank newbank = new ModelBank();
                        newbank.setId(object.getInt("Id"));
                        newbank.setName(object.getString("Name"));
                        newbank.setAdress(object.getString("Adress"));
                        newbank.setLogo(object.getString("Logo"));
                        newbank.setPhone(object.getString("Phone"));
                        newbank.setSite(object.getString("Site"));
                        bankList.add(newbank);
                        count++;

                    } catch (JSONException e)
                    {

                    }
                }
            }
        };
        //endregion
        //region Errorlistener
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        };
        //endregion
        //region FinishListener
        RequestQueue.RequestFinishedListener finishedListener = new RequestQueue.RequestFinishedListener() {
            @Override
            public void onRequestFinished(Request request)
            {
                staticBanks=bankList;
                Intent loadShow = new Intent(getActivity(),BanksShow.class);
                loadShow.putExtra("title",1);
                startActivity(loadShow);
            }
        };
        //endregion
        String query = "select * from banks";
        RequestMaker myrequest = new RequestMaker(query,jsonListener,errorListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(myrequest);
        queue.addRequestFinishedListener(finishedListener);



    }
    //endregion

    //region GetBanksButton
    public void ShowButton()
    {
        getBanks=(Button)getActivity().findViewById(R.id.showBanksButt);
        getBanks.setOnClickListener(null);
        getBanks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getBanks.setEnabled(false);
                String query = FormingQuery();

                RequestQueue.RequestFinishedListener finishedListener=new RequestQueue.RequestFinishedListener()
                {
                    @Override
                    public void onRequestFinished(Request request)
                    {
                        final SpotsDialog sd = PageViewActivity.sd;
                        sd.show();
                        vkladList=new ArrayList<>();
                        vkladList=LoadVklads.vkladList;
                        if(vkladList.size()>0)
                        {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {
                                    sd.dismiss();
                                    Intent loadShow = new Intent(getActivity(), BanksShow.class);
                                    loadShow.putExtra("title", 1);
                                    //loadShow.setFlags(loadShow.getFlags()|Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(loadShow);
                                }
                            }, 1200);

                        }
                        else if(PageViewActivity.isInternetOn(getContext()))
                            {

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        sd.dismiss();
                                        PageViewActivity.ShowToast(getContext(),"По вашему запросу вклады не найдены,попробуйте изменить запрос.");
//                                        DialogCustom dialog= new DialogCustom(getActivity(),"По вашему запросу вклады не найдены,попробуйте изменить запрос.");
//                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                                        dialog.show();
//                                        Window window=dialog.getWindow();
//                                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                                    }
                                }, 450);

                            }

                    }
                };
                LoadVklads.GetVklads(query,getContext(),finishedListener);
                getBanks.setEnabled(true);
            }
        });

    }
    //endregion

    String FormingQuery()
    {
        double stavka = Double.parseDouble(procentTV.getText().toString());
        String queryfinal="";

        int popol = popolnenie.isChecked()?1:0;
        int rastorj = rastorjenie.isChecked()?1:0;
        int snyat = snyatie.isChecked()?1:0;

        int spinerint = spinerselected;

        switch (spinerint)
        {
            case 0:
                String query = "Select * from `vklad` where ((`rubstavka` >="+stavka+" and `rubstavka` !=0.0) or (`dolstavka` >="+stavka+" and `dolstavka` !=0.0) or (`eurostavka` >="+stavka+" and `eurostavka` !=0.0))";// and `snyatie`=0 and `popolnenie`=0 and `rastorjenie` =0"
                if(popol==1)query+=" and popolnenie=1";
                if(snyat==1)query+=" and snyatie=1";
                if(rastorj==1)query+=" and rastorjenie=1";
                queryfinal=query;
                break;
            case 1:
                String queryrub = "Select * from `vklad` where (`rubstavka` >="+stavka+" and `rubstavka` !=0.0)";
                if(popol==1)queryrub+=" and popolnenie=1";
                if(snyat==1)queryrub+=" and snyatie=1";
                if(rastorj==1)queryrub+=" and rastorjenie=1";
                queryfinal=queryrub;
                break;
            case 2:
                String querydol = "Select * from `vklad` where (`dolstavka` >="+stavka+" and `dolstavka` !=0.0)";
                if(popol==1)querydol+=" and popolnenie=1";
                if(snyat==1)querydol+=" and snyatie=1";
                if(rastorj==1)querydol+=" and rastorjenie=1";
                queryfinal=querydol;
                break;
            case 3:
                String queryeuro = "Select * from `vklad` where (`eurostavka` >="+stavka+" and `eurostavka` !=0.0)";
                if(popol==1)queryeuro+=" and popolnenie=1";
                if(snyat==1)queryeuro+=" and snyatie=1";
                if(rastorj==1)queryeuro+=" and rastorjenie=1";
                queryfinal=queryeuro;
                break;
        }
        Log.e("SDFSDf",queryfinal);
        return queryfinal;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}



