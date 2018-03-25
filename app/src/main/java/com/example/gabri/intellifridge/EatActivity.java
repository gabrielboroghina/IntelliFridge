package com.example.gabri.intellifridge;

import android.app.Activity;
import android.content.Intent;
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

import com.example.gabri.intellifridge.engine.Item;
import com.example.gabri.intellifridge.engine.Merchant;
import com.example.gabri.intellifridge.engine.UserDataSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talksick on 3/25/2018.
 */

class CustomAdapter2 extends ArrayAdapter<Item> {
    private final Activity context;
    private final List<Item> items;

    public CustomAdapter2(Activity context, List<Item> items) {
        super(context, R.layout.list_eat, items);
        // TODO Auto-generated constructor stub

        this.context=  context;
        this.items = items;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_eat, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item_name);
        TextView extratxt = (TextView) rowView.findViewById(R.id.item_quantity);
        ImageView image = (ImageView) rowView.findViewById(R.id.icon1);

        txtTitle.setText(items.get(position).type.name);
        extratxt.setText("" + items.get(position).getQuantity() + "g " + items.get(position).expirationDate);
        image.setImageDrawable(context.getResources().getDrawable(R.drawable.square));
        double a = items.get(position).type.perishability / 14;
        if (a > 1) a = 1.0;
        image.setColorFilter(Color.rgb((int)((1 - a) * 255),(int)(a * 255),0));
        return rowView;
    };
}

public class EatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);
        final int n_days = getIntent().getIntExtra("days", 5);

        Button btn = findViewById(R.id.btn_shopping);
        ListView list_view = findViewById(R.id.lv_shopping);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CookActivity.class));
            }
        });

        final List<Item> list = new ArrayList<>();
        for (Item item : UserDataSingleton.getInstance().getFridge().getItems()) {
            list.add(item);
        }
        final CustomAdapter2 adapter = new CustomAdapter2(this, list);
        adapter.notifyDataSetChanged();
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position).text + " eaten!", Toast.LENGTH_LONG).show();
                UserDataSingleton.getInstance().getFridge().getItems().remove(list.get(position));

                adapter.clear();
                adapter.addAll(UserDataSingleton.getInstance().getFridge().getItems());
                adapter.notifyDataSetChanged();
                parent.invalidate();
            }
        });
    }
}