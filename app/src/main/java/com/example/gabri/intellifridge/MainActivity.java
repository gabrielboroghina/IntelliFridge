package com.example.gabri.intellifridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.*;
import com.example.gabri.intellifridge.engine.*;

public class MainActivity extends AppCompatActivity {

    private boolean registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Item chicken = new Item("chicken");
        Item potato = new Item("potato");
        List<Item> list = new ArrayList<Item>();
        list.add(chicken);
        list.add(potato);

        Cook cook = new Cook(list);
        List<Recipe> recipes = cook.chooseRecipes();
        if (recipes == null) {
            Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
        } else {
            StringBuilder sb = new StringBuilder();
            for (Recipe recipe : recipes) {
                sb.append(recipe.toString());
                sb.append(' ');
            }
            Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_LONG).show();
        }
        /*
        if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        } */

    }
}
