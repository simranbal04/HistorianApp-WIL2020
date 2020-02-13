package com.simrankaurbal.historian_wil_2020;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Demo extends AppCompatActivity {

//    private static final String URL_DATA ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=2000&type=museum&keyword=museum&key=AIzaSyAFRWscDI6hkVARcBPu5bvCgWCyaxHx8fI";
    private static final String URL_DATA = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=museum+in+Scarborough&key=AIzaSyAFRWscDI6hkVARcBPu5bvCgWCyaxHx8fI";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

//    Location location;


    private List<Listitem> listitems;

//    public Button click;
//    public static TextView data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listitems = new ArrayList<>();

        loadRecyclerViewData();


    }


    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonpint = jsonArray.getJSONObject(i);
                        Listitem item = new Listitem(jsonpint.getString("name"),
                                jsonpint.getString("rating"),
                                jsonpint.getString("icon"),jsonpint.getString("id"));
                        listitems.add(item);

                    }

                    adapter = new Myadapter(listitems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


//        for (int i = 0; i< 10;i ++){
//            Listitem listitem = new Listitem("heading" + (i+1),"Hello Welcome to the Developer mode");
//
//            listitems.add(listitem);
//        }

//        adapter = new Myadapter(listitems,this);
//
//        recyclerView.setAdapter(adapter);




        //click = (Button) findViewById(R.id.click);
//        data = (TextView) findViewById(R.id.data);

//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                fetchData process = new fetchData();
//                process.execute();
//
//            }
//        });

