package com.simrankaurbal.historian_wil_2020;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class Pagereviewlistitem extends AppCompatActivity {

public TextView aa_title;
public TextView aa_rating;
public TextView aa_type;
public TextView aa_data;
public ImageView aa_thumbnail;
public AppBarLayout appbarlayout;
public CollapsingToolbarLayout collapsinglayout;
public Button booktickets_button;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagereview_listitem);

        // hide the default action bar
        getSupportActionBar().hide();

        //Recieve Data
        String name = getIntent().getExtras().getString("Museum_Name");
        String description = getIntent().getExtras().getString("Museum_Rating");
        String type = getIntent().getExtras().getString("Museum_Type");
        String img = getIntent().getExtras().getString("Museum_Image");
        String museumdata = getIntent().getExtras().getString("Museum_Data");


        aa_title = (TextView) findViewById(R.id.aa_title);
        aa_rating = (TextView) findViewById(R.id.aa_rating);
        aa_type = (TextView) findViewById(R.id.aa_type);
        aa_thumbnail = (ImageView) findViewById(R.id.aa_thumbnail);
        aa_data = (TextView) findViewById(R.id.aa_data);
        booktickets_button = (Button) findViewById(R.id.booktickets_button);

        appbarlayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        collapsinglayout = (CollapsingToolbarLayout) findViewById(R.id.collapsinglayout);
        collapsinglayout.setTitleEnabled(true );

        // setting Values to each view

        aa_title.setText(name);
        aa_rating.setText(description);
        aa_type.setText(type);
//        collapsinglayout.setText(name);

//        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        // set Image using glide
//        Glide.with(this).load(img).apply(requestOptions).into(aa_thumbnail);
        Glide.with(this).load(img).into(aa_thumbnail);


        // book ticket working
        booktickets_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pagereviewlistitem.this,MainActivity.class);
                startActivity(i);

            }
        });


    }
}
