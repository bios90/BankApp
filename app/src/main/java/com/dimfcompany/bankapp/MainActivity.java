package com.dimfcompany.bankapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{

    ToggleButton togg1,togg2,togg3;
    FrameLayout mainFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrag=(FrameLayout)findViewById(R.id.mainFragment);
        VkladFragOnCreate();


        togg1=(ToggleButton)findViewById(R.id.togg1);
        togg2=(ToggleButton)findViewById(R.id.togg2);
        togg3=(ToggleButton)findViewById(R.id.togg3);

        //region myOnclickListener
        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(v==togg1)
                {
                    togg2.setChecked(false);
                    togg3.setChecked(false);
                    togg2.setEnabled(true);
                    togg3.setEnabled(true);
                    togg1.setEnabled(false);
                    ChangeToVklad();
                }
                if(v==togg2)
                {
                    togg1.setChecked(false);
                    togg3.setChecked(false);
                    togg1.setEnabled(true);
                    togg3.setEnabled(true);
                    togg2.setEnabled(false);
                    ChangeToCreditCard();
                }
                if(v==togg3)
                {
                    togg1.setChecked(false);
                    togg2.setChecked(false);
                    togg1.setEnabled(true);
                    togg2.setEnabled(true);
                    togg3.setEnabled(false);
                    ChangeToCredit();
                }
            }
        };
        //endregion

        togg1.setChecked(true);
        togg1.setEnabled(false);

        togg1.setOnClickListener(myOnClickListener);
        togg2.setOnClickListener(myOnClickListener);
        togg3.setOnClickListener(myOnClickListener);

    }


    void VkladFragOnCreate()
    {
        Fragment fragment=new VkladFrag();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(mainFrag.getId(),fragment,"Vklad");
        ft.commit();
    }

    void ChangeToCreditCard()
    {
        VkladFrag vklad = (VkladFrag)getSupportFragmentManager().findFragmentByTag("Vklad");
        FragCredit card = (FragCredit) getSupportFragmentManager().findFragmentByTag("Credit");
        if(vklad!=null && vklad.isVisible())
        {
            Fragment fragment = new FragCredCard();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            ft.replace(mainFrag.getId(), fragment);
            ft.addToBackStack(null);
            ft.commit();
            return;
        }
        if(card!=null && card.isVisible())
        {
            Fragment fragment = new FragCredCard();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            ft.replace(mainFrag.getId(), fragment);
            ft.addToBackStack(null);
            ft.commit();
            return;
        }
    }

    void ChangeToVklad()
    {

            Fragment fragment=new VkladFrag();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
            ft.replace(mainFrag.getId(),fragment,"Vklad");
            ft.addToBackStack(null);
            ft.commit();
    }

    void ChangeToCredit()
    {

            Fragment fragment = new FragCredit();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            ft.replace(mainFrag.getId(), fragment,"Credit");
            ft.addToBackStack(null);
            ft.commit();
    }
}
