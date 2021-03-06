package com.example.gabri.intellifridge;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.gabri.intellifridge.engine.Item;
import com.example.gabri.intellifridge.engine.UserDataSingleton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 13;

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
        ((View)findViewById(R.id.lv_shopping).getParent()).setAlpha(0.0f);
    }
    @Override
    protected void onRestart() {
// TODO Auto-generated method stub
        super.onRestart();

        ListView list_view = findViewById(R.id.lv_shopping);

        final List<Item> list = new ArrayList<>();
        for (Item item : UserDataSingleton.getInstance().getFridge().getItems()) {
            list.add(item);
        }
        List<Item> wtf = new ArrayList<>();
        int idx = 0;
        for (Item item : UserDataSingleton.getInstance().getFridge().getItems()) {
            if (idx > 1 || item.type.perishability > 7) break;
            ++idx;
            wtf.add(item);
        };

        final CustomAdapter2 adapter = new CustomAdapter2(this, wtf);
        adapter.notifyDataSetChanged();
        list_view.setAdapter(adapter);
        if (idx == 0) ((View)list_view.getParent()).setAlpha(0.0f);
        else ((View)list_view.getParent()).setAlpha(0.7f);
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position).text + " eaten!", Toast.LENGTH_LONG).show();
                UserDataSingleton.getInstance().getFridge().getItems().remove(list.get(position));

                List<Item> wtf = new ArrayList<>();
                int idx = 0;
                for (Item item : UserDataSingleton.getInstance().getFridge().getItems()) {
                    if (idx > 1 || item.type.perishability > 7) break;
                    ++idx;
                    wtf.add(item);
                }
                if (idx == 0) ((View)parent.getParent()).setAlpha(0.0f);
                else ((View)parent.getParent()).setAlpha(0.7f);

                adapter.clear();
                adapter.addAll(wtf);
                adapter.notifyDataSetChanged();
                parent.invalidate();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");

            //Convert to byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // call share location activity
            Intent shareFoodAct = new Intent(getApplicationContext(), ShareFoodActivity.class);
            shareFoodAct.putExtra("image", byteArray);
            startActivity(shareFoodAct);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_menu1:
                showDaysPicker();
                break;
            case R.id.btn_menu2:
                startActivity(new Intent(getApplicationContext(), EatActivity.class));
                break;
            case R.id.btn_menu3:
                dispatchTakePictureIntent();
                break;
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void showDaysPicker() {
        final Dialog d = new Dialog(MenuActivity.this);
        d.setTitle("Pick days");
        d.setContentView(R.layout.dialog_days);
        Button b1 = (Button) d.findViewById(R.id.btn_days1);
        Button b2 = (Button) d.findViewById(R.id.btn_days2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.picker_days);
        np.setMaxValue(30);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShoppingActivity.class).putExtra("days", np.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }
}