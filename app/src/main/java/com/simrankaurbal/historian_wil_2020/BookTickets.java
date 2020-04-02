package com.simrankaurbal.historian_wil_2020;

//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

public class BookTickets extends AppCompatActivity implements SlyCalendarDialog.Callback, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    TextView textView;
    TextView textView3;
    TextView museumname;
    ImageView image;
    TextView date;
    TextView text;
    Spinner spin;
    Button confirm;
    TextView selectdate;
    TextView selecttime;
    TextView time;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA);

    Calendar myCalendar;
    Calendar startDate;

    TimePickerDialog timePickerDialog ;
    int Year, Month, Day, Hour, Minute;

    DataBaseHelper mydatabase;

    public void setIs24HourView(Boolean is24HourView) {

    }

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
        selectdate = (TextView) findViewById(R.id.selectdate);
        selecttime = (TextView) findViewById(R.id.selecttime);
        time = (TextView) findViewById(R.id.time);

        myCalendar = (Calendar) Calendar.getInstance();
        startDate = (Calendar) Calendar.getInstance();

        mydatabase = new DataBaseHelper(this);


        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydatabase.insertTicketDetail(museumname.getText().toString(),spin.getTextAlignment(),selecttime.getText().toString(),selectdate.getText().toString());
                Toast.makeText(BookTickets.this,"Your Ticket Confirmed",Toast.LENGTH_SHORT).show();
            }
        });

        // Recieve Name
        String name = getIntent().getExtras().getString("Museum_Name");
        final String img = getIntent().getExtras().getString("Museum_Image");

        // setting Values to each view
        museumname.setText(name);
        Glide.with(this).load(img).into(image);

        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookTickets.this, "Selected", Toast.LENGTH_SHORT).show();
                new SlyCalendarDialog()
                        .setSingle(true)
                        .setCallback( BookTickets.this)
                        .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
            }
        });
        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = TimePickerDialog.newInstance(BookTickets.this,Hour, Minute,false);
                timePickerDialog.setThemeDark(false);
                timePickerDialog.setTitle("TimePicker");
                timePickerDialog.show(getSupportFragmentManager(),"timePickerDialog");
            }
        });

    }





    public void onCancelled() {

    }

    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            selectdate.setText(dateFormat.format((firstDate.getTime())));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time1 = hourOfDay+"h"+minute+"m"+second;
        Toast.makeText(BookTickets.this, time1, Toast.LENGTH_LONG).show();

        selecttime.setText(time1);
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//    }

//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}

