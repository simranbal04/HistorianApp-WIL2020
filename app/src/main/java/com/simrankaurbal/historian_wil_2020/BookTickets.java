package com.simrankaurbal.historian_wil_2020;

//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class BookTickets extends AppCompatActivity implements SlyCalendarDialog.Callback, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    DataBaseHelper mydatabase;
    public LinearLayout linearLayout;
    public ImageView imageView;
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    TextView textView3;
    TextView text;
    TextView museumname;
    ImageView image;
    Button Confirmbtn;
    public Spinner spinner;
    //defines variables for time picker
    TimePickerDialog timePickerDialog ;
    int Year, Month, Day, Hour, Minute;
    Calendar calendar ;

    public void setIs24HourView(Boolean is24HourView) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_tickets);
        mydatabase = new DataBaseHelper(this);
        Calendar now = Calendar.getInstance();
        textView = (TextView)findViewById(R.id.textView);
        textView1 = (TextView)findViewById(R.id.time);

        textView2 = (TextView) findViewById(R.id.date);
        textView3 = (TextView) findViewById(R.id.textView3);
        museumname = (TextView) findViewById(R.id.musuemname);
        image = (ImageView) findViewById(R.id.image);
        Confirmbtn = (Button) findViewById(R.id.Confirmbtn);
       // Confirmbtn = (Button)findViewById(R.id.Confirmbtn);

        text = (TextView) findViewById(R.id.text);
        spinner = (Spinner) findViewById(R.id.spin);
        // Recieve Name
        String name = getIntent().getExtras().getString("Museum_Name");
        final String img = getIntent().getExtras().getString("Museum_Image");

        // setting Values to each view
        museumname.setText(name);
        Glide.with(this).load(img).into(image);
        //shows calender to pick any date
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BookTickets.this, "Selected", Toast.LENGTH_SHORT).show();
                new SlyCalendarDialog()
                        .setSingle(true)
                        .setCallback(BookTickets.this)
                        .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
            }
        });
        // to call onClicklistener for timepicker
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = TimePickerDialog.newInstance(BookTickets.this,Hour, Minute,false);
                timePickerDialog.setThemeDark(false);
                timePickerDialog.setTitle("TimePicker");
                timePickerDialog.show(getSupportFragmentManager(),"timePickerDialog");
            }
        });
        //method to confrim ticket details and store in database
        Confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydatabase.insertTicketDetail(museumname.getText().toString(),spinner.getTextAlignment(),textView1.getText().toString(),textView2.getText().toString());
                Toast.makeText(BookTickets.this,"Your Ticket Confirmed",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onCancelled() {

    }



    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            //  SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
            textView2.setText(dateFormat.format((firstDate.getTime())));
            //textView1.setText(dateFormat.format((sdf.getTimeZone())));
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = hourOfDay+"h"+minute+"m"+second;
        Toast.makeText(BookTickets.this, time, Toast.LENGTH_LONG).show();

        textView1.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
