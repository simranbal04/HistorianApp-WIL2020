package com.simrankaurbal.historian_wil_2020;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ImageButton profile;
    public ImageButton visa;
    public  ImageButton hotels;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    Demo demo;
    double letValueMain ;
    double longValueMain;

    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private  static final int REQUEST_CODE = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.main_menu);

        visa = (ImageButton) findViewById(R.id.visa);
        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, PaymentPage.class);
                startActivity(intent);

                Toast.makeText(getBaseContext(),"Visa Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        hotels = (ImageButton) findViewById(R.id.hotels);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainMenu.this);

                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                @SuppressLint("MissingPermission")
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                longValueMain = location.getLongitude();
                Log.d("TAG:", "onResponse1: "+longValueMain);

                letValueMain = location.getLatitude();
                Log.d("TAG:", "onResponse1: "+letValueMain);

                Intent intent = new Intent(MainMenu.this, Demo.class);
                intent.putExtra("latitude", letValueMain);
                intent.putExtra("longitude", longValueMain);
                startActivity(intent);
                Toast.makeText(getBaseContext(),"Hotels Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        profile = (ImageButton) findViewById(R.id.profile1);
//        profile = (ImageButton) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainMenu.this, ProfilePage.class);
                startActivity(intent);
//                Toast.makeText(getBaseContext(), "Profile Button Clicked!" , Toast.LENGTH_SHORT ).show();

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


    }

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
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "This is Main Menu Page", Toast.LENGTH_SHORT).show();

            }
            return false;

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
