package com.dimfcompany.bankapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class HelpActivity extends AppCompatActivity {

    Button postHelp;
    Spinner helpType;
    ArrayAdapter<String> adapter;
    int choosedtype;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    EditText name,surName,phone,comment;

    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        helpType=(Spinner)findViewById(R.id.spinneHelp);
        postHelp=(Button)findViewById(R.id.postHelp);

        name=(EditText)findViewById(R.id.helpName);
        surName=(EditText)findViewById(R.id.helpSurname);
        phone=(EditText)findViewById(R.id.helpPhone);
        comment=(EditText)findViewById(R.id.helpQuestion);

        adapter=new ArrayAdapter<String>(HelpActivity.this,R.layout.spinner_item_dark,getResources().getStringArray(R.array.help));
        helpType.setAdapter(adapter);
        helpType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                choosedtype=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        postHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(surName.getText().toString()) && !TextUtils.isEmpty(phone.getText().toString())) {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    ref = firebaseDatabase.getReference();
                    DatabaseReference newHelp = ref.child("Help").push();
                    newHelp.child("Name").setValue(name.getText().toString());
                    newHelp.child("Surname").setValue(surName.getText().toString());
                    newHelp.child("Phone").setValue(phone.getText().toString());
                    newHelp.child("Comment").setValue(comment.getText().toString());
                    newHelp.child("Type").setValue(choosedtype);

                    Date currentTime = Calendar.getInstance().getTime();
                    Log.e("Date!!", String.valueOf(currentTime));
                    newHelp.child("Date").setValue(String.valueOf(currentTime));

                    BanksShow.newRequestsaved=99;
                    finish();

                }
                else
                    {
                        PageViewActivity.ShowToast(HelpActivity.this,"Заполните обязательные поля : Имя, Фамилия, Телефон.");
                    }
        }
        });
    }
}
