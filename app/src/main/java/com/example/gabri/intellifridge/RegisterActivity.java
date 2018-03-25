package com.example.gabri.intellifridge;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_save;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    HashMap<String, Integer> rating;

    @Override
    public void onClick(View view) {
        //if (view.getId() == R.id.rating)
            Toast.makeText(RegisterActivity.this, "Successful registration", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final String email = getIntent().getStringExtra("email");
        final String password = getIntent().getStringExtra("password");

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into database
                try {
                    String result = new InsertDB(RegisterActivity.this).execute(email, password).get();

                    if (result.contains("Error")) // successful insert
                        Toast.makeText(RegisterActivity.this, "Error at register", Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(RegisterActivity.this, "Successful registration", Toast.LENGTH_LONG).show();
                        RegisterActivity.this.finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
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