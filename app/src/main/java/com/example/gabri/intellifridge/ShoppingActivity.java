package com.example.gabri.intellifridge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
;
import com.example.gabri.intellifridge.engine.Fridge;
import com.example.gabri.intellifridge.engine.Merchant;
import com.example.gabri.intellifridge.engine.UserDataSingleton;
import com.example.gabri.intellifridge.engine.UserPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talksick on 3/24/2018.
 */

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Button btn = findViewById(R.id.btn_shopping);
        ListView list_view = findViewById(R.id.lv_shopping);

        final List<String> list = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);;
        list_view.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Merchant.Choice> tmp = Merchant.giveSuggestions(UserDataSingleton.getInstance().getuPref(),
                        UserDataSingleton.getInstance().getFridge(), getIntent().getIntExtra("days", 5));
                int idx = 0;
                for (Merchant.Choice choice : tmp) {
                    if (idx > 10) break;
                    ++idx;
                    list.add(choice.item.type.name + "|" + choice.item.getQuantity() + "|" + choice.importance + "|" + choice.availability);
                }
                UserDataSingleton.getInstance().getFridge().addItem(tmp.get(0).item);
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
