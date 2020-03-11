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

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class PaymentPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SlyCalendarDialog.Callback {

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

    public Button Savebutton;

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

//        drawerLayout = (DrawerLayout) findViewById(R.id.mainLayout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
//        navigationView.setNavigationItemSelectedListener(this);

        welcometextview = (TextView) findViewById(R.id.welcometextview);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextcardnumber = (EditText) findViewById(R.id.editTextcardnumber);
        editTextcvv = (EditText) findViewById(R.id.editTextcvv);
        editTextexpirydate = (EditText) findViewById(R.id.editTextexpirydate);
        calimageView = (ImageButton) findViewById(R.id.calimageView);
        Savebutton = (Button) findViewById(R.id.Savebutton);


        myCalendar = (Calendar) Calendar.getInstance();
        startDate = (Calendar) Calendar.getInstance();

        mydatabase1 = new DataBaseHelper(this);


        editTextexpirydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentPage.this, "ONCLICK", Toast.LENGTH_SHORT).show();
                new SlyCalendarDialog()
                        .setSingle(true)
                        .setCallback(PaymentPage.this)
                        .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
            }


        });

        AddPaymentDetail();

        CheckValidData();

    }

    private void CheckValidData() {

        awesomeValidation.addValidation(PaymentPage.this, R.id.editTextName, "[a-zA-Z\\s]+", R.string.NameOnCard_Error);
        awesomeValidation.addValidation(PaymentPage.this, R.id.editTextcardnumber, RegexTemplate.NOT_EMPTY, R.string.CardNumber_Error);
        awesomeValidation.addValidation(PaymentPage.this, R.id.editTextcvv, RegexTemplate.NOT_EMPTY, R.string.CVV_Error);
        awesomeValidation.addValidation(PaymentPage.this, R.id.editTextexpirydate, RegexTemplate.NOT_EMPTY, R.string.ExpiryDate_Error);
    }

    public void AddPaymentDetail() {
        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    boolean isAdded = mydatabase1.updatePaymentDetail(editTextName.getText().toString(), editTextcardnumber.getText().toString(), editTextcvv.getText().toString(), editTextexpirydate.getText().toString());

                    Log.d("myTag", "This is my payment message");

                    if (isAdded = true)
                        Toast.makeText(PaymentPage.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(PaymentPage.this, "Data Not Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.mainmenu) {
            Intent intent = new Intent(PaymentPage.this, MainMenu.class);
            startActivity(intent);

            Toast.makeText(this, "This is Main Menu Page", Toast.LENGTH_SHORT).show();

        }
        return false;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.action_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy");
            editTextexpirydate.setText(dateFormat.format((firstDate.getTime())));

        }
    }
}