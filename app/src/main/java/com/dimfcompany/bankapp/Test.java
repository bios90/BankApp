package com.dimfcompany.bankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    TextView[] texts;
    DatabaseReference Banks;
    String newName;
    List<BankModel> bankList;

    Button btn1,btn2,btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        texts=new TextView[]{(TextView)findViewById(R.id.textTV),(TextView)findViewById(R.id.textView),(TextView)findViewById(R.id.textView2)};

        bankList=new ArrayList<>();

        btn1=(Button)findViewById(R.id.button2);
        btn2=(Button)findViewById(R.id.button3);
        btn3=(Button)findViewById(R.id.button4);

        View.OnClickListener buttons = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.button2:

                        break;
                    case R.id.button3:

                        break;
                    case R.id.button4:

                        break;
                }

            }
        };

        btn1.setOnClickListener(buttons);
        btn2.setOnClickListener(buttons);
        btn3.setOnClickListener(buttons);

        Banks = FirebaseDatabase.getInstance().getReference("Banks");

       Banks.orderByKey().addValueEventListener(new ValueEventListener() {

           int order=0;
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot child : dataSnapshot.getChildren())
               {
                   String bankName = child.getKey().toString();
                   String adress = child.child("adress").getValue(String.class);
                   String logo = child.child("logo").getValue(String.class);
                   String phone = child.child("phone").getValue(String.class);
                   String site = child.child("site").getValue(String.class);

                   BankModel newbank = new BankModel();

                   newbank.setName(bankName);
                   newbank.setAdress(adress);
                   newbank.setLogo(logo);
                   newbank.setPhone(phone);
                   newbank.setSite(site);

                   bankList.add(newbank);
               }

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }
}
