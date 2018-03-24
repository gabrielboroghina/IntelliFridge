package com.example.gabri.intellifridge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gabri.intellifridge.engine.ItemType;
import com.example.gabri.intellifridge.engine.UserDataSingleton;
import com.example.gabri.intellifridge.util.DBRequest;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private boolean registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load food data
        DBRequest req = new DBRequest();
        try {
            String result = req.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // print user preferences
        for (Map.Entry<String, ItemType> entry : UserDataSingleton.getInstance().getuPref().known_types.entrySet())
            System.out.println(entry.getKey() + entry.getValue());

        /*Button btn = findViewById(R.id.btn_login3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(login);
            }
        });

        if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }*/
    }
}
