package com.dimfcompany.bankapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DialogCustom extends Dialog implements android.view.View.OnClickListener
{


        public Activity c;
        public Dialog d;
        public Button ok;
        public TextView msgTV,title;
        public String msg;
        String titleStr;

        public DialogCustom(Activity a)
            {
                super(a);
                this.c = a;
            }



    public DialogCustom(Activity a,String msg) {
        super(a);
        this.msg=msg;
        this.c = a;
    }

    public DialogCustom(Activity a,String msg,String titleStr)
    {
        super(a);
        this.msg=msg;
        this.c = a;
        this.titleStr=titleStr;
    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog);
            msgTV=(TextView)findViewById(R.id.dialogErrorMSG);
            title=(TextView)findViewById(R.id.dialogTitle);
            ok=(Button)findViewById(R.id.dialogOKButt);
            ok.setOnClickListener(this);

            if(!TextUtils.isEmpty(msg))
            {
                msgTV.setText(msg);
            }
            if(!TextUtils.isEmpty(titleStr))
            {
                title.setText(titleStr);
            }

        }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.dialogOKButt:
                dismiss();
                break;
        }
    }

//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btn_yes:
//                    c.finish();
//                    break;
//                case R.id.btn_no:
//                    dismiss();
//                    break;
//                default:
//                    break;
//            }
//            dismiss()
//
}
