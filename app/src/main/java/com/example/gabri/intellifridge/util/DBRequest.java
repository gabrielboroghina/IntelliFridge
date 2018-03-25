package com.example.gabri.intellifridge.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gabri.intellifridge.engine.UserDataSingleton;
import com.example.gabri.intellifridge.engine.UserPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DBRequest extends AsyncTask<String, Void, String> {
    String script;

    public DBRequest(String script) {
        this.script = script;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {

        try {
            // get food/grades data from database
            String link = "https://dp-experts.000webhostapp.com/" + script;

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            UserPreferences uPref = UserDataSingleton.getInstance().getuPref();
            // Read Server Response

            String response = reader.readLine();
            String[] lines = response.split("<br>");

            for (String line : lines) {
                String[] cols = line.split(" ");

                // insert item into user preferences
                if (script.equals("get_grades.php"))
                    uPref.addGrade(cols[0], Integer.parseInt(cols[1]));
                else
                    uPref.addType(cols[0], cols[5], Double.parseDouble(cols[1]), Double.parseDouble(cols[2]),
                            Double.parseDouble(cols[3]), Double.parseDouble(cols[4]));
            }
            Log.i("OMG", "" + uPref.getTypes().size());

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
