package com.example.gabri.intellifridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Talksick on 3/24/2018.
 */

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Button btn = findViewById(R.id.babb);
        ListView list = findViewById(R.id.list_view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // list.add
            }
        });
        /*
        if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        } */
    }
}