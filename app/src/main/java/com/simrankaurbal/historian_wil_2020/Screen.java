package com.simrankaurbal.historian_wil_2020;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;

public class Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ImageButton profile;
    public ImageButton visa;
    public  ImageButton hotels;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public ImageView imageView;
    public TextView fname;
    public TextView lname;



    Demo demo;
    double letValueMain ;
    double longValueMain;

    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private  static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);


       // startActivityForResult(new Intent(getApplicationContext(),ProfilePage.class), 999);
        //imageView = (ImageView) findViewById(R.id.imageView);
//        final String img = getIntent().getExtras().getString("profileimage");
//        Glide.with(this).load(img).into(imageView);
//
       // fname = (TextView) findViewById(R.id.fname);
//        String fname = getIntent().getExtras().getString("firstname");
//
       // lname = (TextView) findViewById(R.id.lname);
//        String lname = getIntent().getExtras().getString("lastname");

        visa = (ImageButton) findViewById(R.id.visa);
        visa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Screen.this, PaymentPage.class);
                startActivity(intent);

//                Toast.makeText(getBaseContext(),"Visa Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        hotels = (ImageButton) findViewById(R.id.hotels);

        hotels.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Screen.this);

                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                @SuppressLint("MissingPermission")
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                longValueMain = location.getLongitude();
                Log.d("TAG:", "onResponse1: "+longValueMain);

                letValueMain = location.getLatitude();
                Log.d("TAG:", "onResponse1: "+letValueMain);

                Intent intent = new Intent(Screen.this, Demo.class);
                intent.putExtra("latitude", letValueMain);
                intent.putExtra("longitude", longValueMain);
                startActivity(intent);
//                Toast.makeText(getBaseContext(),"Hotels Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        profile = (ImageButton) findViewById(R.id.profile);
//        profile = (ImageButton) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(Screen.this, ProfilePage.class);
                startActivity(intent);
//                Toast.makeText(getBaseContext(), "Profile Button Clicked!" , Toast.LENGTH_SHORT ).show();

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
       // drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


    }

//    // first made this method in this class  for point 2 check Profile class
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 999 && resultCode == RESULT_OK)
//        {
//            // 3. adding rest of the data
//            fname.setText(data.getStringExtra("firstname"));
//            lname.setText(data.getStringExtra("lastname"));
//
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if (id == R.id.mainmenu)
        {
            Intent intent = new Intent(Screen.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "This is Main Menu Page", Toast.LENGTH_SHORT).show();

        }
        return false;

    }



}
