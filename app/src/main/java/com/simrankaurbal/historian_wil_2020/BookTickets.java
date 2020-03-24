package com.simrankaurbal.historian_wil_2020;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class BookTickets extends AppCompatActivity implements SlyCalendarDialog.Callback {

    TextView textView;
    TextView textView3;
    TextView museumname;
    ImageView image;
    TextView date;
    TextView text;
    Spinner spin;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA);

    Calendar myCalendar;
    Calendar startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_tickets);

        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);
        museumname = (TextView) findViewById(R.id.musuemname);
        image = (ImageView) findViewById(R.id.image);
        date = (TextView) findViewById(R.id.date);
        text = (TextView) findViewById(R.id.text);
        spin = (Spinner) findViewById(R.id.spin);

        myCalendar = (Calendar) Calendar.getInstance();
        startDate = (Calendar) Calendar.getInstance();

        // Recieve Name
        String name = getIntent().getExtras().getString("Museum_Name");
        final String img = getIntent().getExtras().getString("Museum_Image");

        // setting Values to each view
        museumname.setText(name);
        Glide.with(this).load(img).into(image);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookTickets.this, "Selected", Toast.LENGTH_SHORT).show();
                new SlyCalendarDialog()
                        .setSingle(true)
                        .setCallback( BookTickets.this)
                        .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
            }
        });


    }

    public void onCancelled() {

    }


    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            date.setText(dateFormat.format((firstDate.getTime())));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

