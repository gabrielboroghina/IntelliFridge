package com.example.gabri.intellifridge;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");


        // insert into database
        try {
            String result = new InsertDB(this).execute(email, password).get();

            if (result.contains("Error")) // successful insert
                Toast.makeText(this, "Error at register", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this, "Successful registration", Toast.LENGTH_LONG).show();
                this.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expandableButton1(View view) {
        expandableLayout1 = findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse
    }
}

class InsertDB extends AsyncTask<String, Void, String> {
    private Context context;

    public InsertDB(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        String email = arg0[0];
        String password = arg0[1];

        try {
            String link = "https://dp-experts.000webhostapp.com/sc.php";
            String data = URLEncoder.encode("email", "UTF-8") + "=" +
                    URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            conn.connect();

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error while inserting into database";
    }

    @Override
    protected void onPostExecute(String result) {
    }
}