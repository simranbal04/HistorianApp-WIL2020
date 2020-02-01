package com.simrankaurbal.historian_wil_2020;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPage extends Fragment {


    public MenuPage() {
        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_menu_page,container,false);

        ImageButton profile = (ImageButton) view.findViewById(R.id.profile1);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),ProfilePage.class);
                startActivity(in);
            }
        });

        ImageButton payment = (ImageButton) view.findViewById(R.id.visa);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),PaymentPage.class);
                startActivity(in);
            }
        });

        ImageButton museumnearby = (ImageButton) view.findViewById(R.id.hotels);
        museumnearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MuseumsNearby.class);
                startActivity(in);
            }
        });




        return view;

    }



}
