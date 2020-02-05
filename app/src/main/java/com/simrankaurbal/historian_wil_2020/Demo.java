package com.simrankaurbal.historian_wil_2020;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Demo extends AppCompatActivity {

    public Button click;
    public static TextView data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        //click = (Button) findViewById(R.id.click);
        data = (TextView) findViewById(R.id.data);

//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                fetchData process = new fetchData();
//                process.execute();
//
//            }
//        });
    }
}
