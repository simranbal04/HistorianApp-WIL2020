package com.simrankaurbal.historian_wil_2020;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    String ratingParsed = "";
    String iconParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            URL url = new URL ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=43.774034,-79.335683&radius=1000&type=museum&keyword=museum&key=AIzaSyAFRWscDI6hkVARcBPu5bvCgWCyaxHx8fI");


            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null)
            {
                line = bufferedReader.readLine();
                data = data + line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        try {
            JSONObject jsonObject = new JSONObject(data);
            String data = jsonObject.getString("results");


            Log.i("data",data);

            JSONArray jsonArray = new JSONArray(data);

            for (int i =0; i< jsonArray.length(); i++){
                JSONObject jsonpart = jsonArray.getJSONObject(i);

                singleParsed = "name" + jsonpart.get("name")  ;
                ratingParsed = "rating" + jsonpart.get("rating");
                iconParsed = "icon" + jsonpart.get("icon");

//                dataParsed = dataParsed + singleParsed;
//                Log.i("geometry",jsonpart.getString("geometry"));
//                Log.i("name",jsonpart.getString("name"));
////                Log.i("id",jsonpart.getString("id"));
//                Log.i("icon",jsonpart.getString("icon"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MuseumsNearby.location.setText(this.singleParsed);
        MuseumsNearby.price.setText(this.ratingParsed);
        MuseumsNearby.imageView5.setImageDrawable(Drawable.createFromPath("https://maps.gstatic.com/mapfiles/place_api/icons/museum-71.png"));
//        MuseumsNearby.imageView5.setText(this.iconParsed);


//        Demo.data.setText(this.data);
    }
}
