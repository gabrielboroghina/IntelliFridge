package com.example.gabri.intellifridge;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
;
import com.example.gabri.intellifridge.engine.Fridge;
import com.example.gabri.intellifridge.engine.Merchant;
import com.example.gabri.intellifridge.engine.UserDataSingleton;
import com.example.gabri.intellifridge.engine.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talksick on 3/24/2018.
 */

class CustomAdapter extends ArrayAdapter<Merchant.Choice> {
    private final Activity context;
    private final List<Merchant.Choice> items;

    public CustomAdapter(Activity context, List<Merchant.Choice> items) {
        super(context, R.layout.list_shopping, items);
        // TODO Auto-generated constructor stub

        this.context=  context;
        this.items = items;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_shopping, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item_name);
        TextView extratxt = (TextView) rowView.findViewById(R.id.item_quantity);
        ImageView image1 = (ImageView) rowView.findViewById(R.id.icon1);
        ImageView image2 = (ImageView) rowView.findViewById(R.id.icon2);

        txtTitle.setText(items.get(position).item.type.name);
        extratxt.setText("" + items.get(position).item.getQuantity() + "g");
        image1.setImageDrawable(context.getResources().getDrawable(R.drawable.square));
        image2.setImageDrawable(context.getResources().getDrawable(R.drawable.square));
        double a = items.get(position).importance;
        double b = items.get(position).availability;
        image1.setColorFilter(Color.rgb((int)((1 - a) * 255),(int)(a * 255),0));
        image2.setColorFilter(Color.rgb((int)((1 - b) * 255),(int)(b * 255),0));
        return rowView;
    };
}

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        final int n_days = getIntent().getIntExtra("days", 5);

        Button btn = findViewById(R.id.btn_shopping);
        ListView list_view = findViewById(R.id.lv_shopping);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final List<Merchant.Choice> tmp = Merchant.giveSuggestions(UserDataSingleton.getInstance().getuPref(),
                UserDataSingleton.getInstance().getFridge(), n_days);
        final CustomAdapter adapter = new CustomAdapter(this, tmp);
        adapter.notifyDataSetChanged();
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), tmp.get(position).item.text + " added to the fridge!", Toast.LENGTH_LONG).show();
                UserDataSingleton.getInstance().getFridge().addItem(tmp.get(position).item);
                adapter.clear();
                adapter.addAll(Merchant.giveSuggestions(UserDataSingleton.getInstance().getuPref(),
                                    UserDataSingleton.getInstance().getFridge(), n_days));
                adapter.notifyDataSetChanged();
                parent.invalidate();
            }
        });

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        final int n_days = getIntent().getIntExtra("days", 5);

        Button btn = findViewById(R.id.btn_shopping);
        ListView list_view = findViewById(R.id.lv_shopping);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        List<String> list = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.list_shopping, R.id.item_name, list);

        final List<Merchant.Choice> tmp = Merchant.giveSuggestions(UserDataSingleton.getInstance().getuPref(),
                UserDataSingleton.getInstance().getFridge(), n_days);
        int idx = 0;
        for (Merchant.Choice choice : tmp) {
            if (idx > 10) break;
            ++idx;
            list.add(choice.item.type.name + " " + choice.item.getQuantity() + "g " + (int)(choice.importance * 100) +
                    "% " + choice.availability * 100 + "%");
        }
        adapter.notifyDataSetChanged();
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), tmp.get(position).item.text + " added to the fridge!", Toast.LENGTH_LONG).show();
                UserDataSingleton.getInstance().getFridge().addItem(tmp.get(position).item);
                List<String> list = new ArrayList<String>();
                tmp.clear();
                tmp.addAll(Merchant.giveSuggestions(UserDataSingleton.getInstance().getuPref(),
                        UserDataSingleton.getInstance().getFridge(), n_days));
                int idx = 0;
                for (Merchant.Choice choice : tmp) {
                    if (idx > 10) break;
                    ++idx;
                    list.add(choice.item.type.name + " " + choice.item.getQuantity() + "g " + (int)(choice.importance * 100) +
                            "% " + choice.availability * 100 + "%");
                }
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
                parent.invalidate();
            }
        }); */
        /*
        if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        } */
    }
}
