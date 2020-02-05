package com.simrankaurbal.historian_wil_2020;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


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
            public void onClick(View v)
            {

                fetchData process = new fetchData();
                process.execute();
//                 new DownloadTask();

                Intent in = new Intent(getActivity(), MuseumsNearby.class);
                startActivity(in);
            }
        });

        return view;


    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        DownloadTask task = new DownloadTask();
//      task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=43.774034,-79.335683&radius=1500&type=timhortons&keyword=cafe&key=AIzaSyAFRWscDI6hkVARcBPu5bvCgWCyaxHx8fI");
//        //task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");
//
//
//    }
//
//    public class DownloadTask extends AsyncTask<String, Void, String>
//    {
//
//        @Override
//        protected String doInBackground(String... urls) {
//
//            String result = "";
//            URL url;
//            HttpURLConnection urlConnection = null;
//
//            try {
//                url = new URL(urls[0]);
//
//                urlConnection = (HttpURLConnection) url.openConnection();
//
//                InputStream in = urlConnection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in);
//                int data = reader.read();
//                while (data != -1)
//                {
//                    char current = (char) data;
////                    result += current;
//                    result += current;
//                    data = reader.read();
//                }
//
//                return result;
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            try {
//
//                JSONObject jsonObject = new JSONObject(result);
//                String museum =  jsonObject.getString("");
//
//                Log.i("museum Content", museum);
//
////                JSONArray jsonArray = new JSONArray(weatherinfo);
////
////                for (int i = 0; i < jsonArray.length(); i++)
////                {
////                    JSONObject jsonpart = jsonArray.getJSONObject(i);
////
//////                    Log.i("main",jsonpart.getString("main"));
//////                    Log.i("description",jsonpart.getString("description"));
//////                    Log.i("icon",jsonpart.getString("icon"));
////                }
//
//            }
//            catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//
//
//        }
//    }
//


}
