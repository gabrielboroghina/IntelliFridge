package com.example.gabri.intellifridge;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.io.ByteArrayOutputStream;

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