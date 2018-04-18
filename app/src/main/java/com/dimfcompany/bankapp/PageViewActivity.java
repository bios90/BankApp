package com.dimfcompany.bankapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class PageViewActivity extends AppCompatActivity {

    TabLayout myTabs;
    ViewPager myPager;
    public FragmentManager fManager;
    public FragCredCard currentCard;
    public FragCredit currentCredit;
    public FragVklad currentVklad;

    Button showButton;

    public static SpotsDialog sd ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);

        sd= new SpotsDialog(PageViewActivity.this, R.style.Custom);

        myTabs=(TabLayout)findViewById(R.id.pageViewTabs);
        myPager=(ViewPager)findViewById(R.id.myViewPage);
        myTabs.setupWithViewPager(myPager);

        SetUpPager(myPager);

        myTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                int pos= tab.getPosition();
                switch (pos)
                {
                    case 1:
                        myPager.setCurrentItem(1);
                        currentCard.showButton();
                        break;
                    case 0:
                        myPager.setCurrentItem(0);
                        currentVklad.ShowButton();
                        break;
                    case 2:
                        myPager.setCurrentItem(2);
                        currentCredit.ShowButton();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });



        showButton=(Button)findViewById(R.id.showBanksButt);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currentVklad.ShowButton();
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                showButton.performClick();
            }
        }, 250);
    }

    public static void ShowToast(Context ctx,String msg)
    {
        Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(16);
        toastMessage.setTextColor(ctx.getResources().getColor(R.color.orange));
        float scale = ctx.getResources().getDisplayMetrics().density;
        int lefrright = (int) (16 * scale + 0.5f);
        int topbottom = (int) (4 * scale + 0.5f);
        toastMessage.setPadding(lefrright, 8, lefrright, 8);
        toastMessage.setCompoundDrawablePadding(16);
        toastView.setBackground(ctx.getResources().getDrawable(R.drawable.toastbg));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static Boolean isInternetOn(Context ctx)
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public void SetUpPager(ViewPager vp)
    {
        fManager=getSupportFragmentManager();
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(fManager);
        adapter.AddFrag(new FragVklad(),"Вклады");
        adapter.AddFrag(new FragCredCard(),"Карты");
        adapter.AddFrag(new FragCredit(),"Кредиты");

        vp.setAdapter(adapter);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter
    {
        private List<Fragment> myFragments=new ArrayList<>();
        private List<String> fragtitles = new ArrayList<>();


        public MyViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        public void AddFrag(Fragment fragment,String title)
        {
            myFragments.add(fragment);
            fragtitles.add(title);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return myFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragtitles.get(position);
        }


        @Override
        public Fragment getItem(int position)
        {

            return myFragments.get(position);
        }

    }
}
