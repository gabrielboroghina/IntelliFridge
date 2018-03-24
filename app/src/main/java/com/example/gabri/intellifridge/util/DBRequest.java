package com.example.gabri.intellifridge.util;

import android.os.AsyncTask;

import com.example.gabri.intellifridge.engine.ItemType;
import com.example.gabri.intellifridge.engine.UserDataSingleton;
import com.example.gabri.intellifridge.engine.UserPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class DBRequest extends AsyncTask<String, Void, String> {

    public DBRequest() {
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {

        try {
            // get food data from database
            String link = "https://dp-experts.000webhostapp.com/get_food.php";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            conn.connect();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            UserPreferences uPref = UserDataSingleton.getInstance().getuPref();
            // Read Server Response

            String response = reader.readLine();
            String[] lines = response.split("<br>");

            for (String line : lines) {
                String[] cols = line.split(" ");

                // insert item into user preferences
                uPref.addType(cols[0], cols[5], Double.parseDouble(cols[1]), Double.parseDouble(cols[2]),
                        Double.parseDouble(cols[3]), Double.parseDouble(cols[4]));
            }

            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";
    }

    @Override
    protected void onPostExecute(String result) {
    }

}
