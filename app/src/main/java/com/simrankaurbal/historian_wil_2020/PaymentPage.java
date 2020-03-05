package com.simrankaurbal.historian_wil_2020;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PaymentPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    DataBaseHelper mydatabase1;



    public TextView welcometextview;
    public TextView nametextview;
    public EditText editTextName;

    public TextView cardnumbertextview;
    public EditText editTextcardnumber;

    public TextView cvvtextview;
    public EditText editTextcvv;

    public TextView expirydatetextview;
    public EditText editTextexpirydate;
    public ImageButton calimageView;

    public Button Savebuttonpayment;

    AwesomeValidation awesomeValidation;

    //Calendar

    Calendar myCalendar;
    Calendar startDate;
    Calendar endDate;
    Calendar startDateC;

    //datecheck type
    int start_or_end;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA);

    protected void onCreate(Bundle savedInstanceState) {

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        drawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        welcometextview = (TextView) findViewById(R.id.welcometextview);
        nametextview = (TextView) findViewById(R.id.nametextview);
        editTextName = (EditText) findViewById(R.id.editTextName);

        cardnumbertextview = (TextView) findViewById(R.id.cardnumbertextview);
        editTextcardnumber = (EditText) findViewById(R.id.editTextcardnumber);

        cvvtextview = (TextView) findViewById(R.id.cvvtextview);
        editTextcvv = (EditText) findViewById(R.id.editTextcvv);

        expirydatetextview = (TextView) findViewById(R.id.expirydatetextview);
        editTextexpirydate = (EditText) findViewById(R.id.editTextexpirydate);
        calimageView = (ImageButton) findViewById(R.id.calimageView);


        Savebuttonpayment =  (Button) findViewById(R.id.Savebuttonpayment);


        myCalendar = (Calendar) Calendar.getInstance();
        startDate = (Calendar) Calendar.getInstance();

        mydatabase1=new DataBaseHelper(this);


        calimageView.setOnClickListener(new View.OnClickListener() {
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
                            editTextexpirydate.setText(sdf.format(myCalendar.getTime()));
                        }
                        else
                        {

                        }

                    }
                };



                editTextexpirydate.setOnFocusChangeListener(new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus)
                    {
                        if (hasFocus)
                        {
                            start_or_end = 1;
                            DatePickerDialog dialog = new DatePickerDialog(PaymentPage.this ,date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                            dialog.show();

                        } else
                        {

                        }
                    }
                });


            }
        });


        AddPaymentDetail();

        CheckValidData();



    }

    private void CheckValidData()
    {

        awesomeValidation.addValidation(PaymentPage.this,R.id.editTextName, "[a-zA-Z\\s]+",R.string.NameOnCard_Error);
        awesomeValidation.addValidation(PaymentPage.this,R.id.editTextcardnumber, RegexTemplate.NOT_EMPTY,R.string.CardNumber_Error);
        awesomeValidation.addValidation(PaymentPage.this,R.id.editTextcvv,RegexTemplate.NOT_EMPTY,R.string.CVV_Error);
        awesomeValidation.addValidation(PaymentPage.this,R.id.editTextexpirydate, RegexTemplate.NOT_EMPTY,R.string.ExpiryDate_Error);
    }

    public void AddPaymentDetail()
    {
        Savebuttonpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate())
                {
                boolean isAdded = mydatabase1.insertDetail(editTextName.getText().toString(),editTextcardnumber.getText().toString(),editTextcvv.getText().toString(),editTextexpirydate.getText().toString());

                Log.d("myTag", "This is my payment message");

                if(isAdded = true)
                    Toast.makeText(PaymentPage.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PaymentPage.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

            }}
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
    public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
        int id = menuItem.getItemId();
        if (id == R.id.mainmenu)
        {
            Intent intent = new Intent(PaymentPage.this, MainMenu.class);
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