package com.example.gabri.intellifridge.engine;

import android.os.AsyncTask;

import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.*;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Cook {

    private static final String EDAMAM_URL = "https://api.edamam.com/search";
    private  static final String EDAMAM_APP_ID = "d34031d2";
    private static final String EDAMAM_APP_KEY = "d5c068de7a5406391fbdab34c3f2b369";

    private List<Item> items;
    public Cook(List<Item> items) {
        this.items = items;
    }

    public List<Recipe> chooseRecipes() {
        return Recipe.parseRecipes(requestFromAPI(items, 3, 100, 1000));
    }

    private String requestFromAPI(List<Item> items, int n, int min_cal, int max_cal) {
        StringBuilder sb = new StringBuilder();
        sb.append(EDAMAM_URL);
        sb.append("?q=");
        for (Item item : items) {
            sb.append(item.getName());
            sb.append('+');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("&app_id=");
        sb.append(EDAMAM_APP_ID);
        sb.append("&app_key=");
        sb.append(EDAMAM_APP_KEY);
        sb.append("&from=0&to=");
        sb.append(n);
        sb.append("&calories=");
        sb.append(min_cal);
        sb.append('-');
        sb.append(max_cal);

        try {
            return new HttpGetRequest().execute(sb.toString()).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class HttpGetRequest extends AsyncTask<String, Void, String> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected String doInBackground(String... params){
        String stringUrl = params[0];
        String result;
        String inputLine;
        try {
            URL myUrl = new URL(stringUrl);
            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
