package com.simrankaurbal.historian_wil_2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MuseumsNearby extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ScrollView scrollView;

    public TextView welcometextview;
    public static ImageView imageView5;
    public ImageView imageView7;
    public static TextView location;
    public static TextView price;
    public TextView price2;
    public TextView price3;
    public TextView location1;
    public  TextView location2;
    public  TextView location3;

    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;



    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.museums_nearby);

        drawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        scrollView = (ScrollView) findViewById(R.id.scrollView);


        welcometextview = (TextView) findViewById(R.id.welcometextview);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView7 = (ImageView) findViewById(R.id.imageView7);


//
        location = (TextView) findViewById(R.id.location);
        location2 = (TextView) findViewById(R.id.location2);
        price = (TextView) findViewById(R.id.price);
        price2 = (TextView) findViewById(R.id.price2);
        price3 = (TextView) findViewById(R.id.price3);
        location1 = (TextView) findViewById(R.id.location1);
        location3 = (TextView) findViewById(R.id.location3);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuseumsNearby.this, PageReview.class);
                startActivity(intent);
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
//




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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.mainmenu)
        {
            Intent intent = new Intent(MuseumsNearby.this, MainMenu.class);
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
