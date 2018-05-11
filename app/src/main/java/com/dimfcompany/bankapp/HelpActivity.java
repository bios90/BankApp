package com.dimfcompany.bankapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dmax.dialog.SpotsDialog;

public class HelpActivity extends AppCompatActivity {

    Button postHelp;
    Spinner helpType;
    ArrayAdapter<String> adapter;
    int choosedtype;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    EditText name,surName,phone,comment,mail;

    public static final String BANK_APP_EMAIL = "moibankapp@gmail.com";
    public static final String BANK_APP_PASSWORD = "AA12411241aa";
    public static final String SUBJECT = "Новое уведомление из приложения Мой Банк";
    public static final String ARTEM_MAIL = "mybank_official@mail.ru";

    Session session = null;

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
        mail=(EditText)findViewById(R.id.helpMail);

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
                if(PageViewActivity.isInternetOn(HelpActivity.this))
                {
                    if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(surName.getText().toString()))
                    {

                        if (!TextUtils.isEmpty(mail.getText().toString()) || !TextUtils.isEmpty(phone.getText().toString()))
                        {
                            postHelp.setEnabled(false);
                            final SpotsDialog sd;
                            sd = new SpotsDialog(HelpActivity.this, R.style.Custom);
                            sd.show();
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

                            final String mailStr = "Имя :" + name.getText().toString() + "\nФамилия :" + surName.getText().toString() + " \nТелефон :" + phone.getText().toString()
                                    + "\nПочта :" + mail.getText().toString()
                                    + "\nДата :" + Calendar.getInstance().getTime().toString()
                                    + "\nВопрос : \n" + comment.getText().toString();

                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Log.e("SENDMAIL", "onClick: TRying to sendddd");
                                    GMailSender sender = new GMailSender(BANK_APP_EMAIL, BANK_APP_PASSWORD);
                                    try
                                    {
                                        sender.sendMail(SUBJECT,
                                                mailStr,
                                                BANK_APP_EMAIL,
                                                ARTEM_MAIL);
                                    } catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                    Log.e("EndSENDDd", "onClick: ENDSENDDDDDDD");
                                    sd.dismiss();
                                    finish();
                                }
                            }).start();


                            BanksShow.newRequestsaved = 99;
                        } else
                        {
                            PageViewActivity.ShowToast(HelpActivity.this, "Заполните обязательные поля : Имя, Фамилия, Телефон или Email.");
                        }

                    } else
                    {
                        PageViewActivity.ShowToast(HelpActivity.this, "Заполните обязательные поля : Имя, Фамилия, Телефон или Email.");
                    }
                }
                else
                    {
                        PageViewActivity.ShowToast(HelpActivity.this, "Отсутсвует соединение с сетью, проверьте настройки или повторите запрос позже.");
                    }
        }
        });

    }

    class RetrieveFeedTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("90bios90@gmail.com"));
                String rec =  "dimfcompany@gmail.com";
                message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(rec));
                message.setSubject("Subject");
                message.setText("раз два три четыре пять вышел зайчик погулять","text/html; charset=utf-8");

                Transport.send(message);
            }
            catch (Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            Log.e("SDFSADF", "onPostExecute: All okk" );
        }
    }
}
