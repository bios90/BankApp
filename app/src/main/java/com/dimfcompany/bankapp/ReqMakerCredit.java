package com.dimfcompany.bankapp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReqMakerCredit extends StringRequest
{
      private static final String REGISTER_REQUEST_URL = "http://bios90.myjino.ru/creditquery.php";

      private Map<String,String> params;

      public ReqMakerCredit(String que, Response.Listener<String> listener, Response.ErrorListener errorListener)
      {
            super(Request.Method.POST,REGISTER_REQUEST_URL,listener,errorListener);
            params=new HashMap<>();
            params.put("que",que);
      }

      @Override
      public Map<String, String> getParams()
      {
            return params;
      }
}
