package com.example.gabri.intellifridge;

import java.util.List;
import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gabri.intellifridge.engine.Fridge;
import com.example.gabri.intellifridge.engine.UserPreferences;


/**
 * Created by Talksick on 3/24/2018.
 */

public class TestActivity extends AppCompatActivity {

    Fridge fridge;

    private static UserPreferences doStuff() {
        UserPreferences preference = new UserPreferences();
        preference.addType("salmon", "fish", 5, 0, 20, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Button btn = findViewById(R.id.babb);
        ListView list_view = findViewById(R.id.list_view);
        final List<String> list = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);;
        list_view.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("WTF");
                list.add("OMG");
                adapter.notifyDataSetChanged();
            }
        });
        /*
        if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        } */
    }
}