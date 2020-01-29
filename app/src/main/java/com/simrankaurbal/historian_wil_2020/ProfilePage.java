package com.simrankaurbal.historian_wil_2020;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfilePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public TextView welcometextview;
    public TextView profilepicturetextview;
    public ImageView imageViewcamera;

    public TextView firstnametextview;
    public EditText editTextfirstname;

    public TextView lastnametextview;
    public EditText editTextlastname;

    public TextView contacttextview;
    public EditText editTextcontact;

    public TextView emailidtextview;
    public EditText editTextemailid;

    public TextView dobtextview;
    public EditText dobedittext;
    public ImageButton imageViewcalendar;
    public Button Savebutton;


    //Calendar

    Calendar myCalendar;
    Calendar startDate;
    Calendar endDate;
    Calendar startDateC;

    //datecheck type
    int start_or_end;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA);

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        welcometextview = (TextView) findViewById(R.id.welcometextview);
        profilepicturetextview = (TextView) findViewById(R.id.profilepicturetextview);
        imageViewcamera = (ImageView) findViewById(R.id.imageViewcamera);

        firstnametextview = (TextView) findViewById(R.id.firstnametextview);
        editTextfirstname = (EditText) findViewById(R.id.editTextfirstname);

        lastnametextview = (TextView) findViewById(R.id.lastnametextview);
        editTextlastname = (EditText) findViewById(R.id.editTextlastname);

        contacttextview = (TextView) findViewById(R.id.contacttextview);
        editTextcontact = (EditText) findViewById(R.id.editTextcontact);

        emailidtextview = (TextView) findViewById(R.id.emailidtextview);
        editTextemailid = (EditText) findViewById(R.id.editTextemailid);

        dobtextview = (TextView) findViewById(R.id.dobtextview);
        dobedittext = (EditText) findViewById(R.id.dobedittext);
        imageViewcalendar = (ImageButton) findViewById(R.id.imageViewcalendar);
        Savebutton = (Button) findViewById(R.id.Savebutton);



        myCalendar = (Calendar) Calendar.getInstance();
        startDate = (Calendar) Calendar.getInstance();



        drawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);



        imageViewcalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        if (start_or_end == 1)
                        {
                            startDate.set(Calendar.YEAR, year);
                            startDate.set(Calendar.MONTH, month);
                            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            dobedittext.setText(sdf.format(myCalendar.getTime()));
                        }
                        else
                        {

                        }

                    }
                };


                dobedittext.setOnFocusChangeListener(new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus)
                    {
                        if (hasFocus)
                        {
                            start_or_end = 1;
                            DatePickerDialog dialog = new DatePickerDialog(ProfilePage.this ,date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                            dialog.show();

                        } else
                        {

                        }
                    }
                });


            }
        });








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
                Intent intent = new Intent(ProfilePage.this, MainMenu.class);
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