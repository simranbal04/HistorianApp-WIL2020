package com.simrankaurbal.historian_wil_2020;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Demo extends AppCompatActivity {

//    public double longitude ;
//    public double latitude ;

    MainMenu mainMenu;

//    public final String strGoogleApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+longitude+","+latitude+"&radius=3500&type=museums&keyword=museums&key=AIzaSyAFRWscDI6hkVARcBPu5bvCgWCyaxHx8fI";



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    // current Location
    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private  static final int REQUEST_CODE = 101;

   // Location location;

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

       final Double letValue = getIntent().getDoubleExtra("latitude", 12.12);
       final  Double longValue = getIntent().getDoubleExtra("longitude", 13.13);

        final String strGoogleApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+letValue+","+longValue+"&radius=3500&type=museums&keyword=museums&key=AIzaSyAFRWscDI6hkVARcBPu5bvCgWCyaxHx8fI";


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, strGoogleApi, new Response.Listener<String>()
            {

                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    try {
                        Log.d("TAG:", "onResponse: " +strGoogleApi);


                        Log.d("myTag", "This is my json check message");
                        Toast.makeText(getApplicationContext(),longValue+"  "+letValue,Toast.LENGTH_SHORT).show();

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("results");

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonpint = jsonArray.getJSONObject(i);
                            Listitem item = new Listitem(jsonpint.getString("name"),
                                    jsonpint.getString("rating"),
                                    jsonpint.getString("icon"),jsonpint.getString("scope"));
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


       // loadRecyclerViewData();



//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        @SuppressLint("MissingPermission")
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        longitude = location.getLongitude();
//        Log.d("TAG:", "onResponse: "+longitude);
//
//        latitude = location.getLatitude();
//        Log.d("TAG:", "onResponse: "+latitude);

    }
//    Double letValue ;
//    Double longValue;





//            Log.d("TAG","NewLocationZ:" +letValuefro);
//    //longValue = mainMenu.longValueMain;
//            Log.d("TAG","NewLocationZ:" +longValuefro);
//
//
//    // letValue =  mainMenu.letValueMain;
//        Log.d("TAG","NewLocation:" +letValue);
//    //longValue = mainMenu.longValueMain;
//        Log.d("TAG","NewLocation:" +longValue);












