package com.example.gabri.intellifridge;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

/**
 * Created by Talksick on 3/24/2018.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button btn1 = findViewById(R.id.btn_menu1);
        btn1.setOnClickListener(this);
        Button btn2 = findViewById(R.id.btn_menu2);
        btn2.setOnClickListener(this);
        Button btn3 = findViewById(R.id.btn_menu3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_menu1:
                showDaysPicker();
                break;
            case R.id.btn_menu2:
                break;
            case R.id.btn_menu3:

                break;
        }
    }

    public void showDaysPicker()
    {
        final Dialog d = new Dialog(MenuActivity.this);
        d.setTitle("Pick days");
        d.setContentView(R.layout.dialog_days);
        Button b1 = (Button) d.findViewById(R.id.btn_days1);
        Button b2 = (Button) d.findViewById(R.id.btn_days2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.picker_days);
        np.setMaxValue(30);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShoppingActivity.class).putExtra("days", np.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }
}