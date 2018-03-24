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
import com.example.gabri.intellifridge.engine.Merchant.Choice;
import com.example.gabri.intellifridge.engine.Merchant;
import com.example.gabri.intellifridge.engine.UserPreferences;


/**
 * Created by Talksick on 3/24/2018.
 */

public class TestActivity extends AppCompatActivity {

    Fridge fridge;

    private static UserPreferences doStuff() {
        UserPreferences preference = new UserPreferences();
        preference.addType("salmon", "fish", 5, 0.0, 0.2, 0.1);
        preference.addType("makrel", "fish", 4, 0.0, 0.3, 0.05);
        preference.addType("bread", "fish", 1, 0.4, 0.2, 0.2);
        preference.addType("wtf", "fish", 4, 0.2, 0.0, 0.4);

        preference.addGrade("salmon", 3);
        preference.addGrade("makrel", 0);
        preference.addGrade("bread", 1);
        preference.addGrade("wtf", 2);

        return preference;
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
        final Fridge fridge = new Fridge();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPreferences pref = doStuff();
                List<Choice> tmp = Merchant.giveSuggestions(pref, fridge, 5);
                for (Choice choice : tmp) {
                    list.add(choice.item.type.name + "|" + choice.item.getQuantity() + "|" + choice.importance + "|" + choice.availability);
                }
                fridge.addItem(tmp.get(0).item);
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