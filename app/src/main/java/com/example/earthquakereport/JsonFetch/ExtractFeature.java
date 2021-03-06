package com.example.earthquakereport.JsonFetch;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.earthquakereport.ConnectAll.Model;
import com.example.earthquakereport.RecAdapter.EarthAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExtractFeature {

    private Context context;
    private String url;
    private ArrayList<Model> arrayList;
    private RecyclerView recyclerView;
    private EarthAdapter earthAdapter;
    public  ExtractFeature(Context context, String url, ArrayList<Model> arrayList, RecyclerView recyclerView){
        this.context = context;
        this.url = url;
        this.arrayList = arrayList;
        this.recyclerView = recyclerView;
    }

    public void extractFeature(){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("features");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("properties");
                        double mag = Double.parseDouble(jsonObject2.getString("mag"));
                        String place = jsonObject2.getString("place");
                        String url = jsonObject2.getString("url");
                        long time = jsonObject2.getLong("time");

                        arrayList.add(new Model(mag,place,url,time));

                    }

                    earthAdapter = new EarthAdapter(arrayList,context);
                    recyclerView.setAdapter(earthAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
